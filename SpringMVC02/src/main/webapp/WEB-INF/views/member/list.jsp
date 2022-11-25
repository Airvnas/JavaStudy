<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top"/>
<style>
	.txt0{
		color:green;
	}
	.txt-1{
		color:blue;
	}
	.txt-2{
		color:tomato;
	}
</style>

<div class="container mt-3" style="height:600px;overflow:auto;">
	<h1 class="text-center">Users [Admin Page]</h1>
	<div>
		<%-- ${userArr} --%>
		<table class="table table-bordered mt-3">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>아이디</td>
				<td>연락처</td>
				<td>회원상태</td>
				<td>수정|삭제</td>
			</tr>
			<!-- ---------------------------  -->
			<c:forEach var="user" items="${userArr}">
			<tr>
				<td>${user.idx}</td>
				<td>${user.name}</td>
				<td>${user.userid}</td>
				<td>${user.allHp}</td>
				<td class="txt${user.status}">${user.statusStr}</td>
				<td>
				<a href="javascript:userEdit('${user.idx}')">수정</a>|
				<a href="#" onclick="userDel('${user.idx}')">삭제</a>
				</td>
			</tr>
			</c:forEach>
			<!-- ---------------------------  -->
		</table>
	</div>
	
</div>
<form name="frm" id="frm" method="post">
	<input type="text" name="idx"id="idx">
</form>
<script>
	function userDel(vidx){
		$('#idx').val(vidx);
		//attr():정적인 속성을 추가할때, prop():기능적인 속성을 추가할 때 사용
		$('#frm').prop('action','userDel');
		$('#frm').submit();
	}
	function userEdit(vidx){
		$('#idx').val(vidx);
		$('#frm').prop('action','userEdit');
		$('#frm').submit();
	}
</script>





<c:import url="/foot"/>