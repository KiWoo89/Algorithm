package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//메모리 : 25,336 KB
//실행시간 : 1,239 ms
//접근 방법 : 순열 생성 방법 중 Next Permutation 활용.

public class Main1 {
	static int[] gyu;	//규영이의 카드를 저장할 배열
	static int[] inyoung;	//인영이의 카드를 저장할 배열
	static int count1;	//규영이가 게임을 이긴다
	static int count2;	//규영이가 게임을 진다
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			count1 = 0;	//규영이가 이기는 횟수 초기화
			count2 = 0;	//규영이가 지는 횟수 초기화
			gyu = new int[9];	//18개의 카드 중 반으로 나누니 9개 배열 생성
			inyoung = new int[9];
			boolean[] selected = new boolean[19];	//규영이가 사용중인 카드 true표시. 0인덱스 사용 X
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<9; i++) {	//규영이가 사용중인 카드 배열에 저장
				gyu[i] = Integer.parseInt(st.nextToken());
			}
			
			int index = 0;
			for(int i = 0; i<9; i++) {	//사용중인 카드 true 표시.
				selected[gyu[index++]] = true;		
			}
			
			index = 0;
			for(int i = 1; i<19; i++) {	//사용중인 카드를 제외한 나머지 카드 인영이 카드 배열에 저장
				if(selected[i]==false) {
					inyoung[index++] = i;
				}
			}
			
			Arrays.sort(inyoung);	//인영이 카드 오름차순 정렬. (Next_permutaion 돌리기 위해서)
					
			do {	//next permutation
				int sum1 = 0;	//규영 점수 합산
				int sum2 = 0;	//인영 점수 합산
				for(int i = 0; i<9; i++) {	//총 9라운드 진행
					if(gyu[i] > inyoung[i]) {	//만약 해당 라운드에 규영이가 인영이보다 카드 점수가 높다면
						sum1 = sum1 + gyu[i] + inyoung[i];	//규영이 점수 합산에 점수 저장
					}
					else if(gyu[i] < inyoung[i]) {	//만약 해당 라운드에 규영이가 인영이보다 카드 점수가 낮다면
						sum2 = sum2 + gyu[i] + inyoung[i];	//인영이 점수 합산에 점수 저장
					}
				}	
				if(sum1 > sum2) count1++;		//9라운드가 모두 종료되고 규영이 점수가 더 높으면 규영이 승리 카운트 증가
				else if(sum1 < sum2) count2++;	//규영이 점수가 더 낮으면 규영이 패배 카운트 증가
			}while(np());		//다음 배열이 있을때까지 반복
			
			
			System.out.println("#"+t + " " + count1 + " " + count2);
			
		}
	}

	private static boolean np() {
		//1. 뒤쪽부터 탐색하며 교환위치(i-1) 찾기 (i: 꼭대기)
		int i = 9-1;	//배열 인덱스가 8이 마지막이므로 9-1 저장
		while(i>0 && inyoung[i-1] >= inyoung[i]) i--;	//i-1까지 비교하기 위해 i는 0보다 커야하고, 꼭대기에서 꺾이는
														//부분 찾을때까지 i인덱스 감소.
		
		if(i==0) return false;	//위의 while문이 끝났는데 만약 i가 0이라면, 꼭대기가 존재하지 않고 \ 이 모양으로 되어 있기 때문에
								//그 다음 큰 순열은 존재하지 않게 되므로 false를 리턴한다.
		
		//2. 뒤쪽부터 탐색하며 교환위치(i-1)와 교환할 큰 값의 위치(j) 찾기
		int j = 9-1;
		while(inyoung[i-1] >= inyoung[j]) j--;	//꼭대기에서 꺾이는 그 앞의 값과 맨 뒤에서부터 탐색하여 꺾인 값보다 큰 값을 가진 인덱스 찾기
		
		//3. 두 위치 값 (i-1, j) 교환
		swap(i-1,j);
		
		//4. 꼭대기위치(i)부터 맨 뒤까지 오름차순 정렬
		int k = 9-1;
		while(i<k) {
			swap(i++, k--);
		}
		
		return true;	//여기까지 진행했으면 새로운 순열이 생성된 것이므로 true 반환.
	}

	private static void swap(int i, int j) {
		int temp = inyoung[i];
		inyoung[i] = inyoung[j];
		inyoung[j] = temp;
	}
}
