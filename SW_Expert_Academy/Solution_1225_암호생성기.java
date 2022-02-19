package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			List<Integer> list = new ArrayList<>();
			
			for(int i = 0; i<8; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
		out:while(true) {
				for(int i = 1; i<=5; i++) {
					int num = list.get(0);
					list.remove(0);
					if(num-i <= 0) {
						list.add(0);
						break out;
					}
					list.add(num-i);
				}
			}
			
			String result = "";
			for(int i = 0; i<8; i++) {
				result = result + list.get(i) + " ";
			}
			result = result.substring(0, result.length()-1);
			
			System.out.println("#"+test_case + " " + result);
		}	
	}
	
}