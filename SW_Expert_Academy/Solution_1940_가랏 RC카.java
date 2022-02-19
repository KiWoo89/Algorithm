package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//메모리 : 20,032 KB
//실행시간 : 113 ms
//접근 방법 : 단순 구현. 입력으로 들어온 mode에 따라서 조건문으로 분기하여 수행함

public class Main1 {
	
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int a = 0; //가속도
			int distance = 0; //거리
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int mode = Integer.parseInt(st.nextToken());
				int value = 0;;
				if(mode != 0) value = Integer.parseInt(st.nextToken());
				
				if(mode == 0) {
					distance = distance + a;
				}
				else if(mode == 1) {
					a = a + value;
					distance = distance + a;
				}
				else if(mode == 2) {
					a = a - value;
					if(a < 0) a = 0;
					distance = distance + a;
				}
				
			}
			
			System.out.println("#"+t + " " + distance);
			
		}
			
		
	}

	
}
