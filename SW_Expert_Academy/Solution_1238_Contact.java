package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//메모리 : 19,412 KB
//시간 : 114 ms
//풀이 아이디어 - BFS접근 까지는 알았으나, 같은 너비에 대해서 어떤식으로 동시에 접근을 표현할지가 고민이 되었음. 그래서 큐에 정점을 넣을 때 해당 정점의
// 너비 값까지 함께 저장하도록 하여, 너비의 값이 가장 큰 정점 중에 큰 값의 정점을 뽑아내도록 구현함.

public class test {
	static int M,V;	//입력되는 간선, 시작 정점 저장 변수
	static int[][] array;	//인접행렬
	static boolean[] Visited;	//방문한 정점 표시
	static int max_v;			//정점 중 가장 큰 수
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t<=10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());	//입력되는 간선
			V = Integer.parseInt(st.nextToken());	//시작 정점
			
			array = new int[101][101];	//0번 인덱스 안씀. 1~100
			Visited =  new boolean[101];	//0번인덱스 안씀. 1~100
			
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 1; i<=M/2; i++) {	//인접행렬에 간선 저장
				int v1 = Integer.parseInt(st1.nextToken());
				int v2 = Integer.parseInt(st1.nextToken());
				array[v1][v2] = 1;
			}
		
			bfs(V);
			sb.append("#").append(t).append(" ").append(max_v).append("\n");
		
		}
		System.out.println(sb.toString());
	
	}
		
	
	public static void bfs(int start) {
		
		Queue<Integer[]> qu = new LinkedList<>();
		Visited[start] = true;
		qu.offer(new Integer[] {start, 0});		//[0] = 정점, [1] = 너비
		int[] depth_max_v = new int[101];	//너비 별로 가장 큰 정점 저장
		depth_max_v[0] = start;				//너비 0에서의 정점 값 저장
		
		while(!qu.isEmpty()) {		//큐가 빌때까지 반복 
			max_v = 0; 	//제일 늦게 연락 받는 사람 중 가장 숫자가 큰 사람 초기화
			
			Integer[] vertex = qu.poll();	//큐에 저장된 (정점,너비)정보 가져오기
			
			for(int i = 1; i<=100; i++) {	//숫자1~100까지 인접한 정점이 있는지 확인 
				if(!Visited[i] && array[vertex[0]][i] != 0) {	//방문한 정점이 아니고, 현재 정점과 인접한 정점이라면
					Visited[i] = true;							//방문 표시
					qu.offer(new Integer[] {i, vertex[1]+1});	//현재 정점보다 다음 너비로 추가
					max_v = Math.max(max_v, i);					//최대 정점 값 갱신
				}
			}
			depth_max_v[vertex[1]+1] = Math.max(depth_max_v[vertex[1]+1], max_v); //너비별로 최대값 갱신
		}
		
		for(int i = 100; i>=0; i--) {	//너비가 가장 큰 정점 중 가장 큰 수 뽑아내기
			if(depth_max_v[i] != 0) {
				max_v = depth_max_v[i];
				return;
			}
		}
		
	}	
	
}
