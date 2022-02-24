package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//메모리 : 118,604 KB
//시간 : 1072 ms
//풀이 방법 : 다익스트라 (인접리스트)	, 인접행렬은 복잡도가 너무 높아져서 해결 불가.

public class Solution1 {
	static int V, E, start;
	static ArrayList<Edge>[] lists;

	static class Edge implements Comparable<Edge> { // 간선 표시
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}		
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		start = Integer.parseInt(br.readLine());

		lists = new ArrayList[V+1]; // 정점 개수 만큼 생성

		for (int i = 1; i <=V; i++) {
			lists[i] = new ArrayList();
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			lists[from].add(new Edge(to, weight));
		}

		int[] distance = new int[V+1]; // 출발지에서 자신으로 오는 최소 비용
		boolean[] visited = new boolean[V+1]; // 최소 비용 확정 여부
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>();

		Arrays.fill(distance, Integer.MAX_VALUE); // 못가는 애들이 0으로 표시되어 있기 때문에 구분하기 위해서 모드 최대값으로 채워넣음
		distance[start] = 0; // 시작점 0으로
		pQueue.offer(new Edge(start, distance[start]));

		while (!pQueue.isEmpty()) {
			// 단계 1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
			Edge current = pQueue.poll();

			if (visited[current.to]) continue; // 전에 같은 정점에 대해서 더 최소 비용으로 저장한 경우에는 continue해야 한다.

			visited[current.to] = true;

			// 단계 2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소 비용을 고려
			for (int j = 0; j < lists[current.to].size(); j++) {
				int to = lists[current.to].get(j).to;
				int weight = lists[current.to].get(j).weight;
				
				if (!visited[to] 
						&& distance[to] > distance[current.to] + weight) { // 더 유리하게 된 경우
					distance[to] = distance[current.to] + weight;
					pQueue.offer(new Edge(to,  distance[to]));
				}
			}
		}

		for(int i = 1; i<distance.length; i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(distance[i]);
			}
		}
		
	}

}