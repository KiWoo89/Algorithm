package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//메모리 : 18,352 KB
//시간 : 108 ms
//풀이 아이디어 - 입력받은 카드의 종류를 카운팅하기 위해서 int형 2차원 배열을 생성 후 해당 카드를 카운팅하고, 카드를 입력받을 때마다 해당 위치 배열의 카운팅
//수가 0이 아니라면 error을 출력하도록 구현함.

public class test {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
	t:	for(int t = 1; t<=T; t++) {
			String str = br.readLine();
			int[][] isSelected = new int[4][14];	//행은 카드의 종류, 열은 1~13 사용 빈도 수. 0인덱스 안씀
			int len = str.length()/3;
			
			for(int i = 0; i<len; i++) {	//3개씩 끊어서 반복
				char c = str.charAt(0);
				
				if(c == 'S') {
					int num = Integer.parseInt(str.substring(1,3));
					if(isSelected[0][num] == 0) {
						isSelected[0][num]++;
						str = str.substring(3, str.length());
					}
					else {
						sb.append("#").append(t).append(" ERROR\n");
						continue t;
					}
				}
				else if(c == 'D') {
					int num = Integer.parseInt(str.substring(1,3));
					if(isSelected[1][num] == 0) {
						isSelected[1][num]++;
						str = str.substring(3, str.length());
					}
					else {
						sb.append("#").append(t).append(" ERROR\n");
						continue t;
					}				
				}
				else if(c == 'H') {
					int num = Integer.parseInt(str.substring(1,3));
					if(isSelected[2][num] == 0) {
						isSelected[2][num]++;
						str = str.substring(3, str.length());
					}
					else {
						sb.append("#").append(t).append(" ERROR\n");
						continue t;
					}
				}
				else if(c == 'C') {
					int num = Integer.parseInt(str.substring(1,3));
					if(isSelected[3][num] == 0) {
						isSelected[3][num]++;
						str = str.substring(3, str.length());
					}
					else {
						sb.append("#").append(t).append(" ERROR\n");
						continue t;
					}
				}
				
				
			}
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i<4; i++) {
				int count = 0;
				for(int j = 1; j<14; j++) {
					if(isSelected[i][j]==1) count++;
				}
				sb.append(13-count).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");			
			
		}//end for test_case
		
		System.out.println(sb.toString());
		
	}
	
	
	
}
