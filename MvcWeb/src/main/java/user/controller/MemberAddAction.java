package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MemberAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//req.setCharacterEncoding("UTF-8");
		//1.이름,아이디,비밀번호,연락처,우편번호,주소1,주소2
		String name=req.getParameter("name");
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String hp1=req.getParameter("hp1");
		String hp2=req.getParameter("hp2");
		String hp3=req.getParameter("hp3");
		String post=req.getParameter("post");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		//2.유효성 체크=>joinForm.do
		if(name==null||userid==null||pwd==null||hp1==null||hp2==null||hp3==null||
				name.trim().isEmpty()||userid.trim().isEmpty()||pwd.trim().isEmpty()||
				hp1.trim().isEmpty()||hp2.trim().isEmpty()||hp3.trim().isEmpty()) {
			this.setViewPage("joinForm.do");
			this.setRediret(true);
		}
		//3.UserVO에 담아주기
		UserVO user=new UserVO(0,name,pwd,userid,hp1,hp2,hp3,post,addr1,addr2,null,1000,0,"");
		//4.UserDAOMyBatis의 insertUser(vo)
		UserDAOMyBatis dao=new UserDAOMyBatis();
		int n = dao.insertUser(user);
		String str=(n>0)?"회원가입 처리 완료- 로그인 페이지로 이동":"가입 실패";
		String loc=(n>0)?"login.do":"javascript:history.back()";
		
		//5.결과에 따른 메시지, 이동경로 =>req에 저장
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setViewPage("message.jsp");
		this.setRediret(false);
	}

}
