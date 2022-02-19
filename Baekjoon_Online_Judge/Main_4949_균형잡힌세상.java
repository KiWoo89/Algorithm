package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//메모리 : 17,852kb
//시간 : 332ms

public class Main {
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
	to:	while(true) {
			char[] str = br.readLine().toCharArray();
			if(str.length==1 && str[0]=='.') break;
			Stack<Character> st = new Stack<>();
			
			for(int i = 0; i<str.length; i++) {
				if(str[i]=='.') break;
				
				if(str[i] == '(' || str[i] == '[') {
					st.push(str[i]);
				}
				else if(str[i] == ')' || str[i] == ']') {
					if(st.isEmpty()) {
						System.out.println("no");
						continue to;
					}
					char ch = st.pop();
					if(ch=='(' && str[i]==')') continue;
					else if(ch=='[' && str[i]==']') continue;
					else {
						System.out.println("no");
						continue to;
					}
				}
			}
			if(!st.isEmpty()) System.out.println("no");
			else System.out.println("yes");
		}
		
	
	
	}
	
}