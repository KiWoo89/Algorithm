package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 19,700 KB
//시간 : 240 ms
//아이디어 : 첫번째 색종이가 들어오면 해당 위치에 1로 채우고, 두번째 색종이가 들어오면 해당 위치에 2를 채우는데 만약 겹치면 2로 덮어씌운다.
//그리고 마지막에 숫자 별로 count를 해서 넓이를 구한다.
public class Algo {
	
	public static void main(String[] args) throws IOException{
		int[][] array = new int[1001][1001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int n = 1; n<=N; n++) {	//1번 색종이부터 쭉 번호에 따라 배열에 넣기
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int i = 1000 - Integer.parseInt(st.nextToken());	//(0,0)은 왼쪽 아래이기 때문에 100인덱스부터 시작
			int j = Integer.parseInt(st.nextToken());
			int row_num = Integer.parseInt(st.nextToken());	//행 이동하는 크기
			int col_num = Integer.parseInt(st.nextToken()); //열 이동하는 크기
			
			for(int row = i; row>i-row_num; row--) {	//(0,0)은 왼쪽 아래이기 때문에 행 for문을 끝번호부터 시작				
				for(int col = j; col<j+col_num; col++) {
					array[row][col] = n;
				}
			}	//for_값 채워 넣기 end
			
		}
		
		int count[] = new int[N+1];	//1번 인덱스부터 1번색종이의 넓이를 구할 배열
		for(int i = 0; i<=1000; i++) {	//색종이 별로 넓이 구하기
			for(int j = 0; j<=1000; j++) {
				int num = array[i][j];
				count[num]++;
			}
		}	//색종이 별 넓이 체크 end
			
		for(int i = 1; i<count.length; i++) {	//1번 색종이부터 넓이 출력
			System.out.println(count[i]);
		}
		
	}
}
