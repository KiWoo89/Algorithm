package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//메모리 : 11,704kb
//시간 : 92ms

public class Main {
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
	T:	for(int t = 0; t<TC; t++) {
			char[] PS = br.readLine().toCharArray();
			Stack<Character> st = new Stack<>();
			
			for(int i = 0; i<PS.length; i++) {
				if(PS[i] == ')') {
					if(st.isEmpty()) {
						System.out.println("NO");
						continue T;
					}
					else {
						if(st.pop() != '(') continue T;
					}
				}
				else {
					st.push(PS[i]);
				}
			}
			if(!st.isEmpty()) System.out.println("NO");
			else System.out.println("YES");	
		}
	
	}
	
}