/**
 * 
 */
 function member_check(){
	if(!isKor(mf.name)){
		alert('이름은 한글만 가능합니다.');
		mf.name.select();
		return;
	}
	if(!isUserid(mf.userid)){
		alert('아이디는 영문자,숫자,_,!로 4~8까지 가능해요');
		mf.userid.select();
		return;
	}
	
	mf.submit();
}//------------------------------

function isUserid(input){
	let val=input.value;
	let pattern=/^[a-zA-Z0-9_!]+$/; //[]로 감싸면: a or b or c
	let b=pattern.test(val);
	alert(b);
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