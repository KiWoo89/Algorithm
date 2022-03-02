package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 메모리 : 11,720 KB
 * 시간 : 88 ms
 * 풀이 방법 : 단순 구현. 앞에서부터 접근
 * 
 */

public class Algo {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] array = br.readLine().toCharArray();
		char[] temp = new char[array.length];
		for(int i = 0; i<array.length; i++) {
			temp[i] = 'N';			//초기 상태 N으로 초기화
		}
		
		int cnt = 0;	//변경 횟수 카운트
		
		for(int i = 0; i<array.length; i++) {
			if(array[i] != temp[i]) {	//만약 바라는 상태와 초기 상태가 다른 상태라면
				cnt++;
				for(int j = i; j<array.length; j++) {	//해당 위치 배수 위치마다 반전시켜주기
					if((j+1)%(i+1) == 0) {
						if(temp[j] == 'Y') temp[j] = 'N';
						else temp[j] = 'Y';
					}
				}
			}
		}
		
		for(int i = 0; i<array.length; i++) {	//만약 다른 부분이 있다면 -1 출력
			if(array[i] != temp[i]) cnt = -1;
		}
		
		System.out.println(cnt);
			
		
		

	}

	
}
