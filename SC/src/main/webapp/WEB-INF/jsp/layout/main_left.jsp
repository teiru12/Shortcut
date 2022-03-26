<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

</head>
<div id="sidebar" class="active">
	<div class="sidebar-wrapper active">
		<div class="sidebar-header">
			<div class="d-flex justify-content-between">
				<div class="logo">
					<a href="main.cut"><img src="assets/images/logo/logo2.png" alt="Logo" style="width:200px; height:200px"></a>
				</div>
				<div class="toggler">
					<a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
				</div>
			</div>
		</div>
		<div class="sidebar-menu">
			<ul class="menu">
			
				<li class="sidebar-title">총 방문자 수 : </li>
				<li class="sidebar-title">오늘 방문자 수 : </li>
				<li class="sidebar-title">검색버튼 위치</li>
			
				<li class="sidebar-title"></li>

					<li class="sidebar-item has-sub">
						<a href="#" class='sidebar-link'>
							<i class="bi bi-grid-fill"></i>
							<span>게시판 리스트</span>
						</a>
						<ul class="submenu active">
							<li class="submenu-item ">
								<a href="shortcutList.cut">단축키 게시판</a>
							</li>
							<li class="submenu-item ">
								<a href="freeList.cut">자유 게시판</a>
							</li>
							<li class="submenu-item ">
								<a href="infoList.cut">정보교류 게시판</a>
							</li>
							<li class="submenu-item active">
								<a href="newsList.cut">뉴스 게시판</a>
							</li>
							<li class="submenu-item ">
								<a href="reportList.cut">신고 게시판</a>
							</li>
							<li class="submenu-item ">
								<a href="noticeList.cut">공지 게시판</a>
							</li>
					    </ul>
					</li>
					
					<!-- 관리자일 경우 추가로 보여주는 페이지 -->
					<c:if test="${id == 'admin'}">
						<li class="sidebar-item has-sub">
							<a href="#" class='sidebar-link'>
								<i class="bi bi-grid-fill"></i>
								<span>관리자</span>
							</a>
							<ul class="submenu active">
								<li class="submenu-item ">
									<a href="adminMainPage.cut">관리자 메인</a>
								</li>
								<li class="submenu-item ">
									<a href="adminMemberList.cut">회원 리스트</a>
								</li>
								<li class="submenu-item ">
									<a href="adminShortWriteForm.cut">단축키 등록</a>
								</li>
								<li class="submenu-item ">
									<a href="noticeWriteForm.cut">공지사항 등록</a>
								</li>
								<!-- 
								배너 등록 기능 추가 시 사용
								<li class="submenu-item ">
									<a href="addBannerForm.cut">배너 등록</a>
								</li>
								-->
						    </ul>
						</li>								
					</c:if>
				</ul>
           </div>
           <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
       </div>
</div>