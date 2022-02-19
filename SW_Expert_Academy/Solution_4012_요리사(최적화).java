package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 22,340 KB
//시간 : 236 ms
//풀이 방법 : 먼저 N/2의 조합대로 재료를 고르고, N/2만큼 재료를 골랐다면 선택된 재료들 끼리 NC2조합의 시너지 합과 비선택된
//재료들 끼리의 NC2조합의 시너지 합을 구해서 차이를 구한다. 그 중에서 작은 값이 나올 때마다 최소 값을 갱신한다.

public class Solution1 {
	static int[][] array;	//시너지 저장할 배열
	static int N;		//재료의 개수
	static boolean[] isSelected;
	static int min;	//테스트 케이스 별로 차이의 최소 값 저장 변수

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			
			min = Integer.MAX_VALUE;	//테스트 케이스마다 최소 값 초기화
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];	//시너지 값 저장할 배열
			isSelected = new boolean[N];
			
			for(int i = 0; i<N; i++) {	//시너지 값 배열에 저장
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			favor(0, 0);
			sb.append("#").append(t).append(" ").append(min).append("\n");
			
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	public static void favor(int cnt, int start) {	//서로다른 NC(N/2) 조합의 합 차이를 구함
		if(cnt == N/2) {		//basis part
			int sum_A = 0;		//선택한 재료들의 시너지 합
			int sum_B = 0;		//비선택한 재료들의 시너지 합
			
			for(int i = 0; i<N; i++) {		//NC2조합 방식
				for(int j = i+1; j<N; j++) {
					if(isSelected[i] && isSelected[j]) {	//둘다 선택된 재료라면 시너지 합 더하기
						sum_A = sum_A + array[i][j] + array[j][i];
					}
					else if(!isSelected[i] && !isSelected[j]) {	//둘다 비선택된 재료라면 시너지 합 더하기
						sum_B = sum_B + array[i][j] + array[j][i];
					}
				}
			}
			min = Math.min(min, Math.abs(sum_A-sum_B));	//기존에 저장된 최소 값 보다 차이 값이 더 작다면 변환
			return;
		}
		
		for(int i = start; i<N; i++) {	//inductive part 
			isSelected[i] = true;		//선택
			favor(cnt+1, i+1);			//선택 후 다음 재료 보기
			isSelected[i] = false;		//선택 X 다음 재료 보기
		}		
	}
	
	
}
