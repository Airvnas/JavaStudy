<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- --%>
<% 
	for(int i=0;i<5;i++){
%>
		<h2>Hello JSP <%=i %>></h2>
<%
}
%>
<hr color=''red'>
<% 
	int dan=8;
	int i=1;
	while(i<10){
		%>
		<h3><%=dan%>x<%=i %>=<%=(dan*i) %></h3>
		<%
		i++;
	}
%>
<hr color=''red'>
<table border="1" style="width:90%;margin:auto">
<tr>
	<%
		for(dan=2;dan<10;dan++){
	%>
	
		<th bgcolor="efefef"><%=dan %>단</th>
	
	<%
	}//for-------- 
	
	
	%>
</tr>
	<% for(i=1;i<10;i++){ %>
	<tr>
		<%for(int k=2;k<10;k++){ %>
		<td style="padding-left:10px">
			<%=k %>x<%=i %>=<%=k*i %>
		</td>
		<%}//for----- %>
	</tr>
	<%}//for----- %>

</table>












