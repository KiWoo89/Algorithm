package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int count = 0;
		String s;
		int n = 666;
		
		while(true) {
			s = Integer.toString(n);
			if(s.contains("666")) {
				count++;
			}
			
			if(count == num) break;
			
			n++;
			
		}
		System.out.println(n);
		
	
	}
	
}