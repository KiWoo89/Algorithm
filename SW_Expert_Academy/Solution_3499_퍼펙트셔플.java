package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 26,732kb
// 155ms

class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int half = N / 2;
			Stack<String> stack1 = new Stack<>();
			Stack<String> stack2 = new Stack<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			if (N % 2 == 0) {
				for (int i = 0; i < half; i++) {
					stack1.push(st.nextToken());
				}
				for (int i = 0; i < half; i++) {
					stack2.push(st.nextToken());
				}
			} else {
				for (int i = 0; i < half + 1; i++) {
					stack1.push(st.nextToken());
				}
				for (int i = 0; i < half; i++) {
					stack2.push(st.nextToken());
				}
			}

			String[] result = new String[N];
			int change;
			if(N%2==0) change = 1;
			else change = 0;
			
			for (int i = 0; i < N; i++) {
				if (change == 0) {
					result[i] = stack1.pop();
					change = 1;
				} else {
					result[i] = stack2.pop();
					change = 0;
				}
			}

			System.out.print("#" + test_case + " ");
			for (int i = N - 1; i >= 0; i--) {
				System.out.print(result[i] + " ");
			}
			System.out.println();

		}

	}
}
