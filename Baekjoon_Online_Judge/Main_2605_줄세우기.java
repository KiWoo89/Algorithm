package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//메모리 : 11,612kb
//시간 : 80ms

public class Algo {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int num = 1;	//1부터 삽입
		
		for(int i = 0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			list.add(i-n, num++);	//인덱스 - 입력으로 들어온 수의 위치로 삽입
		}//for-i_end
		
		for(int i : list) {
			System.out.print(i + " ");
		}//for-i_end
	}
	
}
