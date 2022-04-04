<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


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
		<div class="page-title" style="width: 400px; margin-top: 25px">
			<div class="row">
				<div class="col-12 col-md-6 order-md-1 order-last" style="text-align: center">
					<h3>회원 정보</h3>
				</div>
			</div>
		</div>
		<section class="section">
			<div class="card" style="width: 350px; margin-left: auto; margin-right: auto; margin-top: 10px">
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

				<input type="hidden" id="loginId" value="<%=request.getSession().getAttribute("id")%>">
				<div class="card-content pb-4" style="margin-left: auto; margin-right: auto">
					<div class="px-4">
						<button type="button" class="btn btn-block btn-xl btn-light-primary font-bold mt-3" 
						data-bs-toggle="modal" data-bs-target="#inlineForm"
						data-s="<%= request.getSession().getAttribute("id") %>" data-g="${member.ID}">쪽지보내기</button>
					</div>
					<div class="px-4">
						<button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' onClick="chat()">채팅하기</button>
					</div>
					<div class="px-4">
						<c:if test="${isFollow != true}">
							<button class='btn btn-block btn-xl btn-light-primary font-bold mt-3' id="followButton" 
								onClick="addFollow('${member.ID}')">팔로우하기</button>
						</c:if>
						<c:if test="${isFollow == true}">
							<button class='btn btn-block btn-xl btn-success font-bold mt-3'>팔로우 중</button>
						</c:if>
					</div>

				</div>
			</div>
		</section>
	</div>
	
<script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/js/main.js"></script>

</body>
</html>