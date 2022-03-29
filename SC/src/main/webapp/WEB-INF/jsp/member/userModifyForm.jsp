<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script>
function userModify() {
	
}
</script>
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
                                    <h4 class="card-title">회원정보수정</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-horizontal" id="modifyForm" method="post"
                                        	enctype="multipart/form-data"> <!-- Modify Form -->
                                            <div class="form-body">
                                                <div class="row">
                                                
                                                	<!-- 프로필 이미지 수정 -->
                                                	<div class="col-md-4">
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
															</div>
														</div>
													</div>
													<div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
														
																<!-- modal -->
					                                            <button type="button" class="btn btn-outline-primary block" data-bs-toggle="modal"
					                                        		data-bs-target="#large">
					                                                이미지 선택
					                                            </button>
																
                                                            	<!-- file input일 경우 사용할 폼
																<input class="form-control form-control-sm" id="formFileSm" type="file">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                                 -->
                                                            </div>
                                                        </div>
													</div>
                                                
                                                	<div class="col-md-4">
                                                        <label>ID</label>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="form-group has-icon-left">
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" placeholder="ID" id="ID"
                                                                	value="${member.ID}" disabled>
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
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD"
                                                                	name="PASSWORD" value="${member.PASSWORD}">
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
                                                                <input type="password" class="form-control" placeholder="Password" id="PASSWORD2"
                                                                value="${member.PASSWORD}">
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
                                                                <input type="email" class="form-control" placeholder="Email" id="EMAIL" name="EMAIL"
                                                                	value="${member.ID}" disabled>
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
                                                                <input type="text" class="form-control" placeholder="Name" id="NAME" name="NAME"
                                                                	value="${member.NAME}">
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
                                                                <input type="text" class="form-control" placeholder="NickName" id="NICKNAME" name="NICKNAME"
                                                                	value="${member.NICKNAME}">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <br/><br/>
                                                    <br/><br/>
                                                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1"
                                                         	onClick="userModify()">
                                                         	회원수정
                                                         </button>
                                                        <button type="button" class="btn btn-light-secondary me-1 mb-1"
                                                        	onClick="javascript:history.go(-1);">
                                                        	취소
                                                        </button>
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
    
                                            <!--large size Modal -->
                                            <div class="modal fade text-left" id="large" tabindex="-1" role="dialog"
                                                aria-labelledby="myModalLabel17" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg"
                                                    role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">Large Modal</h4>
                                                            <button type="button" class="close" data-bs-dismiss="modal"
                                                                aria-label="Close">
                                                                <i data-feather="x"></i>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            I love tart cookie cupcake. I love chupa chups biscuit. I
                                                            love
                                                            marshmallow apple pie wafer
                                                            liquorice. Marshmallow cotton candy chocolate. Apple pie
                                                            muffin tart.
                                                            Marshmallow halvah pie
                                                            marzipan lemon drops jujubes. Macaroon sugar plum cake icing
                                                            toffee.
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-light-secondary"
                                                                data-bs-dismiss="modal">
                                                                <i class="bx bx-x d-block d-sm-none"></i>
                                                                <span class="d-none d-sm-block">Close</span>
                                                            </button>
                                                            <button type="button" class="btn btn-primary ml-1"
                                                                data-bs-dismiss="modal">
                                                                <i class="bx bx-check d-block d-sm-none"></i>
                                                                <span class="d-none d-sm-block">Accept</span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
</body>