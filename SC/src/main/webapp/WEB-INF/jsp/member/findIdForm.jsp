<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<script>
//아이디 찾기

</script>
<body>
	<div id="main">
		<div id="findId">
			<div class="row h-100">
				<div class="col-lg-5 col-12">
					<div id="auth-left">
						<h1 class="auth-title">아이디 찾기</h1>
						<p class="auth-subtitle mb-5">
							회원 가입 시 입력하신 이름과 이메일 주소를 입력하시면 <br>이메일로 아이디를 보내드립니다.
						</p>

						<form action="findId.cut">
							<div class="form-group position-relative has-icon-left mb-4">
								<input type="text" id="NAME" name="NAME" class="form-control form-control-xl" placeholder="이름">
								<div class="form-control-icon">
									<i class="bi bi-emoji-smile"></i>
								</div>
							</div>

							<div class="form-group position-relative has-icon-left mb-4">
								<input type="email" id="EMAIL" name="EMAIL" class="form-control form-control-xl" placeholder="이메일">
								<div class="form-control-icon">
									<i class="bi bi-envelope"></i>
								</div>
							</div>
							<button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">아이디 찾기</button>

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