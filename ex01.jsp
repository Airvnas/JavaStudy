<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.*,java.text.*"%>
<!-- page directive(page 지시어) -->
<!-- 1.HTML주석 -->
<h1>처음해보는 JSP</h1>
<%//scriptlet 태그: 이안에서는 자바코드
	Date today= new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String str= sdf.format(today);
	out.println("<h2>"+str+"</h2>");
%>
<%--JSP 주석 
	<%=변수 %>:출력식(expression)=>out.println(변수)과 동일하다.

--%>
<h2 style="color:tomato"><%=str%></h2>