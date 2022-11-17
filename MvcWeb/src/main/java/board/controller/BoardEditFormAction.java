package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session= req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg","로그인해야 글쓰기가 가능합니다.");
			req.setAttribute("loc","javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
			return;
		}
		
		//1. 수정할 글번호 받기
		String numStr=req.getParameter("num");
		if(numStr==null||numStr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRediret(true);
			return;
		}
		int num=Integer.parseInt(numStr.trim());
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		BoardVO vo=dao.viewBoard(num);
		if(!vo.getUserid().equals(user.getUserid())) {
			req.setAttribute("msg","글쓴 사람만 글 수정이 가능합니다.");
			req.setAttribute("loc","javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
			return;
		}
		
		req.setAttribute("board", vo);
		
		this.setViewPage("board/boardEdit.jsp");
		this.setRediret(false);
	}

}
