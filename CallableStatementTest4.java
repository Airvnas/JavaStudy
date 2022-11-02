package day05;
import java.sql.*;
import java.sql.Date;

import common.DBUtil;
import java.util.*;
public class CallableStatementTest4 {
	
	public static void main(String[] args) throws Exception {
		// 부서번호를 인파라미터로 전달하면 해당 부서에 있는 사원정보(사원명, 업무, 입사일)와 부서정보(부서명,근무지)를
		//가져오는 프로시저를 작성하고 이것을 자바에서 호출해서 결과 데이터를 출력하세요
		Scanner sc= new Scanner(System.in);
		System.out.println("검색할 부서번호를 입력하세요: ");
		int dno= sc.nextInt();
		
		Connection con = DBUtil.getCon();
		String sql="{call memo_info(?,?)}";
		CallableStatement cs= con.prepareCall(sql);
		
		cs.setInt(1,dno);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs=(ResultSet)cs.getObject(2);
		
		while(rs.next()) {
			String ename=rs.getString("ename");
			String job=rs.getString("job");
			Date hdate=rs.getDate("hiredate");
			String dname=rs.getString("dname");
			String loc=rs.getString("loc");
			System.out.println(ename+"\t"+job+"\t"+hdate+"\t"+dname+"\t"+loc);
		}
		rs.close();
		cs.close();
		con.close();
		
	}

}














