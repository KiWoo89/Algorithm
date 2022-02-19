package test;

import java.util.Scanner;

public class Main {
	static int[][] array;
	static int count_min = 100;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();	//행
		int N = in.nextInt();	//열
		array = new int[M][N];
		
		for(int i = 0; i<M; i++) {
			String s = in.next();
			for(int j = 0; j<N; j++) {
				if(s.charAt(j)=='W')
					array[i][j] = 0;
				else {
					array[i][j] = 1;
				}
			}
		}
		
		for(int i = 0; i<=M-8; i++) {
			for(int j = 0; j<=N-8; j++) {
				check(i, j);
			}
		}
		System.out.println(count_min);
	}
	
	public static void check(int row, int col) {
		int count1 = 0;
		int count2 = 0;
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(i%2==0) {
					if(j%2==0 && array[i+row][j+col]==0) continue;
					if(j%2==1 && array[i+row][j+col]==1) continue;
					count1++;
				}
				else if(i%2==1) {
					if(j%2==0 && array[i+row][j+col]==1) continue;
					if(j%2==1 && array[i+row][j+col]==0) continue;
					count1++;
				}
			}
		}
		
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(i%2==0) {
					if(j%2==0 && array[i+row][j+col]==1) continue;
					if(j%2==1 && array[i+row][j+col]==0) continue;
					count2++;
				}
				else if(i%2==1) {
					if(j%2==0 && array[i+row][j+col]==0) continue;
					if(j%2==1 && array[i+row][j+col]==1) continue;
					count2++;
				}
			}
		}
		if(count_min > count1) count_min = count1;
		if(count_min > count2) count_min = count2;		
	}
	
	
}
	
