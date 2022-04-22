package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold2
//메모리:109928KB 시간:892ms
public class BOJ_17143_낚시왕 {
	static int R, C, M;
	static Shark map[][];
	static int dx[] = { 0, 0, 0, 1, -1 }; // 위 아래 오른 왼
	static int dy[] = { 0, -1, 1, 0, 0 };
	static int sum;

	static class Shark {
		int x,y;
		int speed, dir, size;
		public Shark(int y, int x, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(M==0) {
			System.out.println(0);
			return;
		}

		map = new Shark[R + 1][C + 1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c]=new Shark(r,c,s,d,z);
		}
		
		for (int i = 1; i <= C; i++) {		
			shark_catch(i,0);
			shark_move();
		}
		
		System.out.println(sum);
	}

	static void shark_catch(int x, int y) {
		if (y == R) return;

		if (map[y+1][x]!=null) {
			sum += map[y+1][x].size;
			map[y+1][x]=null;
			return;
		}
		shark_catch(x, y + 1);
	}
	
	static void shark_move() {
		Queue<Shark> queue = new LinkedList<>(); 
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(map[i][j] != null) {
					queue.offer(new Shark(i, j, map[i][j].speed, map[i][j].dir, map[i][j].size));
				}
			}
		}
		
		Shark temp[][]=new Shark[R+1][C+1];
		
		while(!queue.isEmpty()) {
			Shark cur=queue.poll();		
			int nx=cur.x;
			int ny=cur.y;
			int dir=cur.dir;
			int speed=cur.speed;
			
			if(dir==1||dir==2) speed=speed%(2*(R-1));
			if(dir==3||dir==4) speed=speed%(2*(C-1));
			
			for(int j=1;j<=speed;j++) {
				if(nx+dx[dir]>C||ny+dy[dir]<1) dir++;	
				if(ny+dy[dir]>R||nx+dx[dir]<1) dir--;
				nx+=dx[dir];
				ny+=dy[dir];
			}
			
			if(temp[ny][nx] !=null) {
				if(temp[ny][nx].size<cur.size)
					temp[ny][nx]=new Shark(ny,nx,cur.speed,dir,cur.size);
			}
			else 
				temp[ny][nx]=new Shark(ny,nx,cur.speed,dir,cur.size);
		}
		map=temp;
	}
}
