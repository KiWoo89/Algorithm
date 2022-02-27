package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 메모리 : 190,788 KB
 * 시간 : 796 ms
 * 풀이 방법 : 단순 구현
 * 
 */

public class Algo {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());	//팀의 수
		int K = Integer.parseInt(st.nextToken());	//팀원 세명의 최소 합
		int L = Integer.parseInt(st.nextToken());	//팀원 각각의 최소 레이팅
		
		int array[][] = new int[N][3];
		int count = 0;	//가입한 팀의 수
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
			array[i][2] = Integer.parseInt(st.nextToken());
			
			//모든 팀원이 L레이팅 이상이고, 팀원의 레이팅 합이 k이상일 때 가입가능
			if(array[i][0] >= L && array[i][1] >= L && array[i][2] >= L && array[i][0] + array[i][1] + array[i][2] >= K) {
				sb.append(array[i][0]).append(" ").append(array[i][1]).append(" ").append(array[i][2]).append(" ");
				count++;
			}
			
			
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
		

	}

	
}
