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
<script>
/* 메세지 삭제 Ajax */
function checkIdAndSendMsg() {
	let ID = $('#msgId').val();

	// 존재하는 아이디인지
	$.ajax({
		url 		: "/SC/checkID.cut",
		data		: {"ID" : ID},
		contentType	: "application/json",
		success		: function(data) {

			if(data==false) {
				// 존재하는 아이디인 경우 메세지 전송 
				let SENDID = $('#SENDID').val();
				let GETID = $('#GETID').val();
				let CONTENT = $('#CONTENT').val();
				
				// 메세지 전송 모달 버튼 클릭
				$('#endMsg').data-s = SENDID;
				$('#endMsg').data-g = GETID;
				$('#inlineForm').click();			
				
			} else {
				// 존재하지 안는 아이디일 경우
				swal({
					title				: '회원이 없습니다.',
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
						<h4>쪽지 리스트</h4>
						<div class="card-body mx-auto">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
								    <a class="nav-link" id="home-tab" href="shortcutList.cut?msg=get">받은 쪽지 리스트</a>
								</li>
								<li class="nav-item" role="presentation">
								    <a class="nav-link" id="contact-tab" href="shortcutList.cut?msg=send">보낸 쪽지 리스트</a>
								</li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="shortcutList.cut" role="tabpanel" aria-labelledby="home-tab">
									<table class="table">
										<tr>
											<th class="list-group-horizontal-sm">내용</th>
											<c:if test="${status == 'get'}">
												<th class="list-group-horizontal-sm">보낸사람</th>
											</c:if>
											<c:if test="${status == 'send'}">
												<th class="list-group-horizontal-sm">받은사람</th>
											</c:if>
											<th class="list-group-horizontal-sm">날짜</th>
										</tr>
				                		<thead>
										</thead>
										<c:if test = "${empty msgList}">
											<tr>
												<th width="100%">쪽지가 없습니다.</th>
											</tr>		
										</c:if>
										<c:forEach var="msg" items="${msgList}">
											<tr>
							    				<td class="list-group-horizontal-sm">
							    					<b><a href="messageDetail.cut?MESSAGEIDX=${msg.MESSAGEIDX}">${msg.CONTENT}</a></b>
							    				</td>
							    				<c:if test="${status == 'get'}">
													<td class="list-group-horizontal-sm"><b>${msg.SENDID}</b></td>
												</c:if>
												<c:if test="${status == 'send'}">
													<td class="list-group-horizontal-sm"><b>${msg.GETID}</b></td>
												</c:if>
												<td class="list-group-horizontal-sm" >
													<b><fmt:formatDate pattern="yyyy-MM-dd" value="${msg.SENDDATE}"/></b>
												</td>
											</tr>
										</c:forEach> 
			                        </table>
		                        	<c:if test = "${!empty msgList}">
			                        	<div class="card-body mx-auto">
											<span>${paging.pageHtml}</span>
										</div>
									</c:if>
									<form action="shortcutList.cut">
		                                <div class="col-md-3 mb-1" style="float:right;">
		                                    <div class="input-group mb-3">
		                                        <span class="input-group-text"><i class="bi bi-search"></i></span>
		                                        <input type="text" name="msgId" id="msgId" class="form-control" placeholder="받을 사람">
		                                        <button type="button" class="openModal btn btn-icon-default"
		                                        	onClick="checkIdAndSendMsg()">
													쪽지 보내기
												</button>
												<!-- 보이지 않는 모달 버튼 -->
												<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
												data-bs-target="#inlineForm"
												id ="sendMsg"
												data-s="" data-g=""></button>
		                                    </div>
		                                </div>
                               		</form>
                            	</div>
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