import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Silver 2
//메모리 : 70612KB, 시간 : 268ms
public class BOJ_7562_나이트의이동 {
	static int dx[]= {-1,1,2,2,1,-1,-2,-2};
	static int dy[]= {2,2,1,-1,-2,-2,-1,1};
	static int map[][];
	static int N;
	static int st_x,st_y,des_x,des_y;
	
	//좌표 클래스 선언
	static class CO{
		int x,y,count;
		public CO(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=1;t<=tc;t++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			st=new StringTokenizer(br.readLine());
			st_x=Integer.parseInt(st.nextToken());
			st_y=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			des_x=Integer.parseInt(st.nextToken());
			des_y=Integer.parseInt(st.nextToken());
		
			bfs(new CO(st_x,st_y,0));
		}
		
	}
	//BFS
	static void bfs(CO start) {
		boolean visit[][] = new boolean[N][N];
		Queue<CO> queue = new LinkedList<>();
		queue.offer(start);
		visit[start.y][start.x]=true;
		
		while(!queue.isEmpty()) {
			CO cur= queue.poll();
			//도착점에 도착하면 count return;
			if(cur.x==des_x&&cur.y==des_y) {
				System.out.println(cur.count);
				return;
			}
			for(int d=0;d<8;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				
				if(nx<0||ny<0||nx>=N||ny>=N||visit[ny][nx]) continue;	
			
				queue.offer(new CO(nx,ny,cur.count+1));
				visit[ny][nx]=true;
				
			}
		}
	}
}
