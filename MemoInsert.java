package day01;
import java.sql.*;
import javax.swing.*;

public class MemoInsert {

	public static void main(String[] args) 
	throws ClassNotFoundException,SQLException
	{
		String name = JOptionPane.showInputDialog("작성자 입력하세요");
		String msg = JOptionPane.showInputDialog("메시지를 입력하세요");
		if(name==null) return;
		
		//memo테이블에 insert문을 작성해서 메모글을 insert하는 프로그램을 완성하세요
		//1.홍길동 첫번째 작성한 글입니다 sysdate
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loading Success");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott",pwd="tiger";
		Connection con=DriverManager.getConnection(url,user,pwd);
		System.out.println("DB Connected");
		String sql = "INSERT INTO memo VALUES (memo_seq.nextval,'"+name+"','"+msg+"',sysdate)";
		Statement stmt = con.createStatement();
		//boolean b=stmt.execute(sql);//select문일때만 true를 반환
		int b= stmt.executeUpdate(sql);//sql문에 의해 영향받은 레코드 개수를 반환
		System.out.println("b: "+b+"개의 레코드를 넣었습니다.");
		stmt.close();
		con.close();
		
	}

}
