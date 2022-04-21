package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Gold 3 
//메모리:12768KB 시간:96ms
public class BOJ_17837_새로운게임2 {
	static int N, K;
	static ArrayList<Integer> map[][];
	static int color[][];
	static CO[] horse;
	static int[] dx = { 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int turn,ans;

	static class CO {
		int y, x, dir;
		public CO(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//map 초기화(말 저장할 곳)
		map = new ArrayList[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++)
				map[i][j] = new ArrayList<>();
		}

		//색깔 판 초기화
		color = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				color[i][j] = Integer.parseInt(st.nextToken());
		}

		//말 저장
		horse = new CO[K+1];
		for (int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			horse[k]=new CO(y, x, d);
			map[y][x].add(k);
		}
		
		
		ans=-1;
		while (turn++<=1000) {
			if(play()) {
				ans=turn;
				break;
			}
		}
		System.out.println(ans);		

	}

	static boolean play() {
		for (int i = 1; i <= K; i++) {
			CO cur = horse[i];
			int cx=cur.x;
			int cy=cur.y;
			int size = map[cy][cx].size();
			int nx = cx + dx[cur.dir];
			int ny = cy + dy[cur.dir];
			
			//파란 칸(2) + 범위 외 지역
			if (nx<=0||ny<=0||nx>N||ny>N ||color[ny][nx] == 2) {	
				cur.dir=change_dir(cur.dir);
				nx = cx + dx[cur.dir];
				ny = cy + dy[cur.dir];	
				if ((nx <= 0 || ny <= 0 || nx > N || ny > N) || color[ny][nx] == 2) continue;
			}
			
			
			//흰색+빨간색 칸
			//순서 찾기(몇번째 쌓여 있는지)
			int start = -1;
			for (int j = 0; j < size; j++) {
				if (map[cur.y][cur.x].get(j) == i) {
					start = j;
				}
			}
			if(start==-1) continue;

			//흰색 칸(0) (정순)
			if (color[ny][nx] == 0) {
				for (int j = start; j < size; j++) {
					map[ny][nx].add(map[cy][cx].get(j));
					horse[map[cy][cx].get(j)].x=nx;
					horse[map[cy][cx].get(j)].y=ny;
				}
			} 
			//빨간 칸(1) (역순)
			if (color[ny][nx] == 1) {
				for (int j = size - 1; j >= start; j--) {
					map[ny][nx].add(map[cy][cx].get(j));
					horse[map[cy][cx].get(j)].x=nx;
					horse[map[cy][cx].get(j)].y=ny;
				}
			}
			
			//이동한 말 삭제
			for (int j = start; j < size; j++) 
				map[cy][cx].remove(start);
			
			//말이 4개이상 쌓이면 return
			if(map[ny][nx].size()>=4) 
				return true;
			
		}
		return false;
	}
	
	//방향 바꾸기
    public static int change_dir(int dir) {
        switch(dir) {
        	case 1: return 2;
        	case 2: return 1;
        	case 3: return 4;
        	case 4: return 3;
        }
        return -1;
    }

}
