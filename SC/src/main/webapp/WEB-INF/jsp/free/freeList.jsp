<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<body>

<div id="main">
	<div class="page-heading">
	    <section id="basic-list-group">
	        <div class="row match-height">
	            <div class="col-lg-12 col-md-12">
	                <div class="card">
	                	<div class="card-header">
	                    	<div style="text-align:center"><h5>자유게시판</h5></div>
	                    </div>
	                    <div class="card-content">
	                    	<a href="freeWriteForm.cut" class="btn btn-outline-primary" style="float: right; margin-right: 25px;">글쓰기</a>
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
									<c:if test = "${empty noticeTopList and empty freeList}">
										<tr>
											<th width="100%">게시글이 없습니다.</th>
										</tr>		
									</c:if>
		                        	<c:forEach var="noticeTopItem" items="${noticeTopList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm"><b>공지</b></td>
	                               			<td class="list-group-horizontal-sm"><b><a href="noticeDetail.cut?NOTICEIDX=${noticeTopItem.NOTICEIDX}">${noticeTopItem.TITLE }</a></b></td>
	                               			<td class="list-group-horizontal-sm"><b>관리자</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTopItem.READCOUNT }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTopItem.GOOD }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTopItem.BAD }</b></td>
	                               			<td class="list-group-horizontal-sm" ><b><fmt:formatDate pattern="yyyy-MM-dd" value="${noticeTopItem.NOTICEDATE}"/></b></td>
	                               		</tr>
		                        	</c:forEach>
		                        	<c:forEach var="freeItem" items="${freeList}">
										<c:choose>
											<%-- 삭제되지 않은 게시글 --%>		
	                               			<c:when test="${freeItem.ISDEL == 'N'}">
			                        			<tr>
			                               			<td class="list-group-horizontal-sm">${freeItem.FREEIDX }</td>
			                               			<td class="list-group-horizontal-sm"><a href="freeDetail.cut?FREEIDX=${freeItem.FREEIDX}">${freeItem.TITLE }</a></td>
			                               			<td class="list-group-horizontal-sm">
														<button type="button" class="btn btn-icon-default"
															data-bs-toggle="dropdown" aria-haspopup="false"
															aria-expanded="false">
															<b>${freeItem.ID}</b>
														</button>
														<div class="dropdown-menu">
															<button class="btn btn-icon-default" onClick="writerDetail('${freeItem.ID}')">회원 정보</button><br>
															<c:if test="${! empty sessionScope.id}">
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																data-s="<%= request.getSession().getAttribute("id") %>" data-g="${freeItem.ID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</c:if>
														</div>
			                               			</td>
			                               			<td class="list-group-horizontal-sm">${freeItem.READCOUNT }</td>
			                               			<td class="list-group-horizontal-sm">${freeItem.GOOD }</td>
			                               			<td class="list-group-horizontal-sm">${freeItem.BAD }</td>
			                               			<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${freeItem.FREEDATE }"/></td>
			                               		</tr>
			                               	</c:when>
	                               		
	                               			<%-- 삭제된 게시글 --%>
	                               			<c:otherwise>
			                        			<tr>
			                               			<td class="list-group-horizontal-sm">${freeItem.FREEIDX }</td>
			                               			<td class="list-group-horizontal-sm"><span style="color:red">삭제된 글입니다.</span></td>
			                               			<td class="list-group-horizontal-sm">
			                               			</td>
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
								<form action="freeList.cut">
	                                <div class="col-md-3 mb-1" style="float:right;">
	                                    <div class="input-group mb-3">
	                                        <span class="input-group-text"><i class="bi bi-search"></i></span>
	                                        <input type="text" name="KEYWORD" id="KEYWORD" value="${KEYWORD}" class="form-control" placeholder="검색어 ...">
	                                        <button class="btn btn-outline-secondary">검색</button>
	                                    </div>
	                                </div>
                                </form>
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