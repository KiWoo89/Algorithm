package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리 : 55,096 KB
//시간 : 480 ms
//풀이 아이디어 - 크기와 CCTV의 개수가 그렇게 크지 않아서 브루트포스로 모든 경우를 다 시도해봄

public class test {
	static int N, M;
	static int[][] array; // 실제 배열
	static ArrayList<int[]> list; // CCTV의 위치 저장할 배열
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		list = new ArrayList<>(); // CCTV들 저장할 것 [0] = 행, [1] = 열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] != 0 && array[i][j] != 6) list.add(new int[] { i, j }); // CCTV의 위치 저장
			}
		}

		if (list.size() == 0) { // cctv가 없다면 0 개수 세고 출력
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (array[i][j] == 0)
						sum++;
				}
			}
			System.out.println(sum);
			System.exit(0);
		}

		checkRange(0, array);
		System.out.println(min);
	}

	public static void checkRange(int cnt, int[][] current_arr) { // CCTV가 확인하는 범위 체크(재귀)

		int row = list.get(cnt)[0]; // CCTV의 행
		int col = list.get(cnt)[1]; // CCTV의 열
		int type = array[row][col]; // CCTV의 타입
		int[][] temp_arr = new int[N][M]; // 입력 받은 배열의 값을 복사하여, CCTV의 영역을 반복적으로 체크하는 임시 비열

		for (int d = 0; d < 4; d++) { // 4개 방향 별로 CCTV영역 체크하면서 재귀 호출
			copyArray(current_arr, temp_arr); // 함수 실행 전 배열 값 복사
			drawArray(temp_arr, type, row, col, d); // 해당 방향의 CCTV영역체크
			if (cnt == list.size() - 1) { // CCTV들이 어떤 특정 방향의 조합으로 CCTV목록의 마지막 CCTV까지 #표시를 했다면,
				countArray(temp_arr); // 그때의 CCTV사각지대 개수 카운팅
			}

			for (int i = cnt + 1; i <= list.size() - 1; i++) { // 남은 CCTV개수 까지 재귀 호출
				checkRange(i, temp_arr);
			}
			//copyArray(current_arr, temp_arr); // 함수 실행 전 배열 값 복사

		}
	}


	public static void drawArray(int[][] temp_arr, int type, int row, int col, int direction) { // #표시 메소드

		if (direction == 0) { // 문제에서 보여준 방향대로
			if (type == 1) { // 오른쪽
				right(temp_arr, row, col);
			} 

			else if (type == 2) { // 왼쪽 + 오른쪽
				left(temp_arr, row, col);
				right(temp_arr, row, col);
			}

			else if (type == 3) { // 위 오른쪽
				up(temp_arr, row, col);
				right(temp_arr, row, col);
			} else if (type == 4) { // 왼쪽 위 오른쪽
				left(temp_arr, row, col);
				up(temp_arr, row, col);
				right(temp_arr, row, col);
			} else { // type == 5 왼쪽 위 오른쪽 아래
				left(temp_arr, row, col);
				up(temp_arr, row, col);
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			}
		}

		else if (direction == 1) {
			if (type == 1) { // 아래
				down(temp_arr, row, col);
			} else if (type == 2) { // 위 아래
				up(temp_arr, row, col);
				down(temp_arr, row, col);
			} else if (type == 3) { // 오른쪽 아래
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			} else if (type == 4) { // 위 오른쪽 아래
				up(temp_arr, row, col);
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			} else { // type == 5 //4방
				left(temp_arr, row, col);
				up(temp_arr, row, col);
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			}
		}

		else if (direction == 2) {
			if (type == 1) { // 왼쪽
				left(temp_arr, row, col);
			} else if (type == 2) { // 왼쪽 오른쪽
				left(temp_arr, row, col);
				right(temp_arr, row, col);
			} else if (type == 3) { // 아래 왼쪽
				down(temp_arr, row, col);
				left(temp_arr, row, col);
			} else if (type == 4) { // 오른쪽 아래 왼쪽
				right(temp_arr, row, col);
				down(temp_arr, row, col);
				left(temp_arr, row, col);
			} else { // type == 5
				left(temp_arr, row, col);
				up(temp_arr, row, col);
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			}
		}

		else if (direction == 3) { // 방향이 3번째 꺼 일 때
			if (type == 1) { // 위
				up(temp_arr, row, col);
			} else if (type == 2) { // 위 아래
				up(temp_arr, row, col);
				down(temp_arr, row, col);
			} else if (type == 3) { // 왼쪽 위
				left(temp_arr, row, col);
				up(temp_arr, row, col);
			} else if (type == 4) { // 아래 왼쪽 위
				down(temp_arr, row, col);
				left(temp_arr, row, col);
				up(temp_arr, row, col);
			} else { // type == 5
				left(temp_arr, row, col);
				up(temp_arr, row, col);
				right(temp_arr, row, col);
				down(temp_arr, row, col);
			}
		}

	}

	public static void countArray(int[][] temp_arr) { // 안전 영역 개수 확인

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp_arr[i][j] == 0) { // 만약 해당 위치가 CCTV가 못보는 0이라면
					sum++; // 사각지대 증가
				}
			}
		}
		if (sum < min) {
			min = sum; // 기존 최소 사각지대 보다 더 작다면 변경
		}

	}

	public static void copyArray(int[][] arr, int[][] temp_arr) { // 해당 반복에서의 배열 값을 임시 배열로 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp_arr[i][j] = arr[i][j];
			}
		}
	}

	public static void right(int[][] temp_arr, int row, int col) {
		for (int j = col + 1; j < M; j++) { // 자기 자신 다음부터 체크하기 위해서 +1
			if (temp_arr[row][j] == 0)
				temp_arr[row][j] = -1; // int 2차원 배열이라 #대신 -1 넣음
			else if (temp_arr[row][j] == 6)
				return; // 벽을 만나면 더이상 나갈 수 없기 때문에 리턴
			else if (temp_arr[row][j] != -1)
				continue; // 해당 배열 값이 0도 아니고 6도 아니고 -1도 아니면 CCTV이기 때문에 무시하고 넘어가기
		}
	}
	
	public static void down(int[][] temp_arr, int row, int col) {
		for (int i = row + 1; i < N; i++) { // 자기 자신 다음부터 체크하기 위해서 +1 //아래
			if (temp_arr[i][col] == 0)
				temp_arr[i][col] = -1;
			else if (temp_arr[i][col] == 6)
				return;
			else if (temp_arr[i][col] != -1)
				continue;
		}
	}
	
	public static void left(int[][] temp_arr, int row, int col) {
		for (int j = col - 1; j >= 0; j--) { // 자기 자신 다음부터 체크하기 위해서 -1 //왼쪽
			if (temp_arr[row][j] == 0)
				temp_arr[row][j] = -1;
			else if (temp_arr[row][j] == 6)
				return;
			else if (temp_arr[row][j] != -1)
				continue;
		}
	}
	
	public static void up(int[][] temp_arr, int row, int col) {
		for (int i = row - 1; i >= 0; i--) { // 자기 자신 다음부터 체크하기 위해서 -1 //위
			if (temp_arr[i][col] == 0)
				temp_arr[i][col] = -1;
			else if (temp_arr[i][col] == 6)
				return;
			else if (temp_arr[i][col] != -1)
				continue;
		}
	}
}
