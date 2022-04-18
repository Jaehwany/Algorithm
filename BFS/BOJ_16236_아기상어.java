package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1_16236_아기상어 {
	static int N;
	static int map[][];
	static int time;
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	
	static class CO{
		int x,y,size,distance;

		public CO(int y, int x, int size, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.distance = distance;
		}

	}
	
	static PriorityQueue<CO> pq = new PriorityQueue<CO>(new Comparator<CO>() {
		@Override
		public int compare(CO o1, CO o2) {
			if(o1.distance==o2.distance) {
				if(o1.y==o2.y) 
					return o1.x-o2.x;
				else
					return o1.y-o2.y;
			}
			else
				return o1.distance-o2.distance;
		}
	});
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		CO shark=null;
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark= new CO(i,j,2,0);
					map[i][j]=0;
				}
			}
		}
		
		int eat_count =0;
	
		while(true){
			//먹을 수 있는 물고기 찾기 (거리가 가장 가까운것, 가장위, 가장왼쪽)
			//bfs 탐색 후 우선순위 큐에 물고기 저장
			bfs(shark);
			
			//먹을 수 있는 물고기가 없으면 엄마 상어 호출
			if(pq.isEmpty()) {
				System.out.println(time);
				return;
			}
			
			//물고기 찾아가기
			CO cur = pq.poll();

			//찾는데 걸린시간
			time+=cur.distance;
			
			//물고기 먹기
			map[cur.y][cur.x]=0;
			if(++eat_count==shark.size) {
				shark.size++;
				eat_count=0;
			}
			
			//아기 상어 상태 최신화
			shark=new CO(cur.y,cur.x,shark.size,0);
			
			//물고기 list 비우기
			pq.clear();
		}
	}
	
	
	public static void bfs(CO shark) {
		boolean visit[][]= new boolean[N][N];
		Queue<CO> queue = new LinkedList<>();
		queue.offer(shark);
		visit[shark.y][shark.x]=true;
		
		while(!queue.isEmpty()) {
			CO cur= queue.poll();
			
			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				
				if(nx<0||ny<0||nx>=N||ny>=N||visit[ny][nx]) continue;
				
				if(map[ny][nx]>shark.size) continue;
				
				if(map[ny][nx]!=0&&shark.size>map[ny][nx]) {
					pq.offer(new CO(ny,nx,map[ny][nx],cur.distance+1));
				}
				queue.offer(new CO(ny,nx,map[ny][nx],cur.distance+1));
				visit[ny][nx]=true;
			}
		}
	}
}
