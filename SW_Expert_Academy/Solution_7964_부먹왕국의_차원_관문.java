package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
//메모리 : 84,776 KB
//시간 : 264 ms
//풀이 방법 : 처음에 문제가 아예 이해가 안가서 검색 후 문제 이해를 한 다음에 풀었다. 입력으로 들어오는 제한거리 D는 D거리 이내이기 때문에 본인 포함해서 D거리
//까지 이동이 가능한 것이었다. 최소한의 게이트를 원하기 때문에 D거리 이내에 1이 없는 경우 게이트를 설치하기 때문에 해당 인덱스에서 D거리 후 부터 다시 탐색함.
 
public class Solution1 {
       
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	int N = Integer.parseInt(st.nextToken());	//부먹 왕국의 도시 수
        	int D = Integer.parseInt(st.nextToken());	//제한 거리
        	int[] array = new int[N];
        	
        	
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int i = 0; i<N; i++) {
        		array[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int count = 0;	//게이트 설치 수 
     to:   	for(int i = 0; i<=N-D; i++) {
        		for(int j = i; j<i+D; j++) {
        			if(array[j] == 1) continue to;	//게이트가 있다면 다음 도시로 이동
        		}
        		count++;	//D거리 이내에 게이트가 하나도 없었다면 게이트 설치 개수 증가.
        		i = i+D-1;	//D거리 후 부터 다시 탐색
        	}
        	
        	sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.println(sb.toString());
    
   
    } 
       
    
}