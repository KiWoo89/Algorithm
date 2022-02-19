package test;

import java.util.Scanner;

public class test {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] array = new int[21];
		array[0] = 0;
		array[1] = 1;
		
		for(int i = 0; i<num-1; i++) {
			array[i+2] = array[i] + array[i+1];
		}
		System.out.println(array[num]);
	
		
	}
	
}
	
