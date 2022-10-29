package day02;

import java.sql.*;
import java.util.*;

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
		app.printMemo(memoList);//테이블의 모든 자료 출력
		
		
		app = new MemoSelect();
		Scanner sc =new Scanner(System.in);
		System.out.println("검색할 번호를 입력하세요.");
		int idx= sc.nextInt();
		MemoVO vo = app.selectMemo(idx);
		app.printMemo(vo);//검색한 idx를 단일행 받아와 출력
		
		app = new MemoSelect();
		System.out.println("검색할 메시지를 입력하세요.");
		sc.skip("\r\n");
		String str= sc.nextLine();
		System.out.println(str);
		memoList = app.findMemoByMsg(str);
		app.printMemo(memoList);//검색한 메시지를 다중행 받아와 출력
		
		app = new MemoSelect();
		System.out.println("검색할 이름을 입력하세요.");
		//sc.skip("\r\n");
		str= sc.nextLine();
		System.out.println(str);
		memoList = app.findMemoByName(str);
		app.printMemo(memoList);//검색한 이름을 다중행 받아와 출력
		
	}
	//글 번호로 특정글을 가져오는 메서드
	public MemoVO selectMemo(int idx) {
		try {
			con =DriverManager.getConnection(url,user,pwd);
			String sql="SELECT * FROM memo WHERE IDX="+idx;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int i = rs.getInt("idx");
				if(i==idx) {
					String name= rs.getString("name");
					String msg = rs.getString("msg");
					java.sql.Date wdate = rs.getDate("wdate");
					MemoVO vo = new MemoVO(i,name,msg,wdate);
					return vo;
				}
			}return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
	}//-------------------------------------
	
	public ArrayList<MemoVO> findMemoByMsg(String keyword){
		try {
			con=DriverManager.getConnection(url,user,pwd);
			ArrayList<MemoVO> arrList= new ArrayList<>();
			String sql="SELECT * FROM memo WHERE MSG='"+keyword+"'";
			stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String msg= rs.getString("msg");
				if(msg.equals(keyword)) {
					String name= rs.getString("name");
					int idx = rs.getInt("idx");
					java.sql.Date wdate = rs.getDate("wdate");
					MemoVO vo = new MemoVO(idx,name,msg,wdate);
					arrList.add(vo);
				}
			}
			if(!arrList.isEmpty()) return arrList;
			else return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
	}//-------------------------------------
	public ArrayList<MemoVO> findMemoByName(String keyword){
		try {
			con=DriverManager.getConnection(url,user,pwd);
			ArrayList<MemoVO> arrList= new ArrayList<>();
			stmt=con.createStatement();
			String sql= "SELECT * FROM memo WHERE NAME='"+keyword+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name=rs.getString("name");
				if(name.equals(keyword)) {
					int idx=rs.getInt("idx");
					String msg=rs.getString("msg");
					java.sql.Date wdate=rs.getDate("wdate");
					MemoVO vo =new MemoVO(idx,name,msg,wdate);
					arrList.add(vo);
				}
			}
			if(!arrList.isEmpty()) return arrList;
			else return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
	}//-------------------------------------
	
	
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
	private void printMemo(MemoVO memo) {
		if(memo!=null) {
			System.out.println("---------------------------------");
			System.out.println("\t글번호\t작성자명\t메모내용\t\t작성일");
			System.out.println("---------------------------------");
			System.out.println("\t"+memo.getIdx()+"\t"+memo.getName()+"\t"+memo.getMsg()+"\t\t"+memo.getWdate()); 
			System.out.println("---------------------------------");
		}
	}


}
