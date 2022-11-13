package my.book;

import java.util.ArrayList;

public class Member {
	private String name;
	private String pwd;
	private ArrayList<String> list;
	private boolean isLogin;
	private String str;
	public Member(String n, String pwd) {
		this.name=n;
		this.pwd=pwd;
		this.list= new ArrayList<>();
		this.isLogin=false;
		this.str="";
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public ArrayList<String> getList() {
		return list;
	}
	public boolean isList(String list) {
		return this.list.contains(list);
	}
	public int getSize() {
		return this.list.size();
	}
	public void removeList(String list) {
		this.list.remove(list);
	}
	public void setList(String list) {
		this.list.add(list);
	}

	
}////////////////////////////////////////