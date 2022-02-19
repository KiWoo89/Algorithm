import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, R, C;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());    //2^N * 2^N 크기의 배열
        R = Integer.parseInt(st.nextToken());    //찾고자 하는 열
        C = Integer.parseInt(st.nextToken());    //찾고자 하는 행

        z_search(N, R, C);
    }

    public static void z_search(int n, int r, int c) {
        System.out.println("n : " + n + " r : " + r + " c : " + c);
        if (n == 0) {
            System.out.println("count = " + count);
            return;
        }

        int mid = (int) Math.pow(2, n) / 2;
        int sum_count = (int) Math.pow(2, 2 * (n - 1));    //더해줄 카운트 수
        int dir = 0; //0 = 왼쪽 위, 1 = 오른쪽 위, 2 = 왼쪽 아래, 3 = 오른쪽 아래
        if (mid > r && mid > c) dir = 0;
        else if (mid > r && mid <= c) dir = 1;
        else if (mid <= r && mid > c) dir = 2;
        else if (mid <= r && mid <= c) dir = 3;

        r %= mid;
        c %= mid;

        count += sum_count * dir;
        z_search(n - 1, r, c);

//        switch (dir) {
//            case 0:
//                z_search(n - 1, r, c);
//                break;
//            case 1:
//                count = count + sum_count * 1;
//                z_search(n - 1, r, c);
//                break;
//            case 2:
//                count = count + (sum_count * 2);
//                z_search(n - 1, r, c);
//                break;
//            case 3:
//                count = count + (sum_count * 3);
//                z_search(n - 1, r, c);
//                break;
//        }
    }
}