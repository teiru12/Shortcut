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
					<h4 class="card-title">${infoDetail.TITLE}</h4>
				</div>
				<div class="card-body">
					<div class="btn-group dropend me-1 mb-1">
						<button type="button" class="btn btn-icon-default"
							data-bs-toggle="dropdown" aria-haspopup="false"
							aria-expanded="false">
							${infoDetail.ID}
						</button>
						<div class="dropdown-menu">
							<button class="btn btn-icon-default" onClick="writerDetail('${infoDetail.ID}')">회원 정보</button><br>
							<c:if test="${! empty sessionScope.id}">
								<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
								data-bs-target="#inlineForm"
								data-s="<%= request.getSession().getAttribute("id") %>" data-g="${infoDetail.ID}">
									쪽지 보내기
								</button><br>
								<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
							</c:if>
						</div>
					</div> &nbsp; 
					<span class="text-subtitle text-muted">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${infoDetail.INFODATE}"/> &nbsp;
							조회수 ${infoDetail.READCOUNT} &emsp;
						
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
									'INF', ${infoDetail.INFOIDX}, ${infoDetail.GOOD})">
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
	                    <span id="goodValue">${infoDetail.GOOD}</span>&nbsp;
	                    
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
								'INF', ${infoDetail.INFOIDX}, ${infoDetail.BAD})">
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
						<span id="badValue">${infoDetail.BAD}</span>&nbsp;
						
						<!-- 즐겨찾기 -->
						<!-- 로그인하지 않았을 경우 보여주지 않음 -->
						<% if(request.getSession().getAttribute("id") != null) { %>
							<span id="bookSpan">
							<!-- 즐겨찾기를 했을 경우 꽉찬 별표 -->
							<c:if test="${! empty bookmark}">
								<a id="bookStar" href="javascript:updateBookmark('<%= request.getSession().getAttribute("id") %>',
								'INF', ${infoDetail.INFOIDX}, 'DEL')">
		                            <svg class="bi" width="1em" height="1em" fill="currentColor">
		                                <use xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#star-fill" />
		                            </svg>
								</a>
							</c:if>
							<!-- 즐겨찾기를 하지 않았을 경우 빈 별표 -->
							<c:if test="${empty bookmark}">
								<a id="bookStar" href="javascript:updateBookmark('<%= request.getSession().getAttribute("id") %>',
								'INF', ${infoDetail.INFOIDX}, 'ADD')">
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
					<p>${infoDetail.CONTENT}</p>
					<div style="text-align:right;">
						<a href="/SC/infoList.cut" class="btn btn-sm btn-outline-secondary">목록으로</a>

						<!-- 비회원상태 -->
						<c:if test="${id == null && infoDetail.PASSWORD != null}">
							<a href="javascript:modifyDetailCheck('nonMember', 'INF', ${infoDetail.INFOIDX})" class="btn btn-sm btn-outline-secondary">수정하기</a>
							<a href="javascript:deleteDetailCheck('nonMember', 'INF', ${infoDetail.INFOIDX})" class="btn btn-sm btn-outline-secondary">삭제하기</a>
						</c:if>
						<!-- 로그인상태 -->
						<c:if test="${id == infoDetail.ID}"> 
							<a href="javascript:modifyDetailCheck('member', 'INF', ${infoDetail.INFOIDX})" class="btn btn-sm btn-outline-secondary">수정하기</a>
							<a href="javascript:deleteDetailCheck('member', 'INF', ${infoDetail.INFOIDX})" class="btn btn-sm btn-outline-secondary">삭제하기</a>
						</c:if>
					</div>
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
						<table class="table table-lg" id="comBody">
							<tbody id="comDiv">
								<c:if test = "${empty infoComList}">
									<tr>
										<td><div style="text-align:center">댓글이 없습니다. 댓글을 남겨주세요!</div></td>
									</tr>					
								</c:if>
								<c:forEach var="comment" items="${infoComList}" varStatus="status">
									<tr id="comItemBody">
									    <td class="text-bold-500" id="comItemDiv">
											<div style="text-align:right;">
											
												<a href="javascript:openComWriteForm(${status.index})"
	                                          			style="font-size:small">답댓글</a>&nbsp;
	                                          	
	                                          	<%-- 비회원 상태 & 해당 댓글의 비밀번호가 존재할 때 --%>
												<c:if test="${id == null && comment.PASSWORD != null}">
										    		<a href="javascript:comModify('비회원','INF', ${comment.INFOCOMIDX}, ${status.index}, 'MOD')"
										    			style="font-size:small">수정</a>&nbsp; 
										    		<a href="javascript:comModify('비회원','INF', ${comment.INFOCOMIDX}, ${status.index}, 'DEL')"
														style="font-size:small">삭제</a>
												</c:if>
												<%-- 댓글의 작성자가 로그인 한 사람일 때 --%>
												<c:if test="${id == comment.ID}">
										    		<a href="javascript:comModify('${id}','INF', ${comment.INFOCOMIDX}, ${status.index}, 'MOD')"
										    			style="font-size:small">수정</a>&nbsp; 
										    		<a href="javascript:comModify('${id}','INF', ${comment.INFOCOMIDX}, ${status.index}, 'DEL')"
										    			style="font-size:small">삭제</a>
												</c:if> 
									    	</div>
									    	<div>
									    		 <c:forEach begin="1" end="${comment.RELEVEL}">
									    			&nbsp;
									    		</c:forEach>
									    		<c:if test="${comment.RELEVEL>0}"><%-- 'ㄴ' 아님 특수문자 --%>
									    			∟
									    		</c:if>
										    	<span class="text-subtitle text-muted" id="comID${status.index}">
											    	<c:if test="${comment.ISDEL == 'N'}">
											    		${comment.ID}
											    	</c:if>
											    	<c:if test="${comment.ISDEL == 'Y'}">
											    		삭제
											    	</c:if>
										    	</span>
										    	<p class="text-subtitle text-muted" style="font-size:x-small">${comment.INFOCOMDATE}</p>
	                                            <p class="card-text" id="comCONTENT${status.index}">
	                                            	<c:if test="${comment.ISDEL == 'N'}">
	                                            		${comment.CONTENT}
	                                            	</c:if>
											    	<c:if test="${comment.ISDEL == 'Y'}">
											    		삭제된 댓글입니다.
											    	</c:if>
	                                            </p>
	                                        </div>
											<div id="reForm${status.index}" class="col-12 mx-auto" style="display:none">
					                            <div class="input-group mb-3">
					                                <textarea class="form-control" id="reply${comment.INFOCOMIDX}" placeholder="댓글을 입력해주세요"></textarea>
													<%-- 비회원상태 --%>
													<c:if test="${id == null}">
					                                <button class="btn btn-outline-secondary" type="button"
					                                    id="button-addon2"
					                                    onClick="comWrite('비회원','INF', '${infoDetail.INFOIDX}',
					                                    	${comment.RETYPE eq 0 ? comment.INFOCOMIDX : comment.RETYPE},
					                                    	${comment.INFOCOMIDX}, ${currentPage})">
					                                    댓글입력</button>
													</c:if>
													<%-- 로그인상태 --%>
													<c:if test="${id != null}">
					                                <button class="btn btn-outline-secondary" type="button"
					                                    id="button-addon2"
					                                    onClick="comWrite('${id}','INF', '${infoDetail.INFOIDX}',
					                                    	 ${comment.RETYPE eq 0 ? comment.INFOCOMIDX : comment.RETYPE},
					                                    	 ${comment.INFOCOMIDX}, ${currentPage})">
					                                    댓글입력</button>
													</c:if>
													
					                            </div>
					                        </div>
	                                    </td>
									</tr>
								</c:forEach>						
							</tbody>
						</table>
						<div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <textarea class="form-control" id="reply" placeholder="댓글을 입력해주세요"></textarea>
                                <%-- 비회원상태 --%>
								<c:if test="${id == null}">
                                <button class="btn btn-outline-secondary" type="button"
                                    id="button-addon2"
                                    onClick="comWrite('비회원','INF', '${infoDetail.INFOIDX}', 0, 0, ${currentPage})">
                                    댓글입력</button>
								</c:if>
								<%-- 로그인상태 --%>
								<c:if test="${id != null}">
                                <button class="btn btn-outline-secondary" type="button"
                                    id="button-addon2"
                                    onClick="comWrite('${id}','INF', '${infoDetail.INFOIDX}', 0, 0, ${currentPage})">
                                    댓글입력</button>
								</c:if>
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