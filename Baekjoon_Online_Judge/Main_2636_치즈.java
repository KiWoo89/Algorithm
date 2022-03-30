package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
	
//메모리 : 12,812 KB
//시간 : 108 ms
//풀이방법 : DFS활용. 처음에 문제를 다 녹기 직전에 치즈가 포함된 덩어리의 개수가 몇개인지로 이해했다. 치즈가 있는 칸을 구하면 되는 것인데 또 삽질함.
// 문제를 잘 읽자.



class Solution {
	static int[][] array;		//맵
	static boolean[][] isVisited;	//탐색하면서 방문한 위치인지 체크
	static boolean[][] cheeze;	//녹을 치즈인지 확인
	static int N,M;	//행, 열 크기
	static int start_r=-1, start_c=-1;	//탐색 시작 점
	static int[] d_row = {0, 1, 0, -1};	//오른쪽 아래 왼쪽 위
	static int[] d_col = {1, 0, -1, 0};	
	static int time, result;	//모두 녹는 시간, 한 시간 전 남은 치즈 조각의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());	//행 크기
		M = Integer.parseInt(st.nextToken());	//열 크기
		array = new int[N][M];
		
		for(int i = 0; i<N; i++) {	//맵
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				
				if(start_r == -1 && start_c == -1 && array[i][j] == 0) {	//탐색을 시작할 비어있는 첫 공간을 찾고 저장
					start_r = i;
					start_c = j;
				}
			}
		}
		
		isVisited = new boolean[N][M];
		cheeze = new boolean[N][M];
		

		while(true) {
			time++;		//시간 카운팅
			cheezeCount();	//치즈 칸들 카운팅
			Initial();	//이제 곧 녹을 치즈 false로 초기화
			dfs(start_r, start_c);	//dfs진행
			cheezeBurn();		//dfs진행하면서 표시한 곧 녹을 치즈들 녹이기
			
			
			if(!exit()) {	//모든 치즈가 다 녹지 않았다면, 다시 반복
				continue;
			}
			else break;	//모든 치즈 다 녹았다면 반복문 종료	
		}
		
		System.out.println(time);
		System.out.println(result);

	}

	public static void dfs(int r, int c) {

		isVisited[r][c] = true;		//방문 처리
		
		for(int d = 0; d<4; d++) {	//4방 탐색
			int next_r = r + d_row[d];
			int next_c = c + d_col[d];
			if(rangeCheck(next_r, next_c) && !isVisited[next_r][next_c]) {	//범위가 넘지 않고, 방문하지 않은 곳이라면
				if(array[next_r][next_c] == 1){		//만약 치즈를 만난다면
					cheeze[next_r][next_c] = true;	//이제 곧 녹을 치즈로 표시
					isVisited[next_r][next_c] = true;	//해당 치즈 방문 처리
					continue;
				}
				dfs(next_r, next_c);	//치즈가 아닌 비어있는 공간이었다면 그 지점으로부터 dfs진행
			}	
			else continue;	//범위가 넘어가거나 방문한 지점이라면 다른 방향으로 탐색
		}
	}
	
	public static boolean exit() {	//모든 치즈가 다 녹았는지 확인
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(array[i][j] == 1) return false;	//치즈가 하나라도 발견되면 모든 치즈가 녹지 않은 것
			}
		}
		return true;
	}
	
	public static void cheezeBurn() {	//dfs 한번 쭉 돌고 나서 치즈녹이는 메소드
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(cheeze[i][j]) {	//곧 녹을 치즈로 표시되어 있다면 
					array[i][j] = 0;	//녹인다.
				}
			}
		}
	}
	
	public static boolean rangeCheck(int r, int c) {	//맵의 범위 체크 
		if(r>=0 && r < N && c>=0 && c<M) return true;
		else return false;
	}
	
	public static void Initial() {		//배열 초기화 (DFS실행마다)
		for(int i = 0; i<N; i++) {
			Arrays.fill(cheeze[i], false);	//곧 녹일 치즈들 초기화
			Arrays.fill(isVisited[i], false);	//방문한 지점들 초기화
		}
	}
	
	public static void cheezeCount() {
		int cnt = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(array[i][j] == 1) {		//치즈를 만났다면
					cnt++;					//치즈 칸 카운팅
				}
			}
		}
		result = cnt;		//치즈 덩어리 저장
	}

}
