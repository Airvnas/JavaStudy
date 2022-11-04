package com.multicamp;
import java.sql.*;
import java.sql.Date;
import java.util.*;
//Data Access Object : 모델에 속하며 영속성(Persistence) 게층이라고 부른다.
public class MemoDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public int insertMemo(MemoVO memo) throws SQLException{
		try {
			String sql="INSERT INTO MEMO VALUES(MEMO_SEQ.NEXTVAL,?,?,SYSDATE)";
			con=DBUtil.getCon();
			ps= con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			int n= ps.executeUpdate();
			return n;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			close();
		}
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			
		}
	}//-------------------------------

	public List<MemoVO> selectMemoAll() throws SQLException{
		try {
			con=DBUtil.getCon();
			StringBuilder buf =new StringBuilder("select idx,rpad(name,12,' ')name,rpad(msg,100,' ') msg,")
											.append("wdate from memo order by idx desc");
			String sql= buf.toString();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			List<MemoVO> arr= makeList(rs);
			return arr;
		}finally {
			close();
		}
	}//-------------------------------

	private List<MemoVO> makeList(ResultSet rs)throws SQLException {
		List<MemoVO> arr = new ArrayList<>();
		while(rs.next()) {
			int idx= rs.getInt("idx");
			String name = rs.getString("name");
			String msg= rs.getString("msg");
			java.sql.Date wdate = rs.getDate("wdate");
			MemoVO vo = new MemoVO(idx,name,msg,wdate);
			arr.add(vo);
		}
		
		return arr;
	}//-------------------------------

	public int deleteMemo(int idx) throws SQLException{
		try {
			con=DBUtil.getCon();
			StringBuilder buf= new StringBuilder("delete from memo where idx=?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setInt(1, idx);
			int n= ps.executeUpdate();
			return n;
		}finally {
			close();
		}
		
	}//-----------------------------------------

	public MemoVO selectMemo(int idx) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="select idx,name,msg,wdate from memo where idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			List<MemoVO> arr= makeList(rs);
			if(arr!=null && arr.size()==1) {
				return arr.get(0);
			}
			return null;
		}finally {
			close();
		}
		
	}//--------------------------------------------
	public int updateMemo(MemoVO vo) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="update memo set name=?,msg=? where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getMsg());
			ps.setInt(3, vo.getIdx());
			return ps.executeUpdate();
		}finally {
			close();
		}
	}

	public List<MemoVO> findMemo(int type, String keyword) throws SQLException{
		try {
			con=DBUtil.getCon();
			String typeStr="";
			if(type==0) {
				typeStr="name";
			}else typeStr="msg";
			System.out.println(typeStr);
			System.out.println(keyword);
			String sql="select idx,name,msg,wdate from memo where "+typeStr+" like ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs=ps.executeQuery();
			List<MemoVO> arr= makeList(rs);
			//if(arr!=null)
			return arr;
			//return null;
		}finally {
			close();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}///////////////////////////////////////////////
