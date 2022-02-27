package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



/* 메모리 : 19,156 KB
 * 시간 : 97 ms
 * 풀이 방법 : 8방탐색
 * 
 */

public class Algo{
	static int[] d_row = {-1, -1, -1, 0, 1, 1, 1, 0}; //왼쪽 위부터 팔방
	static int[] d_col = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int N, depth;
	static int[][] array;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			depth = 0;	//깊이 초기화
			array = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<N; j++) {
					if(st.nextToken().charAt(0) == 'G') array[i][j] = 0;	//땅이라면 0으로, 물이면 1로 저장
					else array[i][j] = 1;
				}
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(array[i][j] == 1) {		//현재 위치가 물이라면 깊이 구하기
						depthCalc(i,j);
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(depth).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
		
		
	}
	
	public static void depthCalc(int r, int c) {
		int cnt = 0;
		
		for(int d = 0; d<8; d++) {
			int next_row = r + d_row[d];
			int next_col = c + d_col[d];
			if(rangeCheck(next_row, next_col) && array[next_row][next_col] == 1) {	//8방탐색으로 주변이 물인지 체크
				cnt++;
			}
		}
		
		if(cnt == 0) cnt++;	//주변이 모두 땅이라면 깊이는 1
		
		if(cnt > depth) depth = cnt;	//지금의 물 깊이가 최대 깊이보다 크다면 변경
		
	}
	
	public static boolean rangeCheck(int r, int c) {
		if(r >= 0 && r<N && c>=0 && c<N) return true;
		else return false;
	}
	
}



