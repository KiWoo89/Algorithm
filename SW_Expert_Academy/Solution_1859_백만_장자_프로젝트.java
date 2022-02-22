package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 133,392 KB
//시간 : 777 ms
//풀이방법 : 물건의 매매가를 배열에 저장하면서, 가장 먼저 물건이 가장 비싼 경우의 금액을 저장해두고, 배열 맨 처음부터 반복하면서 가장비싼 금액이 아니라면
//그 물건을 구매하고, 가장 비싼 금액을 만났으면 그 값에 모두 다 판매하여 이익을 계산했다. 그 뒤에 또 물건을 살 수 있으므로, 남은 물건들 중 가장 비싼
//물건의 금액을 다시 구하고, 가장 비싼 금액을 만날때까지 또 물건을 구매한 후에 비싼 금액을 만나면 또 팔았다. 처음에 테케 10개중 7개만 맞았는데, 
//type이 int형을 넘어갈 수 있기 때문에 3문제를 틀렸던 것이다.

public class Solution {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			
			
			int N = Integer.parseInt(br.readLine());	//물건 개수
			long max = 0;	//물건 중 가장 비싼 물건
			int[] array = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<N; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, array[i]);		//제일 비싼 물건의 값 저장
			}
			
			
			long sum_cost = 0L;	//사용한 금액
			int sum_count = 0;	//구매한 물건 수 카운트
			long result = 0L;		//이익 결과
			
			for(int i = 0; i<N; i++) {
				if(array[i] != max) {
					sum_cost = sum_cost + array[i];	//가장 비싼 물건이 아니라면, 구매
					sum_count++;
				}
				else {	//가장 비싼 물건을 만났다면,
					result = result + (sum_count*max - sum_cost);	//지금까지 산 물건을 팔아버리고, 이익 계산
					sum_cost = 0;	//사용 금액, 구매한 물건 수, 가장 비싼 금액 초기화
					sum_count = 0;
					max = 0;
					for(int j = i+1; j<N; j++) {
						if(max < array[j]) max = Math.max(max, array[j]);
					}
					continue;
				}
					
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");			
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());	
	}
	
		
}
