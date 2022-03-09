import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 5 
//메모리:16244KB 시간:148ms
public class BOJ_6593_상범빌딩 {
	// 변수 선언
	static int floor, R, C;
	static char[][][] building;
	// 6방 탐색
	static int dx[] = { 1, -1, 0, 0, 0, 0 };
	static int dy[] = { 0, 0, -1, 1, 0, 0 };
	static int dz[] = { 0, 0, 0, 0, 1, -1 };

	// 좌표 클래스
	static class CO {
		int x, y, floor, time;

		public CO(int x, int y, int floor, int time) {
			super();
			this.x = x;
			this.y = y;
			this.floor = floor;
			this.time = time;
		}
	}

	// 시작점, 끝점
	static CO start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 변수입력
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			floor = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(floor+R+C==0) return;
			
			building = new char[floor + 1][R + 1][C + 1];

			// 건물 정보 입력
			for (int f = 1; f <= floor; f++) {
				for (int i = 1; i <= R; i++) {
					String str = br.readLine();
					for (int j = 1; j <= C; j++) {
						building[f][i][j] = str.charAt(j - 1);
						if (building[f][i][j] == 'S') // 시작점
							start = new CO(j, i, f, 0);
						if (building[f][i][j] == 'E') // 끝점
							end = new CO(j, i, f, 0);
					}
				}
				br.readLine();
			}

			// bfs
			bfs();
		}

	}

	static void bfs() {
		boolean visit[][][] = new boolean[floor + 1][R + 1][C + 1];
		Queue<CO> queue = new LinkedList<>();
		queue.offer(start);
		visit[start.floor][start.y][start.x] = true;

		while (!queue.isEmpty()) {
			CO cur = queue.poll();
			// 끝점에 도달하면 결과값 출력후 return;
			// 6방 탐색
			for (int d = 0; d < 6; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int nz = cur.floor + dz[d];
				if (nx <= 0 || ny <= 0 || nz <= 0 || nx > C || ny > R || nz > floor)
					continue;

				if (nx == end.x && ny == end.y && nz == end.floor) {
					System.out.println("Escaped in " + (cur.time + 1) + " minute(s).");
					return;
				}

				if (!visit[nz][ny][nx] && building[nz][ny][nx] == '.') {
					queue.offer(new CO(nx, ny, nz, cur.time + 1));
					visit[nz][ny][nx] = true;
				}
			}
		}

		// 끝점에 도달하지 못하면 trapped 출력
		System.out.println("Trapped!");

	}

}
