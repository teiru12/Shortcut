<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="assets/css/bootstrap.css">

<link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="assets/css/app.css">
<link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>
<body>

	<div class="page-heading">
		<div class="page-title" style="width: 400px; height:600; margin-top: 25px">
			<div class="row">
				<div class="col-12 col-md-6 order-md-1 order-last" style="text-align: center">
					<h3>회원 정보</h3>
				</div>
				<!-- <div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#" onClick="close()">윈도우창끄기</a></li>
							</ol>
						</nav>
					</div> -->
			</div>
		</div>
		<section class="section">
			<div class="card" style="width: 350px; margin-left: auto; margin-right: auto;">
				<div class="card-header" style="text-align: center">
					<h4 class="card-title">${member.NAME }</h4>
				</div>

				<div class="card-body py-4 px-5" style="margin-left: auto; margin-right: auto">
					<div class="d-flex align-items-center">
						<div class="avatar avatar-xl">
							<!-- 이미지 없을 시 기본 이미지 이미지 있을 경우 해당 이미지 -->
							<c:if test="${empty member.PROFILE}">
								<img src="assets/images/faces/blank.png" alt="Face">
							</c:if>
							<c:if test="${!empty member.PROFILE}">
								<img src="assets/images/faces/${member.PROFILE}" alt="Face">
							</c:if>
						</div>
						<br>
						<div class="ms-3 name">
							<h5 class="font-bold">레벨 : ${level}</h5>
							<h6 class="text-muted mb-0">경험치 : ${member.EXP}</h6>
						</div>
					</div>
				</div>

				<div class="card-content pb-4" style="margin-left: auto; margin-right: auto">
					<div class="px-4">
						<button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' onClick="sendMessage()">쪽지보내기</button>
					</div>
					<div class="px-4">
						<button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' onClick="chat()">채팅하기</button>
					</div>
					<div class="px-4">
						<button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' onClick="follow()">팔로우하기</button>
					</div>

				</div>
			</div>
		</section>
	</div>

</body>
</html>