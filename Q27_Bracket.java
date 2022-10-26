package day08;

import java.util.Scanner;
import java.util.Stack;

public class Q27_Bracket {
	
	public String solution(String str) {
		Stack<Character> st= new Stack<>();
		int cnt=0;
		String res="";
		char[] ch = str.toCharArray();
		for(int i=0;i<ch.length;i++) {
			if(ch[i]==')' ) {
				if(!st.isEmpty()&& cnt>0) {
					while(st.peek()!='(')
						st.pop();
				}st.pop();
				cnt--;
			}
			else {
				st.add(ch[i]);
				cnt++;
			}
		}
		while(!st.isEmpty())
			res+=st.pop();
		StringBuffer sb = new StringBuffer(res);
		res = sb.reverse().toString();
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println("문자열 입력:");
		Scanner sc = new Scanner(System.in);
		String str= sc.next();
		Q27_Bracket app= new Q27_Bracket();
		System.out.println(app.solution(str));
		
		
	}

}