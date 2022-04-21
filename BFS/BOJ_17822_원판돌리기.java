package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 3 
//메모리:21472KB 시간:180ms
public class BOJ_17822_원판돌리기 {
	static int N,M,T;
	static int circle[][];
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	static int ans;
	
	static class CO{
		int y,x;
		public CO(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		T= Integer.parseInt(st.nextToken());
		
		//회전판 정보 입력
		circle= new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) 
				circle[i][j]=Integer.parseInt(st.nextToken());
		}

		//회전 조건 입력 + 시뮬레이션
		for(int t=1; t<=T;t++) {
			st= new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int dir= Integer.parseInt(st.nextToken());
			int K= Integer.parseInt(st.nextToken());
	
			//Simulation
			 for(int i=x;i<=N;i=i+x)
				 for(int k=0;k<K;k++)
					 rotate(i,dir);					 
			
			if(!find_adj()) balance_cal();
		}
		
		//답 구하기
		for(int i=1;i<=N;i++) 
			for(int j=1;j<=M;j++) 
				if(circle[i][j]>0) 
					ans+=circle[i][j];
		
		System.out.println(ans);
	}
	
	//회전하기
	static void rotate(int i,int dir) {
		//시계
		if(dir==0) {
			int tmp=circle[i][M];
			for(int j=M-1;j>=1;j--)
				circle[i][j+1]=circle[i][j];
			circle[i][1]=tmp;
		}
		//반시계
		else if(dir==1) {
			int tmp=circle[i][1];
			for(int j=1;j<M;j++)
				circle[i][j]=circle[i][j+1];
			circle[i][M]=tmp;			
		}
	}
	
	//BFS로 인접하면서 같은 수 찾기
	static boolean find_adj() {
		boolean isChange=false;
		Queue<CO> queue = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(circle[i][j]>0) 
					queue.offer(new CO(i,j));
			}
		}
		
		int temp[][]=new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++)
				temp[i][j]=circle[i][j];
		}
		
		while(!queue.isEmpty()) {
			CO cur = queue.poll();
			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				
				if(nx==M+1) nx=1;
				if(nx==0) nx=M;
				if(nx<1||nx>M||ny<1||ny>N) continue;				
				
				if(circle[ny][nx]==circle[cur.y][cur.x]) {
					temp[ny][nx]=0;
					temp[cur.y][cur.x]=0;
					isChange=true;
				}
			}
		}
		circle=temp;
		return isChange;
	}
	
	//balancing
	static void balance_cal() {
		int sum=0;
		int cnt=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(circle[i][j]>0) {
					sum+=circle[i][j];
					cnt++;
				}
			}
		}
		double avg=(double)sum/cnt;
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(circle[i][j]<=0) continue;
				if(circle[i][j]>avg) circle[i][j]--;
				else if(circle[i][j]<avg) circle[i][j]++;
			}
		}
	}
	
}