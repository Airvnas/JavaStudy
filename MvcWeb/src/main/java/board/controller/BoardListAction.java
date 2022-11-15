package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int count = dao.getTotalCount();
		
		List<BoardVO> boardArr=dao.listBoard();
		req.setAttribute("boardArr", boardArr);
		req.setAttribute("totalCount", count);
		
		this.setViewPage("board/boardList.jsp");
		this.setRediret(false);
		
	}

}
