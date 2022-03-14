package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//메모리 : 62,080 KB
//시간 : 560 ms
//풀이 아이디어 - 순열을 이용해서 타자의 순서를 정했고, 해당 순열마다 게임을 시켜서 점수를 획득한 후 점수를 비교하여 최대 점수를 구함.
//			첫번째 선수는 4번 타자로 고정이니 8명의 선수에 대해 순열을 구하면 40,320 가지 수 이다. 여기에 1 이닝 당 최소 1개의 아웃은
//			있을 테니 최대 24번의 타격을 할 것이고, 이닝 수 N 도 최대 50이니 40,320 * 24 * 50 = 48,384,000이다.
//			1억 연산을 1초라고 했을 때 약 0.5초 걸릴 것으로 예상되어 풀만 하다고 생각함

//

public class test {
	static int N, result;	//이닝 수, 최대 점수
	static int[][] array;	//
	static boolean[] isSelected;	//순열에 사용
	static int[] player;	//타석 순서

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = 0;
		
		array = new int[N+1][10];	//1번인덱스부터 사용 이닝 / 타자
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=9; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isSelected = new boolean[10];	//1번 인덱스부터 사용
		player = new int[10];		//1번 인덱스부터 사용
		
		isSelected[4] = true;	//1번 선수는 4번타자 고정
		player[4] = 1;
		
		perm(2);	//1번 선수 4번 고정했으니 나머지 8명의 선수에 대해서 순열 진행하기 위해 매개변수는 2. 0번 인덱스는 안쓰기 때문에
		
		System.out.println(result);
	}
	
	public static void perm(int cnt) {	//순열
		if(cnt == 10) {
			play();
			return;
		}
		
		for(int i = 1; i<=9; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			player[i] = cnt;		//해당 선수의 타격 순서 = 여태 뽑은 사람의 수
			perm(cnt+1);
			isSelected[i] = false;
		}
		
	}
	
	public static void play() {	//야구 게임 (위에서 결정한 순열 순서대로 타자
		int score = 0;	//해당 순열로 했을 때 획득하는 점수
		int start = 1;	//각 이닝마다 시작하는 첫 선수
		boolean[] base;	//각 선수가 현재 밟고 있는 base판을 표현.	0 == 홈, 1 == 1루
		
		for(int i = 1; i<=N; i++) {		//입력 받은 이닝 수 만큼 반복
			int out = 0;	//아웃 cnt
			base = new boolean[4];
			
	out:	while(true) {	// 3아웃이 되면 반복문 탈출
				for(int j = start; j<=9; j++) {	//첫 타자부터 9번타자까지 반복, 9번타자 쳤으면 다시 1번 타자로
					int play = array[i][player[j]];	//순열로 얻은 순서의 타자가 안타인지, 홈런인지, 아웃인지 가져옴

					if(play == 0) { // 아웃이라면
						out++;
						if(out == 3) { // 쓰리 아웃이라면
							start = j+1;	// 다음이닝엔 현재 보다 다음 차례 타자부터
							if(start == 10) start = 1;	//10번 타자를 부르면 1로 초기화
							break out;	//1이닝 종료
						}
						continue;
					} // end 아웃
					
					else if(play == 1) {	//안타(1루타)라면
						for(int k = 3; k>=1; k--) {	//3번 베이스부터 1번 베이스까지 밟고 있는지 확인
							if(base[k]) {	//현재 베이스를 밟고 있으면,
								if(k==3) {	//현재 베이스를 발고 있고, 그게 3번 베이스라면
									score++;	//점수 추가
									base[k] = false;	//3번 베이스 false처리
									continue;
								}
								
								base[k] = false;	//현재 밟고 있는 베이스는 false처리
								base[k+1] = true;	//다음 위치 베이스 true처리
							}
						}
						base[1] = true;	//현재 타자는 1루로 진출
						continue;	//다음 타자가 치는 곳으로	
					}	// end 안타
					
					else if(play == 2) {	//2루타 라면
						for(int k = 3; k>=1; k--) {
							if(base[k]) {	//현재 베이스를 밟고 있다면
								if(k==3 || k==2) {	//현재 베이스가 3 또는 2 베이스라면,
									score++;
									base[k] = false;
									continue;
								}
								
								base[k] = false;
								base[k+2] = true;	// + 2루 진출
							}
						}
						
						base[2] = true;	//현재 타자는 2루 진출
						continue;			//다음 차자가 치는 곳으로
					}	// end 2루타
					
					else if(play == 3) {	// 3루타 라면
						for(int k = 3; k>=1; k--) {
							if(base[k]) {	//현재 베이스를 밟고 있다면, + 3루 진출이니 베이스를 밟고 있으면 모두 득점
								score++;
								base[k] = false;
							}
						}
						
						base[3] = true;	//현재 타자는 3루 진출
						continue;
					}	// end 3루타
					
					else {	//홈런 이라면
						for(int k = 3; k>=1; k--) {
							if(base[k]) {	//현재 베이스를 밟고 있다면, 모두 득점
								score++;
								base[k] = false;
							}
						}
						
						score++;	//현재 친 타자도 홈런이기 때문에 득점
						continue;	//다음 타자로
					}	//	end 홈런
					
				}// end start~9번타자
				
				start = 1;	//9번타자까지 다 쳐서 for문을 나왔다면, 다시 첫번째 타자 순서로 되돌리기 위해 초기화
			}
			
		}

		result = Math.max(result, score);	//위에서 만들어진 순열로 저장된 점수와 비교하여 최대 값을 저장.
	}

}
