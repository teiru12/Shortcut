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
	                        <h4 class="card-title">자유게시판</h4>
	                        <a href="freeWriteForm.cut" class="btn btn-outline-primary" style="float:right; color:aqua; background-color:blueviolet;">글쓰기</a>
	                    </div>
	                    <div class="card-content">
	                        <div class="card-body">
	                       		<div style="text-align:center"><h5>공지사항</h5></div>
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
		                        	<c:forEach var="noticeTopItem" items="${noticeTopList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm">${noticeTopItem.NOTICEIDX }</td>
	                               			<td class="list-group-horizontal-sm"><a href="noticeDetail.cut?NOTICEIDX=${noticeTopItem.NOTICEIDX}">${noticeTopItem.TITLE }</a></td>
	                               			<td class="list-group-horizontal-sm">관리자</td>
	                               			<td class="list-group-horizontal-sm">${noticeTopItem.READCOUNT }</td>
	                               			<td class="list-group-horizontal-sm">${noticeTopItem.GOOD }</td>
	                               			<td class="list-group-horizontal-sm">${noticeTopItem.BAD }</td>
	                               			<td class="list-group-horizontal-sm" ><fmt:formatDate pattern="yyyy-MM-dd" value="${noticeTopItem.NOTICEDATE}"/></td>
	                               		</tr>
		                        	</c:forEach>
                        		</table>
	                        </div>
						</div>
						<div class="card-content">
							<div class="card-body">
	                       		<div style="text-align:center"><h5>자유게시판</h5></div>
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
		                        	<c:forEach var="freeItem" items="${freeList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm">${freeItem.FREEIDX }</td>
	                               			<td class="list-group-horizontal-sm"><a href="freeDetail.cut?FREEIDX=${freeItem.FREEIDX}">${freeItem.TITLE }</a></td>
	                               			<td class="list-group-horizontal-sm">${freeItem.ID }</td>
	                               			<td class="list-group-horizontal-sm">${freeItem.READCOUNT }</td>
	                               			<td class="list-group-horizontal-sm">${freeItem.GOOD }</td>
	                               			<td class="list-group-horizontal-sm">${freeItem.BAD }</td>
	                               			<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${freeItem.FREEDATE }"/></td>
	                               		</tr>
		                        	</c:forEach>
                        		</table>
	                        </div>

<%-- 	                        	<table class="table">
	                        		
			                        	<c:forEach var="freeItem" items="${freeList}">
			                                <li class="list-group-item"><a href="freeDetail.cut?FREEIDX=${freeItem.FREEIDX}">${freeItem.TITLE }</a></li>
			                        	</c:forEach>
	                        	</table> --%>
						</div>
					</div>
				</div>
			</div>
	    </section>
	</div>
</div>
</body>
</html>