<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
	<style>
		.container{
			width: 800px;
			margin: 0 auto;
		}
		
		ul.tabs{
			margin: 0px;
			padding: 0px;
			list-style: none;
		}
		
		ul.tabs li{
			background: none;
			color: #222;
			display: inline-block;
			padding: 10px 15px;
			cursor: pointer;
		}

		ul.tabs li.current{
			background: #ededed;
			color: #222;
		}

		.tab-content{
			display: none;
			background: #ededed;
			padding: 15px;
		}

		.tab-content.current{
			display: inherit;
		}
	</style>
	<script>
	$(document).ready(function(){
		
		$('ul.tabs li').click(function(){
			var tab_id = $(this).attr('data-tab');
			var tab_id2 = $(this).attr('data-tab2');
			
			if($(this).parent().prop('id') == 'tab1'){
				
				$("#tab1").children().removeClass('current');
				$("#tab-1").removeClass('current');
				$("#tab-2").removeClass('current');
				$("#tab-3").removeClass('current');
			}
			
			if($(this).parent().prop('id') == 'tab2'){
				
				$("#tab2").children().removeClass('current');
				$("#tab2-1").removeClass('current');
				$("#tab2-2").removeClass('current');
				$("#tab2-3").removeClass('current');
			}

			$(this).addClass('current');
			$("#"+tab_id).addClass('current');
			$("#"+tab_id2).addClass('current');
		})

	})
	</script>
	
	<!-- 		<script>
	$(document).ready(function(){
		
		$('tab1').click(function(){
			var tab_id = $(this).attr('data-tab');
			var tab_id2 = $(this).attr('data-tab2');

			$('tab1').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#"+tab_id).addClass('current');
			$("#"+tab_id2).addClass('current');
		})
	})
	</script> -->
</head>
<body>

