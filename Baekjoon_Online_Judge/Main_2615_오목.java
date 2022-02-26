package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



/* 메모리 : 13,064 KB
 * 시간 : 100 ms
 * 풀이 방법 : 겉으로 보면 8방탐색이지만, 왼쪽에서 발견되는 것을 기준으로 출력하라 했으니, 행 기준으로 탐색을 진행하고, 행기준으로 탐색하기
 * 때문에 아래, 아래오른쪽, 오른쪽, 오른쪽 위 부분만 탐색해주는 방식으로 해결
 */

public class Algo{
	static int[][] array;
	static int result = 0;
	static int[] d_row = {1, 1, 0, -1};	//아래, 아래오른쪽, 오른쪽, 오른쪽위
	static int[] d_col = {0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new int[19][19];
		for(int i = 0; i<19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<19; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int j = 0; j<19; j++) {
			for(int i = 0; i<19; i++) {
				if(array[i][j] != 0 && check5Mok(i,j,array[i][j])) {
					System.out.println(result);
					System.out.println((i+1) + " " + (j+1));
					System.exit(0);
				}
			}
		}
		
		System.out.println(0);	//무승부일때
		
		
		
	}
	
	public static boolean check5Mok(int r, int c, int color) {
	to:	for(int d = 0; d<4; d++) {
			int next_row = r;
			int next_col = c;	
			int cnt = 1;	//현재 자기자리 포함

			for(int i = 0; i<5; i++) {
				next_row = next_row + d_row[d];
				next_col = next_col + d_col[d];

				if(checkRange(next_row, next_col) && array[next_row][next_col] == color) {
					cnt++;
				}
				else break;

			}
			
			if(cnt == 5) {
				if(checkRange(r-d_row[d], c-d_col[d]) && array[r-d_row[d]][c-d_col[d]] == color) return false;
				
				result = color;
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean checkRange(int r, int c) {
		if(r>=0 && r<19 && c>=0 && c<19) return true;
		else return false;
	}
	
}



