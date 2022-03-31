<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("ID").focus();
}
/* 공백, 특수문자 입력 불가 */
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})

</script>
<script type="text/javascript">
$(document).ready(function(){
	
	
	var key = getCookie("key");
	$("#ID").val(key);
	
	if($("#ID").val() != "") {
		$("#SAVEID").attr("checked", true);
	}
	
	$("#SAVEID").change(function(){ //체크 박스의 변화
		if($("#SAVEID").is(":checked")){ //아이디 저장하기 체크
			setCookie("key", $("#ID").val(), 7); //7일 동안 쿠기 저장
		} else { //아이디 저장하기 체크 해제
			deleteCookie("key");
		}
	});
	
	//아이디 저장하기 체크한 상태에서 아이디 입력하는 경우에도 쿠키 저장
	$("#ID").keyup(function(){
		if($("#SAVEID").is(":checked")){
			setCookie("key", $("#ID").val(), 7);
		}
	});
	
	
	//쿠키 저장하기
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value) 
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}
	
	//쿠키 삭제
	function deleteCookie(cookieName) {
		var exprireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "=" + "; expires=" 
								+ expireDate.toGMTString();
	}
	
	//쿠키 가져오기
	function getCookie(cookieName) {
	   cookieName = cookieName + '=';
	   var cookieData = document.cookie;
	   var start = cookieData.indexOf(cookieName);
	   var cookieValue = '';
	   if(start != -1) { //쿠키가 존재
			start += cookieName.length;
	   		var end = cookieData.indexOf(';', start);
	   		if(end == -1) //쿠키 값의 마지막 위치 인덱스 번호 설정
	   			end = cookieData.length;
	   		console.log("end위치 : " + end);
	   		cookieValue = cookieData.substring(start, end);
	   }
	   return unescape(cookieValue);
	}
	
	/* 로그인 화면에서 엔터키 입력시 로그인 시도 */
	$("#PASSWORD").keydown(function(key) {
		if (key.keyCode == 13) {
			$("#loginCkd").click();
		}
	});
	$('#loginCkd').click(function(e) {
		
		var ID = $("#ID").val();
		var PASSWORD = $("#PASSWORD").val();
		
		if($("#SAVEID").is(":checked")){
			var userInputId = ID
			setCookie("userInputId", userInputId, 60);
			setCookie("setCookieYN", "Y", 60);
		} else {
			deleteCookie("userInputId");
			deleteCookie("setCookieYN", "Y", 60);
		}
		
		if(ID == null || ID.trim() == "") {
			 swal({
				title				: '아이디를 입력해주세요.',
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			}); 
			return false;
		}
		
		if(PASSWORD == null || PASSWORD.trim() == "") {
			swal({
				title				: '비밀번호를 입력해주세요.',
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			});
			return false;
		}
	    
		$.ajax({
			url 		: "login.cut",
			data		: {"ID" : ID, "PASSWORD" : PASSWORD},
			contentType	: "application/json; charset=UTF-8",
			success		: function(data) {
				
				// 로그인에 실패했을 경우
				if(data.message != 'success') {
					
					let alertMsg;
					if(data.message == 'fail') {
						alertMsg = '로그인에 실패하였습니다.';
					} else if(data.message == 'invalidPw') {
						alertMsg = '비밀번호가 틀렸습니다.';
					} else if(data.message == 'notExist') {
						alertMsg = '존재하지 않는 아이디입니다.';
					}
					swal({
						title				: alertMsg,
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					});
				} else { // 로그인에 성공했을 경우
					swal({
						title				: '로그인에 성공했습니다',
						text 				: '메인 페이지로 이동합니다.',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					}).then((result) => {
						location.href="/SC/main.cut";
					});
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("ERROR : " + textStatus + " : " + errorThrown);
			}
		})
	});
})
</script>
<script>

</script>
</head>
<body>

<div id="main">
<section id="input-with-icons">
	<div class="col-12">
		<div class="col-md-4 col-12 mx-auto">
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
								                <input type="text" class="form-control" placeholder="ID" name="ID" id="ID">
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
									            <input type="password" id="PASSWORD" name="PASSWORD" class="form-control" placeholder="Password">
									            <div class="form-control-icon">
									                <i class="bi bi-lock"></i>
									            </div>
									        </div>
									    </div>
									</div>
			                        <div class="col-11 ">
										<a href="/SC/findIdForm.cut">아이디 찾기</a>
										<a href="/SC/findPwForm.cut">비밀번호 찾기</a>
									</div>
									<div class="col-12 d-flex justify-content-end">
		                                <div class="checkbox mt-2">
		                                    <input type="checkbox" id="SAVEID" class=''>
		                                    <label for="SAVEID">아이디 저장</label>
		                                </div>
			                        </div>
			                        <br><br>
			                        <div class="col-12" style="text-align: center; margin: 0 auto;">
			                        	<div>
			                        		<button type="button" id="loginCkd" class="btn btn-primary" style="width:300px; height:45px;">로그인</button>
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