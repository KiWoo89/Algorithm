package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 메모리 : 40,836 KB
 * 시간 : 256 ms
 * 풀이 방법 : 조합과 bfs활용하여 품. 바이러스를 M만큼 조합으로 초기에 배치한 후, 선택되지 않은 비활성화 바이러스는 빈칸으로 두고 해야하는 줄
 * 알고 삽질 엄청하다가 삽이 부러짐... 깨닫고 비활성화 바이러스는 3으로 맵에 표시한 후 bfs할 때 따로 if문으로 처리해줌
 * 
 */

public class Algo {
	static class Virus{
		int r;
		int c;
		int t;	//몇초 만에 감염되었는지
		
		Virus(int r, int c, int t){
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	
	static int[] d_row = {-1, 0, 1, 0};	//위 오른쪽 아래 왼쪽
	static int[] d_col = {0, 1, 0, -1};	
	static int[][] array;	//연구소 맵
	static int[][] copy_array;	//연구소 맵 복사
	static int N,M;
	static int blank = 0;	//초기 맵에 빈 칸인 곳(0)
	static ArrayList<Virus> virus = new ArrayList<>();	//바이러스 위치 저장
	
	static int[] numbers;	//조합에서 선택한 바이러스 index 저장
	static int min_time = Integer.MAX_VALUE;	//최소 시간 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	//바이러스 개수
		
		array = new int[N][N];
		copy_array = new int[N][N];
		numbers = new int[M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if(array[i][j] == 0) blank++;	//만약 빈 공간이라면 count
				else if(array[i][j] == 2) {		//만약 바이러스가 있는 공간이면 해당 좌표 저장
					virus.add(new Virus(i,j,0));
				}
			}
		}
		
		if(blank == 0) System.out.println(0);	//빈칸이 하나도 없으면 0출력 후 종료
		else {	//빈칸이 1개라도 있으면 조합 생성 후 bfs
			comb(0, 0);
			if(min_time == Integer.MAX_VALUE) min_time = -1;	//bfs 다 돌았는데, 모든 영역 감염 못시켜서 최소 시간 갱신 못했다면 -1
			
			System.out.println(min_time);
		}
	}
	
	public static void comb(int cnt, int start) {	//초기 바이러스 위치 조합 경우의 수 구하기
		if(cnt == M) {	// 초기 바이러스 수 만큼 뽑았다면 bfs 돌기
			bfs();		
			return;
		}	
		
		for(int i = start; i<virus.size(); i++) {
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}	
	}
	
	public static void bfs() {
		copyArray();	//기존 연구소 맵 복사
		
		Queue<Virus> qu = new LinkedList<>();
		
		int idx = 0;	//조합에 사용된 인덱스 접근 int
		for(int i = 0; i<virus.size(); i++) {
			if(idx != M && i == numbers[idx]) {		//조합에 포함된 바이러스 인 경우
				qu.add(virus.get(numbers[idx]));	//큐에 넣기
				idx++;		
			}
			else {		//조합에 포함되지 않은 바이러스 인 경우
				Virus v = virus.get(i);
				copy_array[v.r][v.c] = 3;	//비활성화 바이러스 3으로 변경
			}
		}
		
		int t_blank = blank;	//감염시켜야 하는 공간의 수
		Virus v = null;
		
		while(!qu.isEmpty()) {
			v = qu.poll();
			
			for(int i = 0; i<4; i++) {
				int next_row = v.r + d_row[i];
				int next_col = v.c + d_col[i];
					
				if(!rangeCheck(next_row, next_col)) continue;	//범위 넘어가면 continue
				if(copy_array[next_row][next_col] == 1 || copy_array[next_row][next_col] == 2) {//다음 칸이 벽(1), 감염(2)이면
					continue;
				}
				
				if(copy_array[next_row][next_col] == 0) {	//다음 칸이 빈칸이면
					t_blank--;
					if(t_blank == 0) {	//감염시켜야 하는 공간의 수 만큼 감염시켰다면
						min_time = Math.min(min_time, v.t + 1);	//지금까지 소모한 시간 초와 최소 값 비교 후 업데이트
						return;
					}
					copy_array[next_row][next_col] = 2;		//감염시킬 공간이 더 남았다면 해당 공간 감염
					qu.offer(new Virus(next_row, next_col, v.t + 1));	//바이러스 queue에 add
				}
				else if(copy_array[next_row][next_col] == 3) {	// 비활성화 바이러스라면
					copy_array[next_row][next_col] = 2;		//감염시킬 공간이 더 남았다면 해당 공간 감염
					qu.offer(new Virus(next_row, next_col, v.t + 1));	//바이러스 queue에 add
				}
			}
		}	
	}
	
	public static void copyArray() {	//맵 복사하는 메소드
		for(int i = 0; i<N; i++) {
			System.arraycopy(array[i], 0, copy_array[i], 0, copy_array[i].length);
		}
	}
	
	public static boolean rangeCheck(int r, int c) {	//범위 체크 메소드
		if(r>=0 && r<N && c>=0 && c<N) return true;
		else return false;
	}
}
