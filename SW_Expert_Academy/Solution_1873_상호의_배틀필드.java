package test;

import java.util.Scanner;
import java.io.FileInputStream;

//메모리 : 43,116kb,	시간 : 250ms

class battle {
	
	static char[][] filed;
	static int cur_row;
	static int cur_col;
	static int[] d_row = {-1, 0, 1, 0};	//위 오른쪽 아래 왼쪽
	static int[] d_col = {0, 1, 0, -1}; //위 오른쪽 아래 왼쪽
	
	public static void main(String args[]) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		

		for (int test_case = 1; test_case <= T; test_case++) {
			int H = sc.nextInt();
			int W = sc.nextInt();

			filed = new char[H][W];
			for(int i = 0; i<filed.length; i++) {
				String s = sc.next();
				for(int j = 0; j<filed[0].length; j++) {
					filed[i][j] = s.charAt(j);
				}
			}
			
			int n = sc.nextInt();
			char[] input = new char[n];
			String s = sc.next();
			for(int i = 0; i<input.length; i++) {
				input[i] = s.charAt(i);
			}


			for(int i = 0; i<filed.length; i++) {
				for(int j = 0; j<filed[0].length; j++) {
					if(filed[i][j] == '^' || filed[i][j] == 'v' || filed[i][j] == '<' || filed[i][j] == '>') {
						cur_row = i;
						cur_col = j;
						break;
					}
				}
			}
			
			for(int i = 0; i<input.length; i++) {
				function(input[i]);
			}
			
			System.out.print("#"+test_case+" ");
			for(int i = 0; i<filed.length; i++) {
				for(int j = 0; j<filed[0].length; j++) {
					System.out.print(filed[i][j]);
				}
				System.out.println();
			}
			
			
			

		}
	}
	
	public static void function(char func) {
		int next_row;
		int next_col;
		
		if(func == 'U') {	//전차가 바라보는 방향 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 그 위 칸으로 이동.
			next_row = cur_row + d_row[0];
			next_col = cur_col + d_col[0];
			if(next_row >= 0 && filed[next_row][next_col] == '.') {
				filed[cur_row][cur_col] = '.';
				filed[next_row][next_col] = '^';
				cur_row = next_row;
				cur_col = next_col;
			}
			else {
				filed[cur_row][cur_col] = '^';
			}
		}
		
		else if(func == 'D') {
			next_row = cur_row + d_row[2];
			next_col = cur_col + d_col[2];
			if(next_row < filed.length && filed[next_row][next_col] == '.') {
				filed[cur_row][cur_col] = '.';
				filed[next_row][next_col] = 'v';
				cur_row = next_row;
				cur_col = next_col;
			}
			else {
				filed[cur_row][cur_col] = 'v';
			}
		}
		
		else if(func == 'L') {
			next_row = cur_row + d_row[3];
			next_col = cur_col + d_col[3];
			if(next_col >= 0 && filed[next_row][next_col] == '.') {
				filed[cur_row][cur_col] = '.';
				filed[next_row][next_col] = '<';
				cur_row = next_row;
				cur_col = next_col;
			}
			else {
				filed[cur_row][cur_col] = '<';
			}
		}
		
		else if(func == 'R') {
			next_row = cur_row + d_row[1];
			next_col = cur_col + d_col[1];
			if(next_col < filed[0].length && filed[next_row][next_col] == '.') {
				filed[cur_row][cur_col] = '.';
				filed[next_row][next_col] = '>';
				cur_row = next_row;
				cur_col = next_col;
			}
			else {
				filed[cur_row][cur_col] = '>';
			}
		}
		
		else if(func == 'S') {		//전차가 현재 바라보고 있는 방향으로 포탄을 발사함. 강철 # 은 못 부숨.
			if(filed[cur_row][cur_col] == '^') {	//현재 전자차 위쪽 바라보고 있을 때
				next_row = cur_row;
				next_col = cur_col;
				for(int i = next_row; i>=0; i--) {
					next_row--;
					if(next_row >= 0) {
						if(filed[next_row][next_col] == '*') {	//벽돌 벽 만났을 때
							filed[next_row][next_col] = '.';
							break;
						}
						else if(filed[next_row][next_col] == '#') { //강철 벽 만났을 때
							break;
						}
						else if(filed[next_row][next_col] == '.') {
							continue;
						}
					}
					else break;
				}
					
			}
			
			else if(filed[cur_row][cur_col] == '>') {
				next_row = cur_row;
				next_col = cur_col;
				for(int i = next_col; i < filed[0].length; i++) {
					next_col++;
					if(next_col < filed[0].length) {
						if(filed[next_row][next_col] == '*') {	//벽돌 벽 만났을 때
							filed[next_row][next_col] = '.';
							break;
						}
						else if(filed[next_row][next_col] == '#') { //강철 벽 만났을 때
							break;
						}
						else if(filed[next_row][next_col] == '.') {
							continue;
						}
					}
					else break;
				}	
			}
			
			else if(filed[cur_row][cur_col] == 'v') {
				next_row = cur_row;
				next_col = cur_col;
				for(int i = next_row; i < filed.length; i++) {
					next_row++;
					if(next_row < filed.length) {
						if(filed[next_row][next_col] == '*') {	//벽돌 벽 만났을 때
							filed[next_row][next_col] = '.';
							break;
						}
						else if(filed[next_row][next_col] == '#') { //강철 벽 만났을 때
							break;
						}
						else if(filed[next_row][next_col] == '.') {
							continue;
						}
					}
					else break;
				}	
			}
			
			else if(filed[cur_row][cur_col] == '<') {
				next_row = cur_row;
				next_col = cur_col;
				for(int i = next_col; i >= 0; i--) {
					next_col--;
					if(next_col >= 0) {
						if(filed[next_row][next_col] == '*') {	//벽돌 벽 만났을 때
							filed[next_row][next_col] = '.';
							break;
						}
						else if(filed[next_row][next_col] == '#') { //강철 벽 만났을 때
							break;
						}
						else if(filed[next_row][next_col] == '.') {
							continue;
						}
					}
					else break;
				}	
				
				
			}
			
		}
		
	}
	
		
}
