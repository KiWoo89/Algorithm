package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



/* 메모리 : 11,520 KB
 * 시간 :	88 ms
 * 풀이 방법 : 단순히 N C M 조합을 구하는 재귀를 구현함.
 */

public class Algo{
	static int N,M;
	static int array[];
	static boolean isSelected[];
	static int numbers[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());	//자연수 N까지
		M = Integer.parseInt(st.nextToken());	//M개 뽑기
		array = new int[N];
		isSelected = new boolean[N];
		numbers = new int[M];
		
		for(int i = 0; i<N; i++) {	//자연수 1~N저장
			array[i] = i+1;
		}
		
		combi(0, 0);	//재귀 저장
		
	}
	
	public static void combi(int cnt, int start) {
		if(cnt == M) {	//기초파트
			for(int i = 0; i<M; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i<N; i++) {	//반복 파트
			numbers[cnt] = array[i];
			combi(cnt+1, i+1);		
		}
		
		
	}
	
	
}



