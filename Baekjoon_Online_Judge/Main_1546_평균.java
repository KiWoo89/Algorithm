package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double[] array = new double[n];
		
		for(int i = 0; i<n; i++) {
			array[i] = in.nextInt();
		}
		
		double max = 0;
		for(int i= 0; i<n; i++) {
			if(array[i] > max) max = array[i];
		}

		double sum = 0;
		for(int i = 0; i<n; i++) {
			array[i] = (double)array[i] / max * 100;
			sum = sum + array[i];
		}
		
		System.out.println((double)sum / (double)n);
		
		
	}
	
}
	 
