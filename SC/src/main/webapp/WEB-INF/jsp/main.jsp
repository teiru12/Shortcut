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
					    					<th scope="col" width="100%">단축키</th> 
					    				</tr> 
						    				</thead>
											<c:if test = "${empty goodShortList}">
												<tr>
													<td>게시글이 없습니다.</td>
												</tr>					
											</c:if>
											<c:if test = "${! empty goodShortList}">
						    					<c:forEach var="shortItem" items="${goodShortList}">
													<tr>
														<td><a href="shortDetail.cut?SHORTIDX=${shortItem.SHORTIDX}">${shortItem.TITLE}</a></td>
													</tr>
												</c:forEach>
											</c:if>
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
											<c:if test = "${! empty goodFreeList}">
						    					<c:forEach var="freeItem" items="${goodFreeList}">
														<tr>
															<td width="80%"><a href="freeDetail.cut?FREEIDX=${freeItem.FREEIDX }">${freeItem.TITLE}</a></td>
															<td width="20%">${freeItem.READCOUNT}</td>
														</tr>
												</c:forEach>
											</c:if>
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
											<c:if test = "${! empty goodInfoList}">
						    					<c:forEach var="infoItem" items="${goodInfoList}">
														<tr>
															<td width="80%"><a href="infoDetail.cut?INFOIDX=${infoItem.INFOIDX }">${infoItem.TITLE}</a></td>
															<td width="20%">${infoItem.READCOUNT}</td>
														</tr>
												</c:forEach>
											</c:if> 
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

</body>
</html>