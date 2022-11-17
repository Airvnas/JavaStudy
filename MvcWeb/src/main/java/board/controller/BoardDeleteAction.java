package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session= req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg","로그인해야 글삭제가 가능합니다.");
			req.setAttribute("loc","javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
			return;
		}
		
		String numStr= req.getParameter("num");
		if(numStr==null||numStr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRediret(true);
			return;
		}
		int num=Integer.parseInt(numStr.trim());
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//db에서 삭제할 해당 글 가져오기
		BoardVO vo=dao.viewBoard(Integer.parseInt(numStr.trim()));
		
		if(!vo.getUserid().equals(user.getUserid())) {
			req.setAttribute("msg","글쓴이만 글삭제 가능합니다.");
			req.setAttribute("loc","javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
			return;
		}
		
		if(vo.getFilename()!=null) {//첨부파일이 있다면 서버 upload디렉토리에서 해당파일을 먼저 삭제
			String upDir=req.getServletContext().getRealPath("/Upload");
			File delFile=new File(upDir,vo.getFilename());
			if(delFile!=null) {
				delFile.delete();
			}
		}//---------------------------------
		
		int n= dao.deleteBoard(num);
		
		String msg=(n>0)?"글삭제 성공":"삭제 실패";
		String loc="boardList.do";
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		this.setViewPage("message.jsp");
		this.setRediret(false);
	}

}
