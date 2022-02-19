package test;

import java.util.Scanner;

//실행시간 = 130ms, 메모리 = 21,484 kb
class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			String s = sc.next();
			String a = "";
			int count = 0;
			char temp = '0';
			
			for(int i = 0; i<s.length(); i++) {
				a = a + "0";
			}
			
			if(s.charAt(0) == '1') {
				count++;
				temp = '1';
			}
			
			for(int i = 0; i<s.length(); i++) {
				if(s.charAt(i) != temp) {
					count++;
					temp = s.charAt(i);
				}
			}
			System.out.printf("#%d %d\n", test_case, count);

		}
	}
}
