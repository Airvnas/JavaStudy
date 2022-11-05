package day07;

import java.util.Arrays;
import java.util.Scanner;

public class Q21_ArrMerge {

	public static void main(String[] args) {
		Q21_ArrMerge app = new Q21_ArrMerge();
		Scanner sc =new Scanner(System.in);
		System.out.println("배열 1의 개수: ");
		int n= sc.nextInt();
		System.out.println("배열 2의 개수: ");
		int m= sc.nextInt();
		
		int[] a=new int[n];
		int[] b=new int[m];
		
		System.out.println("배열 1에 저장할 값 입력: ");
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		System.out.println("배열 2에 저장할 값 입력: ");
		for(int i=0;i<m;i++) {
			b[i]=sc.nextInt();
		}
		System.out.println(Arrays.toString(app.merge(a, b)));
	}
	
	public int[] merge(int[]a, int[]b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int[] res= new int[a.length+b.length];
		System.arraycopy(a, 0, res, 0, a.length);
		System.arraycopy(b, 0, res, a.length, b.length);
		Arrays.sort(res);
		
		return res;
	}
	public int[] merge2(int[]a, int[]b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int pa=0;
		int pb=0;
		int c[]=new int[a.length+b.length];
		int pc=0;
		while(pa<a.length && pb<b.length) {
			c[pc++]=(a[pa]<b[pb])? a[pa++]: b[pb++];
		}
		if(pa<a.length) c[pc++]=a[pa++];
		if(pb<a.length) c[pc++]=a[pb++];
		return c;
	}
}
