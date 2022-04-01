<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script>
function deleteMessage(MSGIDX) {
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
			/* 메세지 삭제 */
			$.ajax({
				url 		: "/SC/messageDelete.cut",
				data		: {"MSGIDX" : MSGIDX},
				contentType	: "application/json",
				success		: function(data) {
					/* 삭제 뒤 메세지 리스트로 이동 */
					swal({
						title				: '삭제하였습니다',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					}).then((result) => {
						if(result) {
							location.href="/SC/messageList.cut";							
						}
					});
				}				
			});
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
		
			<div class="col-12 col-md-6 col-lg-6 mx-auto">
				<div class="card">
					<div class="card-header">
						<h4>쪽지 상세보기</h4>
						<div class="card-body mx-auto">
							<!-- 현재 로그인한 사람의 id -->
							<input type="hidden" id="loginId" value="<%= request.getSession().getAttribute("id") %>">
							<table class="table">
								<tr>
									<td class="list-group-horizontal-sm" style="width:50%">
										${msg.SENDID}
									</td>
									<td class="list-group-horizontal-sm"style="width:20%">
										<b><fmt:formatDate pattern="yyyy-MM-dd" value="${msg.SENDDATE}"/></b>
									</td>
								</tr>
								<tr>
									<td class="list-group-horizontal-sm" colspan="2"
										style="height:200px; word-break:break-all">
										${msg.CONTENT}
									</td>
								</tr>
							</table>
							<div class="col-md-12 mb-1">
								<button type="button" class="openModal btn btn-icon-default"
									onClick="javascript:history.go(-1);">
									<!-- onClick="javascript:location.href='messageList.cut';"> -->
									<b>쪽지 리스트</b>
								</button>
								<!-- 받은 메세지 리스트에서 넘어왔을 경우 -->
								<!-- 보낼 사람 : 메세지를 보낸 사람(SENDID), 받을 사람 : 메세지를 받은 사람(GETID-나자신) -->
								<!-- 받은 메세지만 삭제 가능하도록 구현 -->
								<c:if test="${status == 'get'}">
									<button type="button" class="openModal btn btn-icon-default"
										onClick="deleteMessage(${msg.MSGIDX})">
										<b>삭제</b>
									</button>
									<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
										data-bs-target="#inlineForm"
										data-s="${msg.GETID}" data-g="${msg.SENDID}"
									id ="sendMsg"><b>답장</b></button>
								</c:if>
								<!-- 보낸 메세지 리스트에서 넘어오면 답장을 하지 않는다. -->
								<!-- 보낼 사람 :  -->
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