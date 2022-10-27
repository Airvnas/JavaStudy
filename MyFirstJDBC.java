package day01;
import java.sql.*;
/*
 * 
 * 이클립스에서 
 * */

public class MyFirstJDBC {

	public static void main(String[] args) {
		try {
		//1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			//프로토콜:dbms유형:driver타입:host:port:전역데이터베이스이름
			
			String user="scott",pwd="tiger";
		//2.DB와 연결=>DriverManager클래스의 getConnetion()를 이용
			
			Connection con=DriverManager.getConnection(url,user,pwd);
			System.out.println("DB Connected...");
			
		//3.Query문 작성-SQL문
			String sql = "CREATE TABLE memo(";
				   sql+="idx number(4) primary key,";//글번호
				   sql+="name varchar2(30) not null,";//작성자
				   sql+="msg varchar2(100),";
				   sql+="wdate date default sysdate)";
		//4.Statement객체 얻기 => Connection의 createStatement()를 이용
			Statement stmt= con.createStatement();
		//5.Statement의 execute()/executeXXX()메서드를 이용해서 쿼리문을 실행시킨다.
		//boolean execute(): 모든 sql문을 실행시킨다.
		//int executeUpdate(): insert/delete/update문을 실행시킨다
		//ResultSet executeQuery(): select문을 실행시킨다.
			boolean b=stmt.execute(sql);
			System.out.println("b: "+b);
		//6.DB 관련 자원 반납- 순서중요
			stmt.close();
			con.close();//필수!
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
