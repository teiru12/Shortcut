<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="assets/js/detail.js"></script>
</head>
<body>
<div id="main">
<section class="section">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4 class="card-title">${newsDetail.TITLE}</h4>
				</div>
				<div class="card-body">
					<div class="btn-group dropend me-1 mb-1">
						<button type="button" class="btn btn-icon-default"
							data-bs-toggle="dropdown" aria-haspopup="false"
							aria-expanded="false">
							${newsDetail.ID}
						</button>
						<div class="dropdown-menu">
							<button class="btn btn-icon-default" onClick="writerDetail('${newsDetail.ID}')">회원 정보</button><br>
							<c:if test="${! empty sessionScope.id}">
								<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
								data-bs-target="#inlineForm"
								data-s="<%= request.getSession().getAttribute("id") %>" data-g="${newsDetail.ID}">
									쪽지 보내기
								</button><br>
								<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
							</c:if>
						</div>
					</div> &nbsp; 
					<span class="text-subtitle text-muted">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${newsDetail.NEWSDATE}"/> &nbsp;
							조회수 ${newsDetail.READCOUNT} &emsp;
						
						<!-- 좋아요 -->		
						<span id="goodSpan">
							<!-- 좋아요를 이미 했을 경우 -->
							<c:if test="${goodUsed == 'Y'}">
								<a href="#">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-up-fill" />
			                    	</svg>
			                    </a>							
							</c:if>
							<!-- 좋아요를 하지 않았을 경우 -->
							<c:if test="${goodUsed != 'Y'}">
								<!-- 로그인했을 경우 좋아요 버튼 활성화 -->
								<% if(request.getSession().getAttribute("id") != null) { %>
								<a id="goodButton" href="javascript:addGood('<%= request.getSession().getAttribute("id") %>',
									'NEW', ${newsDetail.NEWSIDX}, ${newsDetail.GOOD})">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-up" />
			                    	</svg>
			                    </a>
								<% } else { %>
			                    <!-- 로그인하지 않았을 경우 좋아요 버튼 비활성화 -->
								<a href="#">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-up" />
			                    	</svg>
			                    </a>
								<% } %>
							</c:if>
						</span> <!-- end goodSpan -->
	                    <span id="goodValue">${newsDetail.GOOD}</span>&nbsp;
	                    
	                    <!-- 싫어요 -->
	                    <span id="badSpan">
							<!-- 싫어요를 이미 했을 경우 -->
							<c:if test="${badUsed == 'Y'}">
								<a href="#">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-down-fill" />
			                    	</svg>
			                    </a>							
							</c:if>
							<!-- 싫어요를 하지 않았을 경우 -->
							<c:if test="${badUsed != 'Y'}">
								<!-- 로그인했을 경우 싫어요 버튼 활성화 -->
								<% if(request.getSession().getAttribute("id") != null) { %>
								<a id="badButton" href="javascript:addBad('<%= request.getSession().getAttribute("id") %>',
								'NEW', ${newsDetail.NEWSIDX}, ${newsDetail.BAD})">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-down" />
			                    	</svg>
			                    </a>
								<% } else { %>
			                    <!-- 로그인하지 않았을 경우 싫어요 버튼 비활성화 -->
								<a href="#">
									<svg class="bi" width="1em" height="1em" fill="currentColor">
			                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-down" />
			                    	</svg>
			                    </a>
								<% } %>
							</c:if>
						</span> <!-- end goodSpan -->
						<span id="badValue">${newsDetail.BAD}</span>&nbsp;
						
						<!-- 즐겨찾기 -->
						<!-- 로그인하지 않았을 경우 보여주지 않음 -->
						<% if(request.getSession().getAttribute("id") != null) { %>
							<span id="bookSpan">
							<!-- 즐겨찾기를 했을 경우 꽉찬 별표 -->
							<c:if test="${! empty bookmark}">
								<a id="bookStar" href="javascript:updateBookmark('<%= request.getSession().getAttribute("id") %>',
								'NEW', ${newsDetail.NEWSIDX}, 'DEL')">
		                            <svg class="bi" width="1em" height="1em" fill="currentColor">
		                                <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#star-fill" />
		                            </svg>
								</a>
							</c:if>
							<!-- 즐겨찾기를 하지 않았을 경우 빈 별표 -->
							<c:if test="${empty bookmark}">
								<a id="bookStar" href="javascript:updateBookmark('<%= request.getSession().getAttribute("id") %>',
								'NEW', ${newsDetail.NEWSIDX}, 'ADD')">
		                            <svg class="bi" width="1em" height="1em" fill="currentColor">
		                                <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#star" />
		                            </svg>
								</a>
							</c:if>
							</span>
						<% } %>
						&emsp;
						
						<!-- 신고 -->
						<a href="#"><span style="color:#C00000">신고</span></a>
					</span><hr>
					<p>${newsDetail.CONTENT}</p>
					<div style="text-align:right;">
						<a href="/SC/newsList.cut" class="btn btn-sm btn-outline-secondary">목록으로</a>

						<!-- 로그인상태 -->
						<c:if test="${id == freeDetail.ID}"> 
							<a href="javascript:modifyDetailCheck('member', 'NEW', ${newsDetail.NEWSIDX})" class="btn btn-sm btn-outline-secondary">수정하기</a>
							<a href="javascript:deleteDetailCheck('member', 'NEW', ${newsDetail.NEWSIDX})" class="btn btn-sm btn-outline-secondary">삭제하기</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div>
</body>
</html>