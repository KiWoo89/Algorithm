package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 메모리 : 
 * 시간 : 
 * 풀이 방법 : 
 * 
 */

public class Algo {
	static int N,L;
	static int[][] array;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;	//지나갈 수 있는 길 개수 카운트
		
		for(int i = 0; i<N; i++) {
			if(rowCheck(i)) result++;
			if(colCheck(i)) result++;
		}
		
		System.out.println(result);
	}

	
	public static boolean rowCheck(int row) {
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i<N-1; i++) {	//다음 인덱스와 비교하니까 N-1까지 반복
			if(array[row][i] == array[row][i+1]) continue;	//높이가 서로 같다면
			else if(array[row][i] - array[row][i+1] == 1) {	//내리막길 이라면
				
			}
		}
		
	}
	
	
	public static boolean colCheck(int col) {
		
		
	}
}
