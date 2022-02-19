package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//메모리 : 14,804 KB
//시간 : 140 ms
//풀이 아이디어 - 

public class test {
	static int max_count = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		int num = 1; //선택하는 수 (N의 절반 이하의 수를 선택하면 2차례 안에 음수 값이 나와서 끝나게 된다.)
	to:	for(int i = num; i<=N; i++) {
			list.removeAll(list);
			int count = 2;
			list.add(N);
			list.add(i);
			
			int j = 2;
			while(true) {
				int n = list.get(j-2) - list.get(j-1);
				if(n < 0) {
					if(max_count < count) {
						result.removeAll(result);
						for(int k = 0; k<list.size(); k++) {
							result.add(list.get(k));
						}
						max_count = count;
						continue to;
					}
					else {
						continue to;
					}
				}
				else {
					list.add(n);
					j++;
					count++;
				}
				
			}
			
		}
		
		System.out.println(max_count);
		StringBuilder sb = new StringBuilder();
		for(int i : result) {
			sb.append(i + " ");
		}
		System.out.println(sb.toString());
		
	}		
	
}
