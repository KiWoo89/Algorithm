package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//memory : 20,884kb
//run : 120ms

public class test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int count = st.countTokens();
				st.nextToken();
				char c = st.nextToken().charAt(0);
				
				if(count == 2) {	//리프 노드일 때, 숫자가 나와야 함.
					if(c-'0' >= 0) continue;	// 특수문자 - '0' 하면 음수나옴. 음수 아니면 숫자
					else if(c-'0' < 0) {		// 리프노드인데, 연산자가 나오면 유효하지 않음.
						result = 0;
						continue;
					}
				}
				else {				//가지 노드일 때 (count == 4) 연산자가 나와야함
					if(c-'0' < 0) {	//연산자 라면
						continue;
					}
					else if(c-'0' >= 0) {	//가지노드 인데 숫자가 나온다면 유효하지 않음.
						result = 0;
						continue;
					}
					
				}

			}
			System.out.println("#"+t + " " + result);
		}	
	
	}	

}
