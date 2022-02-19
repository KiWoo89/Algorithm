package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


//메모리 : 12,484 KB
//실행시간 : 108 ms

class Document{
	int prior; //우선순위
	int want; //1이면 원하는 문서, 0이면 다른 문서 
	
	public Document(int prior, int want) {
		this.prior = prior;
		this.want = want;
	}
}

public class Main1 {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); //문서 개수
			int M = Integer.parseInt(st.nextToken()); //찾고자 하는 문서의 위치
			Queue<Document> qu = new LinkedList<>();  //문서를 저장할 큐
			PriorityQueue<Integer> quInt = new PriorityQueue<>(Collections.reverseOrder());	//문서의 우선순위를 기준으로 정렬할 큐
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(M==i) {	//만약 찾고자 하는 인덱스 번호와 같다면
					qu.add(new Document(num, 1));	//원하는 Document생성 후 큐에 add
					quInt.add(num);					//우선순위 큐에 우선순위 저장
				}
				else {								// 원하는 Document가 아니면
					qu.add(new Document(num, 0));	// 원하지 않는 Document생성 후 큐에 add
					quInt.add(num);
				}
			}
			
			int count = 0;					//몇 번째로 프린트 되었는지 저장
		to:	for(int i = 0; i<N; i++) {
				int pri = quInt.poll();		//우선순위가 가장 높은 수 저장
				
				while(true) {			
					if(qu.peek().prior == pri) {	//만약 큐에 저장된 문서의 우선순위가 위의 우선순위와 같다면
						Document d = qu.poll();		//Document poll
						count++;					//출력 수 증가
						if(d.want == 1) {			//원하는 문서가 맞다면
							System.out.println(count);	//프린트 했던 수 출력 후 종료
							break to;
						}
						else continue to;	//원하는문서 아니라면 다음 우선순위 찾기
						
					}				
					else {					//바라는 우선순위가 큐에 저장된 우선순위와 다르다면,		
						qu.add(qu.poll());	//큐의 맨 뒤로 보내기
						continue;
					}
				}
			}		
		}

	}
}
