<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="user.model.*" errorPage="/login/errorAlert.jsp"%>
<%
	//1.아이디,비번값 받기
	request.setCharacterEncoding("utf-8");
	String userid=request.getParameter("userid");
	String pwd=request.getParameter("pwd");
	//2.유효성 체크=>login.jsp
	if(userid==null||userid.trim().isEmpty()||pwd==null||pwd.trim().isEmpty()){
		response.sendRedirect("login.jsp");
		return;
	}
	//3.UserDAO 빈 생성해서 UserVO loginCheck(userid,pwd) 호출
	/*
		1) 아이디와 비번이 일치하는지 db에서 확인해서 일치하면 해당 회원정보를 UserVO에 담아서
			반환해줌
		2) 일치하지 않으면 사용자정의 예외(NotUserException)를 발생시킨다.
	*/
	
%>
<jsp:useBean id="userDao" class="user.model.UserDAO" scope="session"/>
<% 
	UserVO loginUser=userDao.loginCheck(userid,pwd); 
	if(loginUser!=null){
		//out.println("<h1>로그인 성공</h1>");
		//회원 인증을 받았다면 인증받은 회원정보를 session에 저장하자.
		/*session내장객체: HttpSession 타입
			-setAttribute(String key,Object value)
			-Object getAttribute(String key)
		*/
		session.setAttribute("loginUser", loginUser);
		//session은 서버쪽에 저장, cookie는 클라이언트쪽에 저장
		response.sendRedirect("../index.jsp");
		
		
	}
	
%>
