<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />


<div class="container">
<%
	//내장객체: request =>HttpServletRequest타입
	int java=0;
	int db=0;
	int jsp=0;
	String empno=(String)request.getParameter("empno");
	String javaStr=(String)request.getParameter("java");
	String dbStr=(String)request.getParameter("db");
	String jspStr=(String)request.getParameter("jsp");
	
	if(javaStr.trim()==""){
		java=0;
	}else{
		java=Integer.parseInt(javaStr);
	}
	if(dbStr.trim()==""){
		db=0;
	}else{
		db=Integer.parseInt(dbStr);
	}
	if(jspStr.trim()==""){
		jsp=0;
	}else{
		jsp=Integer.parseInt(jspStr);
	}
	int avg=(java+db+jsp)/3;
%>
	<h1>처리 결과</h1>
	<form name="join" action="result.jsp" method="post">
		<table border="1">
			<tr>
				<td colspan=2>사번</td>
				<td><input type="text" name="empno" value="<%=empno%>" readonly placeholder="Empno"></td>
			</tr>
			<tr >
				<td rowspan=5>과목</td>
					<tr>
						<td>Java</td>
						<td><input type="number" value="<%=java%>"readonly name="java"></td>
					</tr>
					<tr>
						<td>Database</td>
						<td><input type="number" value="<%=db%>"readonly name="db"></td>
					<tr>
					<tr>
						<td>JSP</td>
						<td><input type="number" value="<%=jsp%>"readonly name="jsp"></td>
					</tr>
			</tr>
			<tr>
				<td colspan=2>평균	</td>
				<td><input type="number" value="<%=avg%>"readonly name="avg">	</td>
				
			</tr>
			<tr><td colspan=5><button type="button" onclick="location.href='ex06.jsp'">입력화면</button></td></tr>
		</table>
	</form>
</div>


<jsp:include page="/foot.jsp" />