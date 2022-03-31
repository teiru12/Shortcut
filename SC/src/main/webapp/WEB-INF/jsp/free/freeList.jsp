<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeList</title>
</head>
<body>

<div id="main">
	<div class="page-heading">
	    <section id="basic-list-group">
	        <div class="row match-height">
	            <div class="col-lg-12 col-md-12">
	                <div class="card">
	                    <div class="card-header">
							<a href="freeWriteForm.cut" class="btn btn-outline-primary" style="float:right;">글쓰기</a>
	                    </div>
	                    <div style="text-align:center"><h5>자유게시판</h5></div>
	                    <div class="card-content">
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
		                        	<c:forEach var="noticeTop" items="${noticeTopList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm"><b>공지</b></td>
	                               			<td class="list-group-horizontal-sm"><b><a href="noticeDetail.cut?NOTICEIDX=${noticeTop.NOTICEIDX}">${noticeTop.TITLE }</a></b></td>
	                               			<td class="list-group-horizontal-sm"><b>관리자</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTop.READCOUNT }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTop.GOOD }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${noticeTop.BAD }</b></td>
	                               			<td class="list-group-horizontal-sm" ><b><fmt:formatDate pattern="yyyy-MM-dd" value="${noticeTop.NOTICEDATE}"/></b></td>
	                               		</tr>
		                        	</c:forEach>
		                        	<c:forEach var="free" items="${freeList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm">${free.FREEIDX }</td>
	                               			<td class="list-group-horizontal-sm"><a href="freeDetail.cut?FREEIDX=${free.FREEIDX}">${free.TITLE }</a></td>
	                               			<td class="list-group-horizontal-sm">${free.ID }</td>
	                               			<td class="list-group-horizontal-sm">${free.READCOUNT }</td>
	                               			<td class="list-group-horizontal-sm">${free.GOOD }</td>
	                               			<td class="list-group-horizontal-sm">${free.BAD }</td>
	                               			<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${free.FREEDATE }"/></td>
	                               		</tr>
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