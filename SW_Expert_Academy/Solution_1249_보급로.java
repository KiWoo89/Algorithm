package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


/*
 * 메모리 : 22,788 KB
 * 실행시간 : 137 ms
 * 풀이 방법 : PQ를 이용한 BFS
 */


public class SolutionTest {

	static int N,T,MIN;
	static int[][] array;
	static int[] d_row = {-1, 0, 1, 0};	//위 오른쪽 아래 왼쪽
	static int[] d_col = {0, 1, 0, -1};	
	static boolean[][] visited;
	
	
	static class Pos implements Comparable<Pos>{
		int r;	//행 위치
		int c;	//열 위치
		int t;	//해당 지점까지 걸린 시간
		
		public Pos(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public int compareTo(Pos o) {	// 걸린 시간 오름차순
			return this.t - o.t;
		}

	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			MIN = Integer.MAX_VALUE;	// 도착지점까지 걸리는 최소 시간
			visited = new boolean[N][N];

			
			for(int i = 0; i<N; i++) {
				String str = br.readLine();
				for(int j = 0; j<N; j++) {
					array[i][j] = str.charAt(j) - '0';		// 숫자로 변환
				}
			}
					
			bfs(new Pos(0, 0, 0));
			
			sb.append("#").append(t).append(" ").append(MIN).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void bfs(Pos pos) {
	
		PriorityQueue<Pos> queue = new PriorityQueue<>();	//매 순간 항상 적은 공사 시간이 걸리는 순으로 정렬
		queue.offer(pos);
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Pos p = queue.poll();

			if(p.r == N-1 && p.c == N-1) {	// 도착 지점에 도착하면, 매 순간 최소 시간의 자리로 이동했기 때문에 도착지점까지 걸린 시간이 최소
				MIN = p.t;

				break;
			}
			
			for(int d = 0; d<4; d++) {	// 4방 탐색
				int next_r = p.r + d_row[d];
				int next_c = p.c + d_col[d];
				if(rangeCheck(next_r, next_c) && !visited[next_r][next_c]) {	//다음 위치가 범위 안에 있어야 하고, 방문하지 않았어야함.
					queue.offer(new Pos(next_r, next_c, p.t + array[next_r][next_c]));	//지금까지 걸린 시간 + 다음칸에 걸리는 시간
					visited[next_r][next_c] = true;
				}
			}	
		}
	
	}
	
	
	
	public static boolean rangeCheck(int r, int c) {	//범위 체크
		if(r>=0 && r<N && c>=0 && c<N) return true;
		else return false;
	}
	
	

}
