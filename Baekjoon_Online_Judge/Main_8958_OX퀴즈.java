package test;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int TC = in.nextInt();
		
		for(int t = 0; t<TC; t++) {
			String s = in.next();
			int sum = 0;
			int count = 0;
			
			for(int i =0; i<s.length(); i++) {
				if(s.charAt(i)=='O') {
					count++;
					sum = sum + count;
				}
				else {
					count = 0;
				}
			}
			System.out.println(sum);
		}
		
		
		
	}
	
}
	 
