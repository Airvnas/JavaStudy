package day07;

import java.util.Scanner;

public class Q23_SlidingWindow {
	
	public static int getMaxOut(int n,int k,int[]a ) {
		int max=0;int sum=0;
		for(int i=0;i<a.length-k+1;i++) {
			sum=0;
			for(int j=i;j<i+k;j++) {
				sum+=a[j];
			}
			System.out.print(sum+" ");
			max=(max>sum)?max:sum;
		}System.out.println();
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("며칠의 매출기록을 입력? ");
		int n= sc.nextInt();
		System.out.println("연속 며칠의 매출을 구할까요? ");
		int k= sc.nextInt();
		System.out.println(n+"일간의 기록을 입력하세요");
		int[] a=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		int max=getMaxOut(n,k,a);
		System.out.println("연속 "+k+"일의 최대매출액: "+max);
		
	}

}
