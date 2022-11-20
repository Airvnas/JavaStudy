<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String myctx=request.getContextPath();
	System.out.println(myctx);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=myctx%>/css/JYlayout.css">
</head>
<body>
<div id="wrapper" class="container">
	<header>
		<img src="<%=myctx%>/images/header.jpg">
	</header>	
	<nav>
		<ul>
			<li><a href="<%=myctx%>/index.do">Home</a></li>
			<li><a href="#">Login</a></li>
			<li><a href="#">Logout</a></li>
			<li><a href="<%=myctx%>/boardWrite.do">게시글쓰기</a></li>
			<li><a href="#">게시글목록</a></li>
		</ul>
	</nav>
	<div class="clear"></div>
	<article>
		
	