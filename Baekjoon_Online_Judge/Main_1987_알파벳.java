package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
//메모리 : 15,312 KB
//시간 : 3912 ms
//풀이 방법 : 시간 제한은 2초이고, R*C의 배열이 주어질 때 실행시간에 크게 신경쓰지 않아도 될 것 같았음. 현재 밟고 있는 위치에서 4방탐색을
//진행하고, 탐색을 진행하기 전에 다음 위치가 배열의 범위를 넘지 않고, 다음 위치가 가지고 있는 알파벳이 저장되어 있지 않다면 다음 위치로
//재귀 호출 하는 식으로 구현함.
 
public class Solution1 {
    static char[][] array;
    static int R;
    static int C;
    static ArrayList<Character> list;
    static int[] d_row = {0, 1, 0, -1};	//우 하 좌 상
    static int[] d_col = {1, 0, -1, 0};
    static int max = Integer.MIN_VALUE;	//최대 이동 칸수 저장
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());	//행의 크기
        C = Integer.parseInt(st.nextToken());	//열의 크기
        		
        array = new char[R][C];		//character형 배열 생성
        list = new ArrayList<>();	//내가 이동한 알파벳들 저장
        
        for(int i = 0; i<R; i++) {	//주어진 배열로 초기화
        	String str = br.readLine();
        	for(int j = 0; j<C; j++) {
        		array[i][j] = str.charAt(j);
        	}
        }
        
        list.add(array[0][0]);		//초기 시작 위치의 알파벳 리스트에 저장
        alphabet(0, 0, 1);			//초기 시작 위치의 알파벳 리스트를 추가했기에 카운트를 1부터 시작
        System.out.println(max);
        
        
    }
       
    public static void alphabet(int r, int c, int count) {	//경로를 찾는 메소드
    	
    	for(int i = 0; i<4; i++) {	//델타의 4방탐색 만큼 반복
    		int next_row = r + d_row[i];	//다음 위치 행
    		int next_col = c + d_col[i];	//다음 위치 열
    		if(next_row >= 0 && next_row < R && next_col >=0 && next_col < C) {	//다음 위치가 배열 범위 안에 있는지
    			char chr = array[next_row][next_col];		//다음 위치에 있는 알파벳 가져오기
    			if(list.contains(chr)) continue;			//만약 다음 위치의 알파벳이 현재 방문한 리스트에 포함됐으면 continue
    			else {		//다음 위치의 알파벳이 아직 밟은적 없는 알파벳이라면
    				list.add(chr);	//내가 밟은 알파벳 리스트에 추가
    				alphabet(next_row, next_col, count+1);	//다음 위치에서 다시 경로 찾기
    				list.remove(list.size()-1);		//다음 델타 방향으로 나아가보기 위해서 추가했던 알파벳 삭제
    				
    			}//end else
    		}//end_if_범위
    		else continue;
    	}//end_for _ delta
    	if(count > max) max = count;	//만약 지금까지의 최대 카운트 수보다 현재 카운트 수가 더 크다면 변경

    }
         
}