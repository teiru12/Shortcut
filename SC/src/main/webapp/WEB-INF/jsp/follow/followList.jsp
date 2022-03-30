<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
<script>
/* 팔로우 삭제 Ajax */
function followDelete(FOLLOWIDX, FOLLOWID, currentPage) {
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
				url 		: "/SC/followDelete.cut",
				data		: {"FOLLOWIDX" : FOLLOWIDX, "FOLLOWID" : FOLLOWID, "currentPage" : currentPage},
				contentType	: "application/json",
				success		: function(data) {

					/* 삭제 뒤 게시판 새로 구성 */
					$("#followBody").remove();
					$("#followTable").append(data.newFollow);

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
							<h4>팔로우 리스트</h4>
							<div class="card-body mx-auto">
								<div style="text-align:center">
									<div class="col-md-8 ftco-animate mx-auto">
										<div class="cart-list col-md-12 mx-auto" style="text-align:center">
											<table id="followTable" class="table">
 												<thead class="thead-primary">
  													<tr> 
  														<th scope="col" width="100%" colspan="2">팔로우</th>
  													</tr> 
  												</thead>
  												<tbody id="followBody">
													<c:if test = "${empty followList}">
														<tr>
															<td>팔로우한 사람이 없습니다.</td>
														</tr>					
													</c:if>
													<c:forEach var="follow" items="${followList}">
														<tr id="follow${follow.FOLLOWIDX}">
															<td width="50%">${follow.FOLLOWID}</td>
															<td width="50%">
															<button class="btn btn-sm btn-light"
																onClick="followDelete(${follow.FOLLOWIDX},'${follow.FOLLOWID}',${currentPage})">
																삭제
															</button>
															</td>
														</tr>
													</c:forEach>
												</tbody>
												<tbody>
												</tbody>
											</table>
										</div>
										<div class="card-body mx-auto" id="pagination">
											<span id="pageBody">${paging.pageHtml}</span>
										</div>
									</div> 
								</div>
							</div>
							
							<div >
							
							</div>
						</div> <!-- end card-header -->
					</div>
				</div>
				
			</div>
		</section>
	</div>
</div>

</body>
</html>