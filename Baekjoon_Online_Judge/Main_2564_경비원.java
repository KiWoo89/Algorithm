package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리 : 11,576 KB
//시간 : 80 ms
//풀이 아이디어 - if-else if-else 문으로 모든 경우마다 분기하여 처리 

public class test {
	static int R,C;
	static int place[][];	//상점들 위치 저장 [0]가로 위치, [1] = 세로위치
	static int x,y;	//동근 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	//가로 길이
		C = Integer.parseInt(st.nextToken());	//세로 길이
		
		int N = Integer.parseInt(br.readLine());
		place = new int[N+1][3];	//동근이 위치까지 저장하기 위해 +1.	[2] = 동서남북 방향
		
		for(int i = 0; i<=N; i++) {	//상점들 위치 저장	
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			if(dir == 1) { // 북
				place[i][0] = dis;
				place[i][1] = C;
				place[i][2] = dir;
			}
			else if(dir == 2) {	//남
				place[i][0] = dis;
				place[i][1] = 0;
				place[i][2] = dir;
			}
			else if(dir == 3) {	//서
				place[i][0] = 0;
				place[i][1] = C-dis;
				place[i][2] = dir;
			}
			else {	//동
				place[i][0] = R;
				place[i][1] = C-dis;
				place[i][2] = dir;
			}
		}

		int min_result = 0;
		int x = place[N][0];	//동근이 가로 위치
		int y = place[N][1];	//동근이 세로 위치
		int dir = place[N][2];	//동근이 방향
		
		for(int i = 0; i<N; i++) {
			//place[i][0]	목표 상점의 가로위치
			//place[i][1]	목표 상점의 세로 위치
			//place[i][2]	목표 상점의 방향
			
			if(dir == 1) {	//동근이 방향 북쪽
				if(place[i][2] == 1) {	//목표 상점 방향 북쪽
					min_result = min_result + Math.abs(x-place[i][0]);	
				}
				else if(place[i][2] == 2) {	//목표 상점 방향 남쪽
					min_result = min_result + Math.min( (x+C+place[i][0]) , (R-x+C+R-place[i][0]) );
				}
				else if(place[i][2] == 3) {	//목표 상점 방향 서쪽
					min_result = min_result + Math.min( (x+C-place[i][1]), (R-x + C + R + place[i][1] ) );
				}
				else {	//목표 상점 방향 동쪽
					min_result = min_result + Math.min( (x + C + R + place[i][1] ), (R-x + C-place[i][1]) );
				}			
			}
			
			else if(dir == 2) {	//동근이 방향 남쪽
				if(place[i][2] == 1) {	//목표 상점 방향 북쪽
					min_result = min_result + Math.min( (x + C + place[i][0]), (R-x + C + R-place[i][0]) );
				}
				else if(place[i][2] == 2) {	//목표 상점 방향 남쪽
					min_result = min_result + Math.abs(x-place[i][0]);	
				}
				else if(place[i][2] == 3) {	//목표 상점 방향 서쪽
					min_result = min_result + Math.min(x + place[i][1], R-x + C + R + C-place[i][1]);
				}
				else {	//목표 상점 방향 동쪽
					min_result = min_result + Math.min(x + C + R + C-place[i][1] , R-x + place[i][1]);
				}
			}
			
			else if(dir == 3) {	//동근이 방향 서쪽
				if(place[i][2] == 1) {	//목표 상점 방향 북쪽
					min_result = min_result + Math.min( (x+C-place[i][1]), (R-x + C + R + place[i][1] ) );
				}
				else if(place[i][2] == 2) {	//목표 상점 방향 남쪽
					min_result = min_result + Math.min(x + place[i][1], R-x + C + R + C-place[i][1]);
				}
				else if(place[i][2] == 3) {	//목표 상점 방향 서쪽
					min_result = min_result + Math.abs(y-place[i][1]);	
				}
				else {	//목표 상점 방향 동쪽
					min_result = min_result + Math.min(C-y + R + C-place[i][1] , y + R + place[i][1]);
				}
			}
			
			else {	//동근이 방향 동쪽
				if(place[i][2] == 1) {	//목표 상점 방향 북쪽
					min_result = min_result + Math.min( C-y + R-place[i][0], y + R + C + place[i][0] );
				}
				else if(place[i][2] == 2) {	//목표 상점 방향 남쪽
					min_result = min_result + Math.min(y + R-place[i][0] , C-y + R + C + place[i][0]);
				}
				else if(place[i][2] == 3) {	//목표 상점 방향 서쪽
					min_result = min_result + Math.min(y + R + place[i][1] , C-y + R + C-place[i][1]);
				}
				else {	//목표 상점 방향 동쪽
					min_result = min_result + Math.abs(y-place[i][1]);
				}
			}

		}
		
		System.out.println(min_result);
		
	}
	
	
	
}
