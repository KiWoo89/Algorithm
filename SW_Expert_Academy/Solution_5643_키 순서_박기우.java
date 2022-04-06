package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//메모리 : 94,796 KB
//시간 : 2,316 ms
//풀이방법 : 플로이드 알고리즘 활용

class Solution {
	static final int INF = 99999;
	static int T, N, M;
	static int[][] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine()); // 학생 수 (500)
			M = Integer.parseInt(br.readLine()); // 키 비교 횟수 (125,000)
			array = new int[N + 1][N + 1];

			for (int i = 0; i <= N; i++) {	
				Arrays.fill(array[i], INF);
			}

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				array[a][b] = 1;
			}

			for (int k = 1; k <= N; k++) {	//플로이드

				for (int i = 1; i <= N; i++) {
					if (i == k)
						continue;

					for (int j = 1; j <= N; j++) {
						if (j == i || j == k)
							continue;

						if (array[i][j] > array[i][k] + array[k][j]) {
							array[i][j] = array[i][k] + array[k][j];
						}
					}
				}
			}
			
			
			int[] con_cnt = new int[N+1];	//해당 정점에 들어오고 나가는 수 카운팅
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=N; j++) {
					if(array[i][j] != INF) {	// INF가 아니라면 연결이 되어 있다는 의미
						con_cnt[i]++;			// a -> b 에서 a 부분 카운팅
						con_cnt[j]++;			// b 부분 카운팅
					}
				}
			}
			
			int cnt=0;
			for(int i = 1; i<=N; i++) {	
				if(con_cnt[i] == N-1) cnt++;		// 해당 정점에 들어오고 나가는 수가 자기 자신 정점 제외한 수와 같다면 카운팅 
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
			

		}
		
		System.out.println(sb.toString());

	}

}
