package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간복잡도 : 100N -> O(N)
//메모리 11,612kb
//시간 80ms

public class test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int	N = Integer.parseInt(br.readLine());
		int[][] array = new int[100][100];
		int result = 100*N;
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int X = x; X<x+10; X++) {
				for(int Y = y; Y<y+10; Y++) {
					if(array[X][Y] == 0){
						array[X][Y] = 1;
					}
					else {
						result = result - 1;
					}
				}
			}
		}
		
		System.out.println(result);
	}	

}
