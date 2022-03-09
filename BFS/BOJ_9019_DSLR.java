import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 4 
//메모리:297096KB 시간:4440ms
public class BOJ_9019_DSLR {
	//Point 클래스 선언
	static class Point{
		int value;
		String str;
		public Point(int value, String str) {
			super();
			this.value = value;
			this.str = str;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			bfs(start,end);	
		}
	}
	
	static void bfs(int start, int end) {
		boolean visit[]= new boolean[10000];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(start,""));
		visit[start]=true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//도착점에 도착하면 return;
			if(cur.value==end) {
				System.out.println(cur.str);
				return;
			}
			//D,S,L,R 차례대로 check
			for(int d=0;d<4;d++) {
				int next=0;
				String s="";
				if(d==0) {
					next=2*cur.value%10000;
					s="D";
				}
				else if(d==1) {
					next = (cur.value+10000-1)%10000;
					s="S";
				}
				else if(d==2) {
					next = left_shift(cur.value);
					s="L";
				}
				else if(d==3) {
					next= right_shift(cur.value);
					s="R";
				}		
				if(visit[next]) continue;
				
				queue.offer(new Point(next,cur.str+s));
				visit[next]= true;
			}
		}
	}
	//좌로 shift
	static int left_shift(int num) {
		int t4=num/1000;
		int t3=(num%1000)/100;
		int t2=(num%100)/10;
		int t1=(num%10);	
		return 1000*t3+100*t2+10*t1+t4;
	}
	//우로 shift
	static int right_shift(int num) {
		int t4=num/1000;
		int t3=(num%1000)/100;
		int t2=(num%100)/10;
		int t1=(num%10);
		return 1000*t1+100*t4+10*t3+t2;
	}
}
