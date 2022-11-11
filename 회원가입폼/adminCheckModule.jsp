<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 관리저 여부를 체크하는 모듈 (관리자는 status 값이 9) 
	1.로그인이 되어있어야하고=>loginCheckModule
	2.정지회원은 아니며=>loginCheckModule
	3.status값이 9
-->
<%@include file="/login/loginCheckModule.jsp" %>
<%
	if(user.getStatus()!=9){
		%>
		<script>
			alert('관리자만 이용할수 있어요');
			history.back();
		</script>
		<%
		return;
	}
	
%>