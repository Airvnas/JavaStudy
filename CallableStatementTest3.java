package day05;
import java.sql.*;
import java.sql.Date;

import common.DBUtil;
import java.util.*;
/*
create or replace procedure MEMO_ALL
(MYCR OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN MYCR FOR 
    SELECT IDX,NAME,MSG,WDATE FROM MEMO
    ORDER BY IDX DESC;
END;
/
 * */


public class CallableStatementTest3 {

	public static void main(String[] args)
	throws SQLException
	{
		Connection con= DBUtil.getCon();
		String sql="{call memo_all(?)}";
		
		CallableStatement cs= con.prepareCall(sql);
		cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
		
		//ResultSet rs= cs.executeQuery();[x]에러발생
		cs.execute();
		
		ResultSet rs=(ResultSet)cs.getObject(1);
		
		while(rs.next()) {
			int idx= rs.getInt("idx");
			String name=rs.getString("name");
			String msg=rs.getString("msg");
			Date wdate= rs.getDate("wdate");
			System.out.println(idx+"\t"+name+"\t"+msg+"\t"+wdate);
		}
		
		rs.close();
		cs.close();
		con.close();
	}

}




















