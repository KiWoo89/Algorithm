package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//메모리 : 26,656 KB
//시간 : 118 ms
//풀이 아이디어 - 처음에 BFS로 접근할까 했는데, 무리의 수를 구하기 위해서는 DFS로 1번 사람에 대해서 연결된 모든 사람들을 방문하고, 
//나머지 방문처리 되지 않은 false로 된 사람부터 다시 DFS탐색을 시도하면 될 것 같았다. DFS탐색을 시작 하고 모두 마치고 나오면, 해당 사람으로부터
//연결된 모든 관계를 방문하고 오기 때문에 하나의 무리로 볼 수 있어서, DFS탐색을 끝마치면 무리의 수를 카운트 하도록 구현함.

public class test {
	static int N,M, cnt;
	static int[][] array;	//그래프 표현 배열
	static boolean Visited[];	//방문 했는지 체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 사람의 수 
			M = Integer.parseInt(st.nextToken());	// 관계의 수
			cnt = 0;	//관계 수 초기화
			
			array = new int[N+1][N+1];	//0번인덱스 안쓰기 위해서 +1
			Visited = new boolean[N+1];	//0번인덱스 안씀
			
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				array[v1][v2] = 1;
				array[v2][v1] = 1;
			}			
			
			for(int i = 1; i<=N; i++) {	//사람 수 만큼 반복.	한번 dfs탐색을 다녀오면, 해당 사람으로부터 연결된 관계 모두 반복하고 옴. 
										//false된 것은 다른 관계인 것이다.
				if(!Visited[i]) {	//만약 해당 사람이 방문 처리 되지 않았다면,
					dfs(i);		//해당 사람으로부터 dfs 탐색
					cnt++;		//탐색을 마치고 관계 덩어리 수 증가
				}
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");	
		}
		System.out.println(sb.toString());	
	}
	
	public static void dfs(int current) {
		
		Visited[current] = true;
		
		for(int i = 1; i<=N; i++) {
			if(!Visited[i] && array[current][i] != 0) {
				dfs(i);
			}

		}
	}
		
	
	
	
}
