package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 11,520 KB
//시간 : 76 ms

//접근 방법 : 부분 집합의 생성 방법 중 하나인 바이너리 카운팅 방법을 사용해서 신맛과 쓴맛의 차이를 구했다.

public class Algo {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //원소의 개수
		int array[][] = new int[N][2];	//[][0]은 신맛 [][1]은 쓴맛
		int min_diff = Integer.MAX_VALUE;	//가장 적은 차이값 저장
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i< (1<<N); i++) {	//부분집합의 개수 2^N 만큼 반복. 공집합을 제외하기 위해서 i는 1부터 시작
			int sum_sin = 1;	//신맛끼리 곱셈
			int sum_ssen = 0;	//쓴맛끼리 덧셈
			for(int j = 0; j<N; j++) {		//원소의 개수 만큼 반복
				if((i & (1<<j)) != 0) {
					sum_sin = sum_sin * array[j][0];
					sum_ssen = sum_ssen + array[j][1];
					
				}	
			}
			int diff = Math.abs(sum_sin - sum_ssen);
			if(diff < min_diff) min_diff = diff;
			
		}
		
		System.out.println(min_diff);		
	}//main end
	
	
	
	
	
}