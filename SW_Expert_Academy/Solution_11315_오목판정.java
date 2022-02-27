package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



/* 메모리 : 18,644KB 105ms
 * 시간 : 105ms
 * 풀이 방법 : 행을 기준으로 탐색하여 4방탐색을 하도록 함.
 * 
 */

public class Algo{
	static int[] d_row = {1, 1, 0, -1};	//아래, 오른쪽아래, 오른쪽, 오른쪽위
	static int[] d_col = {0, 1, 1, 1};
	static int[][] array;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
	t:	for(int t = 1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				String str = br.readLine();
				for(int j = 0; j<N; j++) {
					if(str.charAt(j) == '.') array[i][j] = 0;	//빈칸이면 0 저장
					else array[i][j] = 1;		//돌이 있으면 1저장
				}
			}
			
			for(int j = 0; j<N; j++) {
				for(int i = 0; i<N; i++) {
					if(array[i][j] == 1 && check5Mok(i,j)) {	//현재 위치에 돌이 있고, 5목이 있는지 체크
						sb.append("#").append(t).append(" YES\n");
						continue t;
					}
				}
			}
			sb.append("#").append(t).append(" NO\n");
		}
		System.out.println(sb.toString());
	}
	
	public static boolean check5Mok(int r, int c) {
		
	d:	for(int d = 0; d<4; d++) {
			int next_row = r;
			int next_col = c;
			int cnt = 1;	//현재 위치에는 돌이 있으니까 1부터 시작
			
			for(int i = 0; i<4; i++) {
				next_row = next_row + d_row[d];
				next_col = next_col + d_col[d];
				if(checkRange(next_row, next_col) && array[next_row][next_col] == 1) {	//범위를 넘지 않고, 다음 위치에 돌이 있다면 카운트
					cnt++;
				}
				else continue d;
			}
			if(cnt == 5) return true;	//5목이면 true반환
		}
		return false;	//4방 탐색으로 5목이 발견 안된 경우이므로 false반환
	}
	
	
	public static boolean checkRange(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) return true;
		else return false;
	}
	
}



