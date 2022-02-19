package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] array = new int[9];
		int max = 0;
		
		for(int i = 0; i<9; i++) {
			array[i] = in.nextInt();
		}
		
		for(int i = 0; i<array.length; i++) {
			if(array[i]>max) max = array[i];
		}
		
		System.out.println(max);
		for(int i = 0; i<array.length; i++) {
			if(array[i]==max) System.out.println(i+1);
		}
		
	}
	
}
	 
