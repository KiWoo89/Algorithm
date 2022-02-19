package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		
		
		for(int i = 1; i<num; i++) {
			int intt = i;

			if(intt/1000000 != 0) {
				list.add(intt/1000000);
				intt = intt - (intt/1000000) * 1000000;
			}
			if(intt/100000 != 0) {
				list.add(intt/100000);
				intt = intt - (intt/100000) * 100000;
			}
			if(intt/10000 != 0) {
				list.add(intt/10000);
				intt = intt - (intt/10000) * 10000;
			}
			if(intt/1000 != 0) {
				list.add(intt/1000);
				intt = intt - (intt/1000) * 1000;
			}
			if(intt/100 != 0) {
				list.add(intt/100);
				intt = intt - (intt/100) * 100;
			}
			if(intt/10 != 0) {
				list.add(intt/10);
				intt = intt - (intt/10) * 10;
			}
			
			list.add(intt%10);
			
			
			
			int n = i;
			for(int j = 0; j<list.size(); j++) {
				n = n + list.get(j);
			}
			if(num == n) {
				System.out.println(i);
				return;
			}
			else {
				list.removeAll(list);
				continue;
			}
			
		}
		System.out.println(0);
	}
	
}
	
