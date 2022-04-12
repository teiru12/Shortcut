<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/memMenu.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
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
</head>
<body>
<div id="main">
	<div class="page-heading">
		<section class="section">
			<div class="row">
				<div class="col-sm-9 col-md-7 col-lg-7 mx-auto">
					<div class="card">
						<div class="card-header">
							<h4>즐겨찾기</h4>
							<div class="card-body">
							<ul class="tabs" id="tab1">
								<c:if test="${STYPE == null }">
								<li class="tab-link current" id="test1"><a href="bookmarkList.cut">단축키 즐겨찾기</a></li>
								<li class="tab-link" id="test2"><a href="bookmarkList.cut?STYPE=B">게시판 즐겨찾기</a></li>
								</c:if>
								<c:if test="${STYPE != null }">
								<li class="tab-link" id="test1"><a href="bookmarkList.cut">단축키 즐겨찾기</a></li>
								<li class="tab-link current" id="test2"><a href="bookmarkList.cut?STYPE=B">게시판 즐겨찾기</a></li>
								</c:if>
							</ul>
							
								<div id="tab-1" class="tab-content current">
								<div style="text-align:center">
									<div class="col-md-12 ftco-animate">
									<div class="cart-list">
										<table class="table" id="bookmarkTable">
										<c:if test="${STYPE == null }">
						    				<thead class="thead-primary">
							    				<tr> 
							    					<th scope="col" width="15%">프로그램</th>
							    					<th scope="col" width="20%">제목</th>
							    					<th scope="col" width="30%">내용</th>
							    					<th scope="col" width="15%"></th>
							    				</tr>
							    			</thead>
							    			<tbody id="bookmarkBody">
												<c:if test = "${empty bookmarklist}">
													<tr>
														<td width="30%">게시글이 없습니다.</td>
														<td width="20%"></td>
														<td width="30%"></td>
														<td width="15%"></td>
													</tr>		
												</c:if>
						    					<c:forEach var="bookmark" items="${bookmarklist}">
													<tr>
														<c:if test="${bookmark.STYPE == 'E'}">
															<td width="15%">이클립스</td>
														</c:if>
														<c:if test="${bookmark.STYPE == 'H'}">
															<td width="15%">한글</td>
														</c:if>
														<c:if test="${bookmark.STYPE == 'W'}">
															<td width="15%">윈도우</td>
														</c:if>
														<c:if test="${bookmark.STYPE == 'N'}">
															<td width="15%">노트패드</td>
														</c:if>
														<c:if test="${bookmark.STYPE == 'M'}">
															<td width="15%">오피스</td>
														</c:if>
														<td width="20%"><a href="shortDetail.cut?SHORTIDX=${bookmark.SHORTIDX }">${bookmark.TITLE}</a></td>
														<td width="30%">${bookmark.CONTENT}</td>
														<td width="15%"><button class="btn btn-sm btn-light"
																onClick="bookmarkshortDelete(${bookmark.BOOKMARKIDX},'${bookmark.ID}',${currentPage})">
																삭제
															</button>														
														</td>
													</tr>
												</c:forEach>
											</tbody>
											</c:if>
											<c:if test="${STYPE != null }">
											<thead class="thead-primary">
							    				<tr> 
							    					<th scope="col" width="25%">게시판종류</th>
							    					<th scope="col" width="20%">제목</th>
							    					<th scope="col" width="20%">글쓴이</th>
							    					<th scope="col" width="15%"></th>
							    				</tr> 
							    			</thead>
							    			<tbody id="bookmarkBody">
												<c:if test = "${empty bookmarklist}">
													<tr>
														<td width="30%">게시글이 없습니다.</td>
														<td width="20%"></td>
														<td width="30%"></td>
														<td width="15%"></td>
													</tr>		
												</c:if>
						    					<c:forEach var="bookmark" items="${bookmarklist}" varStatus="vs">
													<tr>
													<c:if test="${bookmark.TYPE == 'FRE' }">
														<td width="25%">자유 게시판</td>
														<td width="20%"><a href="freeDetail.cut?FREEIDX=${bookmark.FREEIDX }">${bookmark.FTITLE}</a></td>
														<td width="20%">
														<div class="btn-group dropend me-1 mb-1">																
															<button type="button" class="btn btn-icon-default"
																data-bs-toggle="dropdown" aria-haspopup="false"
																aria-expanded="false">
																${bookmark.FID}
															</button>
															<div class="dropdown-menu">
																<button class="btn btn-icon-default" onClick="writerDetail('${bookmark.FID}')">회원 정보</button><br>
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																id="sendModal${vs.index}"
																data-s="${bookmark.BID}" data-g="${bookmark.FID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</div>
														</div>
														</td>													
													</c:if>
													<c:if test="${bookmark.TYPE == 'NEW' }">
														<td width="25%">뉴스 게시판</td>
														<td width="20%"><a href="newsDetail.cut?NEWSIDX=${bookmark.NEWSIDX }">${bookmark.NTITLE}</a></td>
														<td width="20%">
														<div class="btn-group dropend me-1 mb-1">																
															<button type="button" class="btn btn-icon-default"
																data-bs-toggle="dropdown" aria-haspopup="false"
																aria-expanded="false">
																${bookmark.NID}
															</button>
															<div class="dropdown-menu">
																<button class="btn btn-icon-default" onClick="writerDetail('${bookmark.NID}')">회원 정보</button><br>
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																id="sendModal${vs.index}"
																data-s="${bookmark.BID}" data-g="${bookmark.NID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</div>
														</div>
														</td>													
													</c:if>
													<c:if test="${bookmark.TYPE == 'INF' }">
														<td width="25%">정보교류 게시판</td>
														<td width="20%"><a href="infoDetail.cut?INFOIDX=${bookmark.INFOIDX }">${bookmark.ITITLE}</a></td>
														<td width="20%">
														<div class="btn-group dropend me-1 mb-1">																
															<button type="button" class="btn btn-icon-default"
																data-bs-toggle="dropdown" aria-haspopup="false"
																aria-expanded="false">
																${bookmark.IID}
															</button>
															<div class="dropdown-menu">
																<button class="btn btn-icon-default" onClick="writerDetail('${bookmark.IID}')">회원 정보</button><br>
																<button type="button" class="openModal btn btn-icon-default" data-bs-toggle="modal"
																data-bs-target="#inlineForm"
																id="sendModal${vs.index}"
																data-s="${bookmark.BID}" data-g="${bookmark.IID}">
																	쪽지 보내기
																</button><br>
																<a class="btn btn-icon-default" href="chat.cut">1:1 채팅</a>
															</div>
														</div>
														</td>													
													</c:if>
														<td width="15%"><button class="btn btn-sm btn-light"
																onClick="bookmarkboardDelete(${bookmark.BOOKMARKIDX},'${bookmark.BID}',${currentPage})">
																삭제
															</button>	
														</td>
													</tr>
												</c:forEach>
											</tbody>
											</c:if>
										</table>
									</div>
									</div> 
								</div>
								</div>
																
								<c:if test="${STYPE == null }">
									<div class="card-body mx-auto" id="pagination">
											<span id="pageBody">${paging.pageHtml}</span>
									</div>
								</c:if>
								<c:if test="${STYPE != null }">
									<div class="card-body mx-auto" id="pagination2">
											<span id="pageBody2">${paging2.pageHtml}</span>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
