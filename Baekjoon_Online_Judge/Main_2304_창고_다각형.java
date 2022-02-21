package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/* 메모리 : 18,464 KB
 * 시간 : 240 ms
 * 풀이 방법 : 처음에 정방향으로 넓이를 계산해보았는데, 신경 써야할 부분이 너무 많았다. 그래서 먼저 정방향으로 넓이를 계산하면서 나보다 높이가 같거나
 * 높은 기둥까지만 넓이를 구해서 더해주고, 최대 높이 인덱스를 저장한다. 그리고 뒤에서부터 역방향으로 최대 높이 인덱스까지 넓이를 구해서 더해주고, 마지막으로
 * 최대 높이를 더해주는 방식으로 풀었다.
 */

public class Main1 {
	
	static int[][] array;
	static int N;
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];	//[][0] = 왼쪽 위치, [][1] = 높이
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array, (o1,  o2) -> o1[0]-o2[0]);	//왼쪽 위치 기준으로 오름차순 정렬
		
		result = 0;	//넓이 초기화

		
		int max_i = 0;	//가장 높은 높이의 인덱스 번호
		int x = array[0][0];	//초기 x값
		int y = array[0][1];	//초기 y값
		
		for(int i = 1; i<N; i++) {	//왼쪽부터 높거나 같은 높이 기둥 만날 때 까지 넓이 구하기
			if(y <= array[i][1]) {	//만약 현재의 내 기둥의 높이가 다음 기둥의 높이보다 크거나 같으면
				result = result + (array[i][0]-x) * y;	//넓이 구해서 더해주기
				x = array[i][0];	//넓이를 구할 기준이 되는 기둥 초기화
				y = array[i][1];
				max_i = i;			//최대 높이 인덱스 초기화
			}	
		}
			
		x = array[N-1][0];	//오른쪽에서부터 왼쪽으로 가장 높은 기둥까지 찾기. 초기 x값
		y = array[N-1][1];	//초기 y값
		for(int i = N-2; i>=max_i; i--) {	//뒤에서부터 접근
			if(y <= array[i][1]) {	//만약 나보다 내 앞에 기둥이 더 높다면
				result = result + (x-array[i][0]) * y;	//높이 계산
				x = array[i][0];	//넓이를 구할 기준이 되는 기둥 초기화
				y = array[i][1];
			}
		}
		
		result = result + array[max_i][1];	//최대 높이 기둥의 넓이는 결과에 반영되지 않았으므로 결과에 반영하기
		
		System.out.println(result);
	}

}
