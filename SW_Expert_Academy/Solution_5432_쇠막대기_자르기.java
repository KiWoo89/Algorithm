package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//시간복잡도 : 
//메모리 : 35,876kb
//시간 : 186ms

public class test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			char[] input = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			int sum = 0;
			
			for(int i = 0; i< input.length; i++) {
				if(input[i] == '(') {
					stack.push(input[i]);
				}
				else {	//')'
					if(input[i-1]==')') {
						sum++;
						stack.pop();
						continue;
					}
					stack.pop();
					sum = sum + stack.size();
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}	
}