<script type="text/javascript">
function bookmarkshortDelete(BOOKMARKIDX, ID, currentPage) {
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
			/* 팔로우 삭제 */
			$.ajax({
				url 		: "bookmarkshortDelete.cut",
				data		: {"BOOKMARKIDX" : BOOKMARKIDX, "ID" : ID, "currentPage" : currentPage},
				contentType	: "application/json",
				success		: function(data) {

					/* 삭제 뒤 게시판 새로 구성 */
					$("#bookmarkBody").remove();
					$("#bookmarkTable").append(data.newFollow);

					$("#pageBody").remove();
					$("#pagination").append(data.newPage);
					
					swal({
						title				: '삭제하셨습니다',
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
			});
		}
	});
}
function bookmarkboardDelete(BOOKMARKIDX, ID, currentPage) {
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
			/* 팔로우 삭제 */
			$.ajax({
				url 		: "bookmarkboardDelete.cut",
				data		: {"BOOKMARKIDX" : BOOKMARKIDX, "ID" : ID, "currentPage" : currentPage},
				contentType	: "application/json",
				success		: function(data) {

					/* 삭제 뒤 게시판 새로 구성 */
					$("#bookmarkBody").remove();
					$("#bookmarkTable").append(data.newFollow);

					$("#pageBody2").remove();
					$("#pagination2").append(data.newPage);
					
					swal({
						title				: '삭제하셨습니다',
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
			});
		}
	});
}
</script>
</body>
</html>