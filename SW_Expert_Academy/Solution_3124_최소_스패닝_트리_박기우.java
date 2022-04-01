package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


//메모리 : 124,744 KB
//시간 : 1,892 ms
//풀이 방법 : MST중 크루스칼 알고리즘 사용(union-find). 가중치 합인 cost를 처음에 int로 선언하여 통과하지 못했음. 최대 간선의 수가 많고,
//가중치의 절대 값이 최대 100만이라 int 값의 범위를 넘어갈 수 있기 때문에 long으로 사용했어야 했다.

public class Solution1 {
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());	//정점 개수
			int E = Integer.parseInt(st.nextToken());	//간선 개수
			int array[][] = new int[E][3];	//[0] = from, [1] = to, [2] = weight
			
			for(int i = 0; i<E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				array[i][0] = Integer.parseInt(st.nextToken()) - 1;	//0번인덱스부터 사용하기 위해
				array[i][1] = Integer.parseInt(st.nextToken()) - 1;
				array[i][2] = Integer.parseInt(st.nextToken());
			}
			
			parent = new int[V];	//각 정점의 부모 저장
			for(int i = 0; i<V; i++) { //각 정점의 부모를 자기 자신으로 초기화
				parent[i] = i;
			}
			
			Arrays.sort(array, new Comparator<int[]>() {	//가중치 기준으로 정렬

				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[2], o2[2]);
				}			
			});
			
			long cost = 0L;	//비용
			
			for(int i = 0; i<E; i++) {
				if(find(array[i][0]) != find(array[i][1])) {	//주어진 간점에서 두 정점의 부모가 다르다면 -> union
					union(array[i][0], array[i][1]);
					cost = cost + array[i][2];			// union함으로써 해당 간선이 가진 가중치 누적 합
				}
			}
			
			sb.append("#").append(t).append(" ").append(cost).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
    }

	public static int find(int x) {
		if(parent[x] == x) return x;
		else return find(parent[x]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a>b) {
			parent[a] = b;
		}
		else {
			parent[b] = a;
		}
	}
	
	
	
}