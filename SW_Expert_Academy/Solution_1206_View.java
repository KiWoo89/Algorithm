package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
//메모리 : 22,840 KB
//시간 : 133 ms
//풀이 방법 : 해당 기둥에서 왼쪽 오른쪽 각각2개의 빌딩에 대해서 제일 높은 빌딩들에 대해서 해당 빌딩이 높으면 조망 확보 세대가 존재하는 것이고, 양옆 빌딩들 중
//가장 높은 빌딩이 해당 빌딩의 조망을 가리기 때문에 양옆 빌딩 중 최대 높이를 현재 기둥 높이에서 뺀 만큼이 조망 확보 세대수가 되는 것이다.
 
public class Solution1 {
       
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int t = 1; t<=10; t++) {
        	
        	int N = Integer.parseInt(br.readLine());	//가로 길이
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	int array[] = new int[N];
        	
        	for(int i = 0; i<N; i++) {
        		array[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int count = 0;	//조망 확보 세대 수 카운트
        	for(int i = 2; i<N-2; i++) {	//앞에서2개, 뒤에서 2개는 제외
        		int n1 = Math.max(array[i-1], array[i-2]);	//왼쪽 빌딩 2개 중 높은 건물의 높이
        		int n2 = Math.max(array[i+1], array[i+2]);	//오른쪽 빌딩 2개 중 높은 건물의 높이
        		if(array[i] > n1 && array[i] > n2) {
        			count = count + (array[i] - Math.max(n1, n2));	//왼쪽, 오른쪽 각각 2개 빌딩 중 제일 높은 빌딩 높이 - 현재높이 = 조망 확보 세대 수
        		}
        	}
        	
        	sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.println(sb.toString());
   
    } 
       
    
}