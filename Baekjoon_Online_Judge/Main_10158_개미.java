package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/* 메모리 : 11,544 KB
 * 시간 : 80 ms
 * 풀이 방법 : 시간제한은 0.15초이고, time은 최대 2억번 이라고 하여 완전탐색은 불가능하다고 생각함. 저 짧은 시간에 저 큰 수를
 * 다루기 위해서는 어떤 규칙이 있을 것이라 생각하고 규칙을 찾으려 해봄. 개미는 항상 오른쪽 위 방향으로 가기 때문에 벽을 홀수번 뚫고
 * 가면 x,y값이 감소하고, 벽을 짝수번 뚫으면 x,y값이 증가하는 것을 발견함. 따라서 주어진 현재 위치에서 몇시간이 지났는지를 더하고,
 * 몇번 벽을 뚫었는지 좌우 벽과 상하 벽 각각 분리하여 구했음. 
 */

public class Algo{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());	//배열 x 크기
		int H = Integer.parseInt(st.nextToken());	//배열 y 크기
		
		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken());	//시작 x
		int y = Integer.parseInt(st.nextToken());	//시작 y
		
		int time = Integer.parseInt(br.readLine());	//몇 시간가는지
		
		int result_x = 0;	//결과 x
		int result_y = 0;	//결과 y
		
		if(((x+time)/W) %2 == 0 ) {	//벽을 뚫고 간 횟수가 짝수라면, 오른쪽으로 이동하기 때문에 x+time을 %W한 나머지에 x에 위치하게 된다.
			result_x = (x+time) % W;
		}
		else {	//벽을 뚫고 간 횟수가 홀수라면, 왼쪽으로 이동하기 때문에 배열 크기 W(x)에서 %W 한 나머지를 빼주면 된다.
			result_x = W - (x+time) % W;
		}
		
		if(((y+time)/H) % 2 == 0) { //위로 벽을 뚫고 간 횟수가 짝수라면, 다시 위쪽으로 이동하기 때문에 y+time에 %H한 나머지 y에 위치하게 된다.
			result_y = (y+time) % H;
		}
		else {	//위로 벽을 뚫고 간 횟수가 홀수라면, 아래쪽으로 튕겨서 이동하기 때문에 H에 (y+time) % H를 빼주면 해당 y에 위치하게 된다.
			result_y = H - (y+time) % H;
		}
		
		
		System.out.println(result_x + " " + result_y);
	}
	
	
	
}



