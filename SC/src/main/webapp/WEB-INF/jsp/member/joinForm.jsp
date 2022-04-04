<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
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
                <section id="basic-horizontal-layouts">
                    <div class="row match-height">                        
                        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">회원가입</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-horizontal">
                                            <div class="form-body">
                                                <div class="row">
                                                	<div class="col-md-4">
                                                        <label>ID</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative input-group has-validation">
                                                                <input type="text" class="form-control" placeholder="ID" id="ID" oninput="checkID();">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                            <a id="checkID"></a>
                                                        </div>
                                                    </div>                                                    
                                                    <div class="col-md-4">
                                                        <label>Password</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD" oninput="checkPW()">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-lock"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>CheckPassword</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="password" class="form-control" id="PASSWORD2" placeholder="Password" oninput="checkPW()">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-lock"></i>
                                                                </div>
                                                            </div>
                                                            <a id="checkPW2"></a>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Email</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="email" class="form-control" placeholder="Email" id="EMAIL" >
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-envelope"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Name</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="Name" id="NAME">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                                                    
                                                    <div class="col-md-4">
                                                        <label>NickName</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="NickName" id="NICKNAME">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- <div class="form-group col-md-8 offset-md-4">
                                                        <div class='form-check'>
                                                            <div class="checkbox">
                                                                <input type="checkbox" id="checkbox2"
                                                                    class='form-check-input' checked>
                                                                <label for="checkbox2">Remember Me</label>
                                                            </div>
                                                        </div>
                                                    </div> -->
                                                    <div class="g-recaptcha" data-sitekey="6Ldp5h4fAAAAANhHE8kOrxz-elPrw9FeYxSx-SHK" data-callback="recaptchacallback"></div>
                                                    <br/><br/>
                                                    <br/><br/>
                                                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1" id="join">회원가입</button>
                                                        <a class="btn btn-light-secondary me-1 mb-1" href="/SC/main.cut">취소</a>
                                                    </div>
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
    let checkPw = false;
    let checkId = false;
    
    $(document).ready(function(){
	    $("#join").on("click", function(e){ //회원가입버튼 버튼
	    	e.preventDefault();
	    	let id = $("#ID").val();
	    	let password = $("#PASSWORD").val();
	    	let password2 = $("#PASSWORD2").val();
	    	let email = $("#EMAIL").val();
	    	let name = $("#NAME").val();
	    	let nickname = $("#NICKNAME").val();
	    	let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;//이메일 체크 정규식

	    	
	    	if(id == null || id == ""){	    		
	    		swal({
					title				: 'ID를 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#ID").focus();
	    		});
	    		return false;
	    	}else if(password == null || password == ""){
	    		swal({
					title				: '비밀번호를 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#PASSWORD").focus();
	    		});
	    		return false;
	    	}else if(email == null || email == ""){
	    		swal({
					title				: '이메일을 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#EMAIL").focus();
	    		});
	    		return false;
	    	}else if(email.match(regExp) == null){
	    		swal({
					title				: '이메일형식이 옳바르지 않습니다.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#EMAIL").focus();
	    		});
	    		return false;
	    	}else if(name == null || name == ""){
	    		swal({
					title				: '이름을 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#NAME").focus();
	    		});
	    		return false;
	    	}else if(nickname == null || nickname == ""){
	    		swal({
					title				: '닉네임을 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#NICKNAME").focus();
	    		});
	    		return false;
	    	}else if(!checkId){
	    		swal({
					title				: 'ID 중복을 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#ID").focus();
	    		});
	    		return false;
	    	}else if(!checkPw){
	    		swal({
					title				: '사용가능한 비밀번호인지 확인해주세요.',
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				}).then(function(){
					$("#PASSWORD").focus();
	    		});
	    		return false;
	    	}else{
				$.ajax({
			        url : "join.cut",
			      	data : {
			      		"ID" : id,
			      		"PASSWORD" : password, 
			      		"EMAIL" : email, 
			      		"NAME" : name, 
			      		"NICKNAME" : nickname
			      	},
			      	contentType : "application/json; charset=UTF-8",
			      	success : function(result){
			      		location.href="testemail.cut";
			      		location.href="loginForm.cut";
			      	},
			        error:function(request, error) {
			            alert("fail");
			            // error 발생 이유를 알려준다.
			         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			        }
				})	    		
	    	}
			
		});
    })
    
    function checkPW(){
		let pw1 = $("#PASSWORD").val();
		let pw2 = $("#PASSWORD2").val();
		
		if(pw1 == pw2 && pw1 != "" && pw2 != ""){
			$("#checkPW2").empty();
			$("#checkPW2").append("(비밀번호가 일치합니다.)");
			checkPw = true;
		}else{
			$("#checkPW2").empty();
			$("#checkPW2").append("(비밀번호가 일치하지않습니다.)");
			checkPw = false;
		}
	}
    
    function checkID(){
    	let id = $("#ID").val();
    	
    	$.ajax({
	        url : "checkID.cut",
	      	data : {
	      		"ID" : id
	      	},
	      	contentType : "application/json; charset=UTF-8",
	      	success : function(result){
	      		if(result){
	      			$("#checkID").empty();
	    			$("#checkID").append("(사용가능한 아이디입니다.)");
	    			checkId = true;
	      		}else{
	      			$("#checkID").empty();
	    			$("#checkID").append("(중복된 아이디입니다.)");
	    			checkId = false;
	      		}
	      	}
		})
    }
    
    function recaptchacallback(){
    	alert("오나");
    }
    </script>
</body>
</html>