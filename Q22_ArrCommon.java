package day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q22_ArrCommon {

	public int[] getCommonItem(int[]a,int[]b) {
		ArrayList<Integer> ar=new ArrayList<>();
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<b.length;j++) {
				if(a[i]==b[j]) {
					ar.add(a[i]);
				}
			}
		}
		int c[]=new int[ar.size()];
		int size=0;
		for(int j:ar)
			c[size++]=j;
		return c;
	}
	
	public int[] getCommonItem2(int[]a,int[]b) {
		int p1=0;//a를 가리킬 포인터
		int p2=0;//b를 가리킬 포인터
		List<Integer> arrList= new ArrayList<>();
		while(p1<a.length&&p2<b.length) {
			if(a[p1]==b[p2]) {
				arrList.add(a[p1]);
				p1++;
				p2++;
			}else if(a[p1]<b[p2]) {
				p1++;
			}else {
				p2++;
			}
		}
		int []c=new int[arrList.size()];
		for(int i=0;i<arrList.size();i++)
			c[i]=arrList.get(i);
		return c;
		
	}
	

	public static void main(String[] args) {
		Q22_ArrCommon app = new Q22_ArrCommon();
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
		
		int c[]= app.getCommonItem2(a, b);
		System.out.println(Arrays.toString(c));
		
	}

	
}
