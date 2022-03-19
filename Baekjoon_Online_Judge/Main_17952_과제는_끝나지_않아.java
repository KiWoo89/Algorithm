package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *  199,368 KB
 *  1028 ms
 *  풀이방법 : 스택 이용
 */ 

public class IM {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<>();
		int score = 0;
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			if(Integer.parseInt(st.nextToken()) == 1) {	//과제가 들어왔다면, 바로 과제 시작하니 남은시간 1감소 후 push
				int[] n = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1};
				if(n[1] == 0) {
					score = score + n[0];
					continue;
				}
				else {					
					stack.push(n);
				}
			}
			else {	//과제가 안들어왔다면,
				int[] n = null;
				if(stack.size() != 0) {		//스택 비어있는지 체크				
					n = stack.pop();
				}
				else {			//스택 비어있으면 continue
					continue;
				}
				n[1]--;
				if(n[1] == 0) {	//과제가 완료되었다면
					score = score + n[0];
					continue;
				}
				else {		//과제가 아직 남았다면
					stack.push(n);
				}
			}
			
		}
		
		System.out.println(score);
	}

}
