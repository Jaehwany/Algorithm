import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//Gold 2
//메모리:12132KB 시간:88ms
public class BOJ_1938_통나무옮기기 {
	static int N;
	static int map[][];

	// 상하좌우
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	
	//좌표 클래스 implements comparable
	static class CO implements Comparable<CO> {
		int x, y, flag,distance;
		public CO(int x, int y, int flag, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.flag = flag;
			this.distance = distance;
		}

		//x 좌표가 같다면 ㅣ형 통나무 (flag 1)
		//y 좌표가 같다면 ㅡ형 통나무 (flag 2)
		@Override
		public int compareTo(CO o) {
			if (this.x == o.x) {
				flag = 1;
				return this.y - o.y;
			}
			flag = 2;
			return this.x - o.x;
		}
	}

	static CO[] goal = new CO[3]; //목적지
	static CO[] tong = new CO[3]; //통나무

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int tong_cnt = 0;
		int goal_cnt = 0;
		
		//지도 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char temp = str.charAt(j);
				if (temp == 'B') {
					tong[tong_cnt++] = new CO(j, i, 0,0);
					map[i][j] = 0;
				} else if (temp == 'E') {
					goal[goal_cnt++] = new CO(j, i, 0,0);
					map[i][j] = 0;
				} else
					map[i][j] = temp - '0';
			}
		}
		
		//통나무,목적지 정렬
		Arrays.sort(goal);
		Arrays.sort(tong);

		bfs();

	}

	//bfs
	static void bfs() {
		boolean visit[][][] = new boolean[3][N][N];//방향,y좌표,x좌표
		Queue<CO> queue = new LinkedList<>();
		queue.offer(tong[1]);
		visit[tong[1].flag][tong[1].y][tong[1].x] = true;

		while (!queue.isEmpty()) {
			CO cur = queue.poll();
			
			//목적지에 도달하면 최소동작횟수 출력 후 종료
			if (cur.x == goal[1].x && cur.y == goal[1].y && cur.flag == goal[1].flag) {
				System.out.println(cur.distance);
				return;
			}
			
			//회전 가능한지 체크 횟수 (2가 되면 회전 가능)
			int cnt1 = 0; //세로 기준 ->왼쪽이 비면 ++, 오른쪽이 비면 ++ / 둘다 비면 2가 됨
			int cnt2 = 0;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
			
				//세로일 때 조건
				if (cur.flag == 1) {
					if(nx<0||nx>=N||ny<1||ny>=N-1) continue;
					
					if ((d == 2 || d == 3)) {
						if (map[ny][nx] != 0 || map[ny + 1][nx] != 0 || map[ny - 1][nx] != 0) continue;
						cnt1++;
					}
					if (d == 0 && map[ny - 1][nx] != 0) continue;
					if (d == 1 && map[ny + 1][nx] != 0) continue;
				}

				//가로일 때 조건
				else if (cur.flag == 2) {
					if(ny<0||ny>=N||nx<1||nx>=N-1) continue;
					
					if (d == 0 || d == 1) {
						if (map[ny][nx] != 0 || map[ny][nx + 1] != 0 || map[ny][nx - 1] != 0) continue;
						cnt2++;
					}
					if (d == 2 && map[ny][nx - 1] != 0) continue;
					if (d == 3 && map[ny][nx + 1] != 0) continue;
				}

				//방문하지 않았다면 다음 목적지로 등록 
				if (!visit[cur.flag][ny][nx]) {		
					queue.offer(new CO(nx, ny, cur.flag,cur.distance+1));
					visit[cur.flag][ny][nx] = true;
				}
				
			}

			//회전 가능하면 회전 케이스 queue에 추가 (양쪽 둘다 비었으면(cnt=2)) 
			if (!visit[2][cur.y][cur.x] && cnt1 == 2)
				queue.offer(new CO(cur.x, cur.y, 2,cur.distance+1));
			if (!visit[1][cur.y][cur.x] && cnt2 == 2)
				queue.offer(new CO(cur.x, cur.y, 1,cur.distance+1));
		}
		
		System.out.println(0);
	}

}
