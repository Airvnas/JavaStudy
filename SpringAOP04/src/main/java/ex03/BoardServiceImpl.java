package ex03;

import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService {

	List<BoardVO> boardArr=new ArrayList<>();
	@Override
	public int insertBoard(BoardVO vo) {
		System.out.println("�ٽ� ������ �����ϴ� �޼���: insertBoard"+vo.getTitle()+"���� ����մϴ�..");
		//boardArr=null;//trace3 test
		boardArr.add(vo);
		boardArr.add(vo);
		//boardArr=null;//trace4 test
		boardArr.add(vo);
		
		return boardArr.size();
	}

}
