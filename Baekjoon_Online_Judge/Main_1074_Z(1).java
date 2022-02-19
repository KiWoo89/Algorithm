package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 : 
//시간 : 
//풀이 아이디어 - 

public class asdf {
   static int N, R, C;
   static int count = 0;
   
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      N = Integer.parseInt(st.nextToken());   //2^N * 2^N 크기의 배열
      R = Integer.parseInt(st.nextToken());   //찾고자 하는 열
      C = Integer.parseInt(st.nextToken());   //찾고자 하는 행
      
      z_search(N, R, C);   // <- 변경
   }
   
   public static void z_search(int n, int r, int c) {
      
      if(n==1) {   
//         System.out.println("다은) n은 1!");
//         System.out.println("다은) r : " + r + ", c : " + c + ", count : " + count);
         check(r, c);   // <- 변경
         check(r, c-1);   // <- 변경
         check(r-1, c);   // <- 변경
         check(r-1, c-1);// <- 변경
         return;
      }
      
//      System.out.println("r : " + r + ", c : " + c + ", count : " + count);
      int mid_row = (int)Math.pow(2, n) / 2;
      int mid_col = (int)Math.pow(2, n) / 2;
//      System.out.println("mid_col : " + mid_col);
      int sum_count = (int)Math.pow(2, 2 * (n - 1));   //더해줄 카운트 수
      int dir=0; //0 = 왼쪽 위, 1 = 오른쪽 위, 2 = 왼쪽 아래, 3 = 오른쪽 아래
      if(r < mid_row && c < mid_col) dir = 0;         // <- 변경
      else if(r < mid_row && c >= mid_col) dir = 1;   // <- 변경
      else if(r >= mid_row && c < mid_col) dir = 2;   // <- 변경
      else if(r >= mid_row && c >= mid_col) dir = 3;   // <- 변경
//      System.out.println("dir : " + dir);
      
      switch(dir) {
         case 0:
               z_search(n-1, r, c);
               break;
         case 1:
               count = count + sum_count; 
               z_search(n-1, r, c - mid_col);   // <- 변경
               break;
         case 2:
               count = count + (sum_count*2); 
               z_search(n-1, r - mid_row, c);   // <- 변경
               break;
         case 3:
               count = count + (sum_count*3); 
               z_search(n-1, r - mid_row, c - mid_col);   // <- 변경
               break;
      }
   }
   
   public static void check(int r, int c) {
      if(r==0 && c==0) {   // <- 변경
//         System.out.println("기우) 정답 : " + count);
         System.out.println(count);
         System.exit(0);
      }
      else {
         count++;
         return;
      }      
   }
}