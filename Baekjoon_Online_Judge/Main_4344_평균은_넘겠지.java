package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		
		for(int t = 0; t<TC; t++) {
			int num = in.nextInt();
			int[] array = new int[num];
			int sum = 0;
			for(int i = 0; i<array.length; i++) {
				array[i] = in.nextInt();
				sum = sum + array[i];
			}
			double avg = (double)sum / array.length;
			
			int count = 0;
			for(int i = 0; i<array.length; i++) {
				if(array[i]>avg) count++;
			}
			
			double result = count / (double)array.length;
			System.out.printf("%.3f", result*100);
			System.out.println("%");
			
			
			
		}
		
		
		
	}
	
}
	 
