<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script>
function makePDF(STYPE) {
	swal({
		title				: 'PDF로 출력하시겠습니까?',
		dangerMode			: true, // 확인 버튼 빨갛게
		closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
		buttons				: {
			cancle : {
				text 		: '취소',
				value 		: false,
				className 	: 'btn btn-primary' 
			},
			confirm : {
				text 		: '확인',
				value 		: true,
				className 	: 'btn btn-outline-primary' 
			}
		}	
	}).then((result) => {
		
		if(result) {
			location.href = "/SC/shortSavePdf.cut?STYPE=" + STYPE;
		} 
	});
}
</script>
</head>
<body>
<div id="main">
	<div class="page-heading">
	    <section class="section">
			<div class="row">
				<div class="col-lg-12 col-md-12">
	                <div class="card">
	                    <div class="card-header">
	                        <h5 class="card-title" style="text-align:center">단축키게시판</h5>
	                    </div>
	                    <div class="card-body">
	                        <ul class="nav nav-tabs" id="myTab" role="tablist">
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="home-tab" href="shortcutList.cut">전체</a>
	                            </li>
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="contact-tab" href="shortcutList.cut?STYPE=M">마이크로소프트</a>
	                            </li>
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="contact-tab" href="shortcutList.cut?STYPE=W">윈도우</a>
	                            </li>
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="contact-tab" href="shortcutList.cut?STYPE=E">이클립스</a>
	                            </li>
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="contact-tab" href="shortcutList.cut?STYPE=H">한글</a>
	                            </li>
	                            <li class="nav-item" role="presentation">
	                                <a class="nav-link" id="contact-tab" href="shortcutList.cut?STYPE=N">노트패드++</a>
	                            </li>
	                            <li>
									<c:if test="${STYPE == 'M'}">
										<a href="javascript:makePDF('M')" class="btn btn-outline-primary rounded-pill">PDF 다운</a>
									</c:if>
									<c:if test="${STYPE == 'W'}">
										<a href="javascript:makePDF('W')" class="btn btn-outline-primary rounded-pill">PDF 다운</a>
									</c:if>
									<c:if test="${STYPE == 'E'}">
										<a href="javascript:makePDF('E')" class="btn btn-outline-primary rounded-pill">PDF 다운</a>
									</c:if>
									<c:if test="${STYPE == 'H'}">
										<a href="javascript:makePDF('H')" class="btn btn-outline-primary rounded-pill">PDF 다운</a>
									</c:if>
									<c:if test="${STYPE == 'N'}">
										<a href="javascript:makePDF('N')" class="btn btn-outline-primary rounded-pill">PDF 다운</a>
									</c:if>
	                            </li>
	                        </ul>
	                        <div class="tab-content" id="myTabContent">
	                            <div class="tab-pane fade show active" id="shortcutList.cut" role="tabpanel" aria-labelledby="home-tab">
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
									<c:if test = "${empty noticeTopList}">
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
		                        	<c:forEach var="shortItem" items="${shortList}">
		                        			<tr>
		                               			<td class="list-group-horizontal-sm">${shortItem.SHORTIDX }</td>
		                               			<td class="list-group-horizontal-sm"><a href="shortDetail.cut?SHORTIDX=${shortItem.SHORTIDX}">${shortItem.TITLE }</a></td>
		                               			<td class="list-group-horizontal-sm">${shortItem.ID }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.READCOUNT }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.GOOD }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.BAD }</td>
		                               			<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${shortItem.SHORTDATE }"/></td>
		                               		</tr>
		                        	</c:forEach>
		                        	</table>
		                        	<c:if test = "${!empty noticeTopList}">
			                        	<div class="card-body mx-auto">
											<span>${paging.pageHtml}</span>
										</div>
									</c:if>
									
									<%-- PDF 출력 버튼 --%>
										
									<form action="shortcutList.cut">
		                                <div class="col-md-3 mb-1" style="float:right;">
		                                    <div class="input-group mb-3">
		                                        <span class="input-group-text"><i class="bi bi-search"></i></span>
		                                        <input type="text" name="KEYWORD" id="KEYWORD" value="${KEYWORD}" class="form-control" placeholder="검색어 ...">
		                                        <c:if test="${STYPE == 'M'}">
		                                        	<input type="hidden" name="STYPE" id="STYPE" value="M">
		                                        </c:if>
		                                        <c:if test="${STYPE == 'W'}">
		                                        	<input type="hidden" name="STYPE" id="STYPE" value="W">
		                                        </c:if>
		                                        <c:if test="${STYPE == 'E'}">
		                                        	<input type="hidden" name="STYPE" id="STYPE" value="E">
		                                        </c:if>
		                                        <c:if test="${STYPE == 'H'}">
		                                        	<input type="hidden" name="STYPE" id="STYPE" value="H">
		                                        </c:if>
		                                        <c:if test="${STYPE == 'N'}">
		                                        	<input type="hidden" name="STYPE" id="STYPE" value="N">
		                                        </c:if>
		                                        <button class="btn btn-outline-secondary">검색</button>&nbsp;
		                                    </div>
		                                </div>
                                	</form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	</div>
</div>






	                            
	                            
	                            <%-- <div class="tab-pane fade" id="shortcutList.cut?STYPE=M" role="tabpanel" aria-labelledby="contact-tab">
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
									<c:if test = "${empty noticeTopList}">
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
		                        	<c:forEach var="shortItem" items="${shortList}">
		                        		<c:if test="${shortItem.STYPE == 'M' }">
		                        			<tr>
		                               			<td class="list-group-horizontal-sm">${shortItem.SHORTIDX }</td>
		                               			<td class="list-group-horizontal-sm"><a href="shortDetail.cut?SHORTIDX=${shortItem.SHORTIDX}">${shortItem.TITLE }</a></td>
		                               			<td class="list-group-horizontal-sm">${shortItem.ID }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.READCOUNT }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.GOOD }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.BAD }</td>
		                               			<td class="list-group-horizontal-sm">${shortItem.STYPE }</td>
		                               			<td class="list-group-horizontal-sm"><fmt:formatDate pattern="yyyy-MM-dd" value="${shortItem.SHORTDATE }"/></td>
		                               		</tr>
		                               	</c:if>
		                        	</c:forEach>
		                        
		                        	</table>
	                            </div>
	                            
	                            
	                            <div class="tab-pane fade" id="shortcutList.cut?STYPE=W" role="tabpanel" aria-labelledby="contact-tab">
	                                <p class="mt-2">Duis </p>
	                            </div>
	                            <div class="tab-pane fade" id="shortcutList.cut?STYPE=E" role="tabpanel" aria-labelledby="contact-tab">
	                                <p class="mt-2">2 </p>
	                            </div>
	                            <div class="tab-pane fade" id="shortcutList.cut?STYPE=H" role="tabpanel" aria-labelledby="contact-tab">
	                                <p class="mt-2">3 </p>
	                            </div>
	                            <div class="tab-pane fade" id="shortcutList.cut?STYPE=N" role="tabpanel" aria-labelledby="contact-tab">
	                                <p class="mt-2">4 </p>
	                            </div> --%>
</body>
</html>