package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//메모리 : 12,972 KB
//실행시간 : 84 ms
/*접근 방법 : 그리디? 경우의 수 = 4개. 1 = 5로만 나눌 수 있을 떄, 2 = 3으로만 나눌 수 있을때, 3 = 3, 5 둘다 못나눌때, 
 * 4 = 3,5 섞어서 나눌때.		봉지의 수가 줄어드는 우선순위 = 1 - 3 - 2 - 4
 * 우선순위 순으로 나누어지는지 수행함.
 */ 

public class Main1 {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		if(N % 5 == 0) {
			count = N / 5;	//5로만 나누어질 때
			System.out.println(count);
			System.exit(0);
		}
		
		int div = N/5;	//나눈 몫 저장
		while(div > 0) {	//3,5 섞어야 할 때.	//5로 나눈 봉지가 가장 많을 때 부터 1감소시키면서 나누어 떨어지는지 확인
			int nam = N - (div * 5); //나머지
			
			if(nam%3 == 0) {
				count = div + (nam/3);	//3으로 나눈 봉지 수 카운트, 5로 나눈 봉지 수 카운트
				System.out.println(count);
				System.exit(0);
			}	
			else {		//3으로 나누어지지 않는다면 5로 나눈 몫 감소
				div--;
			}	
		}

		if(N % 3 == 0){			//3으로만 나누어 떨어질 때
			count = N / 3;
			System.out.println(count);
			System.exit(0);
		}
	
		count = -1;				// 3,5나누어지는 수가 아무것도 없을 때
		System.out.println(count);
		
	}	
}
