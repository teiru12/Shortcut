<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
function userModify() {
	swal({
		title				: '수정하시겠습니까?',
		closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
		buttons				: {
			confirm : {
				text 		: '수정',
				value 		: true,
				className 	: 'btn btn-primary' 
			},
			cancle : {
				text 		: '취소',
				value 		: false,
				className 	: 'btn btn-outline-primary' 
			}
		}
	}).then((result) => {
		if(result) {
			// 회원 정보 수정에 필요한 변수
			let ID = document.getElementById("ID").value;
			let PASSWORD = document.getElementById("PASSWORD").value;
			let PASSWORD2 = document.getElementById("PASSWORD2").value;
			let NAME = document.getElementById("NAME").value;
			let NICKNAME = document.getElementById("NICKNAME").value;
			let PROFILE = document.getElementById("PROFILE").value;
	
			// 입력받은 값들의 유효성 검사
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
			if(PASSWORD.trim() != PASSWORD2.trim()) {
				swal({
					title				: '비밀번호가 일치하지 않습니다.',
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
			if(NAME == null || NAME.trim() == "") {
				swal({
					title				: '이름을 입력해주세요.',
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
			if(NICKNAME == null || NICKNAME.trim() == "") {
				swal({
					title				: '닉네임을 입력해주세요.',
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
			
			/* 회원 수정 */
			$.ajax({
				url 		: "userModify.cut",
				data		: {"ID" : ID, "PASSWORD" : PASSWORD, "NAME" : NAME, "NICKNAME" : NICKNAME, "PROFILE" : PROFILE},
				contentType	: "application/json",
				success		: function(data) {
					
					swal({
						title				: '수정하였습니다',
						text 				: '마이 페이지로 이동합니다.',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					}).then((result) => {
						/* 회원 정보 수정 후 myPage로 리다이렉트 */
						location.href="/SC/myPage.cut";
					});
				}				
			});
		}
	});
}
function selectImage(imgNumber) {
	let src;
	if(imgNumber != 0 ) {
		src = "assets/images/faces/" + imgNumber + ".jpg";
	} else {
		src = "assets/images/faces/blank.png";
	}
	
	// modal 창 닫기
	$('#large').modal("hide");
	// 폼에 넘겨줄 hidden 태그의 value값 변경
	if(imgNumber != 0 ) {
		document.getElementById("PROFILE").value = imgNumber + ".jpg";
	} else {
		document.getElementById("PROFILE").value = "blank.png";
	}
	
	// 이미지 출력 변경
	document.getElementById("faceImg").src = src;
}
</script>
</head>
<body>
	<div id="app">
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

            <div class="page-heading">

                <!-- Basic Horizontal form layout section start -->
                <section id="basic-horizontal-layouts">
                    <div class="row match-height">                        
                        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">회원정보수정</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-horizontal" id="modifyForm" method="post"> <!-- Modify Form -->
                                            <div class="form-body">
                                                <div class="row">
                                                
                                                	<!-- 프로필 이미지 수정 -->
                                                	<div class="col-md-4">
														<div class="card-body py-4 px-5" style="margin-left:auto; margin-right:auto">
															<div class="d-flex align-items-center">
		                                                		<div class="avatar avatar-xl">
																	<!-- 이미지 없을 시 기본 이미지 이미지 있을 경우 해당 이미지 -->
																	<c:if test="${empty member.PROFILE}">
																		<img src="assets/images/faces/blank.png" alt="Face">
																	</c:if>
																	<c:if test="${! empty member.PROFILE}">
																		<img src="assets/images/faces/${member.PROFILE}" alt="Face" id="faceImg">
																	</c:if>
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
														
																<!-- modal -->
					                                            <button type="button" class="btn btn-outline-primary block" data-bs-toggle="modal"
					                                        		data-bs-target="#large">
					                                                이미지 선택
					                                            </button>
					                                            <input type="hidden" id="PROFILE" name="PROFILE" value="${member.PROFILE}">
																
                                                            	<!-- file input일 경우 사용할 폼
																<input class="form-control form-control-sm" id="formFileSm" type="file">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                                 -->
                                                            </div>
                                                        </div>
													</div>
                                                
                                                	<div class="col-md-4">
                                                        <label>ID</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="ID" id="ID" name="ID"
                                                                	value="${member.ID}" disabled>
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                    
                                                                                    
                                                    <div class="col-md-4">
                                                        <label>Password</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD" maxlength="10"
                                                                	name="PASSWORD" value="${member.PASSWORD}">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-lock"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="col-md-4">
                                                        <label>CheckPassword</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD2" maxlength="10"
                                                                value="${member.PASSWORD}">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-lock"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="col-md-4">
                                                        <label>Email</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="email" class="form-control" placeholder="Email" id="EMAIL" name="EMAIL"
                                                                	value="${member.EMAIL}" disabled>
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-envelope"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Name</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="Name" id="NAME" name="NAME"
                                                                	value="${member.NAME}" disabled>
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                                                    
                                                    <div class="col-md-4">
                                                        <label>NickName</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="NickName" id="NICKNAME" name="NICKNAME" maxlength="16"
                                                                	value="${member.NICKNAME}">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <br/><br/>
                                                    <br/><br/>
                                                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto d-flex justify-content-end">
                                                        <button type="button" class="btn btn-primary me-1 mb-1"
                                                         	onClick="userModify()">
                                                         	회원수정
                                                         </button>
                                                        <button type="button" class="btn btn-light-secondary me-1 mb-1"
                                                        	onClick="javascript:history.go(-1);">
                                                        	취소
                                                        </button>
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
                <!-- // Basic Horizontal form layout section end -->           
            </div>

        </div>
    </div>
    
	<!--large size Modal -->
	<div class="modal fade text-left" id="large" tabindex="-1" role="dialog"
	    aria-labelledby="myModalLabel17" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
		    role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel17">이미지 선택</h4>
					<button type="button" class="close" data-bs-dismiss="modal"
					    aria-label="Close">
					    <i data-feather="x"></i>
					</button>
				</div>
				<div class="modal-body">
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/blank.png" alt="Face" onClick="selectImage(0)">
					</div>	
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/1.jpg" alt="Face" onClick="selectImage(1)">
					</div>		
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/2.jpg" alt="Face" onClick="selectImage(2)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/3.jpg" alt="Face" onClick="selectImage(3)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/4.jpg" alt="Face" onClick="selectImage(4)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/5.jpg" alt="Face" onClick="selectImage(5)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/6.jpg" alt="Face" onClick="selectImage(6)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/7.jpg" alt="Face" onClick="selectImage(7)">
					</div>
					<div class="avatar avatar-xl">
						<img src="assets/images/faces/8.jpg" alt="Face" onClick="selectImage(8)">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-light-secondary"
					    data-bs-dismiss="modal">
					    <i class="bx bx-x d-block d-sm-none"></i>
					    <span class="d-none d-sm-block">취소</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>