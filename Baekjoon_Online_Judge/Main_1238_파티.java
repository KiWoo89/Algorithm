package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
	
//메모리 : 22,748 KB
//시간 : 188 ms
//풀이방법 : 다익스트라 이용. X를 기준으로 다시 각각의 마을로 돌아가는 비용을 distance배열에 저장하고, 간선을 뒤집어서 저장한 r_array배열에 대해서 다시 다익스트라를
//적용해서 각각의 마을에서 X마을로 가는 비용을 r_distance배열에 저장함. 그 후 distance 배열과 r_distance배열의 합이 가장 큰 노드의 값을 구함.

class Solution {
	static int N, M, X;
	static int[][] array;
	static int[][] r_array; // 반대로 저장한 배열

	static class Vertex implements Comparable<Vertex> {
		int no, minDistance; // 정점 번호, 출발지에서 자신으로의 최소비용

		public Vertex(int no, int minDistance) {
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 학생 수 (정점 수)
		M = Integer.parseInt(st.nextToken()); // 단방향 도로 수 (간선 수)
		X = Integer.parseInt(st.nextToken()); // 파티 장소 (파티 정점)

		array = new int[N + 1][N + 1]; // 1번 인덱스부터 사용
		r_array = new int[N + 1][N + 1]; // 1번 인덱스부터 사용

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			array[r][c] = w;
			r_array[c][r] = w;

		}

//    	for(int in[] : array) {
//    		for(int i : in) {
//    			System.out.print(i + " ");
//    		}
//    		System.out.println();
//    	}
//    	System.out.println();
//    	for(int in[] : r_array) {
//    		for(int i : in) {
//    			System.out.print(i + " ");
//    		}
//    		System.out.println();
//    	}

		// X마을에서 각각의 마을로 돌아가는 시간
		int[] distance = new int[N + 1]; // 출발지에서 자신으로 오는 최소 비용
		boolean[] visited = new boolean[N + 1]; // 최소 비용 확정 여부
		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();

		Arrays.fill(distance, Integer.MAX_VALUE); // 못가는 애들이 0으로 표시되어 있기 때문에 구분하기 위해서 모드 최대값으로 채워넣음
		distance[X] = 0; // 시작점 0으로
		pQueue.offer(new Vertex(X, distance[X]));

		while (!pQueue.isEmpty()) {
			// 단계 1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
			Vertex current = pQueue.poll();

			if (visited[current.no])
				continue; // 전에 같은 정점에 대해서 더 최소 비용으로 저장한 경우에는 continue해야 한다.

			visited[current.no] = true;

			// 단계 2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소 비용을 고려
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && array[current.no][j] != 0
						&& distance[j] > distance[current.no] + array[current.no][j]) { // 더 유리하게 된 경우
					distance[j] = distance[current.no] + array[current.no][j];
					pQueue.offer(new Vertex(j, distance[j]));
				}
			}

		}

//		System.out.println(Arrays.toString(distance));

		//각각의 마을에서 X마을로 가는 시간
		int[] r_distance = new int[N + 1]; // 출발지에서 자신으로 오는 최소 비용
		Arrays.fill(visited, false); // 최소 비용 확정 여부
		pQueue = new PriorityQueue<Vertex>();

		Arrays.fill(r_distance, Integer.MAX_VALUE); // 못가는 애들이 0으로 표시되어 있기 때문에 구분하기 위해서 모드 최대값으로 채워넣음
		r_distance[X] = 0; // 시작점 0으로
		pQueue.offer(new Vertex(X, r_distance[X]));

		while (!pQueue.isEmpty()) {
			// 단계 1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
			Vertex current = pQueue.poll();

			if (visited[current.no])
				continue; // 전에 같은 정점에 대해서 더 최소 비용으로 저장한 경우에는 continue해야 한다.

			visited[current.no] = true;

			// 단계 2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소 비용을 고려
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && r_array[current.no][j] != 0
						&& r_distance[j] > r_distance[current.no] + r_array[current.no][j]) { // 더 유리하게 된 경우
					r_distance[j] = r_distance[current.no] + r_array[current.no][j];
					pQueue.offer(new Vertex(j, r_distance[j]));
				}
			}

		}

//		System.out.println(Arrays.toString(r_distance));

		int result = -1; // 최대로 많이 걸리는 시간
		for (int i = 1; i <= N; i++) {
			if (result < distance[i] + r_distance[i]) {	//저장된 최대 시간보다 더 많은 시간이 걸리는 마을이라면 최대값 업데이트
				result = distance[i] + r_distance[i];
			}
		}

		System.out.println(result);
	}

}
