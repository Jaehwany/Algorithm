package git_should_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Silver 2
//메모리 : 13796KB, 시간 : 108ms
public class BOJ_1012_유기농배추 {
	static int farm[][];
	static int N,M,K;
	static boolean visit[][];
	
	//4방탐색
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	
	//좌표 클래스
	static class CO{
		int x,y;
		public CO(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			farm = new int[N][M];
			visit = new boolean[N][M];
			
			for(int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				farm[y][x]=1;
			}
			
			
			int ans=0;
			//배추가 심긴곳중에서 방문하지 않은 곳을 선택해서 Count
			//(한번 방문하는 순간 인접 배추들은 다 방문처리됨)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(farm[i][j]==1&&!visit[i][j]) {
						bfs(new CO(j,i));
						ans++;
					}
				}				
			}
			
			sb.append(ans);
			System.out.println(sb);
			
		}
	}
	
	//BFS-->4방 탐색
	static void bfs(CO start) {
		Queue<CO> queue = new LinkedList<>();
		queue.offer(start);
		visit[start.y][start.x]=true;
		
		while(!queue.isEmpty()) {
			CO cur=queue.poll();
			
			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				
				if(nx<0||nx>=M||ny<0||ny>=N||visit[ny][nx]) continue;
				
				if(farm[ny][nx]==1) {
					queue.offer(new CO(nx,ny));
					visit[ny][nx]=true;
				}
				
			}
			
		}
	}
}
