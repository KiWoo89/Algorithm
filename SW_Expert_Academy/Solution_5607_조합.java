package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//메모리 : 27,236 KB
//시간 : 126 ms
// 페르마의 소정리


public class Main {
	static int T,N,R;
	static final long MOD = 1234567891;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        long[] factorial = new long[1000001];
        
        factorial[0] = 1;	// 초기화
        for(int i = 1; i <= 1000000; i++){	//factorial 값 저장
            factorial[i] = (i * factorial[i-1]) % MOD;
        }
        
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
 
            // nCr % p = (n! / ((n-r)! * r!)) % p
            //			=> (n!%p * (n-r)!^(p-2)%p * r!^(p-2)%p)%p
            // n!%p => factorial[N]
            // (n-r)! % p => factorial(N-R)
            // r! % p => factorial(R)
             
            long result = (factorial[N] * pow( (factorial[R]*factorial[N-R]) % MOD, 1234567891-2)) % MOD;
            sb.append("#").append(t).append(" ").append(result).append("\n");
            
            
        }
        System.out.println(sb.toString());
 
    }
 

    public static long pow(long a, long b){	// x^y 를 구하기 위한 분할정복 알고리즘
        if(b == 0) {
            return 1;
        }
        
        long temp = pow(a,b/2);
        long result = (temp * temp) % MOD;
        if(b % 2 == 0)	// 짝수 승이라면 
            return result;
        else	// 홀수 승이라면 a 한번 더 곱하기
            return (result * a) % 1234567891;
    }
}