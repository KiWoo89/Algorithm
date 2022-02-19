package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 16,424 KB
//시간 : 128 ms
//풀이방법 : 단순 구현

public class Solution {
	static int C;	//가로 (열)	(0,0) = 왼쪽 아래
	static int R;	//세로 (행)
	static int Number;	//관객 번호
	static int[][] array;
	static int[] d_row = {-1, 0, 1, 0};	//상 우 하 좌
	static int[] d_col = {0, 1, 0, -1};
	static int[] temp_row = {1, 0, -1, 0};	//행이 거꾸로 되어 있어서 출력할 행 번호를 관리해줄 델타
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Number = Integer.parseInt(br.readLine());
		
		array = new int[R][C];
		int d = 0;	//방향 초기값
		int r = R-1;	//열 초기값
		int c = 0;	//행 초기값
		int num = 1; //관객 번호 초기값
		int temp_r = 1;	//뒤집어진 행을 처리하기 위해 출력할 행 번호를 관리
		array[r][c] = num;	//관객 1번 넣어주기
		
		if(Number==1) {
			System.out.println((c+1) + " " + temp_r);
			System.exit(0);
		}
		
		if(Number > C*R) {
			System.out.println(0);
			System.exit(0);
		}

		
		while(num < C*R) {	//관객 수 최대 값인 C*R보다 커지면 반복문 나옴
			int next_row = r + d_row[d];
			int next_col = c + d_col[d];
			int next_temp = temp_r + temp_row[d];
			
			if(!checkRange(next_row, next_col)) {
				d = (d + 1) % 4;
				continue;
			}
			
			num++;
			
			array[next_row][next_col] = num;
			
			if(num == Number) {		//찾고자 하는 관객번호와 같다면, 해당 위치 출력 후 break.
				System.out.println((next_col+1) + " " + (next_temp));	//(0,0)부터 시작했으니 +1씩 
				System.exit(0);
			}

			
			r = next_row;
			c = next_col;
			temp_r = next_temp;
		}
		
		
	}
	
	public static boolean checkRange(int r, int c) {	//범위 체크
		if(r >= 0 && r < R && c >= 0 && c < C && array[r][c] == 0) return true;
		else return false;	
	}
		
}
