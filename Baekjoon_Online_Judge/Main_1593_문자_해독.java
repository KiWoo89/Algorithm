package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 메모리 : 38,676 KB
 * 시간 : 248 ms
 * 풀이 방법 : 찾고자 하는 문자, 선택한 순열의 문자를 int[52]배열로 표현하여, 찾고자 하는 문자의 길이와 같은 수준으로 수열이 만들어지면
 * 두 int[52]배열을 서로 비교하여 사용한 문자의 수가 같은지 비교하고, 같으면 개수 카운트를 증가시킨다. 이렇게 수열을 한번 비교하고 나면
 * 슬라이딩 윈도우 기법을 사용하기 위해 수열의 첫 문자의 사용은 감소하고, 바라볼 인덱스의 수는 증가시켰다.
 */

public class Algo {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		char[] w = br.readLine().toCharArray();
		char[] s = br.readLine().toCharArray();

		int result = 0; // 개수 카운트

		int[] w_use = new int[52]; // 찾고자 하는 문자 개수 카운트
		int[] s_use = new int[52]; // 해당 순열의 문자 개수 카운트


		for (int i = 0; i < W; i++) { // 사용중인 인덱스 카운트
			if (w[i] < 'a')
				w_use[w[i] - 65]++;
			else if (w[i] >= 'a')
				w_use[w[i] - 71]++;
		}

//		for(int i = 0; i<52; i++) {
//			System.out.print(w_use[i]);
//			if(i==25) System.out.println();
//		}

		int next = 0; 
		int len = 0;
		int from = 0;
		
		for (int i = 0; i < S; i++) {
			if (s[i] < 'a') next = s[i] - 65;
			else if (s[i] >= 'a') next = s[i] - 71;
			
			len++;				//선택한 문자 수 증가
			s_use[next]++;		//사용중인 문자 인덱스 증가
			if(len == W) {
				int answer = 1;	//그대로 1이면 문자 사용이 같은 것
				for (int j = 0; j < 52; j++) { // 서로 사용한 문자가 같은지 확인
					if (s_use[j] != w_use[j]) { // 문자 사용이 다르다면
						answer = 0;				// 문자 사용이 다름을 표시
						break;
					}
				}
				if (answer == 1) result++;	//서로 사용한 문자가 같다면 카운트 개수 증가
				
				if (s[from] < 'a') s_use[s[from]-65]--;		//맨 앞 문자의 사용 감소
				else if (s[from] >= 'a') s_use[s[from]-71]--;	
				
				from++;		//맨 앞 문자의 인덱스 증가
				len--;		//선택한 문자 수 감소
			}

		}

		System.out.println(result);

	}

}
