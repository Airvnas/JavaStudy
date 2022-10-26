package day08;
import java.util.*;
import java.util.Stack;

public class Q26_Bracket {
	
	public String solution(String str) {
		Stack<Character> st= new Stack<>();
		char[] ch = str.toCharArray();
		for(int i=0;i<ch.length;i++) {
			if(ch[i]=='(')
				st.add(ch[i]);
			else if(ch[i]==')') {
				if(!st.isEmpty())
					st.pop();
				else {
					return "No";
				}
			}
		}
		if(!st.isEmpty()) return "NO";
		else return "Yes";
	}
	
	public static void main(String[] args) {
		System.out.println("괄호를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String str= sc.next();
		Q26_Bracket app= new Q26_Bracket();
		System.out.println(app.solution(str));
		
		
	}

}
