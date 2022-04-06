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


/* 메모리 : 96,804 KB
 * 시간 : 3,779 ms
 * 풀이 방법 : 모든 쌍 최단경로 ( 플로이드 워샬 알고리즘 )
 *  
 */

public class Main1 {
	static int T,N;
	static int[][] array;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());	//tc
        for(int t = 1; t<=T; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	N = Integer.parseInt(st.nextToken());
        	array = new int[N][N];
        	
        	for(int i = 0; i<N; i++) {
        		for(int j = 0; j<N; j++) {
        			array[i][j] = Integer.parseInt(st.nextToken());
        			if(i != j && array[i][j]==0) {	//자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
    					array[i][j]=99999;
    				}
        		}
        	}
        	
        	
        	for(int k = 0; k<N; k++) {
        		
        		for(int i = 0; i<N; i++) {
        			if(i==k) continue;
        			
        			for(int j = 0; j<N; j++) {
        				if(j==i || j==k) continue;
        				
        				if(array[i][j] > array[i][k] + array[k][j]) {
        					array[i][j] = array[i][k] + array[k][j];
        				}
        			}
        			
        		}
        		        		
        	}
        	
        	
        	int min = Integer.MAX_VALUE;
        	int sum = 0;
        	for(int i = 0; i<N; i++) {
        		sum = 0;
        		for(int j = 0; j<N; j++) {
        			sum = sum + array[i][j];
        		}
        		if(min > sum) {
        			min = sum;
        		}
        	}
        	
        	sb.append("#").append(t).append(" ").append(min).append("\n");
        		
        	
        }
        
        System.out.println(sb.toString());
        
        
    }
		
	

}
