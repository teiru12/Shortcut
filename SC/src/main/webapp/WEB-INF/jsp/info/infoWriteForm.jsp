<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<!-- 클래식 에디터 -->
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script src="//cdn.ckeditor.com/4.5.9/standard/ckeditor.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/ckeditor/4.5.9/adapters/jquery.js"></script>

<script>
function validation(){
	let TITLE = $("#TITLE").val();
	let CONTENT = $('textarea[name="editor"]').val();
	let PASSWORD = "1";

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
	} else if($("#inputId").val() == '비회원'){
		swal({
			title: '비밀번호 입력',
			dangerMode: true, // 확인 버튼 빨갛게
			closeOnClickOutside: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			content: {
				element: 'input',
				attributes: {
					placeholder: '비밀번호를 입력해주세요.',
					type: 'password'
				}
			},
			buttons: {
				confirm: {
					text: '확인',
					value: false,
					className: 'btn btn-outline-primary'
				}
			}
		}).then((result) => { // result : 입력한 비밀번호
			PASSWORD = result;
			if(PASSWORD.length >= 10) {
				swal({
					title				: "글자 수가 너무 많습니다",
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				});
			} else {
				writeProcess(TITLE, CONTENT, PASSWORD);
			}
		});
	}else{
		writeProcess(TITLE, CONTENT, PASSWORD);
	}
}
function writeProcess(TITLE, CONTENT, PASSWORD){
	$.ajax({
        url:"/SC/infoWrite.cut",
        data: {"TITLE": TITLE,"CONTENT": CONTENT, "PASSWORD": PASSWORD},
        contentType	:"application/json",
        success		:function(data){
        	swal({
				text				: data.ID+'님이 글을 등록했습니다.',
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			}).then((result) => {
				location.href='infoList.cut';
			});
		},
		error		:function(request, error){
			alert("fail");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
				    <h4>정보 게시판 등록</h4>
				</div>
					<div class="card-content">
						<div class="card-body">
			    			<div class="col-12">
			           			<div class="position-relative" style="line-height:30%;">
								<input type="text" class="form-control" placeholder="제목" id="TITLE" maxlength="30"><br>
			            		</div>
			    			</div>
							<div class="row" style="line-height:30%;">
                                <div class="col-12">
                                    <fieldset>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">작성자</span>
                                            </div>
											<% if(request.getSession().getAttribute("id") == null) { %>
								    		<input type="text" class="form-control" id="inputId" value="비회원" disabled>
											<% } else { %>
												<input type="text" class="form-control" id="inputId" 
												value="<%=(String)request.getSession().getAttribute("id")%>" disabled>
											<% } %>
                                        </div>
                                    </fieldset><br>
                                </div>
                            </div>
						    <div id="classic">
								<textarea id="CONTENT" name="editor" maxlength="400"></textarea>
	    					</div>
   						<br>
	    				<div class="col-12 d-flex justify-content-end">
                           <button type="submit" onclick="validation()"
                               class="btn btn-primary me-1 mb-1">작성</button>
                           <button type="button"
                               class="btn btn-light-secondary me-1 mb-1"
                               onClick="javascript:history.go(-1);">취소</button>
						</div>
					</div>
   				 </div>
    		</div>
	    </div>
	</div>
</body>
</html>