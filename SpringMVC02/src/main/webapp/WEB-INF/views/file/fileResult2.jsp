<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top"/>
<div class="container mt-3" style="height:600px;overflow:auto;">
	<h1 class="text-center">파일 업로드 결과</h1>
	<hr>
	
	<h3>올 린 이 : ${name}</h3>
	<h3>첨부파일명1: ${fname1}</h3>
	<h3>첨부파일 크기: ${fsize1}[bytes]</h3>
	<h3>첨부파일명2: ${fname2}</h3>
	<h3>첨부파일 크기: ${fsize2}[bytes]</h3>
</div>

<c:import url="/foot"/>