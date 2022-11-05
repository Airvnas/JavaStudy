package day07;
import java.util.*;
public class Q20_ArrMaxSum {
	
	public int getArrayMaxSum(int n,int[][] arr) {
		int[] sum=new int[2*n+2];
		int i=0;
		for(i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sum[i]+=arr[i][j];
			}
		}
		for(int k=0;k<n;k++) {
			for(int j=0;j<n;j++) {
				sum[i]+=arr[j][k];
			}i++;
		}for(int k=0;k<n;k++) {
			sum[i]+=arr[k][k];
		}i++;
		for(int k=0;k<n;k++) {
			sum[i]+=arr[k][n-1-k];
		}
		System.out.println(Arrays.toString(sum));
		
			for(int j=0;j<sum.length-1;j++) {
				if(sum[j]>sum[j+1]) {
					int tmp=sum[j];
					sum[j]=sum[j+1];
					sum[j+1]=tmp;
				}
			}
		
		return sum[2*n+2-1];
	}//-----------------------------------------------
	
	
	public static void main(String[] args) {
		Q20_ArrMaxSum app=new Q20_ArrMaxSum();
		Scanner sc =new Scanner(System.in);
		System.out.println("배열의 크기 입력: ");
		int n= sc.nextInt();
		int[][]arr = new int[n][n];
		System.out.printf("%d행%d열 배열에 저장할 값 입력: %n",n,n);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int rand= (int) (Math.random()*100)+1;
				arr[i][j]=rand;
				System.out.print(arr[i][j]+" ");
			}System.out.println();
		}
		
		//app.getArrayMaxSum(n, arr);
		System.out.println(app.getArrayMaxSum(n, arr));
	}//------------------------------------------------
}
