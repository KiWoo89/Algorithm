package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/* 메모리 : 11,612 KB
 * 시간 : 92 ms
 * 풀이 방법 : 남자와 여자일때 구분하여 처리했고, 
 * 
 */

public class Algo {
	static int N;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] array = new int[N+1];	//0번인덱스안씀
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 1; i<=N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int peo = Integer.parseInt(br.readLine());	//사람의 수
		
		for(int i = 0; i<peo; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());	//성별
			int index = Integer.parseInt(st.nextToken());	//인덱스번호
			
			if(num == 1) {			//남자라면
				for(int j = index; j<=N; j++) {
					if(j%index == 0) array[j] = (array[j] + 1)%2;	//배수 마다 상태 변경
				}
			}
			
			else {			//여자라면
				int start = index;	//초기 시작 인덱스
				int end = index;
				
				while(true) {
					start--;	//좌우 인덱스 설정
					end++;
					if(rangeCheck(start) && rangeCheck(end) && array[start] == array[end]) {	//상태가 같으면 다시반복
						continue;
					}
					else {		//서로 상태가 같지 않으면 그 전 상태의 좌우 인덱스로 설정 조정
						start++;
						end--;
						for(int j = start; j<=end; j++) {	//좌우가 같은 인덱스들의 상태 변경
							array[j] = (array[j] + 1)%2;
						}
						break;
					}
				}	
			}
			
		}

		for(int i = 1; i<=N; i++) {
			System.out.print(array[i] + " ");
			if(i%20 == 0) System.out.println();
		}
		

	}
	
	public static boolean rangeCheck(int r) {
		if(r >= 1 && r<=N) return true;
		else return false;
	}
	
}
