<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
/* 회원 탈퇴 Ajax */
function userDelete() {
	swal({
		title				: '탈퇴하시겠습니까?',
		text 				: '탈퇴 후 재가입 가능합니다.',
		dangerMode			: true, // 확인 버튼 빨갛게
		closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
		buttons				: {
			cancle : {
				text 		: '취소',
				value 		: false,
				className 	: 'btn btn-outline-primary' 
			},
			confirm : {
				text 		: '탈퇴',
				value 		: true,
				className 	: 'btn btn-outline-primary' 
			}
		}
	
	}).then((result) => {
		if(result) {
			/* 회원 탈퇴 */
			$.ajax({
				url 		: "/SC/userDelete.cut",
				data		: { },
				contentType	: "application/json",
				success		: function(data) {
					
					swal({
						title				: '탈퇴하셨습니다',
						text 				: '로그인 페이지로 이동합니다.',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-outline-primary' 
							}
						}
					}).then((result) => {
						/* 회원 탈퇴 후 loginForm으로 리다이렉트 */
						location.href="/SC/loginForm.cut";
					});
				}				
			});
		}
	});
}
/* 회원정보수정 */
function userModifyForm() {
	location.href="/SC/userModifyForm.cut";
}
/* 쪽지 리스트 */
function messageList() {
	location.href="/SC/messageList.cut";
}
/* 즐겨찾기 리스트 */
function bookmarkList() {
	location.href="/SC/bookmarkList.cut";
}
/* 팔로우 리스트 */
function followList() {
	location.href="/SC/followList.cut";
}
</script>
</head>
<body>
<div id="main">
	<div class="page-content">
		<section class="row">
			<div class="col-12 col-lg-12">
				<div class="col-md-3 col-12 mx-auto">
					<div class="card">
					
						<div class="col-12 col-lg-12" style="text-align:center">
							<br>
							<h3 class="font-bold">마이페이지</h3>                		
						</div>
					
						<div class="card-body py-4 px-5" style="margin-left:auto; margin-right:auto">
							<div class="d-flex align-items-center">
								<div class="avatar avatar-xl">
									<!-- 이미지 없을 시 기본 이미지 이미지 있을 경우 해당 이미지 -->
									<img src="assets/images/faces/${member.PROFILE}" alt="Face">
								</div>
								<div class="ms-3 name">
									<h5 class="font-bold">레벨 : ${level}</h5>
									<h6 class="text-muted mb-0">경험치 : ${member.EXP}</h6>
								</div>
							</div>
						</div>
					
					    <div class="card-content pb-4" style="margin-left:auto; margin-right:auto">
							<div class="px-4">
					  		  <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'
					  		  	onClick="userModifyForm()">
					  		  	회원 정보수정
					  		  </button>
							</div>
							<div class="px-4">
							    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'
							    	onClick="messageList()">
							    	쪽지
							    </button>
							</div>
							<div class="px-4">
							    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'
							    	onClick="bookmarkList()">
							    	즐겨찾기
							    </button>
							</div>
							<div class="px-4">
							    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'
									onClick="followList()">
									팔로우
								</button>
							</div>
							<div class="px-4">
							    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' style="color:red;"
							    	onClick="userDelete()">
							    	회원 탈퇴
							    </button>
							</div>
				        </div>
				    </div> <!-- end card -->
			    
			    </div>			    
			</div>
		</section>
	</div>
</div>
</body>
</html>