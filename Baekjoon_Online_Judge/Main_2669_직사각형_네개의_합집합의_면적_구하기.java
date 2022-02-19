package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//18:35
//메모리 : 11,472 KB
//시간 : 76 ms
//접근방법 : 100x100배열을 생성한 후 입력 받은 위치의 배열 값을 1로 변경함. 0,0은 왼쪽 아래임을 표현하기 위해 100-i로 행 값을 표현함.
//배열 표현과 넓이 계산 부분에서 이해가 되지 않아서 그냥 n1,n2,n3,n4입력 받았을 때 n3-n1만큼 행 이동, n4-n2만큼 열이동 이라고 생각했음

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] array = new int[101][101]; //0~100
		int sum = 0;
		
		for(int N = 0; N<4; N++) { //4개 사각형
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int row1 = Math.abs(100 - Integer.parseInt(st.nextToken()));	//0,0이 왼쪽 아래가 되도록 설정
			int col1 = Integer.parseInt(st.nextToken());
			int row2 = Math.abs(100 - Integer.parseInt(st.nextToken()));;
			int col2 = Integer.parseInt(st.nextToken());
			
			for(int i = row1; i>row2; i--) {
				for(int j = col1; j<col2; j++) {
					array[i][j] = 1;
				}
			}			
			
		}
		
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				sum = sum + array[i][j];
			}
		}

		System.out.println(sum);
		
	}
	
	
	
}