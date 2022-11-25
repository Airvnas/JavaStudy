let win=null;
function open_idcheck(){
	win=window.open("idCheck.do","idCheck","width=700,height=400,left=200,top=200")
}//--------------------------

function ajax_idcheck(uid){
	if(!uid){
		uid=$('#userid').val();
		if(!uid){
			alert('아이디를 입력하세요')
			$('#userid').focus();
			return;
		}
		if(!isUserid(mf.userid)){
			alert('아이디는 영문자로 시작하고 영문,숫자,_,!포함 4~8자리여야 해요');
			mf.userid.select();
			return;
		}
	}

	$.ajax({
		type:'get',
		url:'idcheck?userid='+uid,
		dataType:'json',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			if(res.result=='ok'){
				$('#id_result').html(uid+"는 사용 가능합니다.").css('color','green')
				$('#id_flag').val("Y");
			}else{
				$('#id_result').html(uid+"는 이미 사용중입니다.").css('color','red')
				$('#id_flag').val("N");
			}
		},
		error:function(err){
			alert('err: '+err.status);
		}
	
	})
}//--------------------------

function setId(uid){
	//alert(uid);
	//부모창 참조(opener=>window객체)
	opener.document.mf.userid.value=uid;
	self.close();
}//---------------------------

function id_check(){
	if(!idf.userid.value){
		alert('아이디를 입력해야해요');
		idf.userid.focus();
		return;
	}
	if(!isUserid(idf.userid)){
		alert('아이디는 영문자, 숫자, _, !만 사용가능합니다.');
		idf.userid.select();
		return;
	}
	idf.submit();
}//--------------------------
 
 
 
 function member_check(){
	/*
	if(!isDate(mf.birth)){
		alert('날짜형식에 맞아야합니다.');
		mf.birth.select();
		return;
	}
	if(!isEmail(mf.email)){
		alert('이메일 형식에 맞아야합니다.');
		mf.email.select();
		return;
	}*/
	if(!isKor(mf.name)){
		alert('이름은 한글만 가능합니다.');
		mf.name.select();
		return;
	}
	if(!isUserid(mf.userid)){
		alert('아이디는 영문자,숫자,_,!로 4~8까지 가능해요');
		mf.id_flag.value="N";
		mf.userid.select();
		return;
	}
	if(mf.id_flag.value=="N"){
		alert('아이디 중복 체크를 해야합니다.');
		mf.userid.focus();
		return;
	}
	
	if(!isPasswd(mf.pwd)){
		alert('비밀번호는 영문자,숫자,.,!로 4~8까지 가능해요');
		mf.pwd.select();
		return;
	}
	if(mf.pwd.value!=mf.pwd2.value){
		alert('비밀번호가 달라요');
		mf.pwd2.select();
		return;
	}
	if(!isMobile(mf.hp1,mf.hp2,mf.hp3)){
		alert('핸드폰 제약조건에 맞지 않아요.(010|011)-(숫자3~4자리)-(숫자4자리)');
		mf.hp1.select();
		return;
	}
	
	
	mf.submit();
}//------------------------------
/**
	asdf-12sd@naver.com
	asdf-12sd@daum.net
	asdf-12sd@google.co.kr
 */

function isEmail(input){
	let val=input.value;
	let pattern=/^[\w-_]+(\.[\w]+)*@([a-zA-Z]+\.)+[a-z]{2,3}$/;
	let b=pattern.test(val);
	//alert('email'+b);
	return b;
}//-------------------------------
 
/**
\b: 단어의 경계를 나타내며 해당 패턴이 정확하게 일치해야함을 의미
(010|011):010또는 011이 나와야함
\d{3,4}:숫자가 3개이상 4개 이하 나와야 함을 의미
 */
 
function isDate(input){
	let val=input.value;
	let pattern=/(^\d{4}[-\/](0[1-9]|1[012])[-\/](0[1-9]|[12][0-9]|3[01])$)/;
	let b=pattern.test(val);
	alert(b);
	return b;
}//-------------------------------
 
function isMobile(input1,input2,input3){
	let val=input1.value+"-"+input2.value+"-"+input3.value;
	//alert(val);
	let pattern=/\b(010|011)[-]\d{3,4}[-]\d{4}\b/;
	let b=pattern.test(val);
	//alert('hp'+b);
	return b;
}

/*
\w:알파벳 대소문자,숫자
\.: 마침표
 */
function isPasswd(input){
	let val=input.value;
	let pattern=/^[\w!\.]{4,8}$/;
	let b=pattern.test(val);
	//alert('pwd '+b);
	return b;
}

/*
^:시작을 의미
$:끝을 의미
a-z
A-Z
0-9
+: 패턴문자가 한개이상 올수있음
{4}:4자리까지
{4,}: 4자리 이상가능
{4,8}:4-8자리까지 가능
 */
function isUserid(input){
	let val=input.value;
	let pattern=/^[a-zA-Z]{1}[a-zA-Z0-9_!]{3,7}$/; //[]로 감싸면: a or b or c
	//알파벳으로 시작하고, 영문,숫자,_,!포함 4~8자리
	let b=pattern.test(val);
	//alert(b);
	return b;
}


/**
^:시작을 의미
$:끝을 의미
가-힣:한글범위
+:패턴이 한번이상 반복된다는 의미
 */
 function isKor(input){
	let val=input.value;
	//let pattern=new RefExp(/multi/g);//multi문자열이 있는지 여부를 체크하는 패턴
	//let pattern=/multi/g;
	let pattern=/^[가-힣]+$/;
	let b=pattern.test(val);//정규식 패턴에 맞으면 true를 반환, 틀리면 false반환
	//alert(b);
	return b;
}