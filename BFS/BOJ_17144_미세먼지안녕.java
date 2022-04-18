package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 4
//메모리 : 143960KB, 시간 : 404ms
public class BOJ_2_17144_미세먼지안녕 {
	static int R,C,T;
	static int room[][];
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	
	static int cleaner;
	static Queue<CO> dust;
	
	static class CO{
		int x,y;
		public CO(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());		
		room = new int[R+1][C+1];

		for(int i=1;i<=R;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=C;j++) {
				room[i][j]=Integer.parseInt(st.nextToken());
				if(room[i][j]==-1) 
					cleaner=i;
			}
		}
	
		for(int t=1;t<=T;t++) {
			find_dust();
			spread();
			clean();
		}
		
		int sum=2;
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				sum+=room[i][j];
			}
		}
		System.out.println(sum);
	}
	
	//미세먼지 찾기
	static void find_dust() {
		dust= new LinkedList<>();
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				if(room[i][j]>0)
					dust.offer(new CO(i,j));
			}
		}
	}
	
	//확산
	static void spread() {
		int[][] temp= new int[R+1][C+1];
	
		while(!dust.isEmpty()) {
			CO cur = dust.poll();	
			if(room[cur.y][cur.x]<5) continue;
			
			int div_dust=room[cur.y][cur.x]/5;
			for(int d=0;d<4;d++) {
				int nx=cur.x+dx[d];
				int ny=cur.y+dy[d];
				
				if(nx<1||ny<1||nx>C||ny>R||room[ny][nx]==-1) continue;

				temp[ny][nx]+=div_dust;
				room[cur.y][cur.x]-=div_dust;
			}		
		}
		
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) 
				room[i][j]+=temp[i][j];
		}
	}
	
	//공기청정기 가동 
	static void clean() {
		//1. 반시계 (상)
		rotate_reverse(cleaner-1,1);
		//2. 시계 (하)
		rotate(cleaner,1);
	}
	
	static void rotate_reverse(int y,int x) {
		for(int i=y-2;i>=1;i--) {
			room[i+1][1]=room[i][1];
		}
		
		for(int j=2;j<=C;j++) {
			room[1][j-1]=room[1][j];
		}
		
		for(int i=1;i<=y;i++) {
			room[i-1][C]=room[i][C];
		}
		
		for(int j=C;j>2;j--) {
			room[y][j]=room[y][j-1];
		}
		room[y][2]=0;
	}

	static void rotate(int y,int x) {
		
		for(int i=y+2;i<=R;i++) {
			room[i-1][1]=room[i][1];
		}
		
		for(int j=1;j<C;j++) {
			room[R][j]=room[R][j+1];
		}
		
		for(int i=R;i>y;i--) {
			room[i][C]=room[i-1][C];
		}
		
		for(int j=C;j>2;j--) {
			room[y][j]=room[y][j-1];
		}
		room[y][2]=0;
		
	}
}
