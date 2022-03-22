package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


//메모리 : 67,128	KB
//시간 : 892 ms
//풀이 방법 : Union-find, hash map을 사용함. 

public class Solution1 {
	static int parent[];	//각각 친구들의 부모 인덱스 저장
	static int count[];		//각각 친구들의 높이 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t<T; t++) {
			int F = Integer.parseInt(br.readLine());	//친구 관계 수
			Map<String, Integer> map = new HashMap<>();	//String = 친구 이름, Integer = 새 친구 등록시 마다 서로 다른 번호 부여
			
			parent = new int[2*F];	//각각 친구들의 부모 저장 위해  * 2
			for(int i = 0; i<2*F; i++) parent[i] = i;	//부모 초기화
			count = new int[2*F];
			Arrays.fill(count, -1);	//-1로 모두 초기화	(모두 1층)
			
			int number = 0;	//map의 번호 부여
			for(int f = 0; f<F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				
				if(!map.containsKey(name1)) {	//해당 이름의 친구가 맵에 저장되어 있지 않다면,
					map.put(name1, number++);
				}
				
				if(!map.containsKey(name2)) {	//해당 이름의 친구가 맵에 저장되어 있지 않다면,
					map.put(name2, number++);
				}
				
				int c = union(map.get(name1), map.get(name2));
				sb.append(c * -1).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
 
    public static int union(int a, int b) {
        a = find(a);
        b = find(b);
 
        // 같은 부모가 아닐 때
        if (a != b) {
            parent[b] = a;	// b의 부모를 a로 변경
            count[a] = count[a] + count[b]; // b가 가진 층 수를 a가 가진 층수에 덧셈 
        }
        return count[a];
    }

}