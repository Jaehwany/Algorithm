import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Silver 1
//메모리 : 12448KB, 시간 : 100ms
public class BOJ_9205_맥주마시면서걸어가기 {
	static CO festival = null;
	static int n;
	static String ans;
	
	//좌표 클래스 구하기
	static class CO{
		int x, y;
		public CO(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			n= Integer.parseInt(br.readLine());
			CO[] stores = new CO[n+2];
			CO home=null;
			for(int i=0;i<=n+1;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(i==0) 
					home = new CO(x,y);
				else if(i==n+1)
					festival=new CO(x,y);
				stores[i]=new CO(x,y);
			}
			ans="sad";
			bfs(home,stores);
			System.out.println(ans);
				
		}
		
	}
	//BFS
	static void bfs(CO start,CO[] store) {
		boolean visit[]= new boolean[n+1];
		Queue<CO> queue= new LinkedList<>();
		queue.offer(start);
		visit[0]=true;

		while(!queue.isEmpty()) {
			CO cur = queue.poll();
			//festival에 도착할 수 있으면 happy
			if(distance(cur,festival)<=1000) {
				ans="happy";
				return;
			}
			for(int i=1;i<=n;i++) {
				CO next = store[i];
				if(visit[i]) continue;
				//맥주 20병안에 갈 수 있는 상점을 queue에 넣기
				if(distance(cur,next)<=1000) {
					queue.offer(next);
					visit[i]=true;
				}
			}
		}
	}
	//거리 구하기
	static int distance(CO start, CO next) {
		return Math.abs(start.y-next.y)+Math.abs(start.x-next.x);
	}

	
	
}
