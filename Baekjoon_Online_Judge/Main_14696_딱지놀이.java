package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 26,124 KB
//시간 : 228 ms

//접근 방법 : 딱지에 모양에 우선순위가 있었다. *은 4로 가장 높은 우선순위를 가지고 있었는데, 모양을 인덱스와 대응시키고, 
//A와 B플레이어의 입력을 받아와 모양의 값으로 인덱스에 접근해 카운트 수를 증가시켰다. A와 B 각각 int[5]배열 2개를 생성하여 카운트함.
//우선순위가 가장 높은 4번 인덱스부터 서로 비교하여 우선순위가 높은 카운트 수가 더 많은 사람이 이기도록 for문을 돌렸다. 
public class Algo {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //라운드 수
		StringBuilder sb = new StringBuilder(); //누가 라운드마다 승리했는지 마지막 출력에 사용
		
		for(int i = 0; i<N; i++) {			//N라운드 만큼 반복
			int[] A_ddakzi = new int[5];	//A, B플레이어에 각각 어떤 모양들이 입력으로 들어왔는지 index로 관리
			int[] B_ddakzi = new int[5];	//0인덱스는 안쓰고 1~4 사용
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");	//A플레이어 입력
			int index = Integer.parseInt(st.nextToken());
			for(int k = 0; k<index; k++) { //들어오는 카드 수 만큼 A플레이어 반복
				A_ddakzi[Integer.parseInt(st.nextToken())]++;
			}//for_k end
			
			st = new StringTokenizer(br.readLine(), " ");	//B플레이어 입력
			index = Integer.parseInt(st.nextToken());
			for(int k = 0; k<index; k++) { //들어오는 카드 수 만큼 A플레이어 반복
				B_ddakzi[Integer.parseInt(st.nextToken())]++;
			}//for_k end
		
			for(int x = 4; x>=1; x--) {	//해당 라운드에 누가 이겼는지 판단
				if(A_ddakzi[x] > B_ddakzi[x]) {	//A가 해당 인덱스에서 이겼다면
					sb.append("A\n");
					break;	//해당 라운드 종료
				}
				else if(A_ddakzi[x] < B_ddakzi[x]) { //B가 해당 인덱스에서 이겼다면
					sb.append("B\n");
					break;	//해당 라운드 종료
				}
				else {	//만약 해당 인덱스가 무승부라면
					if(x==1) sb.append("D\n");	//만약 마지막 인덱스인 1번까지 갔는데도 무승부라면 무승부 처리
					else continue;	//다음 우선순위의 그림  개수로 승부보기
				}
			}
		}//for_i end
		
		System.out.println(sb.toString());
		
	}
}