package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 11,556 KB
//시간 : 76 ms

//접근 방법 : 5*5행렬이다. 5개의 행에 빙고가 있는지 체크하는 것 25번연산, 열 체크는 25번연산, 대각선 체크는 10번연산이 필요하여
//빙고 체크 연산은 사회자가 숫자를 부를때마다 60번의 연산을 한다. 그런데 사회자가 부른 숫자를 0으로 변경할 것인데, 모든 인덱스에 방문
//하여 숫자를 찾기 때문에 25번 연산을 한다. 그러면 사회자는 총 25번의 숫자를 부르고, 25개의 배열을 탐색하여 해당 숫자의 위치의 값을
//0으로 변경해야 하기 때문에 25*25 = 625번 연산을 해야한다. 거기에 빙고가 있는지 체크하기 위해 60번 연산이 필요하므로 
//625*60 = 37,500 연산이 필요하고, 3빙고가 되기 위해서는 최소 12개의 수를 불러야 하기 때문에 12개의 수를 부른 시점부터 
//빙고를 체크한다. 따라서 37500 * 14 = 525,000 연산이 필요하므로, 할만한? 하드 코딩이 된다.
public class Algo {
	static int[][] array;
	static int bingo = 0; //0빙고로 초기화
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		array = new int[5][5];
		
		for(int i = 0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<5; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;	//사회자가 몇번 부르는지.	사회자가 부를때마다 카운트 증가
		
	to:	for(int i = 0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	next:	for(int j = 0; j<5; j++) {	// i,j는 사회자가 불러오는 수 반복문
				int num = Integer.parseInt(st.nextToken());	//사회자가 부른 숫자
				bingo = 0;			//빙고 초기화
				
				for(int r = 0; r<5; r++) {
					for(int c = 0; c<5; c++) {	//r,c는 배열 안에 숫자가 일치하는지 체크 후 0으로 변경
						if(array[r][c] == num) {	//사회자가 부른 수와 같다면
							array[r][c] = 0;		//해당 칸을 0으로 변경
							count++;	//카운트 증가
							
							if(count>=12) {			//3빙고가 나오기 위한 최소 카운트 수는 12이상이다.
								row_check();		//행, 열, 대각선 빙고 체크
								col_check();
								daegak_check();
								if(bingo>=3) {		//3빙고 이상이라면
									System.out.println(count);	//사회자가 부른 카운트 수 출력하고 종료
									break to;
								}//end_if (3빙고)
							}//end_if(count는 12이상)
							
							continue next;	//count가 12보다 작거나 3빙고 이상 아니면 사회자의 다음 숫자 받아오기.
						}//end_if
						
					}//for c end
				}//for r end

			}//for j end
		}//for i end
		
	}//main end
	
	public static void row_check() {
		
	to:	for(int r = 0; r<5; r++) {
			for(int c = 0; c<5; c++) {
				if(array[r][c] != 0) {	//사회자가 부르지 않은 칸을 만나면 다음 행으로 continue;
					continue to;
				}//if_end	
			}//for_c end
			bingo++;	//행 전체가 0이어서 무사히 여기까지 왔기에 1빙고 추가.
		}//for_r end
	}
	
	public static void col_check() {
		to:	for(int c = 0; c<5; c++) {
			for(int r = 0; r<5; r++) {
				if(array[r][c] != 0) {	//사회자가 부르지 않은 칸을 만나면 다음 행으로 continue;
					continue to;
				}//if_end	
			}//for_c end
			bingo++;	//행 전체가 0이어서 무사히 여기까지 왔기에 1빙고 추가.
		}//for_r end
	}
	
	public static void daegak_check() {
		int check = 1;	//대각선이 빙고인지 아닌지체크. 0이면 빙고 아닌거
		for(int i = 0; i<5; i++) {
			if(array[i][i] != 0) {	//사회자가 부르지 않은 칸을 만나면 다음 행으로 continue;
				check = 0;
				break;
			}//if_end	
		}//for_i end 왼쪽 위의 대각선 체크
		if(check == 1) bingo++;
		
		check = 1;	//대각선이 빙고인지 아닌지체크. 0이면 빙고 아닌거
		for(int i = 4; i>=0; i--) {
			if(array[4-i][i] != 0) {	//사회자가 부르지 않은 칸을 만나면 다음 행으로 continue;
				check = 0;
				break;
			}//if_end	
		}//for_i end 오른쪽 위의 대각선 체크
		if(check == 1) bingo++;
	}
	
}