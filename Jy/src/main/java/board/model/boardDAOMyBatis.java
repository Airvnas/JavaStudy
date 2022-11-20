package board.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class boardDAOMyBatis {
	private final String NS="board.model.BoardMapper";
	private String resource="common/config/mybatis-config.xml";
	private SqlSession ses;
	
	public SqlSessionFactory getSessionFactory() {
		try {
			InputStream is=Resources.getResourceAsStream(resource);
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
			return factory;		
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int insertBoard(BoardVO vo) throws SQLException{ 
		try {
			ses=this.getSessionFactory().openSession(true);
			int n=ses.insert(NS+".insertBoard", vo);
			return n;
		}finally {
			close();
		}
	}
	
	public void close() {
		if(ses!=null) {
			ses.close();
		}
	}
}
