<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div id="main">
<section class="section">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4 class="card-title">${infoDetail.TITLE}</h4>
				</div>
				<div class="card-body">
					<span class="text-subtitle text-muted">${infoDetail.ID} &nbsp; 
								<fmt:formatDate pattern="yyyy-MM-dd" value="${infoDetail.INFODATE}"/> &nbsp;
								조회수 ${infoDetail.READCOUNT} &emsp;
						<a href="#">
							<svg class="bi" width="1em" height="1em" fill="currentColor">
	                        	<use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-up" />
	                    	</svg>
	                    </a>${infoDetail.GOOD} &nbsp;
						<a href="#">
						    <svg class="bi" width="1em" height="1em" fill="currentColor">
						        <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-down" />
						    </svg>
						</a>${infoDetail.BAD} &nbsp;
						<a href="#">
                            <svg class="bi" width="1em" height="1em" fill="currentColor">
                                <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#star" />
                            </svg>
						</a>&emsp;
						<a href="#"><span style="color:#C00000">신고</span></a>
					</span><hr>
					<p>${infoDetail.CONTENT}</p>
					<c:if test="${id == null}">
					<div style="text-align:right;">
						<a href="/SC/infoList.cut" class="btn btn-sm btn-outline-secondary">목록으로</a>
						<a href="/SC/infoModifyForm.cut" class="btn btn-sm btn-outline-secondary">수정하기</a>
						<a href="/SC/infoDelete.cut" class="btn btn-sm btn-outline-secondary">삭제하기</a>
					</div>
					</c:if>
					<c:if test="${id != null}">
						<c:if test="${id == infoDetail.ID}">
						<div style="text-align:right;">
							<a href="/SC/infoList.cut" class="btn btn-sm btn-outline-secondary">목록으로</a>
							<a href="/SC/infoModifyForm.cut" class="btn btn-sm btn-outline-secondary">수정하기</a>
							<a href="/SC/infoDelete.cut" class="btn btn-sm btn-outline-secondary">삭제하기</a>
						</div>
						</c:if>
						<c:if test="${id != infoDetail.ID}">
						<div style="text-align:right;">
							<a href="/SC/infoList.cut" class="btn btn-sm btn-outline-secondary">목록으로</a>
						</div>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h6>댓글</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-lg">
							<tbody>
								<c:if test = "${empty infoComList}">
									<tr>
										<td><div style="text-align:center">댓글이 없습니다. 댓글을 남겨주세요!</div></td>
									</tr>					
								</c:if>
								<c:forEach var="comment" items="${infoComList}" varStatus="status">
									<tr>
									    <td class="text-bold-500">
									    	<c:if test="${id == null}">
									    	<div style="text-align:right;">
	                                          	<a href="#" style="font-size:small">답댓글</a>&nbsp; 
									    		<a href="#" style="font-size:small">수정</a>&nbsp; 
									    		<a href="#" style="font-size:small">삭제</a>
									    	</div>
									    	</c:if>
									    	<c:if test="${id != null}">
									    		<c:if test="${id == comment.ID}">
									    			<div style="text-align:right;">
			                                          	<a href="#" style="font-size:small">답댓글</a>&nbsp; 
											    		<a href="#" style="font-size:small">수정</a>&nbsp; 
											    		<a href="#" style="font-size:small">삭제</a>
											    	</div>
									    		</c:if>
									    		<c:if test="${id != comment.ID}">
									    			<div style="text-align:right;">
									    				<a href="#" style="font-size:small">답댓글</a>
									    			</div>
									    		</c:if>
									    	</c:if>
									    	<div>
										    	<span class="text-subtitle text-muted">${comment.ID}</span>
										    	<p class="text-subtitle text-muted" style="font-size:x-small">${comment.INFOCOMDATE}</p>
	                                            <p class="card-text">${comment.CONTENT}</p>
	                                        </div>
	                                    </td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <textarea class="form-control" id="reply" placeholder="댓글을 입력해주세요"></textarea>
                                <button class="btn btn-outline-secondary" type="button"
                                    id="button-addon2">댓글입력</button>
                            </div>
                        </div>
					</div>
					<div class="card-body mx-auto" id="pagination">
						<span id="pageBody">${paging.pageHtml}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div>
</body>
</html>