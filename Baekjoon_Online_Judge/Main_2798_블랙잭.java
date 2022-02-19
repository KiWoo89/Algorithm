package test;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int top = in.nextInt();
		int[] array = new int[N];
		int max = 0;
		
		for(int i = 0; i<array.length; i++) {
			array[i]= in.nextInt();
		}
		
		for(int i = 0; i<array.length; i++) {
			for(int j = i+1; j<array.length; j++) {
				for(int k = j+1; k<array.length; k++) {
					int sum = array[i] + array[j] + array[k];
					if(sum <= top && sum > max) max = sum;
				}
			}
		}
		
		System.out.println(max);
	
		
	}
	
}
	
