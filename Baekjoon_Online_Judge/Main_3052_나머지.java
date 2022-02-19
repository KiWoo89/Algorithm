package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] array = new int[42];
		
		for(int i = 0; i<10; i++) {
			int n = in.nextInt();
			int nam = n % 42;
			array[nam]++; 
		}
		
		int count = 0;
		for(int i = 0; i<array.length; i++) {
			if(array[i]>=1) count++;
		}
		System.out.println(count);
		
	}
	
}
	 