<div id="main">
	<div class="page-heading">
		<section class="section">
			<div class="row">
			<!--------------------------최신글-------------------------->
				<div class="col-12 col-md-6 col-lg-6">
					<div class="card">
						<div class="card-header">
							<h4>최신글</h4>
							<div class="card-body">
							<ul class="tabs" id="tab1">
								<li class="tab-link current" data-tab="tab-1">단축키</li>
								<li class="tab-link" data-tab="tab-2">자유</li>
								<li class="tab-link" data-tab="tab-3">정보교류</li>
							</ul>
							
								<div id="tab-1" class="tab-content current">
								<div style="text-align:center">
									<div class="col-md-12 ftco-animate">
									<div class="cart-list">
										<table class="table">
						    				<thead class="thead-primary">
							    				<tr> 
							    					<th scope="col" width="15%">프로그램</th>
							    					<th scope="col" width="25%">단축키</th>
							    					<th scope="col" width="45%">설명</th>
							    					<th scope="col" width="15%">조회수</th>
							    				</tr>
							    			</thead>
												<c:if test = "${empty shortList}">
													<tr>
														<th width="100%">게시글이 없습니다.</th>
													</tr>		
												</c:if>
					    					<c:forEach var="shortItem" items="${shortList}">
												<tr>
													<td>
													<c:if test="${shortItem.STYPE eq 'M'}">
													마이크로소프트
													</c:if>
													<c:if test="${shortItem.STYPE eq 'W'}">
													윈도우
													</c:if>
													<c:if test="${shortItem.STYPE eq 'E'}">
													이클립스
													</c:if>
													<c:if test="${shortItem.STYPE eq 'H'}">
													한글
													</c:if>
													<c:if test="${shortItem.STYPE eq 'N'}">
													노트패드++
													</c:if>
													</td>
													<td><a href="shortDetail.cut?SHORTIDX=${shortItem.SHORTIDX}">${shortItem.TITLE}</a></td>
													<td>${shortItem.CONTENT}</td>
													<td>${shortItem.READCOUNT}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
									</div> 
								</div>
								</div>
								<div id="tab-2" class="tab-content">
								<div style="text-align:center">
									<div class="col-md-12 ftco-animate">
									<div class="cart-list">
								<table class="table">
				    				<thead class="thead-primary"> 
				    				<tr> 
				    					<th scope="col" width="80%">제목</th>
				    					<th scope="col" width="20%">조회수</th>
				    				</tr> 
					    				</thead>
											<c:if test = "${empty freeList}">
												<tr>
													<td width="80%">게시글이 없습니다.</td>
													<td width="20%">0</td>
												</tr>					
											</c:if>
					    					<c:forEach var="freeItem" items="${freeList}">
													<tr>
														<td width="80%"><a href="freeDetail.cut?FREEIDX=${freeItem.FREEIDX }">${freeItem.TITLE}</a></td>
														<td width="20%">${freeItem.READCOUNT}</td>
													</tr>
											</c:forEach>
										</table>
									</div>
									</div> 
								</div>
								</div>
								
								<div id="tab-3" class="tab-content">
								<div style="text-align:center">
									<div class="col-md-12 ftco-animate">
									<div class="cart-list">
								<table class="table">
				    				<thead class="thead-primary"> 
				    				<tr> 
				    					<th scope="col" width="80%">제목</th>
				    					<th scope="col" width="20%">조회수</th>
				    				</tr> 
					    				</thead>
											<c:if test = "${empty infoList}">
												<tr>
													<td width="80%">게시글이 없습니다.</td>
													<td width="20%">0</td>
												</tr>					
											</c:if>
					    					<c:forEach var="infoItem" items="${infoList}">
													<tr>
														<td width="80%"><a href="infoDetail.cut?INFOIDX=${infoItem.INFOIDX }">${infoItem.TITLE}</a></td>
														<td width="20%">${infoItem.READCOUNT}</td>
													</tr>
											</c:forEach> 
										</table>
									</div>
									</div> 
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--------------------------10추 글-------------------------->
				<div class="col-12 col-md-6 col-lg-6">
						<div class="card">
							<div class="card-header">
								<h4>인기글</h4>
								<div class="card-body">
								<ul class="tabs" id="tab2">
									<li class="tab-link current" data-tab2="tab2-1">단축키</li>
									<li class="tab-link" data-tab2="tab2-2">자유</li>
									<li class="tab-link" data-tab2 ="tab2-3">정보교류</li>
								</ul>
								
									<div id="tab2-1" class="tab-content current">
									<div style="text-align:center">
										<div class="col-md-12 ftco-animate">
										<div class="cart-list">
									<table class="table">
					    				<thead class="thead-primary"> 
					    				<tr> 
					    					<th scope="col" width="100%">제목</th> 
					    				</tr> 
						    				</thead>
											<c:if test = "${empty goodShortList}">
												<tr>
													<td>게시글이 없습니다.</td>
												</tr>					
											</c:if>
						    					<c:forEach var="goodShortItem" items="${goodShortList}">
													<tr>
														<td width="100%">${goodShortItem.TITLE}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
										</div> 
									</div>
									</div>
									
									<div id="tab2-2" class="tab-content">
									<div style="text-align:center">
										<div class="col-md-12 ftco-animate">
										<div class="cart-list">
									<table class="table">
					    				<thead class="thead-primary"> 
					    				<tr> 
					    					<th scope="col" width="80%">제목</th>
					    					<th scope="col" width="20%">조회수</th>
					    				</tr> 
						    				</thead>
											<c:if test = "${empty goodFreeList}">
												<tr>
													<td width="80%">게시글이 없습니다.</td>
													<td width="20%">0</td>
												</tr>					
											</c:if>
						    					<c:forEach var="freeItem" items="${goodFreeList}">
														<tr>
															<td width="80%">${freeItem.TITLE}</td>
															<td width="20%">${freeItem.READCOUNT}</td>
														</tr>
												</c:forEach>
											</table>
										</div>
										</div> 
									</div>
									</div>

									<div id="tab2-3" class="tab-content">
									<div style="text-align:center">
										<div class="col-md-12 ftco-animate">
										<div class="cart-list">
									<table class="table">
					    				<thead class="thead-primary"> 
					    				<tr> 
					    					<th scope="col" width="80%">제목</th>
					    					<th scope="col" width="20%">조회수</th>
					    				</tr>
						    				</thead>
											<c:if test = "${empty goodInfoList}">
												<tr>
													<td width="80%">게시글이 없습니다.</td>
													<td width="20%">0</td>
												</tr>					
											</c:if>
						    					<c:forEach var="infoItem" items="${goodInfoList}">
														<tr>
															<td width="80%">${infoItem.TITLE}</td>
															<td width="20%">${infoItem.READCOUNT}</td>
														</tr>
												</c:forEach> 
											</table>
										</div>
										</div> 
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


	<!-- 기존 소스 -->
