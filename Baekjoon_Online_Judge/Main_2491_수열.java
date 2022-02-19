package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리 : 21,308 KB
//시간 : 204 ms
//풀이 아이디어 - 처음에는 수열 인덱스마다 길이를 체크해서 N^2 을 수행했는데, 시간초과가 나서 답을 보고 2N으로 줄여서 통과함

public class test {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int max_count = 1;
		
		int count = 1;
		for(int i = 0; i<N-1; i++) {	//오름차순일 때
			
			if(array[i] >= array[i+1]) {
				count++;
			}
			else count = 1;
			
			max_count = Math.max(max_count, count);
		}
		
		count = 1;
		for(int i = 0; i<N-1; i++) {	//내림차순일 때
			
			if(array[i] <= array[i+1]) {
				count++;
			}
			else count = 1;
			
			max_count = Math.max(max_count, count);
		}
		
		
		 System.out.println(max_count);
	}
}
