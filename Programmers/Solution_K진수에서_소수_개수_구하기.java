package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


//메모리 : 
//시간 : 
//풀이방법 : 


class Solution {
    public boolean checkPrime(long l){ //소수 체크 메소드
        if(l <= 1) return false;
        
        for(int i = 2; i<=Math.sqrt(l); i++){   //나누어 떨어지는지 체크
            if(l % i == 0) return false;
        }
        return true;
    }
    
    public int solution(int n, int k) {   
        int answer = 0;
        String str = "";	//진법 저장할 변수
        
        while(n>0){ //진법 변환
            str = n % k + str;
            n = n / k;
        }
        
        String strings[] = str.split("0");  //0기준으로 분해
        
        for(String s : strings){		
            if(s.equals("")) continue;	//0기준으로 나눴을 때 0을 지우니 ""가 저장될 때 처리
            long l = Long.parseLong(s);	//n이 최대 100만인데, 이걸 k진법으로 바꾸면 엄청 길게 분해된 경우도 있기 때문
            if(checkPrime(l)) answer++;	//소수가 맞으면 카운트
        }     
        return answer;
    }
   
}
