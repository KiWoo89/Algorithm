package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num1 = in.nextInt();
		int num2 = in.nextInt();
		int num3 = in.nextInt();
		int result = num1*num2*num3;
		int[] array = new int[10];
		String s = Integer.toString(result);

		
		for(int i = 0; i<s.length(); i++) {
			array[s.charAt(i)-'0']++;
		}
		for(int i = 0; i<array.length; i++) {
			System.out.println(array[i]);
		}
		
	}
	
}
	 
