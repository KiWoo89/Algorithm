package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
//메모리 : 12,288 KB
//시간 : 88 ms
//풀이 방법 : 적록색약이 있는 사람은 빨강과 초록을 같은 색으로 인식하니, 배열에 저장할 때 파랑이 아니라면 모두 빨강으로 저장했다.
//색약 유무에 따라서 서로 다른 dfs를 구현하고, 각각 깊이 우선 탐색을 진행함.
 
public class Solution1 {
    static int N;
    static char[][] array1, array2; 
    static int[] d_row = {-1, 0, 1, 0};	//상 우 하 좌
    static int[] d_col = {0, 1, 0, -1};
    static int cnt1, cnt2;	//적록색약이 없는 사람과 있는 사람의 구역 수 카운트
    
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       N = Integer.parseInt(br.readLine());
       array1 = new char[N][N];	//적록색약이 아닌 사람의 배열
       array2 = new char[N][N];	//적록색약이 있는 사람의 배열
       
       for(int i = 0; i<N; i++) {
    	   String str = br.readLine();
    	   for(int j = 0; j<N; j++) {
    		   array1[i][j] = str.charAt(j);
    		   if(array1[i][j] == 'B') array2[i][j] = 'B';
    		   else array2[i][j] = 'R';		//적록색약이 있는 경우에는 파란색을 제외하고 모두 빨간색을 보는 것으로 간주
    	   }
       }
        
       for(int i = 0; i<N; i++) {		//색약이 없는 경우
    	   for(int j = 0; j<N; j++) {
    		   if(array1[i][j] != 0) {	//방문한 곳이 아니라면
    			   dfs1(i, j, array1[i][j]);
    			   cnt1++;				//구역 하나 0으로 바꿨으니 1증가
    		   }
    	   }
       }
       
       for(int i = 0; i<N; i++) {		//색약이 있는 경우
    	   for(int j = 0; j<N; j++) {
    		   if(array2[i][j] != 0) {	//방문한 곳이 아니라면
    			   dfs2(i, j, array2[i][j]);
    			   cnt2++;				//구역 하나 0으로 바꿨으니 1증가
    		   }
    	   }
       }
       
       System.out.println(cnt1 + " " + cnt2);
   
    } 
       
    public static void dfs1(int r, int c, char color) {	//색약이 없는 사람의 dfs
    	array1[r][c] = 0;	//방문한 곳 0으로 표시하기
    	
    	for(int d = 0; d<4; d++) {	//4방 탐색하여 같은 알파벳으로 인식하는지 체크
    		int next_row = r + d_row[d];
    		int next_col = c + d_col[d];
    		if(rangeCheck(next_row, next_col) && array1[next_row][next_col] == color){//다음 인덱스 위치가 범위를 넘지 않고, 같은 알파벳으로 인식한다면
    			dfs1(next_row, next_col, color);
    		}
    	}
    	
    }
    
    public static void dfs2(int r, int c, char color) {	//색약이 있는 사람의 dfs
    	array2[r][c] = 0;	//방문한 곳 0으로 표시하기
    	
    	for(int d = 0; d<4; d++) {	//4방 탐색하여 같은 알파벳으로 인식하는지 체크
    		int next_row = r + d_row[d];
    		int next_col = c + d_col[d];
    		if(rangeCheck(next_row, next_col) && array2[next_row][next_col] == color){//다음 인덱스 위치가 범위를 넘지 않고, 같은 알파벳으로 인식한다면
    			dfs2(next_row, next_col, color);
    		}
    	}
    }
    
    
    public static boolean rangeCheck(int next_r, int next_c) {	//배열 인덱스 범위 체크
    	if(next_r >= 0 && next_r < N && next_c >= 0 && next_c < N) return true;
    	else return false;
    }
    
}