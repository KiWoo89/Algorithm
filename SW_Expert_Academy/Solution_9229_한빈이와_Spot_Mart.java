package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//메모리 : 25,292kb
//시간 : 163ms

class Main {
	static int[] weights;
	static int N;
	static int M;
	static int Max_weight;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			Max_weight = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			//isSelected = new boolean[N];
			weights = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			max_snack(0, 0, 0);
			System.out.println("#"+test_case + " " + Max_weight);
		}
	}
	
	public static void max_snack(int index, int n, int weight) {
		if(weight>M) return;
		if(n == 2) {
			if(weight > Max_weight) Max_weight = weight;
			return;
		}	
		if(index==N) return;
		
		max_snack(index+1, n+1, weight + weights[index]);	//true일때

		max_snack(index+1, n, weight);		// false일때
	}	
}