package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
//메모리 : 21,248 KB
//시간 : 224 ms
//풀이 방법 : 인접행렬을 이용한 DFS, BFS구현
 
public class Solution1 {
    static int N,M,V;
    static int[][] array;
    static StringBuilder sb;
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());	//정점의 개수
        M = Integer.parseInt(st.nextToken());	//간선의 개수
        V = Integer.parseInt(st.nextToken());	//시작할 정점의 번호 (1~N)
        
        dfsVisited = new boolean[N+1];	//0번인덱스 안씀. 1번정점부터 사용
        bfsVisited = new boolean[N+1];
        
        array = new int[N+1][N+1];	//0번인덱스 안씀 (1~N)
        for(int i = 0; i<M; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	array[r][c] = 1;
        	array[c][r] = 1;
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
    	
    	for(int i = 1; i<=N; i++) {
    		if(!dfsVisited[i] && array[current][i] != 0) {
    			dfs(i);
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
    		
    		for(int i = 1; i<=N; i++) {
    			if(!bfsVisited[i] && array[v][i] != 0) {
    				bfsVisited[i] = true;
    				qu.offer(i);
    			}
    		}
    		
    	}
    	
    }
    
    
       
    
}