<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeList</title>
</head>
<body>

	<div id="main">
		<div class="page-heading">
			<section id="basic-list-group">
				<div class="row match-height">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-header">
								<div style="text-align: center">
									<h5>공지사항</h5>
								</div>
							</div>
							<div class="card-content">
							
							<!-- 관리자만 작성하기 버튼 활성화 -->
							<c:if test="${id == 'ADMIN'}">
								<a href="noticeWriteForm.cut" class="btn btn-outline-primary" style="float: right; margin-right: 25px;">글쓰기</a>
							</c:if>	
								<div class="card-body">
									<table class="table">
										<tr>
											<th class="list-group-horizontal-sm">글번호</th>
											<th class="list-group-horizontal-sm">제목</th>
											<th class="list-group-horizontal-sm">작성자</th>
											<th class="list-group-horizontal-sm">조회수</th>
											<th class="list-group-horizontal-sm">좋아요</th>
											<th class="list-group-horizontal-sm">싫어요</th>
											<th class="list-group-horizontal-sm">작성날짜</th>
										</tr>
										<thead>
										</thead>
										<c:if test="${empty noticeList}">
											<tr>
												<th width="100%">게시글이 없습니다.</th>
											</tr>
										</c:if>
										<c:forEach var="noticeItem" items="${noticeList}">
											<c:choose>
												<%-- 삭제되지 않은 게시글 --%>
												<c:when test="${noticeItem.ISDEL == 'N'}">
													<tr>
														<td class="list-group-horizontal-sm">공지</td>
														<td class="list-group-horizontal-sm"><a href="noticeDetail.cut?NOTICEIDX=${noticeItem.NOTICEIDX}">${noticeItem.TITLE }</a></td>
														<td class="list-group-horizontal-sm">관리자</td>
														<td class="list-group-horizontal-sm">${noticeItem.READCOUNT }</td>
														<td class="list-group-horizontal-sm">${noticeItem.GOOD }</td>
														<td class="list-group-horizontal-sm">${noticeItem.BAD }</td>
														<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${noticeItem.NOTICEDATE}" /></td>
													</tr>
												</c:when>
												<%-- 삭제된 게시글 --%>
												<c:otherwise>
													<tr>
														<td class="list-group-horizontal-sm">${noticeItem.NOTICEIDX }</td>
														<td class="list-group-horizontal-sm"><span style="color: red">삭제된 글입니다.</span></td>
														<td class="list-group-horizontal-sm"></td>
														<td class="list-group-horizontal-sm"></td>
														<td class="list-group-horizontal-sm"></td>
														<td class="list-group-horizontal-sm"></td>
														<td class="list-group-horizontal-sm"></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</table>
									<div class="card-body mx-auto">
										<span>${paging.pageHtml}</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>