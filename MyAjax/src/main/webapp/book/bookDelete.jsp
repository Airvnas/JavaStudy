<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.book.*,java.util.*"%>

<%	
	
	String isbn=request.getParameter("isbn");
	System.out.println(isbn);
	
	BookDAO dao=new BookDAO();
	int n=dao.deleteBook(isbn);
	//String str=(n>0)?"삭제 성공":"삭제 실패";
	
%>
<result><%=n%></result>