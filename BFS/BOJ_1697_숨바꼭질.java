import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Silver 1
//메모리:17112KB 시간:132ms
public class BOJ_1697_숨바꼭질 {
	static int N, K;
	static int ans;

	//클래스 Info 선언
	static class Info {
		int point;
		int time;
		public Info(int point, int time) {
			super();
			this.point = point;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		
		bfs(new Info(N, 1));
		if(N==K) ans=0;
		System.out.println(ans);
	}

	static void bfs(Info start) {
		boolean visit[] = new boolean[100001];
		Queue<Info> queue = new LinkedList<Info>();
		queue.offer(start);
		visit[start.point] = true;

		while (!queue.isEmpty()) {
			Info cur = queue.poll();

			//앞,뒤, 순간이동
			for (int i = 0; i < 3; i++) {
				int next = 0;
				if (i == 0)	next = cur.point + 1;
				else if (i == 1) next = cur.point - 1;
				else if (i == 2) next = cur.point * 2;
				
				if (next == K) {
					ans = cur.time;
					return;
				}

				if (next >= 0 && next < 100001 && !visit[next]) {
					queue.offer(new Info(next, cur.time + 1));
					visit[next] = true;
				}

			}
		}
	}


}
