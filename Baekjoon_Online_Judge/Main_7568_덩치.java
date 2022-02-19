package test;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] weight = new int[N];
		int[] height = new int[N];
		int[] count = new int[N];
		
		for(int i = 0; i<N; i++) {
			weight[i] = in.nextInt();
			height[i] = in.nextInt();
		}
		
		for(int i = 0; i<N; i++) {
			count[i] = 1;
			for(int j = 0; j<N; j++){
				if(weight[i] < weight[j] && height[i] < height[j]) {
					count[i]++;
				}
			}
		}
		
		for(int i = 0; i<count.length; i++) {
			System.out.print(count[i] + " ");
		}
		
	}
	
}
	
