package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import common.controller.AbstractAction;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String numStr= req.getParameter("num");
		if(numStr==null||numStr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRediret(true);
			return;
		}
		int num=Integer.parseInt(numStr.trim());
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int n= dao.deleteBoard(num);
		String msg=(n>0)?"글삭제 성공":"삭제 실패";
		String loc="boardList.do";
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		this.setViewPage(loc);
		this.setRediret(false);
	}

}
