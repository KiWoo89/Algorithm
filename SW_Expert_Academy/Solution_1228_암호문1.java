package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 18,660kb
// 108ms

class Main1 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int count = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "I");
			for(int i = 0; i<count; i++) {
				String s = st.nextToken();	// I로 나눈 명령어 저장
				s = s.substring(1,s.length()-1);
				
				StringTokenizer st2 = new StringTokenizer(s, " ");
				int index = Integer.parseInt(st2.nextToken());
				int n = Integer.parseInt(st2.nextToken());
				String str[] = new String[n]; //하나의 명령어를 공백 기준으로 분리
				for(int j = 0; j<n; j++) {
					str[j] = st2.nextToken();
				}
				for(int j = n-1; j>=0; j--) {
					list.add(index, Integer.parseInt(str[j]));
				}
			}
			
			System.out.print("#"+test_case + " ");
			for(int i = 0; i<10; i++) {
				System.out.print(list.get(i) + " ");
			}		
		}
		
	}
}
