package test;

import java.util.Scanner;


// 1954.달팽이 숫자-D2	실행시간 = 152ms,	메모리 = 20,788 kb
public class Main {
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int TC = in.nextInt();
		
		for(int t = 1; t<=TC; t++) {
			
			int n = in.nextInt();
			int num = 1;
			int next_r = 0;
			int next_c = 0;
			int direction = 0;	// 0 = 오른쪽, 1 = 아래, 2 = 왼쪽, 3 = 위쪽
			int[][] result = new int[n][n];
			
			
			result[next_r][next_c] = num++;
			while(num <= Math.pow(n, 2)) {
				if(direction == 0) {
					next_c++;
					result[next_r][next_c] = num++;
					if(next_c + 1 >= n || result[next_r][next_c+1] != 0) {
						direction = 1;
						continue;
					}
					else if(next_c + 1 <= n && result[next_r][next_c+1] == 0) {
						continue;
					}
				}
				else if(direction == 1) {
					next_r++;
					result[next_r][next_c] = num++;
					if(next_r + 1 >= n || result[next_r+1][next_c] != 0) {
						direction = 2;
					}
					else if(next_r + 1 <= n && result[next_r+1][next_c] == 0) {
						continue;
					}
				}
				else if(direction == 2) {
					next_c--;
					result[next_r][next_c] = num++;
					if(next_c - 1 < 0 || result[next_r][next_c-1] != 0) {
						direction = 3;
					}
					else if(next_c - 1 >= 0 && result[next_r][next_c-1] == 0) {
						continue;
					}
				}
				else if(direction == 3) {
					next_r--;
					result[next_r][next_c] = num++;
					if(next_r - 1 < 0 || result[next_r-1][next_c] != 0) {
						direction = 0;
					}
					else if(next_r - 1 >= 0 && result[next_r-1][next_c] == 0) {
						continue;
					}
				}
			
			}
			
			System.out.println("#" + t);
			for(int i = 0; i<result.length; i++) {
				for(int j =0; j<result[0].length; j++) {
					System.out.print(result[i][j] + " ");
				}
				System.out.println();
			}
			
		}


	}
	
	
	
}

	 
