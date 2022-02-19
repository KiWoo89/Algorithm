package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//시간복잡도 : 
//메모리 : 11,564KB
//시간 : 80ms
//풀이 아이디어 - 일단 전체 넓이를 구하기 위해 입력 받은 정수 중 가장 큰 값을 저장함. 길이가 최대 값의 인덱스 왼쪽 오른쪽의 값을 이용하면
			//꺾인 부분의 한 변의 길이를 구할 수 있었음. 길이가 최대 값의 인덱스 왼쪽 오른쪽의 값 중 값이 더 큰 쪽이 반듯한
			//면이 되어 전체 넓이를 구할 수 있고, 더 나아가 그 인덱스에서 한칸 더 간 값을 최대 길이에서 빼면 또 다른 꺾인 부분의 한
			//변을 구할 수 있었다. 결국 배열의 index를 이용해서 풀었음.

public class test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int W = Integer.parseInt(br.readLine());	//참외 몇개 자라는지
		ArrayList<int[]> list = new ArrayList<>();	//[0] = 동서남북, [1]=길이
		
		for(int i = 0; i<6; i++) {	//값을 int[] 형태로 add
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});	
		}
		
		int max1 = 0;	//최대 값을 가지는 변의 길이 저장 변수
		int index=0; //최대값의 인덱스 구하기
		for(int i = 0; i<6; i++) {
			if(list.get(i)[1] > max1) {
				max1 = list.get(i)[1];	//최대값 -> 반듯한 변 중 가장 긴 부분
				index = i;				//최대값을 가지는 인덱스 
			}
		}
		
		int max2=0; //넓이를 구하기 위해 최대값 옆 면 중 멀쩡한 면 구하기
		int max1_left=0;	//꺾인 면을 제외한 길이
		int max2_right=0;	//꺾인 면을 제외한 길이 (max와 max2_right의 차이와 max2와 max_left의 차이를 곱하면 빠진 부분의 넓이이다)
		
		int index_temp = index;	//최대값 인덱스 잠시 저장할 변수
		
		if(index_temp == 0) index_temp = 6;	//만약 최대값 변수가 0이면 음수가 나오지 않게 하기 위해 6으로 초기화
		if(list.get(((index-1)%6))[1] > list.get((index+1)%6)[1]) {	//넓이를 구하기 위해 최대값 옆 면 중 멀쩡한 면 구하기
																//최대 면의 왼쪽이 더 크다면 전체 넓이에 사용.
			if(index_temp == 0) index_temp = 6;			//만약 최대 값 인덱스가 0이면 -1 또는 -2할 때 인덱스가 맨 뒤로 가야 하기 때문에 값 조정.
			max2 = list.get((index_temp-1)%6)[1];		
			
			if(index_temp == 0) index_temp = 6;
			if(index_temp == 1) index_temp = 7;
			max2_right = list.get((index_temp-2)%6)[1];
			max1_left = list.get((index+1)%6)[1];
		}
		else {
			if(index_temp == 0) index_temp = 6;
			max2 = list.get((index+1)%6)[1];
			
			if(index_temp == 0) index_temp = 6;
			if(index_temp == 1) index_temp = 7;
			max2_right = list.get((index+2)%6)[1];
			max1_left = list.get((index_temp-1)%6)[1];
		}
		
		
		int width = max1 * max2; //넓이
		int sub_width = (max2-max1_left) * (max1-max2_right);	//서브 넓이
		int result = width - sub_width;	//참외 키우는 실제 밭의 넓이
		System.out.println(result * W);		//해당 밭을 이용해서 수확할 수 있는 참외 개수 출력
		
		
		
		
		
		//int max2 = (list.get((index-1)%7)[1] > list.get((index+1)%7)[1]) ? (list.get((index-1)%7)[1]) : (list.get((index+1)%7)[1]);	//넓이를 구하기 위해 최대값 옆 면 중 멀쩡한 면 구하기
		
//		int[] counts = new int[5];	//동서남북(1234) 나온 횟수 별로 카운트 해서 어디가 2번 나온지 체크
//											//2번 카운트 된 곳은 꺾인 곳으로 그곳 넓이를 구해서 전체 넓이에서 빼줘야함.
//		for(int i = 0; i<6; i++) {	//카운트하기
//			int in = list.get(i)[0];
//			counts[in]++;
//		}
//		
//		int[] direction = new int[2]; //꺾인 방향 저장
//		int p = 0; //방향 인덱스
//		for(int i = 1; i<5; i++) {
//			if(counts[i]==2) {
//				direction[p++] = i;
//			}
//		}		
		
	}		
	
}
