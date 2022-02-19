package test;

import java.util.Scanner;

public class test {
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int result = 1;
		
		while(num>0) {
			result = result * num;
			num--;
		}
		System.out.println(result);
		
	}
	
}
	
