<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script>

/* 로그인 화면에서 엔터키 입력시 로그인 시도 */
function keyPress() {
	if(window.event.keyCode ==13) {
		return formCheck();	
	}	
}

/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("ID").focus();
}
/* 공백, 특수문자 입력 불가 */
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})

function loginProcess(){ 
	var id = $("#ID").val(); 
	var pw = $("#PASSWORD").val(); 
	var idChk = $("#SAVEID").is(":checked"); // 체크박스가 체크되었는지를 담아준다. ( true/false 로 담긴다.) 
	
	if(id == ""){ // 아이디가 입력이 안된 채 로그인 버튼을 누른 경우 
		alert("아이디를 입력해주세요"); // 입력하라는 메세지 출력 
		$("#ID").focus(); // 아이디 인풋창에 포커스를 맞춰준다. 
		return false; // return false를 해줘서 서버이동을 막는다. 
	}else if(pw ==""){ 
		alert("비밀번호를 입력해주세요"); 
		$("#PASSWORD").focus(); 
		return false; 
	}else if(idChk){ // 아이디, 비밀번호 저장 체크박스가 체크 된 경우 (true) 
		setCookie("Cookie_id", id, 7); // 쿠키에 저장하는 이벤트를 호출한다. Cookie_mail 이름으로 id가 7일동안 저장 
	}else{ // 체크가 해제 된 경우 (false) 
		deleteCookie("Cookie_id"); // 쿠키 정보를 지우는 이벤트를 호출한다. 
	} $("#loginForm").submit(); 
};

</script>

</head>
<body>
<div id="main">
<section id="input-with-icons">
	<div class="col-12">
		<div class="col-md-3 col-12 mx-auto">
			<div class="card">
			    <div class="card-header">
			        <h4 class="card-title">로그인</h4>
			    </div>
			    <div class="card-content">
			        <div class="card-body">
			            <form class="form form-vertical" id="loginForm" action="login.cut" method="post">
			                <div class="form-body">
			                    <div class="row">
			                        <div class="col-md-4">
										<label>아이디</label>
									</div>
								    <div class="col-md-8">
								        <div class="form-group has-icon-left">
								            <div class="position-relative">
								                <input type="text" class="form-control" placeholder="ID" id="ID" noBlank>
								                <div class="form-control-icon">
								                    <i class="bi bi-person"></i>
								                </div>
								            </div>
								        </div>
								    </div>
			                        <div class="col-md-4">
									    <label>비밀번호</label>
									</div>
									<div class="col-md-8">
									    <div class="form-group has-icon-left">
									        <div class="position-relative">
									            <input type="password" class="form-control" placeholder="Password" noSpecial>
									            <div class="form-control-icon">
									                <i class="bi bi-lock"></i>
									            </div>
									        </div>
									    </div>
									</div>
			                        <div class="col-11 ">
										<a href="/SC/findId.cut">아이디 찾기</a>
										<a href="/SC/findPw.cut">비밀번호 찾기</a>
									</div>
									<div class="col-12 d-flex justify-content-end">
		                                <div class="checkbox mt-2">
		                                    <input type="checkbox" id="SAVEID" class='form-check-input'>
		                                    <label for="remember-me-v">아이디 저장</label>
		                                </div>
			                        </div>
			                        <br><br>
			                        <div class="col-12" style="text-align: center; margin: 0 auto;">
			                        	<div>
			                        		<button onClick="loginProcess();" class="btn btn-primary" style="width:300px; height:45px;">로그인</button>
			                        	</div><br>
			                        	<div>
			                        		<a href="#" ><img src="assets/images/login/kakao_login_medium_wide.png" style="width:300px;"></a>
			                        	</div><br>
			                        	<div>
			                        		<a href="#" ><img src="assets/images/login/btnG_완성형.png" style="width:300px;"></a>
			                        	</div><br>
			                        	<div>
			                        		<a href="#" ><img src="assets/images/login/btn_google_signin_dark_normal_web@2x.png" style="width:300px;"></a>
			                        	</div><br>
			                        	<!-- <div>
			                        		<button onClick="#" class="btn btn-outline-primary" style="width:300px; height:45px;">회원가입</button>
			                        	</div><br> -->
			                        </div>
			                    </div>
			                </div>
			            </form>
			        </div>
			    </div>
			</div>
		</div>
	</div>
</section>
</div>          
</body>
</html>