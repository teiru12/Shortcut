<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>숏컷</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>

<div id="main"> <!-- main div를 사용하면 좌우 패딩이 생김 -->

	<div id="app">
	
		<div class="page-title">
			<div class="row">
				<div class="col-12 col-md-6 order-md-1 order-last">
					<!--
					헤더 왼쪽 위에 추가로 넣을 요소 있으면 여기에 넣기 			    
					<h3>숏컷</h3>
				    <p class="text-subtitle text-muted">For user to check they list</p>
				    -->
				</div>
				<div class="col-12 col-md-6 order-md-2 order-first">
					<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
						<ol class="breadcrumb"> <!-- 우측 상단 메뉴 리스트 -->
						
							<!-- XXX 회원님 로그인 하셨습니다. -->
							<c:if test="${! empty id}">
								<li class="breadcrumb"><a href="#" class="btn icon icon-left">${id} 회원님</a></li>
							</c:if>
						
							<!-- 로그인하지 않은 상태에서 보여줄 상단 메뉴 -->
							<c:if test="${empty id}">
								<li class="breadcrumb"><a href="main.cut" class="btn icon icon-left btn-secondary">로&emsp;그&emsp;인</a>&nbsp;</li>
								<li class="breadcrumb"><a href="main.cut" class="btn icon icon-left btn-secondary">회 원 가 입</a></li>
							</c:if>

							<!-- 로그인한 상태에서 보여줄 상단 메뉴 -->
							<c:if test="${! empty id}">
								<li class="breadcrumb"><a href="main.cut" class="btn icon icon-left btn-secondary">로 그 아 웃</a>&nbsp;</li>
								<li class="breadcrumb"><a href="main.cut" class="btn icon icon-left btn-secondary">마이페이지</a></li>
							</c:if>
							
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>