package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 21,140 KB
//시간 : 1,885 ms
//풀이 아이디어 - 고객들 N명간에 NPN 순열을 구한 후, 그 순열 순서에 해당하는 거리를 구하여 합산하고, 첫고객과 회사, 마지막고객과 집의 거리를 계산하여
//더해줌으로써 그중에 제일 적은 경우의 합을 구했다.

//최적 경로
public class test {
	static int N;	//고객의 수
	static Customer[] customers;	//customer객체 저장 배열
	static int[] home;	//집 좌표	[0] = x좌표, [1] = y좌표
	static int[] office;	//회사 좌표
	static int min_distance;
	static int[] numbers;	//순열에서 선택한 고객 인덱스 번호 저장
	static boolean[] isSelected; //순열에서 선택 되었는지 체크
	
	static class Customer{	//고객 클래스
		int x;	//x좌표
		int y;	//y좌표
		
		public Customer(int x, int y) {	//생성자
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t<=T; t++) {
			min_distance = Integer.MAX_VALUE;	//최소 거리 초기화
			N = Integer.parseInt(br.readLine());	//고객의 수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			office = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};	//회사 좌표 입력
			home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())}; //집 좌표 입력
			
			customers = new Customer[N];	//고객 객체 저장할 배열
			numbers = new int[N];	//순열에서 선택한 고객 인덱스 순서 저장
			isSelected = new boolean[N];	//선택한 인덱스인지 아닌지 
			
			for(int i = 0; i<N; i++) {
				customers[i] = new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //고객 객체 생성
			}
			
			perm(0);
			
			sb.append("#").append(t).append(" ").append(min_distance).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}
	
	public static void perm(int cnt) {	//고객 방문 순열 구하기
		//if(cnt>N) return;
		if(cnt==N){
			int sum = 0;	//거리 합 저장 변수
			sum = sum + getDistance(office[0], office[1], customers[numbers[0]].x, customers[numbers[0]].y);	//회사와 0번 고객집 과의 거리
			for(int i = 0; i<N-1; i++) {
				sum = sum + getDistance(customers[numbers[i]].x, customers[numbers[i]].y, customers[numbers[i+1]].x, customers[numbers[i+1]].y);
			}
			sum = sum + getDistance(customers[numbers[N-1]].x, customers[numbers[N-1]].y, home[0], home[1]); //마지막 고객과 집과의 거리
			
			if(min_distance > sum) {	//만약 거리들의 합이 지금까지 저장된 최소 거리보다 작다면 변경
				min_distance = sum;
				return;
			}
			else return;
		}
		
		for(int i = 0; i<N; i++) {
			if(isSelected[i] == true) continue;
			isSelected[i] = true;
			numbers[cnt] = i;
			perm(cnt+1);	//선택
			
			isSelected[i] = false;	//비선택
		}
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) { //좌표간 거리 계산
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}

}
