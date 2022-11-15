package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String numStr=req.getParameter("num");
		if(numStr==null||numStr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRediret(true);
		}
		int num= Integer.parseInt(numStr.trim());
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		BoardVO vo=dao.viewBoard(num);
		req.setAttribute("board",vo);
		this.setViewPage("board/boardView.jsp");
		this.setRediret(false);
	}

}
