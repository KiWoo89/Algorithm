package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 33,012 KB
//시간 : 212 ms
//풀이방법 : 테스트케이스 10개 합쳐서 20초니까 테스트케이스 1개에 2초면, 단순 반복으로 짜도 될 것 같았다. 모든 배열을 각각 방문하여 1칸씩 이동시킨다.
//이동시키다가 N극 S극이 마주치는 상황이 되면 더이상 이동을 하지 않게하고, 끝부분에 닿으면 떨어지므로 해당 자성체를 지우고 0을 저장했다.
//총 100번 이동시킨 후, 이동이 끝난 배열에서 열단위로 탐색하여 연속하여 1,2 또는 2,1인 경우를 찾아서 교착상태를 찾았다.

public class Solution {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] array = new int[N][N];
			
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int k = 0; k<100; k++) {	//끝단에 있을때 떨어지는 것을 확인하려면 최대 100번 반복해야함.
				
				for(int i = 0; i<N; i++) {
					for(int j = 0; j<N; j++) {
						
						if(array[i][j] == 1) {					//N극(1)인 경우
							if(i == N - 1) array[i][j] = 0;		//N극이 배열 끝인 S극이 다음에 있을 때 떨어지니까 0으로 변경
							else if(array[i+1][j] == 2) continue;	//N극과 S극이 서로 충돌한 경우(교착 상태)
							else if(i+1 < N) {					//다음 칸에 아무것도 없다면 이동
								array[i][j] = 0;
								array[i+1][j] = 1;
							}
						}
						
						else if(array[i][j] == 2) {				//S극(2)인 경우
							if(i == 0) array[i][j] = 0;			//S극이 배열 끝인 N극이 다음에 있을 때 떨어지니까 0으로 변경
							else if(array[i-1][j] == 1) continue;	//N극과 S극이 서로 충돌한 경우(교착 상태)
							else if(i > 0) {					//다음 칸에 아무것도 없다면 이동
								array[i][j] = 0;
								array[i-1][j] = 2;
							}
						}
					}
				}
				
			}
			
			int deadlock = 0;	//교착 상태 카운트
			int col = 0;	//컬러 저장. 1==빨강(N), 2==파랑(S)
			for(int j = 0; j<N; j++) {
				for(int i = 0; i<N; i++) {
					col = array[i][j];
					if(col == 1 && array[i+1][j] == 2){	//현재 컬러가 빨간색인데, 다음 컬러가 파란색이면 데드락상태
						deadlock++;
						i++;				//다음 컬러를 확인했으니 그 다음 컬러로 넘어가기 위해서 1증가
						continue;
					}
					else if(col == 2 && array[i+1][j] == 1){	//현재 컬러가 파란색인데, 다음 컬러가 빨간색이면 데드락상태
						deadlock++;
						i++;				//다음 컬러를 확인했으니 그 다음 컬러로 넘어가기 위해서 1증가
						continue;
					}
					else continue;	//교착상태가 아니면 continue
					
				}
			}
			
			sb.append("#").append(t).append(" ").append(deadlock).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
		
}
