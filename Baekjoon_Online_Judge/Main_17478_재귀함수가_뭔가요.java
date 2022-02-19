package test;

import java.util.Scanner;

//메모리 : 26588KB, 시간 : 116ms
public class Main {
	public static int n;
	
	public static String recur(int num) {
		String s = "";
		String s1 = "\"재귀함수가 뭔가요?\"\n";
		String s2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
		String s3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
		String s4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
		String s5 = "라고 답변하였지.\n";
		String s6 = "";
		if(num==0) {
			for(int i = n; i>0; i--) {
				s6 = s6 + "____";
			}
			s = s6 + s1 + s6 + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n" + s6 + s5;
			return s;
		}
		
		for(int i = num; i<n; i++) {
			s6 = s6 + "____";
		}
		
		s = s6 + s1 + s6 + s2 + s6 + s3 + s6 + s4;
		s = s + recur(num-1);
		s = s + s6 + s5;
	
		return s;
	}

			
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		System.out.println(recur(n));		
		
	}
	
}

	 
