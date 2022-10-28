package day02;
//VO(Value Object),DTO(DATA Transfer Object)
//domain 객체
//db table의 record ==> 클래스로 옮겨 구성
import java.sql.Date;
public class MemoVO {
	private int idx;
	private String name;
	private String msg;
	private java.sql.Date wdate;
	
	
	public MemoVO() {
		
	}//기본생성자 반드시 생성해둘것!
	public MemoVO(int idx,String name, String msg,Date wdate) {
		this.idx=idx;
		this.msg=msg;
		this.name=name;
		this.wdate=wdate;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public java.sql.Date getWdate() {
		return wdate;
	}
	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}
	

}
