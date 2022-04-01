<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 클래식 에디터 -->
<script src="https://cdn.ckeditor.com/ckeditor5/33.0.0/classic/ckeditor.js"></script>
<title>write </title>
<!-- 넓이 높이 조절 -->
<style>
	.ck.ck-editor {
    	max-width: 955px;
	}
	.ck-editor__editable {
	    min-height: 500px;
	}
	</style>
</head>
<body>
<div id="main">
	<div class="col-12">
			<div class="card">
			    <div class="card-header">
				    <h4>자유 게시판 수정</h4>
				</div>
					<div class="card-content">
						<div class="card-body">
							<div class="col-12">
			           			<div class="position-relative">
								<input type="text" class="form-control" placeholder="제목" id="TITLE">
			            		</div>
			    			</div>
			    			<div align="right">
			    			<br>
			    			닉네임
			    			</div>
						    <div id="classic">
						        <p>수정 샘플</p>
						    </div> 
							    <script>
						        ClassicEditor
						            .create( document.querySelector( '#classic' ))
						            .catch( error => {
						                console.error( error );
						            } );
	    					</script>
   						<br>
	    				<div class="col-12 d-flex justify-content-end">
                           <button type="submit"
                               class="btn btn-primary me-1 mb-1">작성</button>
                           <button type="reset"
                               class="btn btn-light-secondary me-1 mb-1">취소</button>
						</div>
					</div>
   				 </div>
    		</div>
	    </div>
	</div>
</body>
</html>