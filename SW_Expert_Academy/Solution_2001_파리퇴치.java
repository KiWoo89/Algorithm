package test;

import java.util.Scanner;

class Pari {
	static int[] d_row = {0, 0, 1, 1};	//처음 위치, 오른쪽, 오른쪽아래, 아래
	static int[] d_col = {0, 1, 1, 0}; //처음 위치, 오른쪽, 오른쪽아래, 아래
	static int[][] array;
	static int[][] parichae;
	static int max = 0;
	
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			max  = 0;
			
			int N = sc.nextInt();	//배열
			int M = sc.nextInt();	//파리채 배열
			
			array = new int[N][N];
			parichae = new int[M][M];
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					array[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i<=N-M; i++) {
				for(int j = 0; j<=N-M; j++) {
					getParichae(i,j);
				}
			}
			
			System.out.println("#"+test_case + " " + max);
		}
	}
	
	public static void getParichae(int row, int col){
		int next_row = row;
		int next_col = col;
		int k = 0;
		int sum = 0;
		
		for(int i = 0; i<parichae.length; i++) {
			next_col = col;
			for(int j = 0; j<parichae.length; j++) {	
				parichae[i][j] = array[next_row][next_col];
				sum = sum + parichae[i][j];
				next_col++;
			}
			next_row++;
		}
		
		if(sum > max) max = sum;
		
	}
	
	
	
}