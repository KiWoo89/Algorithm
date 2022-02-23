package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


//메모리 : 91,040 KB
//시간 : 560 ms
//풀이방법 : 최소신장트리를 구하기 위해서 크루스칼 알고리즘 사용.

public class Solution {
	
	static class isLand implements Comparable<isLand>{
		int x;	//a섬
		int y;	//b섬
		double weight;	//가중치
		
		public isLand(int x, int y, double weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(isLand o) {
			if(this.weight < o.weight) return -1;
			else if(this.weight > o.weight) return 1;
			else return 0;
		}
	}
	
	
	static int[] parents;	//섬 별로 부모가 누구인지
	// 단위집합 생성
	public static void makeSet() {
		parents = new int[N];
		//자신의 부모노드를 자신의 값으로 세팅
		for(int i = 0; i<N; i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if(a==parents[a]) return a;	//자신과 자신의 부모가 같다는 것은 루트라는 의미이다.
		return parents[a] = findSet(parents[a]);	// path compression
	}
	
	// a,b 두 집합 합치기
	public static boolean union(int a, int b) {	//합쳐졌으면 true, 합쳐지지 않았으면 false반환
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	//루트가 같은 경우로 서로 이미 같은 집합인 경우
		
		parents[bRoot] = aRoot;	//b집합의 부모를 a로 지정하는 것이다.
		return true;
	}
	
	static int T,N;
	static double rate;		//환경 부담 세율
	static double result;	//총 환경 부담금
	static isLand[] land;	//섬 객체 저장
	static ArrayList<isLand> list;	//섬의 간선 + 가중치 표시할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());	//테스트 케이스 수
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());	//섬 개수
			land = new isLand[N];					//섬 위치 저장할 배열
			result = 0.0;							//총 환경 부담금 초기화
			list = new ArrayList<>();				
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");	//x좌표 저장
			for(int i = 0; i<N; i++) {
				land[i] = new isLand(Integer.parseInt(st.nextToken()), 0, 0);
			}
					
			st = new StringTokenizer(br.readLine(), " ");	//y좌표 저장
			for(int i = 0; i<N; i++) {
				land[i].y = Integer.parseInt(st.nextToken());
			}
			
			rate = Double.parseDouble(br.readLine());	//환경 부담 세율
			
			for(int i = 0; i<N-1; i++) {		//섬과 섬 간의 거리(가중치)를 list에 저장
				for(int j = i+1; j<N; j++) {
					long distance = (long) (Math.pow((land[i].x - land[j].x), 2) + Math.pow((land[i].y - land[j].y), 2));
					list.add(new isLand(i,j,distance));
				}
			}
			
			Collections.sort(list);		//섬의 가중치를 기준으로 오름차순 정렬
			makeSet();					//단위 집합 생성
			
			int cnt = 0;				//간선 N-1개까지 그었다면
			for(isLand land : list) {	//가중치가 낮은 순으로 가져와서
				if(union(land.x, land.y)) {		//union연산이 올바르게 진행 됐다면,
					result = result + land.weight * rate;	//가중치 합산
					if(++cnt == N-1) break;		//N-1간선까지 가중치를 더했다면 break
				}
			}
			
			sb.append("#").append(t).append(" ").append(Math.round(result)).append("\n");
		}
		System.out.println(sb.toString());

	}
		
}
