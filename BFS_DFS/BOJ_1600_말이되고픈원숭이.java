import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 4
public class BOJ_1600_말이되고픈원숭이 {
	//변수 선언
	static int K,M,N;
	static int map[][];
	static boolean visit[][][];
	
	//4방 탐색 선언
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	
	//말 8방 탐색 선언
	static int dx2[]= {1,2,2,1,-1,-2,-2,-1};
	static int dy2[]= {-2,-1,1,2,2,1,-1,-2};
	
	//좌표 클래스 선언
	static class CO{
		int x,y,k,move;
		public CO(int x, int y, int k, int move) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.move = move;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		map= new int[N][M];
		visit=new boolean[K+1][N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		bfs(new CO(0,0,K,0));
	}
	//BFS
	static void bfs(CO start){
		Queue<CO> queue = new LinkedList<>();
		queue.offer(start);
		visit[start.k][start.y][start.x]=true;
		
		while(!queue.isEmpty()) {
			CO cur = queue.poll();
			if(cur.x==M-1&&cur.y==N-1) {
				System.out.println(cur.move);
				return;
			}
			//인접 4방향 탐색
			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];	
				if(nx<0||ny<0||nx>=M||ny>=N) continue;	
				if(!visit[cur.k][ny][nx]&&map[ny][nx]==0) {
					queue.offer(new CO(nx,ny,cur.k,cur.move+1));
					visit[cur.k][ny][nx]=true;
				}
			}
			//점프 횟수가 남은 경우 말 8방 탐색
			if(cur.k>0) {
				for(int d=0;d<8;d++) {
					int nx=cur.x+dx2[d];
					int ny=cur.y+dy2[d];
					
					if(nx<0||ny<0||nx>=M||ny>=N) continue;
					
					if(!visit[cur.k-1][ny][nx]&&map[ny][nx]==0) {
						queue.offer(new CO(nx,ny,cur.k-1,cur.move+1));
						visit[cur.k-1][ny][nx]=true;
					}
				}
			}
			
		}
		System.out.println(-1);
	}
	
}
