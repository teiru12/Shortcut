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
<style>
{ word-break: normal; }
</style>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="assets/js/detail.js"></script>
<script>
function deleteCom(REPORTCOMIDX) {
	swal({
		title				: '삭제하시겠습니까?',
		dangerMode			: true, // 확인 버튼 빨갛게
		closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
		buttons				: {
			cancle : {
				text 		: '취소',
				value 		: false,
				className 	: 'btn btn-primary' 
			},
			confirm : {
				text 		: '삭제',
				value 		: true,
				className 	: 'btn btn-outline-primary' 
			}
		}	
	}).then((result) => {
		if(result) {
			$.ajax({
				url 		:  "/SC/reportComDelete.cut",
				data		:  {"REPORTCOMIDX" : REPORTCOMIDX},
				contentType	: "application/json",
				success		: function(data) {					
					swal({
						title				: '삭제하셨습니다',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					}).then((result) => {
						$('#commentList').load(location.href + " #commentList");
					});
				}				
			});
		}
	});
}

function writeCom(REPORTIDX) {
	let content = $('#reply').val().trim();
	
	if(content == "") {
		swal({
			title				: "본문을 입력해주세요.",
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		});	
	}
	
	$.ajax({
		url 		: "/SC/reportComWrite.cut",
		data		: {"REPORTIDX" : REPORTIDX, "CONTENT" : content},
		contentType	: "application/json",
		success		: function(data) {
			// 게시글 내의 댓글 리스트 새로 구성
			$('#commentList').load(location.href + " #commentList");
			
			// 입력창 초기화
			$('#reply').val("");
		}				
	});	
}

function modifyCom(REPORTCOMIDX) {
	
	swal({
		title				: '댓글 수정',
		dangerMode			: true, // 확인 버튼 빨갛게
		closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
		content				: {
			element : 'input',
			attributes : {
				placeholder : '댓글을 입력해주세요.',
				type : 'text'					
			}
		},
		buttons				: {
			confirm : {
				text 		: '확인',
				value 		: false,
				className 	: 'btn btn-outline-primary' 
			}
		}
	}).then((result) => { // result : 입력한 댓글
		if(result.trim() == "") {
			swal({
				title				: "댓글을 입력해주세요",
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			});	
		} else if(result.length >= 400) {
			swal({
				title				: "글자 수가 너무 많습니다",
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			});
		} else {
			$.ajax({
				url 		: "/SC/reportComModify.cut",
				data		: {"REPORTCOMIDX" : REPORTCOMIDX, "CONTENT" : result},
				contentType	: "application/json",
				success		: function(data) {
					// 수정 후 내용 반영
					$('#CONTENT').html(result);
					
					$('#commentList').load(location.href + " #commentList");
				}
			});				
		}

	});
}

</script>
</head>
<body>
<div id="main">
<section class="section">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4 class="card-title">${reportDetail.TITLE}</h4>
				</div>
				<div class="card-body">
					<div class="btn-group dropend me-1 mb-1">
						<button type="button" class="btn btn-icon-default"
							data-bs-toggle="dropdown" aria-haspopup="false"
							aria-expanded="false">
							${reportDetail.ID}
						</button>
						<div class="dropdown-menu">
							<button class="btn btn-icon-default" onClick="writerDetail('${reportDetail.ID}')">회원 정보</button><br>
							<c:if test="${! empty sessionScope.id}">
								<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
								data-bs-target="#inlineForm"
								data-s="<%= request.getSession().getAttribute("id") %>" data-g="${reportDetail.ID}">
									쪽지 보내기
								</button><br>
								<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
							</c:if>
						</div>
					</div> &nbsp; 
					<span class="text-subtitle text-muted">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${reportDetail.REPORTDATE}"/> &nbsp;
						
					</span><hr>
					<p>${reportDetail.CONTENT}</p>
					<div style="text-align:right;">
						<a href=<%=referer%> class="btn btn-sm btn-outline-secondary">목록으로</a>

						<!-- 로그인상태 -->
						<c:if test="${id == reportDetail.ID}"> 
							<a href="/SC/reportModifyForm.cut?REPORTIDX=${reportDetail.REPORTIDX}"class="btn btn-sm btn-outline-secondary">수정하기</a>
							<a href="javascript:deleteDetailCheck('member', 'REP', ${reportDetail.REPORTIDX})" class="btn btn-sm btn-outline-secondary">삭제하기</a>
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
					<div class="table-responsive"  id="commentList">
						<table class="table table-lg">
							<tbody>
								<c:if test = "${empty reportComList}">
									<tr>
										<td><div style="text-align:center">댓글이 없습니다.</div></td>
									</tr>					
								</c:if>
								<c:forEach var="comment" items="${reportComList}" varStatus="status">
									<tr>
									    <td class="text-bold-500">
									    	<c:if test="${id == 'ADMIN'}">
									    	<div style="text-align:right;">
									    		<a href="javascript:modifyCom(${comment.REPORTCOMIDX})" style="font-size:small">수정</a>&nbsp; 
									    		<a href="javascript:deleteCom(${comment.REPORTCOMIDX})" style="font-size:small">삭제</a>
									    	</div>
									    	</c:if>
									    	<div>
										    	<div class="btn-group dropend me-1 mb-1">관리자</div>
										    	<p class="text-subtitle text-muted" style="font-size:x-small">${comment.REPORTCOMDATE}</p>
	                                            <p class="card-text">${comment.CONTENT}</p>
	                                        </div>
	                                    </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:if test="${id == 'ADMIN'}">
						<div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <textarea class="form-control" id="reply" placeholder="댓글을 입력해주세요" maxlength="400"></textarea>
                                <button class="btn btn-outline-secondary" type="button" onClick="writeCom(${reportDetail.REPORTIDX})"
                                    id="button-addon2">댓글입력</button>
                            </div>
                        </div>
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