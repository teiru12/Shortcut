<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
//아이디 찾기
function findId_click(){		
	let NAME = $("#NAME").val(); 
	let EMAIL = $("#EMAIL").val();

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
							 swal({
									title				: '아이디 찾기',
									text 				: '회원 정보가 없습니다',
									closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
									buttons				: {
										confirm : {
											text 		: '확인',
											value 		: true,
											className 	: 'btn btn-primary' 
										}
									}
								}).then((result) => {
									$('#EMAIL').focus();
									return false;
								});
						 } else {
							 swal({
									title				: '아이디 찾기',
									text 				: NAME + "님의 아이디는 '" + data + " '입니다.",
									closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
									buttons				: {
										cancle : {
											text 		: '확인',
											value 		: false,
											className 	: 'btn btn-outline-primary' 
										},
										confirm : {
											text 		: '비밀번호 찾기',
											value 		: true,
											className 	: 'btn btn-primary' 
										}
									}
							}).then((result) => {
									if(result) {
										window.location.href="/SC/findPwForm.cut"
									}
							});
						 }
					 }
				 })
			} else {
				swal({
					title				: '아이디 찾기',
					text 				: '이메일 형식이 맞지않습니다.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then((result) => {
					$('#EMAIL').focus();
					return false;
				});
			}
		} else {
			swal({
				title				: '아이디 찾기',
				text 				: '이메일을 입력해주세요.',
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			}).then((result) => {
				$('#EMAIL').focus();
				return false;
			});
		}
	} else {
		swal({
			title				: '아이디 찾기',
			text 				: '이름을 입력해주세요.',
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		}).then((result) => {
			$('#NAME').focus();
			return false;
		});
	}	
}
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

							<a href="#" onclick="findId_click();" class="btn btn-primary btn-block btn-lg shadow-lg mt-5" id="findId">아이디 찾기</a>

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