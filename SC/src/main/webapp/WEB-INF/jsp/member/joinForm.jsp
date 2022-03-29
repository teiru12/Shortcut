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
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="ID" id="ID">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                                                    
                                                    <div class="col-md-4">
                                                        <label>Password</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD">
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
                                                                <input type="password" class="form-control"
                                                                    placeholder="Password">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-lock"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Email</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="email" class="form-control" placeholder="Email" id="EMAIL">
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
                                                    <div class="g-recaptcha" data-sitekey="6Ldp5h4fAAAAANhHE8kOrxz-elPrw9FeYxSx-SHK"></div>
                                                    <br/><br/>
                                                    <br/><br/>
                                                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1" id="join">회원가입</button>
                                                        <button type="button" class="btn btn-light-secondary me-1 mb-1">취소</button>
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
    $(document).ready(function(){
	    $("#join").on("click", function(e){ //회원가입버튼 버튼
	    	let id = $("#ID").val();
	    	let password = $("#PASSWORD").val();
	    	let email = $("#EMAIL").val();
	    	let name = $("#NAME").val();
	    	let nickname = $("#NICKNAME").val();
	    	
			$.ajax({
		        url : "join.cut",
		      	data : {
		      		"ID" : id,
		      		"PASSWORD" : password, 
		      		"EMAIL" : email, 
		      		"NAME" : name, 
		      		"NICKNAME" : nickname
		      	},
		      	contentType : "application/json; charset=UTF-8"
			})
			
		});
    })
    </script>
</body>
</html>