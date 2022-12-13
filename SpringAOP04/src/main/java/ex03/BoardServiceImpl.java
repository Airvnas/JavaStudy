package ex03;

import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService {

	List<BoardVO> boardArr=new ArrayList<>();
	@Override
	public int insertBoard(BoardVO vo) {
		System.out.println("핵심 로직을 수행하는 메서드: insertBoard"+vo.getTitle()+"글을 등록합니다..");
		//boardArr=null;//trace3 test
		boardArr.add(vo);
		boardArr.add(vo);
		//boardArr=null;//trace4 test
		boardArr.add(vo);
		
		return boardArr.size();
	}

}
