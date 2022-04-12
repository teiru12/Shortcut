<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 클래식 에디터 -->
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script src="//cdn.ckeditor.com/4.5.9/standard/ckeditor.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/ckeditor/4.5.9/adapters/jquery.js"></script>
<title>숏컷</title>
<script>
function validation(){
	let TITLE = $("#TITLE").val();
	let CONTENT = $('textarea[name="editor"]').val();
	let IDX = $('#IDX').val();

	if(TITLE == "") {
    	swal({
			text				: "제목을 입력해주세요.",
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		});
	} else if(CONTENT == "") {
    	swal({
			text				: "본문을 입력해주세요.",
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		});
	} else if(CONTENT.length >= 400){
		swal({
			text				: "본문 글자 수가 너무 많습니다.",
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		});
	} else{
		modifyProcess(TITLE, CONTENT, IDX);
	}
}
function modifyProcess(TITLE, CONTENT, IDX){
	$.ajax({
        url:"/SC/noticeModify.cut",
        data: {"TITLE": TITLE,"CONTENT": CONTENT,"IDX": IDX},
        contentType	:"application/json",
        success		:function(data){
        	swal({
				text				: '킹리자님이 글을 수정했습니다.',
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			}).then((result) => {
				location.href='noticeDetail.cut?NOTICEIDX='+IDX;
			});
		},
		error		:function(request, error){
			if(TITLE == "") {
				alert("제목을 입력해주세요.");
			}if(CONTENT == ""){
				alert("내용을 입력해주세요.");
			}
			return swal;
			
/* 			alert("fail");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); */
		}
    });
}
</script>
<script>
$(document).ready(function(){
	$('textarea[name="editor"]').ckeditor();
});
</script>
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
				    <h4>공지 게시판 수정</h4>
				</div>
					<div class="card-content">
						<div class="card-body">
							<div class="col-12">
			           			<div class="position-relative" style="line-height:30%;">
								<input type="text" class="form-control" placeholder="제목" id="TITLE"
								value="${notice.TITLE}">
			            		</div>
			    			</div>
			    			<div class="row" style="line-height:30%;">
                                <div class="col-12">
                                    <fieldset>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">작성자</span>
                                            </div>
								    		<input type="text" class="form-control" id="inputId" value="관리자" disabled>
                                        </div>
                                    </fieldset><br>
                                </div>
                            </div>
						    <div id="classic">
							<textarea id="CONTENT" name="editor">${notice.CONTENT}</textarea>
	    					</div>
   						<br>
	    				<div class="col-12 d-flex justify-content-end">
							<input type="hidden" id="IDX" value="${notice.NOTICEIDX}">
							<button type="submit" onclick="validation()"
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
</body>
</html>