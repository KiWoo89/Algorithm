package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 11,564 KB
//시간 : 76 ms

//접근 방법 : 부분 집합의 생성 방법 중 하나인 재귀 방법으로 부분집합을 생성하고, 신맛과 쓴맛의 차이를 구했다.
// 바이너리 카운팅방법과 메모리 사용양이 약 44KB덜 썼지만, 실행 시간은 똑같았다.

public class Algo {
	static int[][] array;
	static int N;
	static int min_diff = Integer.MAX_VALUE;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N];	//선택한 원소 체크
		
		array = new int[N][2];	//0번인덱스는 신맛, 1번인덱스는 쓴맛
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		taste(0);
		
		System.out.println(min_diff);
		
	}//main end
	
	
	public static void taste(int cnt) {
		if(cnt > N) return;
		if(cnt==N) {	//원소의 수 만큼 다 방문해서 선택 비선택을 마쳤다면,
			int sum_sin = 1;
			int sum_ssen = 0;
			int check = 0;	//재료를 선택했는지 안했는지 체크
			for(int i = 0; i<N; i++) {	//원소 수 만큼 돌면서 해당 원소 선택했는지 체크, 선택했으면 합 누적
				if(isSelected[i]) {
					sum_sin = sum_sin * array[i][0];
					sum_ssen = sum_ssen + array[i][1];
					check=1;
				}
				
			}
			if(check == 0) return;	//재료 선택을 아무것도 안했다면 리턴
			int diff = Math.abs(sum_sin - sum_ssen);
			if(diff < min_diff) min_diff = diff;	//현재의 min값 보다 더 작다면 change
			return;
		}
		
		
		isSelected[cnt] = true;		//해당 원소 선택
		taste(cnt+1);
		isSelected[cnt] = false;	//해당 원소 비선택
		taste(cnt+1);
	}
	
	
}