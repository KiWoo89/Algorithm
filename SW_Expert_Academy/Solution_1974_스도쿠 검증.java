package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
//메모리 : 18,240 KB
//시간 : 102 ms
//풀이 방법 : 행 9 개 먼저 PriorityQueue에 넣어서 합이 45가 나오면 넘어가게 9번반복, 열도 같은 방식으로 9번반복, 3*3배열도 같은식으로 반복
 
public class Solution1 {
    static int[][] array;
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++) {
             
            array = new int[9][9];
            for(int i = 0; i<9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j<9; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            boolean row_check = check_row();
            boolean col_check = check_col();
            boolean rec_check = check_rec();
             
            if(row_check && col_check && rec_check) System.out.println("#"+t+" " + 1);
            else System.out.println("#"+t+" " + 0);
        }
    }
     
    public static boolean check_row() {
        for(int i = 0; i<9; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();  //입력된 수의 인덱스 값을 증가시킴. 0번인덱스 안씀
            for(int j = 0; j<9; j++) {
                int n = array[i][j];
                pq.offer(n);
            }
             
            int sum = 0;
            for(int j = 0; j<9; j++) {   //해당 행에 1~9까지 한번씩만 들어왔는지 체크. 아니라면 false리턴
                sum = sum + pq.poll();
            }
             
            if(sum != 45) return false;
        }
        return true;
         
    }
     
    public static boolean check_col() {
        for(int j = 0; j<9; j++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();  //입력된 수의 인덱스 값을 증가시킴. 0번인덱스 안씀
            for(int i = 0; i<9; i++) {
                int n = array[i][j];
                pq.offer(n);
            }
             
            int sum = 0;
            for(int i = 0; i<9; i++) {   //해당 행에 1~9까지 한번씩만 들어왔는지 체크. 아니라면 false리턴
                sum = sum + pq.poll();
            }
             
            if(sum != 45) return false;
        }
        return true;
    }
 
    private static boolean check_rec() {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        PriorityQueue<Integer> pq3 = new PriorityQueue<>();
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<3; j++) {
                pq1.offer(array[i][j]);
            }
            for(int j = 3; j<6; j++) {
                pq2.offer(array[i][j]);
            }
            for(int j = 6; j<9; j++) {
                pq3.offer(array[i][j]);
            }
            int sum1=0;
            int sum2=0;
            int sum3=0;
            if(i == 2 || i == 5 || i == 8) {
                for(int k = 0; k<9; k++) {   //해당 행에 1~9까지 한번씩만 들어왔는지 체크. 아니라면 false리턴
                    sum1 = sum1 + pq1.poll();
                    sum2 = sum2 + pq2.poll();
                    sum3 = sum3 + pq3.poll();
                }               
                if(sum1 != 45 || sum2 != 45 || sum3 != 45) return false;
            }
        }
        return true;
    }
 
     
     
}