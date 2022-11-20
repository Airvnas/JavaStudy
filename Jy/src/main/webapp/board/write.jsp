<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/top.jsp"/>
<div class="container">
	<h1>boardWrite</h1>

	<form name="boardF" action="${pageContext.request.contextPath}/boardWriteEnd.do" 
	method="post" enctype="multipart/form-data" onsubmit="return board_check()">
		<ul style="list-style-type:none">
			<li>제목</li>
			<li><input type="text" name="name"></li>
			<li>글내용</li>
			<li><textarea rows="10" cols="20" name="context"></textarea></li>
			<li><button>저장</button><button type="reset">다시쓰기</button></li>
		</ul>
	</form>
</div>
<jsp:include page="/foot.jsp"/>