<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<body>
<div id="main">
	<div class="page-heading">
	    <section class="section">
			<div class="row">
				<div class="col-lg-12 col-md-12">
	                <div class="card">
	                    <div class="card-header">
	                        <h5 class="card-title" style="text-align:center">회원리스트</h5>
	                    </div>
	                    <div class="card-body">
	                        <div class="tab-content" id="myTabContent">
	                            <div class="tab-pane fade show active" id="shortcutList.cut" role="tabpanel" aria-labelledby="home-tab">
	                            <div class="col-md-4 mb-1" style="float:right;">
		                            <div class="input-group mb-4">
		                            <a class="btn btn-outline-secondary" href="adminMemberList.cut">초기화</a>
		                            <a class="btn btn-outline-secondary">레벨</a>
			                        <input type=text name="LEVEL1" id="LEVEL1" class="form-control" style="width:60px;" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');" value="${LEVEL1}">
			                         &nbsp;&nbsp;~&nbsp;&nbsp; <input type="text" name="LEVEL2" id="LEVEL2" class="form-control" style="width:60px;" onkeydown="checkNumber();" value="${LEVEL2}">
			                         	<span class="input-group-text"><i class="bi bi-search" onclick="findlevel()" style="cursor:pointer"></i></span>
			                         </div>
			                    </div>
	                                <table class="table">
	                        		<tr>
	                        			<th width="15%">회원번호</th>
	                        			<th width="15%">아이디</th>
	                        			<th width="20%">이메일</th>
	                        			<th width="15%">이름</th>
	                        			<th width="10%">레벨</th>
	                        			<th width="20%">회원 상태
	                        			</th>
	                        		</tr>
	                        		<thead>
									</thead>
									<c:if test = "${empty memberList}">
										<tr>
											<td width="20%" class="list-group-horizontal-sm">가입한 회원이 없습니다.</td>
											<td class="list-group-horizontal-sm"></td>
											<td class="list-group-horizontal-sm"></td>
											<td class="list-group-horizontal-sm"></td>
											<td class="list-group-horizontal-sm"></td>
											<td class="list-group-horizontal-sm"></td>
										</tr>		
									</c:if>
		                        	<c:forEach var="memberList" items="${memberList}">
	                        			<tr>
	                               			<td class="list-group-horizontal-sm"><b>${memberList.MEMIDX}</b></td>
	                               			<td class="list-group-horizontal-sm"><b><a href="adminMemberDetail.cut?ID=${memberList.ID }">${memberList.ID }</a></b></td>
	                               			<td class="list-group-horizontal-sm"><b>${memberList.EMAIL }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${memberList.NAME }</b></td>
	                               			<td class="list-group-horizontal-sm"><b>${memberList.level }	                               			
	                               			</b></td>
	                               			<c:if test="${memberList.STATUS == 'ON'}">
	                               				<td class="list-group-horizontal-sm"><b>사용가능</b></td>
	                               			</c:if>
	                               			<c:if test="${memberList.STATUS == 'OFF'}">
	                               				<td class="list-group-horizontal-sm"><b>정지</b></td>
	                               			</c:if>
	                               			<c:if test="${memberList.STATUS == 'DEL'}">
	                               				<td class="list-group-horizontal-sm"><b>탈퇴</b></td>
	                               			</c:if>
	                               			<c:if test="${memberList.STATUS == 'STAY'}">
	                               				<td class="list-group-horizontal-sm"><b>인증대기</b></td>
	                               			</c:if>
	                               		</tr>
		                        	</c:forEach>
		                        	</table>
		                        	<c:if test = "${!empty memberList}">
			                        	<div class="card-body mx-auto">
											<span>${paging.pageHtml}</span>
										</div>
									</c:if>
									<form id="frm" action="adminMemberList.cut">
									<input type="hidden" id="STATUS" name="STATUS" value="${STATUS }">
		                                <div class="col-md-4 mb-1" style="float:right;">
		                                    <div class="input-group mb-4">
			                                    <div class="dropdown">
	                                                <button class="btn btn-primary dropdown-toggle me-1" type="button"
	                                                    id="dropdownMenuButton" data-bs-toggle="dropdown"
	                                                    aria-haspopup="true" aria-expanded="false" value="회원상태">
	                                                    <c:if test="${STATUS == null }">
	                                                    회원상태
	                                                    </c:if>
	                                                    <c:if test="${STATUS == 'ON' }">
	                                                    사용가능
	                                                    </c:if>
	                                                    <c:if test="${STATUS == 'OFF' }">
	                                                    정지
	                                                    </c:if>
	                                                    <c:if test="${STATUS == 'DEL' }">
	                                                    탈퇴
	                                                    </c:if>
	                                                    <c:if test="${STATUS == 'STAY' }">
	                                                    인증대기
	                                                    </c:if>
	                                                </button>
	                                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                                                	<a class="btn btn-icon-default" onClick="memberstatus('ON')">사용가능</a><br>
	                                                	<a class="btn btn-icon-default" onClick="memberstatus('OFF')">정지</a><br>
	                                                	<a class="btn btn-icon-default" onClick="memberstatus('DEL')">탈퇴</a><br>
	                                                	<a class="btn btn-icon-default" onClick="memberstatus('STAY')">인증대기</a><br>
	                                                </div>
	                                            </div>
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
	        </div>
	    </section>
	</div>
</div>
<script type="text/javascript">
	function memberstatus(data){
		const btnElement = document.getElementById('dropdownMenuButton');
		if(data == 'ON'){
			btnElement.innerText = "사용가능";
		}else if(data == 'OFF'){
			btnElement.innerText = "정지";
		}else if(data == 'DEL'){
			btnElement.innerText = "탈퇴";
		}else if(data == 'STAY'){
			btnElement.innerText = "인증대기";
		}
		document.getElementById('STATUS').value = data;
	}
	
	function findlevel(){
		let level1 = $("#LEVEL1").val();
		let level2 = $("#LEVEL2").val();
		location.href="searchLeveladminMemberList.cut?LEVEL1="+level1+"&LEVEL2="+level2;
	}
	
	function checkNumber() {
		if((event.keyCode > 48 && event.keyCode < 57 ) 
			      || event.keyCode == 8 //backspace
			      || event.keyCode == 37 || event.keyCode == 39 //방향키 →, ←
			      || event.keyCode == 46 //delete키
			      || event.keyCode == 39){
			event.returnValue=true;
		}else{
			event.returnValue=false;
		}		  
	}
</script>
</body>
</html>