package day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Player implements Comparable<Player>{
	int height;
	int weight;
	
	public Player(int h,int w){
		this.height=h;
		this.weight=w;
	}
	@Override
	public int compareTo(Player o) {
		return o.height-this.height;
	}
	
}

//178 66
//165 78
//182 67
//171 70
//180 59
public class Q24_PlayerSelect {
	public static List<Player> choice(List<Player> arr,int n) {
		int cnt=0;
		Collections.sort(arr);
		int max=0;
		List<Player> choiceArr=new ArrayList<>();
		for(Player p:arr) {
			if(p.weight>max) {
				max=p.weight;
				choiceArr.add(p);
				cnt++;
			}
		}
		
		return choiceArr;
	}
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("씨름선수 후보인원수: ");
		int n=sc.nextInt();
		System.out.println("키와 몸무게 순서로 입력하세요");
		List<Player> arr= new ArrayList<>();
		for(int i=0;i<n;i++) {
			int h=sc.nextInt();
			int w=sc.nextInt();
			Player p=new Player(h,w);
			arr.add(p);
		}
		List<Player> choiceArr = choice(arr,n);
		
		System.out.println("선발 가능한 선수: "+choiceArr.size()+"명");
		for(Player p:choiceArr) {
			System.out.println(p.height+" : "+p.weight);
		}
	}

}
