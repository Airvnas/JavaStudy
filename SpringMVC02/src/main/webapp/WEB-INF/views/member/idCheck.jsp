<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!----------------------------------------------------------  -->
<script src="./js/userCheck.js"></script>
<link rel="stylesheet" type="text/css" href="./css/layout.css">
<!----------------------------------------------------------  -->

<div class="container m2" style="margin-top: 2em">
	<form name="idf" action="idCheck.do" method="post">
		<label for="userid">아이디</label> <input type="text" name="userid"
			id="userid" placeholder="User ID" autofocus="autofocus">
		<button type="button" onclick="id_check()">확 인</button>
	</form>
</div>



