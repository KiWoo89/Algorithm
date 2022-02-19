package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//메모리 : 30,648KB
//실행시간 : 188ms

public class Main1 {
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// N개의 숫자
		int K = sc.nextInt();	// K만큼 떨어진 수 수열
		StringBuilder sb = new StringBuilder();
		
		Queue<Integer> qu = new LinkedList<Integer>();

		for(int i = 1; i<=N; i++) {
			qu.add(i);	//queue 초기화
		}
		
		sb.append("<");
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<K-1; j++) {	//본인 숫자도 포함하기 위해 K-1번 반복
				int num = qu.poll();		//head가 가르키는 숫자 빼기
				qu.add(num);				//뺀 숫자 다시 rear로 넣기
			}
			if(i == N-1) sb.append(qu.poll() + ">");	//마지막 큐에 남은 숫자라면
			else sb.append(qu.poll() + ", ");
		}
		
		System.out.println(sb.toString());
		
	}
}
