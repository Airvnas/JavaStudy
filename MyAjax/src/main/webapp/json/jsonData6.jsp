<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,ajax.book.*,net.sf.json.*"%>
<%
	BookDAO dao=new BookDAO();
	List<BookDTO> arr=dao.getAllBook();
	
	JSONArray jsonArr=JSONArray.fromObject(arr);
	//fromObject(arr) :static메서드. ArrayList를 JSONArray유횽으로 변환해서 반환
	
%>
<%=jsonArr.toString()%>