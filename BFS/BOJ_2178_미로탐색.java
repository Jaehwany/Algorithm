import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Silver 1
//메모리:12164KB 시간:92ms
public class BOJ_2178_미로탐색 {
	//입력 변수
	static int N,M;
	static int arr[][];
	
	//출력 변수
	static int distance;

	//4방 탐색
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,-1,1};
	
	//좌표 클래스
	static class CO{
		int x,y;
		int distance;
		public CO(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}	
	}
	//main
	public static void main(String[] args) throws IOException {
		
		//변수 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N+1][M+1];
		
		//미로정보 입력
		for(int i=1;i<=N;i++) {
			String str=br.readLine();
			for(int j=1;j<=M;j++) 
				arr[i][j]=str.charAt(j-1)-'0';
		}
		
		//BFS
		bfs(new CO(1,1,1));
		
		//출력
		System.out.println(distance);
		
	}
	
	//BFS
	static void bfs(CO start) {
		boolean visit[][] = new boolean[N+1][M+1];
		Queue<CO> queue = new LinkedList<>();
		queue.offer(start);
		visit[start.y][start.x]=true;
		
		while(!queue.isEmpty()) {
			CO cur=queue.poll();
			if(cur.x==M&&cur.y==N) {
				distance= cur.distance;
				return;
			}

			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				if(nx<=0||nx>M||ny<=0||ny>N||visit[ny][nx]) continue;
		
				if(arr[ny][nx]==1) {
					queue.offer(new CO(nx,ny,cur.distance+1));
					visit[ny][nx]=true;
				}
			}
		}
	}

}
