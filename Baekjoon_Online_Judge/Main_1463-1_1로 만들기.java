package test;


/*
 * 메모리 : 16,824 KB
 * 시간 : 128 ms
 * 풀이 방법 : DP
 */


import java.util.Scanner;

public class SolutionTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp_table = new int[N+1];
		dp_table[0] = 0;
		dp_table[1] = 0;
		
		for(int i = 2; i<=N; i++) {
			
			dp_table[i] = dp_table[i-1] + 1;	//빼기로 갈  때
			if(i%2 == 0) dp_table[i] = Math.min(dp_table[i], dp_table[i/2] + 1);	//%2로 나누어질 때
			if(i%3 == 0) dp_table[i] = Math.min(dp_table[i], dp_table[i/3] + 1);	//%3으로 나누어질 때

		}
		
		System.out.println(dp_table[N]);
	}

}
