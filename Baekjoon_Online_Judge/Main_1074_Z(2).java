package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 13,048 KB
//시간 : 84 ms
//풀이 아이디어 - 분할 정복 이용.	분할정복 이용해서 총 N번만큼만 재귀 호출하면 되도록 구현. %로 나누는 것은 점점 작아진
//사각형 안에서 또다른 문제를 푸는 것이기 때문에 선택한 방향의 사각형을 0,0으로 시작하도록 나누는 것임

public class test {
	static int N, R, C;
	static int count = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	//2^N * 2^N 크기의 배열
		R = Integer.parseInt(st.nextToken());	//찾고자 하는 열
		C = Integer.parseInt(st.nextToken());	//찾고자 하는 행
		
		z_search(N, 0, 0, R, C);
	}
	
	public static void z_search(int n, int r, int c, int r1, int c1) {
		if(r == R && c == C) {
			System.out.println(count);
			System.exit(0);
		}
		
		if(n==1) {		
			check(r, c);	//왼쪽 위
			check(r, c+1);	//오른쪽 위
			check(r+1, c);	//왼쪽 아래
			check(r+1, c+1);	//오른쪽 아래
			return;
		}
		
		int mid_row = (int)Math.pow(2, n) / 2;			//중간 행 값
		int mid_col = (int)Math.pow(2, n) / 2;			//중간 열 값
		int sum_count = (int)Math.pow(2, 2 * (n - 1));	//더해줄 카운트 수
		
		int dir=0; //0 = 왼쪽 위, 1 = 오른쪽 위, 2 = 왼쪽 아래, 3 = 오른쪽 아래
		if(r1 < mid_row && c1 < mid_col) dir = 0;
		else if(r1 < mid_row && c1 >= mid_col) dir = 1;
		else if(r1 >= mid_row && c1 < mid_col) dir = 2;
		else if(r1 >= mid_row && c1 >= mid_col) dir = 3;
			
		r1 %= mid_row;
		c1 %= mid_row;
		
		switch(dir) {
			case 0:
					z_search(n-1, r, c, r1, c1);
					break;
			case 1:
					count = count + sum_count; 
					z_search(n-1, r, c + mid_col, r1, c1);
					break;
			case 2:
					count = count + (sum_count*2); 
					z_search(n-1, r + mid_row, c, r1, c1);
					break;
			case 3:
					count = count + (sum_count*3); 
					z_search(n-1, r + mid_row, c + mid_col, r1, c1);
					break;
		}
	}
	
	public static void check(int r, int c) {
		if(r==R && c==C) {
			System.out.println(count);
			System.exit(0);
		}
		else {
			count++;
			return;
		}		
	}
}
