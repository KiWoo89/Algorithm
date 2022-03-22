package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 메모리 : 11,560 KB
 * 시간 : 80 ms
 * 풀이 방법 : 최소값을 만들기 위해서 연산자 -를 만난 뒤에 나오는 + 연산자들은 모두 뺄셈처리 해주었다. -를 만난 뒤에 또 -를 만나더라도
 * 최소 값을 만들기 위해서는 계속 빼주기만 하면 된다. 즉, 한번이라도 -를 만난다면 만난 그 뒤 부터 모든 피연산자는 뺄셈해주면 된다.
 * 
 */

public class Algo {
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] array = br.readLine().toCharArray();
		ArrayList<Integer> list = new ArrayList<>();	//숫자들 저장
		ArrayList<Character> oper = new ArrayList<>();	//연산자 저장
		String s = "";
		int score = 0;
		
		for(int i = 0; i<array.length; i++) {
			if(array[i]-'0' > -1 && array[i] - '0' < 10) {	//숫자 만난다면, 문자열에 추가
				s = s + array[i];
			}
			else if(array[i] == '+' || array[i] == '-') {	// +를 만났다면
				list.add(Integer.parseInt(s));		//숫자 저장
				oper.add(array[i]);					//연산자 저장
				s = "";								//숫자 만드는 문자열 초기화
			}
		}
		list.add(Integer.parseInt(s));	//맨 뒤 마지막 숫자 add

		int minus = 0;
		int oper_idx = 0;
		int flag = 0;	//0이면 -안만난 것, 1이면 -만난 것
		score = list.get(0);	//첫번째 피연산자 더해주기
		for(int i = 1; i<list.size(); i++) {
			if(oper.get(oper_idx) == '+' && flag == 0) {	//+만났는데 -만난게 아니라면
				score = score + list.get(i);
			}
			else if(oper.get(oper_idx) == '+' && flag == 1) {	//+만났는데 -를 만난적이 있었다면
				score = score - list.get(i);
			}
			else if(oper.get(oper_idx) == '-') {	//-를 만났다면 그냥 뺄셈
				score = score - list.get(i);
				flag = 1;		// - 를 만났다는 플래그 설정
			}
			oper_idx++;			// 연산자 인덱스 증가
		}
		//55-(50+40+20+30)-10+20

		System.out.println(score);
	}
}
