<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- CDN 참조-------------------------------------- -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- ------------------------------------------------- -->
<script>
$(function(){
	$('#btnSearch').click(function(evt){
		evt.preventDefault();//submit 기본작동을 막는다.
		//검색어 입력값 받기
		let keyword=$('#title').val();
		if(!keyword){
			alert('검색어를 입력하세요');
			$('#title').focus();
			return;
		}
		let url="openApiResult.jsp?query="+encodeURIComponent(keyword)+"&display=20&start=1";
		send(url,keyword,1);
		
	})//--------------------
	
})//-------------------------

function send(url,keyword,cpage){
	$.ajax({
		type:'get',
		url:url,
		dataType:'json',
		cache:false
	}).done(function(res){
		//alert(res);
		console.log(res);
		let total=res.total;
		//alert(total);
		let obj={
				total:total,
				keyword:keyword,
				cpage:cpage,
				display:20
		}
		showList(res.items,obj);
	}).fail(function(err){
		alert('err: '+err.status);
		console.log(err.responseText);
	})
}//send---------------------
function showList(items,obj){
	let str='<h2>'+obj.keyword+' 검색결과 '+obj.total+'개</h2>';
	str+='<table class="table">';
	str+='<tr>';
	$.each(items,function(i,book){
		str+='<td width="20%" style="text-align:center">';
		str+='<a href="'+book.link+'" target="_blank">';
		str+='<img src="'+book.image+'" class="img img-thumbnail" style="width:70%">';
		str+='</a>';
		str+='<h5>'+book.title+'</h5>';
		str+='</td>';
		if(i%5==4){//한 행에 5개 단위로 보여주기위해
			str+='<tr></tr>';//줄바꿈
		}
		
	});
	str+='</tr>';
	str+='</table>';
	
	
	$('#openApiBook').html(str);
}

</script>


<div class="container">
   <div class="row">
      <div class="col-md-12">
         <form action="test.jsp">
            <h1 class="text-center">Naver 도서 검색</h1>
            <input type="text" name="title" id="title" placeholder="도서명을 입력하세요" class="form-control">
            <button class="btn btn-info" id="btnSearch">검    색</button>
         </form>
      </div>      
   </div>
   <hr>
   <div id="pageNavi" class="text-center">
   </div>
   <hr>
   <div id="openApiBook">
   
   </div>
</div>
