package day04;
import java.sql.*;
import common.DBUtil;
/*여러개의 sql문을 한꺼번에 전송하는 일괄처리 방식
 *-여러개의 sql문을 작성해서 Statement의 addBatch(sql),
 *executeBatch()메서드로 일괄처리한다.
 *모두성공하던지, 모두 실패하던지==>transaction 
 * */
public class BatchQueryTest {

	public static void main(String[] args) 
	throws Exception
	{
		Connection con= DBUtil.getCon();
		con.setAutoCommit(false);//자동커밋 취소. 수동으로 트랜잭션 관리 위해
		
		Statement st= con.createStatement();
		st.addBatch("insert into memo values(memo_seq.nextval,'김길동','Batch테스트11',sysdate)");
		st.addBatch("insert into memo values(memo_seq.nextval,'임길동','Batch테스트22',sysdate)");
		st.addBatch("insert into memo values(memo_seq.nextval,'송길동','Batch테스트33',sysdate)");
		st.addBatch("insert into memo values(memo_seq.nextval,'최길동','Batch테스트44',sysdate)");
		//일부러 sql문 하나를 에러를 발생시킴
		st.addBatch("insert into memo values(memo_seq.nextval,'유길동','Batch테스트5',sysdate)");
		boolean isCommit=false;
		try {
			int[] updateCount=st.executeBatch();//일괄처리하여 실행함
			isCommit= true;
		} catch (SQLException e) {
			isCommit=false;
			e.printStackTrace();
		}
		if(isCommit) {
			con.commit();
		}else {
			con.rollback();
		}
		con.setAutoCommit(true);
		
		boolean b=st.execute("select * from memo order by idx desc");
		if(b) {
			ResultSet rs= st.getResultSet();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDate(4));
			}
			rs.close();
		}
		
		st.close();
		con.close();
	}

}
