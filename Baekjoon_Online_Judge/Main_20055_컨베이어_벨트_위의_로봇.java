package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/* 메모리 : 15048
 * 시간 : 232
 * 풀이 방법 : 
 * 
 */

public class Main1 {
	
	
	static int N, K;
    static int[] belt;	//내구도 저장
    static int[] robot;	//로봇 위치 저장
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());	//컨베이어 벨트 길이. 고무 = N*2
        K = Integer.parseInt(stringTokenizer.nextToken());	//종료조건. 내구도 0의 개수

        belt = new int[2*N+1];		// 회전하는 벨트 길이는 N*2
        robot = new int[N+1];		// 로봇은 N위치에서 떨어짐. 1이면 로봇 존재, 0이면 로봇 없음

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= 2*N ;i++){
            belt[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        
        int result = 0;	//단계를 수행한 횟수 카운트
        
        while(true){
            result++;	//단계 카운트

            int tmp = belt[2*N];	//마지막 인덱스 값 임시 저장
            for(int i = 2*N ; i > 1 ; i--){	// 컨베이너 벨트 이동
                belt[i] = belt[i-1];
            }
            belt[1] = tmp;		//첫번째 벨트 위치로 저장

            for(int i = N ; i > 1; i--){	// 로봇이동
                robot[i] = robot[i-1];
            }
            robot[1] = 0;	//이동을 완료하면 첫번째 위치에는 로봇이 없음
            
            
            if(robot[N] == 1)	//벨트의 내리는 위치에 로봇이 있으면 로봇을 내린다
                robot[N] = 0;

            for(int i = N-1 ; i >= 1 ; i--){	        // 로봇 이동
                if(robot[i+1] == 0 && robot[i] == 1 && belt[i+1] > 0){	//다음 위치에 로봇이 없고, 현재 위치에 로봇이 있으며, 다음 위치 벨트 내구도가 0보다 크다면,
                    robot[i] = 0;
                    robot[i+1] = 1;
                    belt[i+1]--;
                }
            }

            if(robot[1] == 0 && belt[1] != 0){	// 올리는 위치에 로봇이 없고, 내구도가 0이 아니면 로봇 올리기
                robot[1] = 1;
                belt[1]--;
            }
            
            int cnt = 0;	//내구도 0인 지점 카운트
            for(int i = 1 ; i <= 2*N ; i++){
                if(belt[i] == 0)	//내구도가 0이라면 카운트
                    cnt++;
            }
            if(cnt >= K) break;	//종료 조건만큼 내구도가 0인 지점이 있다면 while문 탈출
                     
        }

        System.out.println(result);
        
    }
		
	

}
