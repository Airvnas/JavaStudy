package day03;
import java.sql.*;
import java.util.*;

import common.DBUtil;
public class PreparedStatementTest3 {

	public static void main(String[] args)throws Exception {
		Connection con=DBUtil.getCon();
		//검색할 사원이 이름을 입력받아서 해당 사원 정보를 출력
		//사번, 사원명,부서명,입사일,근무지 출력
		Scanner sc=new Scanner(System.in);
		System.out.println("검색할 사원의 이름을 입력하세요: ");
		String name= sc.next();
		
		String sql="select empno,ename,e.job,hiredate, d.loc "
				+ " from emp e join dept d on d.deptno=e.deptno where ename=?"; 
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			String n=rs.getString("ename");
			if(n.equals(name)) {
				int empno=rs.getInt("empno");
				String job=rs.getString("job");
				java.sql.Date hdate=rs.getDate("hiredate");
				String loc=rs.getString("loc");
				System.out.println(empno+"\t"+n+"\t"+job+"\t"+hdate+"\t"+loc);
			}
		}
		ps.close();
		con.close();
	}

}
