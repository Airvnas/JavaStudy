<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top"/>
<script type="text/javascript" src="../js/userCheck.js"></script>
<!-- <script>
function ajax_idcheck(uid){
   if(!uid){
   uid=$('#userid').val();}
   alert(uid);
   $.ajax({
      type:'GET',
      url:'idcheck?userid='+uid,
      dataType:'json',
      cache:false,
      success:function(res){
         //alert(JSON.stringify(res));
         if(res.result=='ok'){
            $('#id_result').html(uid+"는 사용 가능합니다").css('color','green')
            $('#id_flag').val("Y");
         }else{
            $('#id_result').html(uid+"는 이미 사용 중입니다").css('color','red')
            $('#id_flag').val("N");
         }
      },
      error:function(err){
         alert('err: '+err.status);
      }
   
   })
}
</script> -->



<div class="container" style="height:750px;overflow:auto;">
<c:if test="${user eq null }">
   <script >
      alert('없는 회원입니다.');
      history.back();
   </script>
</c:if>
<c:if test="${user ne null}">

   <h1 class="text-center mt-1">User Edit</h1>
   <!-- hidden data----------------------------------------- -->
   <form name="mf" action="userEditEnd" method="post">
   <input type="hidden" name="id_flag" id="id_flag" value="N">
   <input type="hidden" name="idx" id="idx" value="${user.idx }">
      <table class="table">
         <tr>
            <td width="20%" class="m1"><b>이름</b></td>
            <td width="80%" class="m2">
            <input type="text" name="name" id="name" value="${user.name}">
            <br><span class='ck1'>*이름은 한글만 가능해요</span>
            </td>
         </tr>
         <tr>
            <td width="20%" class="m1"><b>아이디</b></td>
            <td width="80%" class="m2">
            <input type="text" name="userid" id="userid" value="${user.userid }"   >
            <button type="button" class="btn btn-success" onclick="ajax_idcheck()">아이디 중복 체크</button>
            <br><span class='ck1' id="id_result">*아이디는 영문자, 숫자, _, !만 사용가능합니다.</span>
            </td>
         </tr>
         <tr>
            <td width="20%" class="m1"><b>비밀번호</b></td>
            <td width="80%" class="m2">
            <input type="password" name="pwd" id="pwd" value="${user.pwd}">
            <br><span class='ck1'>*비밀번호는 문자,숫자,!,. 포함해서 4~8자리 이내</span>
            </td>
         </tr>
         <tr>
            <td width="20%" class="m1"><b>비밀번호 확인</b></td>
            <td width="80%" class="m2">
            <input type="password" name="pwd2" id="pwd2" >
            </td>
         </tr>
         <tr>
            <td width="20%" class="m1"><b>연락처</b></td>
            <td width="80%" class="m2">
            <input type="text" name="hp1" id="hp1" placeholder="HP1" maxlength="3" value="${user.hp1}">-
            <input type="text" name="hp2" id="hp2" placeholder="HP2" maxlength="4" value="${user.hp2}">-
            <input type="text" name="hp3" id="hp3" placeholder="HP3" maxlength="4" value="${user.hp3}">
            <br><span class='ck1'>*앞자리(010|011)중에 하나-(숫자3~4자리)-(숫자4자리)가능해요</span>
            </td>
         </tr>
         <tr>
            <td width="20%" class="m1"><b>우편번호</b></td>
            <td width="80%" class="m2">
            <input type="text" name="post" id="post" value="${user.post}" maxlength="5">
            <button type="button" class="btn btn-success">우편번호 찾기</button>
            </td> 
         </tr>
         <tr>
            <td width="20%" class="m1"><b>주소</b></td>
            <td width="80%" class="m2">
            <input type="text" name="addr1" id="addr1" value="${user.addr1}"><br>
            <input type="text" name="addr2" id="addr2" value="${user.addr2}">
            </td>
         </tr>
         <tr>
            <td colspan="2" class="m3 text-center">
               <button class="btn btn-primary" type="button" onclick="member_check()">정보 수정</button>
               <button class="btn btn-danger" type="reset">다시쓰기</button>
            </td>
         
         </tr>
      </table>
   </form>

</c:if>
</div>

<c:import url="/foot"/>