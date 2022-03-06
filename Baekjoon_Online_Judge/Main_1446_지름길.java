package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/* 메모리 : 11,728 KB
 * 시간 : 92 ms
 * 풀이 방법 : 다익스트라 이용. 목표 위치 D가 최대 1만이므로 배열 1만 까지 생성하여 해당 위치까지 가는데 필요한 이동거리를 저장해도 괜찮을 것 같았음.
 * 
 */

public class Main1 {
	
	static class Road{	//지름길을 저장할 클래스
		int start;
		int end;
		int distance;
		
		public Road(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		List<Road> list = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Road(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list, new Comparator<Road>(){	//지름길 시작 위치를 기준으로 정렬(같으면 끝 위치를 기준으로))
			@Override
			public int compare(Road o1, Road o2) {
				if(o1.start== o2.start) return o1.end - o2.end;
				return o1.start - o2.start;
			}
		});
		
		int index = 0;		//지름길 저장한 list 인덱스
		int location = 0;	//현재 위치
		int[] dist = new int[10001];	//해당 거리까지 가는데 필요한 최소 이동거리 저장
		Arrays.fill(dist, 10001);		//최대 값으로 초기화
		dist[0] = 0;					//시작 위치는 0번인덱스
		
		while(location < D) {	//목표 위치까지 도달하면 반복 종료
			if(index < list.size()) {	//지름길의 개수만큼 경로 계산을 다 반영하지 않았다면,
				Road r = list.get(index);
				if(location == r.start) {	//현재 위치가 지름길 시작 지점이라면
					dist[r.end] = Math.min(dist[location] + r.distance, dist[r.end]); //지름길 도착 지점에 최소 이동거리 저장
					index++;
				}
				else {	//현재 위치에 지름길이 없다면, +1
					dist[location + 1] = Math.min(dist[location+1], dist[location] + 1);
					location++;
				}
			}
			else {	//지름길의 개수 만큼 경로 계산을 다 반영했다면,
				dist[location + 1] = Math.min(dist[location+1], dist[location] + 1);
				location++;
			}
			
		}
		
		//목표 지점까지 저장된 최소 이동 거리 출력
		System.out.println(dist[D]);
		
	}

}
