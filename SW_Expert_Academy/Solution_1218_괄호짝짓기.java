package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
//메모리 : 19,412kb
//시간 : 105ms

class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int result = 1;;
			int N = Integer.parseInt(br.readLine());
			char[] array = br.readLine().toCharArray();
			Stack<Character> st = new Stack<>();
			
			for(int i = 0; i<N; i++) {
				if(array[i] == '>' || array[i] == ')' || array[i] == '}' || array[i] == ']') {
					if(st.isEmpty()) break;
					char c = st.pop();
					
					switch(array[i]) {
					case '>' : 
						if(c == '<') break;
						else result = 0;
					case ')' : 
						if(c == '(') break;
						else result = 0;				
					case '}' : 
						if(c == '{') break;
						else result = 0;		
					case ']' : 
						if(c == '[') break;
						else result = 0;
					}
				}
				else {
					st.push(array[i]);
				}
			}
			System.out.println("#"+test_case + " " + result);
		}
	}
}
