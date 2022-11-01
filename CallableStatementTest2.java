package day04;
/*
CREATE OR REPLACE PROCEDURE MEMO_EDIT(
PIDX IN MEMO.IDX%TYPE,
PNAME IN MEMO.NAME%TYPE,
PMSG IN MEMO.MSG%TYPE)
IS
BEGIN
    UPDATE MEMO SET NAME=PNAME,MSG=PMSG WHERE PIDX=MEMO.IDX;
END;
/
 * */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import common.DBUtil;
import day02.MemoVO;
public class CallableStatementTest2 {

	public static void main(String[] args) throws SQLException {
		Scanner sc= new Scanner(System.in);
		System.out.println("변경할 idx: ");
		int idx= sc.nextInt();
		System.out.println("변경할 이름: ");
		String name= sc.next();
		System.out.println("변경할 메시지: ");
		sc.skip("\r\n");
		String msg= sc.nextLine();
		String sql="{call MEMO_EDIT(?,?,?)}";
		
		MemoVO vo = new MemoVO(idx,name,msg,null);
		
		Connection con = DBUtil.getCon();
		CallableStatement cs= con.prepareCall(sql);
		cs.setInt(1, vo.getIdx());
		cs.setString(2, vo.getName());
		cs.setString(3, vo.getMsg());
		cs.execute();
		System.out.println("변경 완료!");
		cs.close();
		con.close();
	}

}
