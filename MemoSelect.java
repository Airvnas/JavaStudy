package day02;

import java.sql.*;
import java.util.*;

import javax.net.ssl.ManagerFactoryParameters;
//모든 메모글을 가져와 출력하자.(글번호 내림차순)
public class MemoSelect {
	Connection con;
	Statement stmt;
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="scott",pwd="tiger";
	
	public MemoSelect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//----------------------
	
	public void close(){
		try {
			if(stmt!=null)stmt.close();
			if(con !=null)con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}//-----------------------------------

	public static void main(String[] args) {
		MemoSelect app = new MemoSelect();
		ArrayList<MemoVO> memoList = app.selectMemoAll();
		
		app.printMemo(memoList);
		
	}
	private ArrayList<MemoVO> selectMemoAll() {
		try {
			con =DriverManager.getConnection(url,user,pwd);
			String sql="SELECT idx,name,RPAD(msg,30,' ') msg,wdate FROM memo ORDER BY idx DESC";
			stmt=con.createStatement();
			/*boolean b= stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			System.out.println("b: "+b);//select문이면 true*/
			
			//ResultSet executeQuery():select문일 경우
			ResultSet rs= stmt.executeQuery(sql);
			ArrayList<MemoVO> arr= new ArrayList<>();
			
			//boolean next(): 커서는 결과테이블의 beforeFirst에 위치하고 있다가 next()가 호출이되면
			//				 커서를 다음칸으로 이동시키고 이동한 곳에 레코드가 있으면 true를 반환
			while(rs.next()) {
				int idx=rs.getInt("idx");
				String name= rs.getString("name");
				String msg = rs.getString("msg");
				java.sql.Date wdate = rs.getDate("wdate");
				
				MemoVO vo = new MemoVO(idx,name,msg,wdate);//vo객체==>레코드 1개(1행(row))
				arr.add(vo);
			}
			return arr;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
		
		
		
	}

	private void printMemo(ArrayList<MemoVO> memoList) {
		if(memoList!=null) {
			System.out.println("---------------------------------");
			System.out.println("\t글번호\t작성자명\t메모내용\t\t작성일");
			System.out.println("---------------------------------");
			for(MemoVO memo:memoList) {
				System.out.println("\t"+memo.getIdx()+"\t"+memo.getName()+"\t"+memo.getMsg()+"\t\t"+memo.getWdate()); 
			}
			System.out.println("---------------------------------");
		}
		
		
	}


}
