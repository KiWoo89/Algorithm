package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//메모리 : 11,640 KB
//시간 : 76 ms
//풀이 아이디어 - 가로로 자르는 선과 세로로 자르는 선을 따로 배열에 저장한다. 배열일 오름차순으로 정렬하고, 0부터 X까지, 
//0부터 Y까지 중에 차이 값이 가장 큰 경우를 X,Y 각각 구한다. 차이 값이 가장 큰 값끼리 곱하면 가장 큰 면적이 구해진다.

public class test {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int X = Integer.parseInt(st.nextToken());	//가로
		int Y = Integer.parseInt(st.nextToken());	//세로
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list_col = new ArrayList<>();	//가로
		ArrayList<Integer> list_row = new ArrayList<>();	//세로
		for(int i = 0; i<N; i++) {		//입력을 받아서 0으로 시작하면 가로를 자르는 list_row에, 1이면 세로를 자르는 list_col에 저장
			st = new StringTokenizer(br.readLine(), " ");
			if(Integer.parseInt(st.nextToken()) == 0) {
				list_row.add(Integer.parseInt(st.nextToken()));
			}
			else {
				list_col.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//0부터 처음 입력까지, 마지막입력부터 최대 크기까지의 넓이를 구하기 위해 add
		list_row.add(0,0);
		list_row.add(Y);
		list_col.add(0,0);
		list_col.add(X);
		
		//X축,Y축에서 차이의 크기가 가장 큰 수를 저장할 변수 선언 밑, 오름차순 정렬
		int max_diff_x = 0;
		int max_diff_y = 0;
		Collections.sort(list_row);
		Collections.sort(list_col);
		
		for(int i = 0; i<list_col.size()-1; i++) {	//오름차순 순서대로 차이의 크기가 큰 값 저장
			int num = Math.abs(list_col.get(i) - list_col.get(i+1));
			if(max_diff_x < num) max_diff_x = num;
		}
		for(int i = 0; i<list_row.size()-1; i++) {
			int num = Math.abs(list_row.get(i) - list_row.get(i+1));
			if(max_diff_y < num) max_diff_y = num;
		}
		
		System.out.println(max_diff_x * max_diff_y);	//차이의 크기가 큰 값 끼리 곱해서 큰 넓이 구함.	
	}

}
