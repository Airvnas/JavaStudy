<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />
<script>
	function check(){
		if(window.document.join.empno.value==""){
			alert('사번을 입력하세요'+window.document.join.java.value);
			join.empno.focus();//입력포커스
			return;
		}
		if(window.document.join.java.value>100){
			alert('java점수를 다시 입력하세요');
			join.java.focus();//입력포커스
			return;
		}
		if(window.document.join.db.value>100){
			alert('db점수를 다시 입력하세요');
			join.db.focus();//입력포커스
			return;
		}
		if(window.document.join.jsp.value>100){
			alert('jsp점수를 다시 입력하세요');
			join.jsp.focus();//입력포커스
			return;
		}
		join.submit();//서버에 전송
	}
	

</script>



<div class="container" style="margin:auto">
	<h1>성적입력</h1>
	<form name="join" action="result.jsp" method="post">
		<table border="1">
			<tr>
				<td colspan=2>사번</td>
				<td><input type="number" name="empno" placeholder="Empno"></td>
			</tr>
			<tr>
				    <td rowspan=4>과목</td>
					<tr>
						<td>Java</td>
						<td><input type="number" name="java"></td>
					</tr>
					<tr>
						<td>Database</td>
						<td><input type="number" name="db"></td>
					</tr>
					<tr>
						<td>JSP</td>
						<td><input type="number" name="jsp"></td>
					</tr>
			</tr>
			<tr><td colspan=5><button type="button" onclick="check()">저장</button></td></tr>
		</table>
	</form>
</div>

<jsp:include page="/foot.jsp" />