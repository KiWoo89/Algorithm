package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//메모리:21,420kb
//시간 : 117ms
class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			int result = 0;	//농작물 수익
			int N = Integer.parseInt(br.readLine());
			if(N==0) {
				System.out.println("#"+test_case + " " + 0);
				continue;
			}
			int[][] array = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				String s = br.readLine();
				for(int j = 0; j<N; j++) {
					array[i][j] = s.charAt(j) - '0';
				}
			}
			
			int half = N/2;
			int a = -1;
			int blank = half;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(j<blank) continue;
					else if(j>=blank && j<N-blank){
						result = result + array[i][j];
					}
					else if(j>=N-blank) break;	
				}
				blank = blank + a;
				if(blank==0) a = 1;
			}
				System.out.println("#"+test_case + " " + result);			
		}
					
	}
}

