package board.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAOMyBatis {
	
	private final String NS="board.model.BoardMapper";
	private String resource="common/config/mybatis-config.xml";//설계도
	private SqlSession ses;
	
	public SqlSessionFactory getSessionFactory(){
		try {
			InputStream is=Resources.getResourceAsStream(resource);
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
			return factory;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}//-------------------------------
	
	public int getTotalCount() {
		ses=this.getSessionFactory().openSession();
		int count=ses.selectOne(NS+".totalCount");
		if(ses!=null)ses.close();
		return count;
	}//-------------------------------

	public int insertBoard(BoardVO vo) {
		ses=this.getSessionFactory().openSession();//디폴트가 수동커밋
		System.out.println("insert전: vo의 num값+> "+vo.getNum());
		int n=ses.insert(NS+".insertBoard",vo);
		System.out.println("insert후: vo의 num값+> "+vo.getNum());
		if(n>0) {
			ses.commit();
		}else {
			ses.rollback();
		}
		if(ses!=null)ses.close();
		return n;
	}

	public List<BoardVO> listBoard(int start,int end) {
		ses=this.getSessionFactory().openSession();
		//다중행을 가져올 때는 selectList()
		//단일행을 가져올 때는 selectOne()
		Map<String,Integer> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> arr= ses.selectList(NS+".listBoard",map);
		if(ses!=null)ses.close();
		return arr;
	}

	public BoardVO viewBoard(int num) {
		try {
			ses=this.getSessionFactory().openSession();
			BoardVO vo=ses.selectOne(NS+".viewBoard",num);
			return vo;
		}finally {
			close();
		}
		
	}
	public void close() {
		if(ses!=null)ses.close();
	}

	public int deleteBoard(int num) {
		try {
			ses=this.getSessionFactory().openSession();
			//ses=this.getSessionFactory().openSession(true);
			//디폴트가 수동커밋, 매개변수로 true넘기면 auto commit됨
			int n=ses.delete(NS+".deleteBoard",num);
			if(n>0) {
				ses.commit();
			}else {
				ses.rollback();
			}
			return n;
		} finally {
			close();
		}
	}

	public int updateBoard(BoardVO vo) {
		try {
			ses=this.getSessionFactory().openSession(true);
			//ses=this.getSessionFactory().openSession(true);
			//디폴트가 수동커밋, 매개변수로 true넘기면 auto commit됨
			int n=ses.update(NS+".updateBoard",vo);
			return n;
		} finally {
			close();
		}
		
	}
	
	
}/////////////////////////////////////
