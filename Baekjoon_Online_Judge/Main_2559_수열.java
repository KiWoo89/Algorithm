package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 21,876 KB
//시간 : 1004 ms
//풀이 아이디어 - 먼저 연속하는 요일마다의 온도의 합을 구할 때 몇번 반복해야 하는지를 고민해본 결과 N-K+1 만큼 온도의 합을 구해야했다.
//반복문을 통해 N-K+1만큼의 온도의 합을 구하도록 했다.

public class test {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());	//날짜별 온도 입력 개수
		int K = Integer.parseInt(st.nextToken());	//연속하는 요일 입력
		
		int[] temp = new int[N];	//날짜별 온도 개수 만큼 배열 생성
		
		st = new StringTokenizer(br.readLine(), " ");	//날짜별 온도 저장
		for(int i = 0; i<N; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
		}
		
		int index = N - K + 1;	//연속되는 온도의 합을 구해야 하는 max 인덱스 값
		
		int hap;	//연속하는 날마다 저장할 온도의 합
		int max = Integer.MIN_VALUE;	//온도를 최소로 잡기
		for(int i = 0; i<index; i++) {
			hap = 0;
			for(int j = i; j<i+K; j++) {	//연속하는 날마다 온도의 합 반복
				hap = hap + temp[j];
			}
			if(hap > max) max = hap;	//만약 온도의 합이 max보다 크다면 max는 합으로 변경
		}

		System.out.println(max);	
	}		
	
}
