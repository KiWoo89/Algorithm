package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 21,768KB
//시간 : 138ms
//풀이방법 : row랑 col이 뒤집어 있는 것을 못봐서 오래걸림.. 별찍기로 배열에 표현하려 했으나 충전 범위가 겹치는 경우를 표현하지
//못해서 포기함

public class Solution {
	static class BC{	//배터리 차지 클래스
		int r;	//행
		int c;	//열
		int range;	//범위
		int power;	//충전량
		
		public BC(int r, int c, int range, int power) {	//생성자
			this.r = r;
			this.c = c;
			this.range = range;
			this.power = power;
		}
	
		public int charge(int r, int c) {	//유저의 현재위치가 BC범위 안에 들어온다면 충전 
			if(((Math.abs(this.r-r)) + (Math.abs(this.c-c))) <= range) {
				return power;
			}
			else {
				return 0;
			}
		}	
	}
	
	static int[] d_row = {0, -1, 0, 1, 0};	//정지(0), 상(1), 우(2), 하(3), 좌(4)	
	static int[] d_col = {0, 0, 1, 0, -1};
	static int[] A_place;//유저 A의 현재 위치 [0]=행 [1]=열 
	static int[] B_place;	//A,B의 초기위치 
	static int[] user_A;	//A유저의 이동 경로
	static int[] user_B;	//B유저의 이동 경로
	static int result; //유저들의 충전량 합
	static BC[] BCS;	//BC 저장
		

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			result = 0;
			A_place = new int[]{1,1}; //유저 A의 현재 위치 [0]=행 [1]=열 
			B_place = new int[]{10,10};	//유저 B의 현재 위치

			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());	//이동 횟수
			int A = Integer.parseInt(st.nextToken());	//BC 개수
			user_A = new int[M];	//A사용자 이동경로 저장
			user_B = new int[M];	//B사용자 이동경로 저장
			BCS = new BC[A];		//입력받은 BC 수 만큼 BC배열 생성

			
			st = new StringTokenizer(br.readLine(), " ");	//사용자 A의 이동경로
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " "); //사용자 B의 이동경로
			for(int i = 0; i<M; i++) {	//이동경로 저장
				user_A[i] = Integer.parseInt(st.nextToken());
				user_B[i] = Integer.parseInt(st1.nextToken());
			}
			
			for(int i = 0; i<A; i++) {	//AP 정보 기입
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());	//AP위치 행
				int c = Integer.parseInt(st.nextToken());	//AP위치 열
				int range = Integer.parseInt(st.nextToken());	//AP 범위
				int power = Integer.parseInt(st.nextToken());  //AP 충전량
				BCS[i] = new BC(r,c,range,power);		//새로운 BC객체를 생성하여 BC배열에 저장
			}
				
			
			for(int m = -1; m<M; m++) {	//이동 경로 수행.  -1부터 하는 이유는 초기 시작점부터 충전하기 위해서
				if(m > -1) {	//-1인 경우는 맨 처음 시작하는 위치에서도 충전을 할 수 있기 때문에 처음에는 이동 X
					A_place[0] = A_place[0] + d_row[user_A[m]];
					A_place[1] = A_place[1] + d_col[user_A[m]];
					B_place[0] = B_place[0] + d_row[user_B[m]];
					B_place[1] = B_place[1] + d_col[user_B[m]];
				}
				
				int power_A = 0;	//A유저가 충전 받은 충전량 저장
				int power_B = 0;	//B유저가 충전 받은 충전량 저장
				int store[][] = new int[2][A];	//매 초 별로 유저마다, 각각의 배터리로부터 충전량을 저장
				for(int i = 0; i<A; i++) {		//배터리 수 만큼 반복
					power_A = BCS[i].charge(A_place[1], A_place[0]);
					power_B = BCS[i].charge(B_place[1], B_place[0]);
					//System.out.println(i + " " + power_A + "!!!" + power_B);
					store[0][i] = power_A;
					store[1][i] = power_B;
				}
				
				int power_sum = 0;	//매 초 최대의 충전량을 저장할 변수
				for(int i = 0; i<A; i++) {	//충전기 끼리의 조합 합
					for(int j = 0; j<A; j++) {
						int sum = store[0][i] + store[1][j];	//각각의 배터리로부터 받는 충전량의 합
						if(store[0][i] == store[1][j] && i==j) {	//만약 같은 배터리로부터 받아온다면
							sum = sum /2;	//충전량은 반 값
						}
						if(power_sum < sum) power_sum = sum;	//만약 현재 초에서 충전량의 합이 이번 초 최대값 보다 크다면 변경
						else continue;	//이번 초에서 최대 충전량보다 작다면 continue;
					}
				}
				
				result = result + power_sum;	//결과 값에 합산
				System.out.println(result);
			}
			
			System.out.println("#"+test_case+" " + result);
		}
	}
		
}
