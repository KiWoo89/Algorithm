package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 41,704KB
//시간 : 1260ms

public class Algo {
	static int N;
	static int M;
	static int R;
	static int[] d_row = {1, 0, -1, 0}; //아래 오른쪽 위 왼쪽
	static int[] d_col = {0, 1, 0, -1};
	static int[][] array;
	static int rec; //내부 네모의 수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		array = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rec = Math.min(N, M) / 2;	//min(N,M) mod 2 = 0이라 했으니 회전 사각형은 최소의 /2이다.
		
		for(int nemo = 0; nemo<rec; nemo++) {		//회전해야 하는 횟수
				for(int r = 0; r<R; r++) {		//내부 네모의 수만큼 반복
					int row = nemo;	//시작점 초기화
					int col = nemo;
					int delta = 0; //델타 인덱스 초기화
					
					int next_row = nemo;
					int next_col = nemo;

					int temp1 = array[row][col];	//값을 저장할 임시 변수
					int temp2;	//값을 저장할 임시 변수
					
					
					while(delta<=3) {	//델타 인덱스 까지만 돌기
						next_row = row + d_row[delta];
						next_col = col + d_col[delta];
						if(next_row >=nemo && next_row < N-nemo && next_col >= nemo && next_col < M-nemo) {
							temp2 = array[next_row][next_col];
							array[next_row][next_col] = temp1;
							temp1 = temp2;
							row = next_row;
							col = next_col;
						}
						else {
							delta++;
						}
					}							
				}			
			}	
			
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	
	}
	
}
