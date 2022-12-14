package day02;
import java.sql.*;
import java.util.*;
public class MemoUpdate {
	
	private Connection con;
	private Statement stmt;
	
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="scott",pwd="tiger";
	public MemoUpdate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//생성자--------------
	
	
	public void close(){
		try {
			if(stmt!=null)stmt.close();
			if(con !=null)con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}//-----------------------------------
	
	public int updateMemo(MemoVO memo) {
		try {
			con =DriverManager.getConnection(url,user,pwd);
			String sql="update memo set name='"+memo.getName()+"',msg='"+memo.getMsg()+
					"',wdate=sysdate where IDX="+memo.getIdx();
			stmt=con.createStatement();
			int b=stmt.executeUpdate(sql);
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			close();
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 글 번호 입력");
		int idx= sc.nextInt();
		System.out.println("수정할 작성자명: ");
		String name= sc.next();
		//nextLine()을 쓰기위해 엔터값 건너뛰기
		sc.skip("\r\n");
		System.out.println("수정할 메모내용: ");
		String msg= sc.nextLine();
		System.out.println(idx+"/"+name+"/"+msg);
		//VO 객체에 입력받은 값 담아주기
		MemoVO vo = new MemoVO(idx,name,msg,null);
		
		MemoUpdate app= new MemoUpdate();
		int n=app.updateMemo(vo);
		String res=(n>0)?"글 수정 성공":"수정 실패";
		System.out.println(res);
		
	}

}











