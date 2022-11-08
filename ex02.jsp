<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<h1>scripting element</h1>
<%--
[1] scriptlet:	<% %>	=>지역변수
[2] declaration:<%! %>	=>멤버변수
[3] expression:	<%= %>
 --%>

<h1><%=new Date().toString() %></h1>
<%! 
//여기서는 멤버변수를 선언하거나 메서드를 구성할 수 있다.
	String global="인스턴스변수(멤버변수)";
	public String sum(int a,int b){
		return a+"+"+b+"="+(a+b);
	}
%>
<h2><%=global %></h2>
<h2><%=sum(5,10) %></h2>


<%//scriptlet태그에서 작성된 코드들은 서블릿의 _jspService()메서드 안에 들어간다.
	String local="지역변수";
	out.println(local);
	
	//C:\myjava\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\MyWeb\org\apache\jsp\example
	//이클립스에서 서블릿코드 생성하는곳
%>




