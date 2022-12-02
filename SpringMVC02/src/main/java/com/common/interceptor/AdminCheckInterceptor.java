package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.model.UserVO;

/* /admin/** 패턴의 요청일 때는
AdminCheckInterceptor를 구성하자.
=>HandlerInterceptorAdapter추상 클래스를 상속받아 구현해보기

관리자 여부를 체크해서 관리자가 아니면
"관리자만 이용 가능합니다"  보여준다.
return false


AdminCheckInterceptor를 servlet-context.xml에 빈으로 등록하고
매핑 패턴을 지정한다.
*/
public class AdminCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object handler) 
			throws Exception{
		HttpSession ses=req.getSession();
		UserVO user=(UserVO)ses.getAttribute("loginUser");
		if(user!=null) {
			if(user.getStatus()!=9) {
				String view="/WEB-INF/views/msg.jsp";
				req.setAttribute("message","관리자만 이용 가능합니다.");
				req.setAttribute("loc",req.getContextPath()+"/index");
				
				RequestDispatcher disp=req.getRequestDispatcher(view);
				disp.forward(req, res);
				return false;//로그인 했으나 관리자가 아닌경우
			}
			else {
				return true;
			}
		}
		return false;//로그인 하지 않은경우
	}
}
