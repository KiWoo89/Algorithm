package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 12,084 KB
//시간 : 92 ms

//접근 방법 : list에 int[]를 제네릭으로 받아서 성별과 학년을 함께 집어넣고 카운팅을 하는 방법과, 단순히 int[]배열을 남자 여자 2개를
//따로 생성해서 카운팅하는 방법 중에 고민했다. int[]배열 2개 만드는게 더 간편해 보여서 그렇게 관리하고 카운팅했다.
public class Algo {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] woman = new int[7];
		int[] man = new int[7]; // 1학년부터 6학년까지 인원 카운트. 0인덱스는 사용 X
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int Y = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			if(Y==0) {	//입력이 여학생일 때
				woman[grade]++;	//해당 학년 카운트 증가
			}
			else {		//입력이 남학생일 때
				man[grade]++;
			}
			
		}//for_i end
		
		int count = 0;	//필요한 방의 개수 카운트
		
		for(int i = 1; i<=6; i++) {	//여자 필요한 방 카운트
			count = count + (woman[i] / K);	//카운트 나누기 방 최대 수용 인원수를 구해서 필요한 방을 구함.
			if(woman[i] % K != 0) count++;	//만약 최대 수용 인원수로 나눈 나머지가 존재한다면 필요한 방 한개 더 카운트
		}
		for(int i = 1; i<=6; i++) {	//남자 필요한 방 카운트
			count = count + (man[i] / K);
			if(man[i] % K != 0) count++;
		}
		
		System.out.println(count);		
	}
}