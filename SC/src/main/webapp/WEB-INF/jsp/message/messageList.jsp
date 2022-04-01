<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<!-- <style>
.content{
  width        : 20vw;     /* 너비는 변경될수 있습니다. */
  text-overflow: ellipsis;  /* 위에 설정한 너비보다 길면 말줄임표처럼 표시합니다. */
  white-space  : nowrap;    /* 줄바꿈을 하지 않습니다. */
  overflow     : hidden;    /* 내용이 길면 감춤니다 */
  display      : block;     /* ie6이상 현재요소를 블럭처리합니다. */
  color   : black;
  vertical-align : middle;
}
</style> -->
<script>
/* 메세지 삭제 Ajax */
function checkIdAndSendMsg() {
	let ID = $('#msgId').val();
	let loginID = $('#loginId').val();

	// 본인에게 보내려고 하는 경우 오류 출력
	if(ID == loginID) {
		swal({
			title				: '자기 자신에게 보낼 수 없습니다.',
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
		// 존재하는 아이디인지 검사
		$.ajax({
			url 		: "/SC/checkID.cut",
			data		: {"ID" : ID},
			contentType	: "application/json",
			success		: function(data) {
				if(data==false) { // 존재하는 아이디인 경우 메세지 전송 
					
					$('#sendMsg').attr('data-s', loginID);
					$('#sendMsg').attr('data-g', ID);					
					
					// 메세지 전송 모달 버튼 클릭
					$('#sendMsg').click();
					
				} else { // 존재하지 안는 아이디일 경우 오류 출력
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
}
</script>
<script>
$(document).ready(function(){
	$('#sendMsg').hide();
	
	const url = new URL(window.location.href);
	const urlParams = url.searchParams;
	
	let getTab = $('#home-tab');
	let sendTab = $('#contact-tab');
	
	// 보낸 메시지일 경우 탭 색 변환
	if(urlParams.get('msg') == 'send') {
		sendTab.css("background", "#ededed");
		sendTab.css("color", "#222");
		
		getTab.css("background", "none");
		getTab.css("color", "#222");
	}
	// 받은 메시지일 경우 탭 색 변환
	if(urlParams.get('msg') == null || urlParams.get('msg') == 'get') {
		getTab.css("background", "#ededed");
		getTab.css("color", "#222");
		
		sendTab.css("background", "none");
		sendTab.css("color", "#222");
	}
});
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
								    <a class="nav-link" id="home-tab" href="messageList.cut?msg=get"><b>받은 쪽지 리스트</b></a>
								</li>
								<li class="nav-item" role="presentation">
								    <a class="nav-link" id="contact-tab" href="messageList.cut?msg=send"><b>보낸 쪽지 리스트</b></a>
								</li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="messageList.cut" role="tabpanel" aria-labelledby="home-tab">
									<!-- 현재 로그인한 사람의 id -->
									<input type="hidden" id="loginId" value="<%= request.getSession().getAttribute("id") %>">
									<table class="table">
										<tr>
											<th class="list-group-horizontal-sm" style="width:50%">내용</th>
											<c:if test="${status == 'get'}">
												<th class="list-group-horizontal-sm" style="width:30%">보낸사람</th>
											</c:if>
											<c:if test="${status == 'send'}">
												<th class="list-group-horizontal-sm"style="width:30%">받은사람</th>
											</c:if>
											<th class="list-group-horizontal-sm"style="width:20%">날짜</th>
										</tr>
				                		<thead>
										</thead>
										<c:if test = "${empty msgList}">
											<tr>
												<th width="100%" colspan="2">쪽지가 없습니다.</th>
											</tr>		
										</c:if>
										<c:forEach var="msg" items="${msgList}">
											<tr>
							    				<td style="height:6.5vh;">
							    					<b><a href="messageDetail.cut?MSGIDX=${msg.MSGIDX}&msg=<c:if test='${status=="send"}'>send</c:if><c:if test='${status=="get"}'>get</c:if>">
							    						<!-- 글자 수가 일정 수 이상이면 앞 글자면 보여줌 -->
							    						<c:if test="${fn:length(msg.CONTENT)>=15}">
							    						${fn:substring(msg.CONTENT,0,15)}...
							    						</c:if>
							    						<c:if test="${fn:length(msg.CONTENT)<15}">
							    						${msg.CONTENT}
							    						</c:if>
							    					</a></b>
							    				</td>
							    				<c:if test="${status == 'get'}">
							    					<td class="list-group-horizontal-sm" style="height:6.5vh;">
														<div class="btn-group dropend me-1 mb-1">
															
															<button type="button" class="btn btn-icon-default"
																data-bs-toggle="dropdown" aria-haspopup="false"
																aria-expanded="false">
																<b>${msg.SENDID}</b>
															</button>
															<div class="dropdown-menu">
																<button class="btn btn-icon-default" onClick="writerDetail('${msg.SENDID}')">회원 정보</button><br>
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																data-s="<%= request.getSession().getAttribute("id") %>" data-g="${msg.SENDID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</div>
														</div>
													</td>
													<!-- 사용자 정보 연결하지 않았을 때 -->
													<%-- <td class="list-group-horizontal-sm"><b>${msg.SENDID}</b></td> --%>
												</c:if>
												<c:if test="${status == 'send'}">
													<td class="list-group-horizontal-sm" style="height:6.5vh;">
														<div class="btn-group dropend me-1 mb-1">
															
															<button type="button" class="btn btn-icon-default"
																data-bs-toggle="dropdown" aria-haspopup="false"
																aria-expanded="false">
																<b>${msg.GETID}</b>
															</button>
															<div class="dropdown-menu">
																<button class="btn btn-icon-default" onClick="writerDetail('${msg.GETID}')">회원 정보</button><br>
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																data-s="<%= request.getSession().getAttribute("id") %>" data-g="${msg.GETID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</div>
														</div>
													</td>
													<!-- 사용자 정보 연결하지 않았을 때 -->
													<%-- <td class="list-group-horizontal-sm"><b>${msg.GETID}</b></td> --%>
												</c:if>
												<td class="list-group-horizontal-sm" style="height:6.5vh;">
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
									<form action="messageList.cut">
		                                <div class="col-md-6 mb-1" style="float:right;">
		                                    <div class="input-group mb-3">
		                                        <span class="input-group-text"><i class="bi bi-search"></i></span>
		                                        <input type="text" name="msgId" id="msgId" class="form-control" placeholder="받을 사람">
		                                        <button type="button" class="openModal btn btn-icon-default"
		                                        	onClick="checkIdAndSendMsg()">
													<b>쪽지 보내기</b>
												</button>
												<!-- 보이지 않는 모달 버튼 -->
												<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
												data-bs-target="#inlineForm" style="display:block;"
												id ="sendMsg"></button>
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