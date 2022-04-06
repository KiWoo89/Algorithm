package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//메모리 : 296,100 KB
//시간 : 2,132 ms



public class Main {
	static int N, D, K, C, MAX;	//MAX = 최대 가짓수
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 회전 초밥 벨트에 놓인 접시의 수	(30,000)
		D = Integer.parseInt(st.nextToken());	// 초밥의 가짓 수				(3,000)
		K = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수			(3,000)
		C = Integer.parseInt(st.nextToken());	// 쿠폰 번호					(3,000)
		
		
		int[] array = new int[N];
		Set<Integer> set = new HashSet<>();
		MAX = Integer.MIN_VALUE;
		
		for(int i = 0; i<N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i<N; i++) {
			for(int k = 0; k<K; k++) {
				set.add(array[(i+k)%N]);	// 회전초밥 종류를 set에 저장
			}
				
			if(!set.contains(C)) {	// 해당 쿠폰 번호가 포함되어 있지 않다면
				if(set.size() + 1 > MAX) {	// 해당 초밥 가짓수 추가
					MAX = set.size() + 1;
				}	
			}
			else {	//해당 쿠폰 번호가 포함되어 있다면
				if(set.size() > MAX) {	// set에 저장된 사이즈가 max보다 크다면 갱신
					MAX = set.size();
				}
			}
			set.clear();
		}
		
		System.out.println(MAX);
		
	}
	
	
	
}