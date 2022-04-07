<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="app">
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

            <div class="page-heading">

                <!-- Basic Horizontal form layout section start -->
                <section id="multiple-column-form">
                    <div class="row match-height">
                        <div class="col-10 mx-auto">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title" style="text-align:center">회원상세보기</h4>
                                </div>
                                <div class="card-body py-4 px-5" style="margin-left:auto; margin-right:auto">
									<div class="d-flex align-items-center">
										<div class="avatar avatar-xl">
											<!-- 이미지 없을 시 기본 이미지 이미지 있을 경우 해당 이미지 -->
											<c:if test="${empty member.PROFILE}">
												<img src="assets/images/faces/blank.png" alt="Face">
											</c:if>
											<c:if test="${! empty member.PROFILE}">
												<img src="assets/images/faces/${member.PROFILE}" alt="Face">
											</c:if>
										</div>
										<div class="ms-3 name">
											<h5 class="font-bold">레벨 : Lv.${level}</h5>
											<h6 class="text-muted mb-0">경험치 : ${member.EXP}</h6>
										</div>
									</div>
								</div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form">
                                            <div class="row">
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="first-name-column">ID</label>
                                                        <input type="text" class="form-control" id="ID" name="ID" value="${member.ID}" disabled>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="last-name-column">PASSWORD</label>
                                                        <input type="password" class="form-control" placeholder="Password" id="PASSWORD"
                                                                	name="PASSWORD" value="${member.PASSWORD}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="city-column">EMAIL</label>
                                                        <input type="email" class="form-control" placeholder="Email" id="EMAIL" name="EMAIL" value="${member.EMAIL}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="country-floating">NAME</label>
                                                        <input type="text" class="form-control" placeholder="Name" id="NAME" name="NAME" value="${member.NAME}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="company-column">LEVEL</label>
                                                        <input type="text" class="form-control" placeholder="Name" id="EXP" name="EXP" value="${level}" disabled>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="email-id-column">NICKNAME</label>
                                                        <input type="text" class="form-control" placeholder="NickName" id="NICKNAME" name="NICKNAME" value="${member.NICKNAME}">
                                                    </div>
                                                </div>
                                                <div class="col-md-3 col-2 mx-auto">
                                                    
                                                </div>
                                                <div class="col-md-6 col-8 mx-auto">
	                                                사용가능&nbsp;<input type="radio" name="STATUS" id="STATUS1" value="1" style="width:20px;height:20px;border:1px;" onclick="checkSTATUS()">&nbsp;&nbsp;&nbsp;
	                                                정지&nbsp;<input type="radio" name="STATUS" id="STATUS2" value="2" style="width:20px;height:20px;border:1px;" onclick="checkSTATUS()">&nbsp;&nbsp;&nbsp;
                                                    탈퇴&nbsp;<input type="radio" name="STATUS" id="STATUS3"  value="3"style="width:20px;height:20px;border:1px;" onclick="checkSTATUS()">&nbsp;&nbsp;&nbsp;
                                                    인증대기&nbsp;<input type="radio" name="STATUS" id="STATUS4"  value="4"style="width:20px;height:20px;border:1px;" onclick="checkSTATUS()">
                                                </div>
                                                <div class="col-md-3 col-2 mx-auto">
                                                    
                                                </div>
                                                <div class="col-12 d-flex justify-content-end">
                                                    <button type="button" class="btn btn-primary me-1 mb-1"	onClick="memberModify()">회원수정</button>
                                                    <button type="button" class="btn btn-light-secondary me-1 mb-1" onClick="javascript:history.go(-1);">취소</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- // Basic Horizontal form layout section end -->           
            </div>

        </div>
    </div>
<script type="text/javascript">
let check = true;
let check2 = true;
let check3 = true;
$(document).ready(function(){
	if(${member.STATUS == 'ON'}){
		$("#STATUS1").prop("checked", true);		
	}else if(${member.STATUS == 'OFF'}){
		$("#STATUS2").prop("checked", true);
	}else if(${member.STATUS == 'DEL'}){
		$("#STATUS3").prop("checked", true);
	}else{
		$("#STATUS4").prop("checked", true);
	}
})

function memberModify(){
	let PASSWORD = $("#PASSWORD").val();
	let STATUS = ${member.STATUS};
	let NAME = $("#NAME").val();
	let NICKNAME = $("#NICKNAME").val();
	let MEMIDX = ${member.MEMIDX};
	$.ajax({
		url : "adminMemberModify.cut",
		data : {
			"PASSWORD" : PASSWORD,
			"STATUS" : STATUS,
			"NAME" : NAME,
			"NICKNAME" : NICKNAME,
			"MEMIDX" : MEMIDX
		},
		contentType : "application/json; charset=UTF-8",
      	success : function(result){
      		location.href="adminMemberList.cut";
      	}
	});
}

function checkSTATUS(){
	let checkVal = $('input[name=STATUS]:checked').val();
	if(checkVal == '1') {
		$("#STATUS1").prop("checked", true);
		$("#STATUS2").prop("checked", false);
		$("#STATUS3").prop("checked", false);
		$("#STATUS4").prop("checked", false);
		${member.STATUS} = 'ON';
	}else if(checkVal == '2') {
		$("#STATUS1").prop("checked", false);
		$("#STATUS2").prop("checked", true);
		$("#STATUS3").prop("checked", false);
		$("#STATUS4").prop("checked", false);
		${member.STATUS} = 'OFF'
	}else if(checkVal == '3') {
		$("#STATUS1").prop("checked", false);
		$("#STATUS2").prop("checked", false);
		$("#STATUS3").prop("checked", true);
		$("#STATUS4").prop("checked", false);
		${member.STATUS} = 'DEL';
	}else {
		$("#STATUS1").prop("checked", false);
		$("#STATUS2").prop("checked", false);
		$("#STATUS3").prop("checked", false);
		$("#STATUS4").prop("checked", true);
		${member.STATUS} = 'STAY';
	}
	
}

</script>
</body>
</html>