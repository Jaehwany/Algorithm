package blog_should_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Gold 5 
//메모리:92012KB 시간:216ms
public class BOJ_12851_숨바꼭질2 {
	static int num=100000;
	static int N,K;
	static int time=Integer.MAX_VALUE;
	static int cnt;
    
	//Info 클래스 선언
	static class Info{
		int p,time;
		public Info(int p, int time) {
			super();
			this.p = p;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
	
		//처음부터 위치가 같다면
		if(N==K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
        
		bfs();	
		System.out.println(time);
		System.out.println(cnt);
	}
	//BFS
	static void bfs() {
		boolean visit[]=new boolean[num+1];
		Queue<Info> queue=new LinkedList<>();
		queue.offer(new Info(N,0));
		while(!queue.isEmpty()) {
			Info cur = queue.poll();
			visit[cur.p]=true;
			//앞,뒤,순간이동
			for(int d=0;d<3;d++) {
				int next=0;
				if(d==0) next=cur.p-1;
				else if(d==1) next=cur.p+1;
				else if(d==2) next=cur.p*2;
				
				if(next<0||next>100000||visit[next]) continue;
				
				//최초 도착시간 + 몇 번 도착하는지 
				if(next==K) {
					time=Math.min(time, cur.time+1);
					if(time==cur.time+1) cnt++;
					continue;
				}
				queue.offer(new Info(next,cur.time+1));
				
			}
		}
	}
	
}

