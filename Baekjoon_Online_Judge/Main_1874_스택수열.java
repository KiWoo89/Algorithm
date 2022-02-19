package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//메모리 : 85,620kb
//시간 : 400ms

public class Main {
	static int N;
	static int[] su;
	static Stack<Integer> st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		su = new int[N];
		st = new Stack<>();
		for(int i = 0; i<N; i++) {
			su[i] = Integer.parseInt(br.readLine());
		}
		
		suyeol(0,1);
	}
	
	public static void suyeol(int index, int num) {
		if(index==N && st.isEmpty()) {
			System.out.println(sb.toString());
			return;
		}	
		
		if(st.isEmpty() || su[index] != st.peek()) {
			if(num>=N+1) {
				System.out.println("NO");
				return;
			}
			st.push(num);
			sb.append("+\n");
			suyeol(index, num+1);
		}
		else if(su[index] == st.peek()) {
			st.pop();
			sb.append("-\n");
			suyeol(index+1,num);
		}	
	}
	
}