<!-- <body>
>
        <div id="main">
            <div class="page-heading">
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>Button</h3>
                            <p class="text-subtitle text-muted">Use Bootstrap’s custom button styles for actions in
                                forms, dialogs,
                                and more with support for multiple sizes, states,
                                and more.</p>
                        </div>
                        <div class="col-12 col-md-6 order-md-2 order-first">
                            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Button</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <section class="section">

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Basic Buttons</h4>
                                </div>
                                <div class="card-body">
                                    <h6>Default</h6>
                                    <p class="text-muted">Use the <code>.btn .btn-{color}</code> classes.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-primary">Primary</a>
                                        <a href="#" class="btn btn-secondary">Secondary</a>
                                        <a href="#" class="btn btn-info">Info</a>
                                        <a href="#" class="btn btn-warning">Warning</a>
                                        <a href="#" class="btn btn-danger">Danger</a>
                                        <a href="#" class="btn btn-success">Success</a>
                                        <a href="#" class="btn btn-light">Light</a>
                                        <a href="#" class="btn btn-dark">Dark</a>
                                    </div>
                                    <hr>
                                    <h6>Rounded</h6>
                                    <p>Use a class <code>.rounded-pill</code> with <code>.btn</code> class to create
                                        round button.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-primary rounded-pill">Primary</a>
                                        <a href="#" class="btn btn-secondary rounded-pill">Secondary</a>
                                        <a href="#" class="btn btn-info rounded-pill">Info</a>
                                        <a href="#" class="btn btn-warning rounded-pill">Warning</a>
                                        <a href="#" class="btn btn-danger rounded-pill">Danger</a>
                                        <a href="#" class="btn btn-success rounded-pill">Success</a>
                                        <a href="#" class="btn btn-light rounded-pill">Light</a>
                                        <a href="#" class="btn btn-dark rounded-pill">Dark</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Buttons <code>.btn-outline</code></h4>
                                </div>
                                <div class="card-body">
                                    <p class="text-muted">Use the <code>.btn .btn-outline-{color}</code> classes.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-outline-primary">Primary</a>
                                        <a href="#" class="btn btn-outline-secondary">Secondary</a>
                                        <a href="#" class="btn btn-outline-info">Info</a>
                                        <a href="#" class="btn btn-outline-warning">Warning</a>
                                        <a href="#" class="btn btn-outline-danger">Danger</a>
                                        <a href="#" class="btn btn-outline-success">Success</a>
                                        <a href="#" class="btn btn-outline-light">Light</a>
                                        <a href="#" class="btn btn-outline-dark">Dark</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Button Sizes</h4>
                                </div>
                                <div class="card-body">
                                    <p class="text-muted">Use the <code>.btn-lg</code> or <code>.btn-sm</code> classes.
                                    </p>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-sm btn-warning">Small</a>
                                        <a href="#" class="btn btn-danger">Normal</a>
                                        <a href="#" class="btn btn-lg btn-dark">Large</a>
                                    </div>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-sm btn-outline-primary">Small</a>
                                        <a href="#" class="btn btn-outline-primary">Normal</a>
                                        <a href="#" class="btn btn-lg btn-outline-primary">Large</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Button States</h4>
                                </div>
                                <div class="card-body">
                                    <p class="text-muted">Use the <code>.disabled</code> or <code>.btn-progress</code>
                                        classes.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn btn-primary">Normal</a>
                                        <a href="#" class="btn disabled btn-primary">Disabled</a>
                                        <a href="#" class="btn disabled btn-primary btn-progress">Progress</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Icon Button</h4>
                                </div>
                                <div class="card-body">
                                    <p class="text-muted">Use the <code>.icon</code> and <code>.icon-left</code>
                                        classes.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn icon icon-left"><i data-feather="user"></i> Default</a>
                                        <a href="#" class="btn icon icon-left btn-primary"><i data-feather="edit"></i>
                                            Primary</a>
                                        <a href="#" class="btn icon icon-left btn-secondary"><i data-feather="user"></i>
                                            Secondary</a>
                                        <a href="#" class="btn icon icon-left btn-info"><i data-feather="info"></i>
                                            Info</a>
                                        <a href="#" class="btn icon icon-left btn-warning"><i
                                                data-feather="alert-triangle"></i>
                                            Warning</a>
                                        <a href="#" class="btn icon icon-left btn-danger"><i
                                                data-feather="alert-circle"></i>
                                            Danger</a>
                                        <a href="#" class="btn icon icon-left btn-success"><i
                                                data-feather="check-circle"></i>
                                            Success</a>
                                        <a href="#" class="btn icon icon-left btn-light"><i data-feather="star"></i>
                                            Light</a>
                                        <a href="#" class="btn icon icon-left btn-dark"><i data-feather="file"></i>
                                            Dark</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Only Icon Button</h4>
                                </div>
                                <div class="card-body">
                                    <p class="text-muted">Use the <code>.icon</code> class.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn icon btn-primary"><i data-feather="edit"></i></a>
                                        <a href="#" class="btn icon btn-secondary"><i data-feather="user"></i></a>
                                        <a href="#" class="btn icon btn-info"><i data-feather="info-circle"></i></a>
                                        <a href="#" class="btn icon btn-warning"><i
                                                data-feather="exclamation-triangle"></i></a>
                                        <a href="#" class="btn icon btn-danger"><i data-feather="times"></i></a>
                                        <a href="#" class="btn icon btn-success"><i data-feather="check"></i></a>
                                        <a href="#" class="btn icon btn-light"><i data-feather="star"></i></a>
                                        <a href="#" class="btn icon btn-dark"><i data-feather="file"></i></a>
                                    </div>
                                    <p class="text-muted mt-2">Sizes.</p>
                                    <div class="buttons">
                                        <a href="#" class="btn icon btn-sm btn-warning"><i
                                                data-feather="exclamation-triangle"></i></a>
                                        <a href="#" class="btn icon btn-danger"><i data-feather="times"></i></a>
                                        <a href="#" class="btn icon btn-lg btn-success"><i data-feather="check"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Button Group</h4>
                                </div>
                                <div class="card-body">
                                    <div class="btn-group mb-3" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-secondary">Left</button>
                                        <button type="button" class="btn btn-secondary">Middle</button>
                                        <button type="button" class="btn btn-secondary">Right</button>
                                    </div>
                                    <div class="btn-group mb-3" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-danger">Left</button>
                                        <button type="button" class="btn btn-warning">Middle</button>
                                        <button type="button" class="btn btn-success">Right</button>
                                    </div>
                                    <div class="btn-group mb-3 btn-group-sm" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-danger">Left</button>
                                        <button type="button" class="btn btn-danger">Middle</button>
                                        <button type="button" class="btn btn-danger">Right</button>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="btn-group btn-group-lg" role="group" aria-label="Basic example">
                                        <button type="button" class="btn">Left</button>
                                        <button type="button" class="btn btn-primary">Middle</button>
                                        <button type="button" class="btn">Right</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Vertical Variation</h4>
                                </div>
                                <div class="card-body">
                                    <div class="btn-group-vertical" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-secondary">Left</button>
                                        <button type="button" class="btn btn-secondary">Middle</button>
                                        <button type="button" class="btn btn-secondary">Right</button>
                                        <button type="button" class="btn btn-secondary">Justify</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>


        </div> -->

</body>
</html>