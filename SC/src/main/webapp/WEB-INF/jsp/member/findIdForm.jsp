<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<script type="text/javascript">
//아이디 찾기
function findId_click(){		
	let NAME = $("#NAME").val(); 
	let EMAIL = $("#EMAIL").val();
	
	let sendData = "NAME=" + NAME + "&EMAIL=" + EMAIL;
	//이메일 형식 검증을 위한 변수
	let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
	if(NAME != null && NAME != "") {
		if(EMAIL != null && EMAIL != ""){
			if (EMAIL.match(regExp) != null) {
				$.ajax({
					 url : "findId.cut",
					 contentType : "application/json; charset=UTF-8",
					 data : {"NAME" : NAME, "EMAIL" : EMAIL}, 
					 success : function(data){
						 if(data == 0) {
							 $('#ID').text("회원 정보가 없습니다.");
						 } else {
							 $('#ID').text(NAME + "님의 아이디는 '" + data + " '입니다.");
													 
							 ID = data;
						 }
						 $('#modalBox').modal('show');
						 return false;
					 }
				 })
			} else {
				alert('이메일 형식이 맞지않습니다.'); 
				$('#EMAIL').focus();
				return false;
			}
		} else {
			alert('이메일을 입력해주세요.');
			$('#EMAIL').focus();
			return false;
		}
	} else {
		alert('이름을 입력해주세요.');
		$('#NAME').focus();
		return false;
	}	
}
//로그인, 비밀번호 찾기로 넘어가기
$(document).ready(function(){		
	$('#loginBtn').on('click', function(){
	  $('#modalBox').modal('hide');
	  window.location.href="/SC/login.cut";
	});
	$('#findPwBtn').on('click', function(){
	  $('#modalBox').modal('hide');
	  window.location.href="/SC/findPwForm.cut";
	});
});
</script>
<body>
	<div id="main">
		<div id="findId">
			<div class="row h-100">
				<div class="col-lg-5 col-12">
					<div id="auth-left">
						<h3 class="auth-title">아이디 찾기</h3>
						<p class="auth-subtitle mb-5">
							회원 가입 시 입력하신 이름과 이메일 주소를 입력하시면 <br>아이디를 확인할 수 있습니다.
						</p>

						<form action="findId.cut">
							<div class="form-group position-relative has-icon-left mb-4">
								<input type="text" id="NAME" name="NAME" class="form-control form-control-xl" placeholder="이름">
								<div class="form-control-icon">
									<i class="bi bi-person"></i>
								</div>
							</div>

							<div class="form-group position-relative has-icon-left mb-4">
								<input type="email" id="EMAIL" name="EMAIL" class="form-control form-control-xl" placeholder="이메일">
								<div class="form-control-icon">
									<i class="bi bi-envelope"></i>
								</div>
							</div>
							<!-- <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">아이디 찾기</button> -->

							<!-- Modal창 띄우기 -->
							<a href="#" onclick="findId_click();" class="btn btn-primary btn-block btn-lg shadow-lg mt-5" id="findId">아이디 찾기</a>

							<!-- Modal로 ID 확인하기 -->
							<div class="modal fade text-left" id="modalBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
								<div class="modal-dialog modal-dialog-scrollable" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="myModalLabel1">아이디 찾기</h5>
										</div>
										<div class="modal-body">
											<div id=ID></div>
										</div>
										<div class="modal-footer">
											<button type="button" id="loginBtn" class="btn btn-primary ml-1" data-bs-dismiss="modal">
												<i class="bx bx-x d-block d-sm-none"></i> <span class="d-none d-sm-block">로그인</span>
											</button>

											<button type="button" id="findPwBtn" class="btn btn-primary ml-1" data-bs-dismiss="modal">
												<i class="bx bx-check d-block d-sm-none"></i> <span class="d-none d-sm-block">비밀번호 찾기</span>
											</button>
										</div>
									</div>
								</div>
							</div>

						</form>

						<div class="text-center mt-5 text-lg fs-4">
							<p class='text-gray-600'>
								Remember your account? <a href="loginForm.cut" class="font-bold">Log in</a>.
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-7 d-none d-lg-block">
					<div id="auth-right"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>