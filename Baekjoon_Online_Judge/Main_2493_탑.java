package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Building{
	int value;
	int index;
	public Building(int value, int index) {
		this.value = value;
		this.index = index;
	}
}

//메모리 171,120kb
//실행시간 : 3104ms

public class Main1 {
	
	static int N;
	static int[] result;
	static Stack<Building> stack = new Stack<>();
	static int index = 1;	// 실제 몇번째 자리 숫자인지. 1부터
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new int[N];
		int num;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		num = Integer.parseInt(st.nextToken());
		stack.push(new Building(num, index++));
		result[0] = 0;
		
		for(int i = 1; i<N; i++) {
			num = Integer.parseInt(st.nextToken());	//입력으로 들어온 변수 잠깐 저장
					
			if(stack.peek().value > num) {
				result[i] = stack.peek().index;
				stack.push(new Building(num, index++));
			}
			else if(stack.peek().value < num){
				while(!stack.isEmpty()) {
					stack.pop();
					if(stack.isEmpty()) {
						stack.push(new Building(num, index++));
						result[i] = 0;
						break;
					}
					else {
						if(stack.peek().value > num) {
							result[i] = stack.peek().index;
							stack.push(new Building(num, index++));
							break;
						}
						else {
							continue;
						}
					}
				}
			}
							
		}
		for(int i = 0; i<N; i++) {
			System.out.print(result[i] + " ");
		}
	
	}
}
