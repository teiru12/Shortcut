<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			
				<div class="col-12 col-md-6 col-lg-6 mx-auto">
					<div class="card">
						<div class="card-header">
							<h4>팔로우 리스트</h4>
							<div class="card-body mx-auto">
								<div style="text-align:center">
									<div class="col-md-12 ftco-animate mx-auto">
										<div class="cart-list col-md-12 mx-auto" style="text-align:center">
											<table class="table">
 												<thead class="thead-primary">
  													<tr> 
  														<th scope="col" width="100%">팔로우</th>
  													</tr> 
  												</thead>
												<c:if test = "${empty followList}">
													<tr>
														<td>팔로우한 사람이 없습니다.</td>
													</tr>					
												</c:if>
												<c:forEach var="follow" items="${followList}">
													<tr>
														<td width="100%">${follow.FOLLOWID}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
										${paging.pageHtml}
									</div> 
								</div>
							</div>
							
							<div class="card-body mx-auto">
							
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