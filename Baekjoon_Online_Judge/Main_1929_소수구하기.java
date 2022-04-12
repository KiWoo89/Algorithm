package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*	메모리 : 18,188 KB
 *  시간 : 400 ms
 */ 



public class hit2 {
	static int M,N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		M = Integer.parseInt(st.nextToken());	//시작 수
		N = Integer.parseInt(st.nextToken());	//끝 수
		
	k:	for(int k = M; k<=N; k++) {
			if(k == 1) continue;	//1은 소수가 아님
			
			int sqr = (int) Math.sqrt(k);
			for(int i = 2; i<=sqr; i++) {
				if(k % i == 0) continue k;	//소수가 아니면 다음 수로 넘어가기
			}
			sb.append(k).append("\n");	//소수라면 append
		}
		
		System.out.println(sb.toString());
		
	}

}