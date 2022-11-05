package day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Time implements Comparable<Time>{
	int start;
	int end;
	public Time(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Time o) {
		if(this.end==o.end)//끝나는 시간이 같으면
			return this.start-o.start;//시작시간으로 오름차순 정렬
		return this.end - o.end; //끝나는 시간 오름차순 정렬
	}
	@Override
	public String toString() {
		return "Time [start=" + start + ", end=" + end + "]";
	}
	
}/////////////////////////////

public class Q25_Meeting {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<Time> arr=new ArrayList<>();
		
		System.out.println("회의 수: ");
		int n=sc.nextInt();
		System.out.println("회의 시작시간 종료시간 입력:");
		for(int i=0;i<n;i++) {
			int s=sc.nextInt();
			int e=sc.nextInt();
			arr.add(new Time(s,e));
		}
		
		Q25_Meeting app=new Q25_Meeting();
		System.out.println(app.assign(arr, n));

	}

	private int assign(List<Time> arr, int n) {
		int cnt=0;
		Collections.sort(arr);//정렬함
		int endTime=0;
		for(Time obj: arr) {
			if(obj.start>=endTime) {
				cnt++;
				System.out.println(obj);
				endTime=obj.end;
			}
		}
		return cnt;
	}

}
