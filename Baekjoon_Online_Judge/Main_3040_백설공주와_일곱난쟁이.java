package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//메모리 : 11,464 KB
//시간 : 72 ms
//풀이 아이디어 - 9C7조합 문제

public class test {
	static int[] num;
	static int[] hat;
	static int flag = 0;	//1이면 답 구한거. 재귀 다 빠져나오도록
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		hat = new int[9];
		num = new int[7];
		
		for(int i = 0; i<9; i++) {
			hat[i] = Integer.parseInt(br.readLine());
		}
		
		nanjangyee(0,0,0);
		
	}	
	
	public static void nanjangyee(int cnt, int start, int sum) {
		if(flag == 1) return;	//합이 100인 조합 찾았다면 재귀 return;
		if(cnt==7) {	//7개를 뽑았다면
			if(sum != 100) return;	//합이 100이 아니라면 리턴
			else if(sum == 100) {	//합이 100이라면
				for(int i : num) {	//뽑았던 조합 모두 출력
					System.out.println(i);
				}
				flag = 1;		//합이 100인 조합 구했으니 앞으로의 재귀 모두 리턴
				return;
			}
		}
			
		for(int i = start; i<9; i++){	//조합 코드
			num[cnt] = hat[i];
			nanjangyee(cnt+1, i+1, sum+hat[i]);
		}
	}
}
