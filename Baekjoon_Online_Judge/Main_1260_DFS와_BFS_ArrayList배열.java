package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
//메모리 : 17,704 KB
//시간 : 184 ms
//풀이 방법 : ArrayList[] 배열을 이용하여 인접리스트처럼 구현한  DFS, BFS구현
 
public class Solution1 {
    static int N,M,V;
    static StringBuilder sb;
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    static List<Integer>[] list;
       
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());	//정점의 개수
        M = Integer.parseInt(st.nextToken());	//간선의 개수
        V = Integer.parseInt(st.nextToken());	//시작할 정점의 번호 (1~N)
        
        list = new ArrayList[N+1];	//0번인덱스 안씀. 1번 정점부터 사용
        for(int i = 1; i<=N; i++) {
        	list[i] = new ArrayList<Integer>();
        }
        
        dfsVisited = new boolean[N+1];	//0번인덱스 안씀. 1번정점부터 사용
        bfsVisited = new boolean[N+1];
        
        for(int i = 0; i<M; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	list[r].add(c);
        	list[c].add(r);
        }
        
        for(int i = 1; i<=N; i++) {
        	Collections.sort(list[i]);
        }
        
        	dfs(V);
        	sb.append("\n");
        	
        	bfs(V);
        	sb.append("\n");
        	
        	System.out.println(sb.toString());
        
    }
    
    public static void dfs(int current) {
    	
    	dfsVisited[current] = true;
    	sb.append(current + " ");
    	  	
    	for(int temp : list[current]) {
    		if(!dfsVisited[temp]) {
    			dfsVisited[temp] = true;
    			dfs(temp);
    		}
    	}
    }
    
    public static void bfs(int start) {
    	Queue<Integer> qu = new LinkedList<>();
    	qu.offer(start);
    	bfsVisited[start] = true;
    	
    	while(!qu.isEmpty()) {
    		int v = qu.poll();
    		sb.append(v + " ");
    		
    		for(int temp : list[v]) {
    			if(!bfsVisited[temp]) {
    				bfsVisited[temp] = true;
    				qu.offer(temp);
    			}
    		}	
    	}
    } 
       
    
}