package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 23,436 KB
//시간 : 1,004 ms
//풀이 방법 : 먼저 N/2의 조합대로 재료를 고르고, N/2만큼 재료를 골랐다면 그 재료들의 시너지 합을 구한 뒤, 다른 N/2조합의 시너지
//합을 구하는 다른 재귀함수를 호출하여 각각의 합의 차이를 구하고, min값과 비교했다.

public class Solution1 {
	static int[][] array;	//시너지 저장할 배열
	static int N;		//재료의 개수
	static int[] index;	//선택한 식재료 저장 1
	static int[] index2; //선택한 식재료 저장 2
	static int min;	//테스트 케이스 별로 차이의 최소 값 저장 변수
	static int sum;	//인덱스 1에서 사용할 합
	static int sum2;	//인덱스 1에서 사용할 합
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			
			min = Integer.MAX_VALUE;	//테스트 케이스마다 최소 값 초기화
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];	//시너지 값 저장할 배열
			index = new int[N/2];	//선택한 재료 저장할 배열1
			index2 = new int[N/2];	//선택한 재료 저장할 배열2
			
			for(int i = 0; i<N; i++) {	//시너지 값 배열에 저장
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			favor(0, 0);
			System.out.println("#"+t+ " " + min);
			
		}

	}
	
	public static void favor(int cnt, int start) {	//(N)C(N/2)의 합을 구하는 메소드
		if(cnt==N/2) {	// N/2만큼 뽑았다면
			sum = 0;	//합 변수 초기화
			for(int i = 0; i<N/2; i++) {	//뽑은 N/2개 중 NC2조합을 구해서 합을 구함. [1][0], [0][1]을 구하면 되는 것이니 조합임.
				for(int j = i+1; j<N/2; j++) {
					sum = sum + array[index[i]][index[j]] + array[index[j]][index[i]];
				}
			}

			favor2(0, 0);	//N-2C2 의 합을 구하기 위해 다른 재귀함수 호출
			return;
		}
		
		for(int i = start; i<N; i++) {	//N/2만큼의 조합 뽑기
			index[cnt] = i;
			favor(cnt+1, i+1);
		}	
	}
	
	public static void favor2(int cnt, int start) {	//(N-2)C(2)의 합을 구하는 메소드
		if(cnt==N/2) {
			sum2 = 0;
			for(int i = 0; i<N/2; i++) {	//(N-2)C(2)의 합을 구한다
				for(int j = i+1; j<N/2; j++) {
					sum2 = sum2 + array[index2[i]][index2[j]] + array[index2[j]][index2[i]];
				}
			}
			
	
			if(Math.abs(sum-sum2) < min) min = Math.abs(sum-sum2);	//만약 호출되기 전 (N)C(N/2)의 합 보다 차이가 더 작다면 변경

			return;
		}
		
	to:	for(int i = start; i<N; i++) {		//(N)C(N/2) 조합 구하기
			for(int j = 0; j<index.length; j++) {
				if(index[j] == i) continue to;	//만약 앞에서 NC(N/2) 구할때 뽑았던 재료라면 CONTINUE;
			}
			index2[cnt] = i;
			favor2(cnt+1, i+1);
		}		
	}

}
