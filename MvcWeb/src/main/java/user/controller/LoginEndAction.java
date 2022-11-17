package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.NotUserException;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String saveId=req.getParameter("savaId");
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("login.do");
			this.setRediret(true);
			return;
		}
		UserDAOMyBatis dao=new UserDAOMyBatis();
		try {
			UserVO loginUser=dao.loginCheck(userid,pwd);
			//로그인 유저가 null이 아니라면 회원인증 받은것이므로 세션에 loginUser를 저장
			HttpSession session=req.getSession();
			if(loginUser!=null) {
				session.setAttribute("loginUser",loginUser );
				//아이디저장 체크박에스 체크했다면=>쿠키에 해당 아이디를 저장
				Cookie ck=new Cookie("uid",loginUser.getUserid());
				if(saveId!=null) {
					ck.setMaxAge(7*24*60*60);//7일간 유효
				}else {
					ck.setMaxAge(0);//쿠키 삭제
				}
				ck.setPath("/");
				res.addCookie(ck);
			}//if------
			this.setViewPage("index.do");
			this.setRediret(true);
			
		}catch(NotUserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
		}
	}//--------------------------

}
