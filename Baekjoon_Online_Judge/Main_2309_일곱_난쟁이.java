package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//메모리 : 
//시간 : 

//9C7의 조합의 합 문제
public class Algo {
	static int[] heights;
	static int[] isSelected;
	static int FLAG = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		isSelected = new int[7];
		heights = new int[9];
		for(int i = 0; i<9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		
		getNanjangyee(0,0,0);
		
	}
	
	public static void getNanjangyee(int cnt, int start, int sum) {
		if(FLAG==1) return;
		if(cnt==7) {
			if(sum==100) {
				FLAG = 1;
				Arrays.sort(isSelected);
				for(int i : isSelected) {
					System.out.println(i);
				}
				return;
			}
		}
		else if(cnt>7) return;
		
		for(int i = start; i<9; i++) {
			if(cnt==7) return;
			isSelected[cnt] = heights[i];
			getNanjangyee(cnt+1, i+1, sum+isSelected[cnt]);
			
		}
	}
}