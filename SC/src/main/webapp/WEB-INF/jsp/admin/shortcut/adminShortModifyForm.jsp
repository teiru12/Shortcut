<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>

<!-- 클래식 에디터 -->
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script src="//cdn.ckeditor.com/4.5.9/standard/ckeditor.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/ckeditor/4.5.9/adapters/jquery.js"></script>

<script>
function validation(){
	let titleHead = $('#divProgram').text();
	let TITLE = $("#TITLE").val().trim();
	let CONTENT = $('textarea[name="editor"]').val().trim();
	let IDX = $('#SHORTIDX').val();
	
	if(TITLE == "") {
		swal({
			text				: "제목을 입력해주세요",
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
			text				: "본문을 입력해주세요",
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: true,
					className 	: 'btn btn-primary' 
				}
			}
		});		
	} else if(titleHead == "-----------") {
		swal({
			text				: "프로그램을 선택해주세요",
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
		let STYPE = "";
		if(titleHead == "MS오피스") {
			STYPE = "M";
		} else if(titleHead == "윈도우") {
			STYPE = "W";
		} else if(titleHead == "이클립스") {
			STYPE = "E";
		} else if(titleHead == "한글") {
			STYPE = "H";
		} else if(titleHead == "노트패드++") {
			STYPE = "N";
		}	
		
		writeProcess(TITLE, CONTENT, STYPE, IDX);
	}
}
function writeProcess(TITLE, CONTENT, STYPE, IDX){
	$.ajax({
        url:"/SC/adminShortModify.cut",
        data: {"TITLE" : TITLE, "CONTENT" : CONTENT, "STYPE" : STYPE, "IDX" : IDX},
        contentType	:"application/json",
        success		:function(data){
        	swal({
				text				: "단축키를 수정했습니다.",
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: true,
						className 	: 'btn btn-primary' 
					}
				}
			}).then((result) => {
				location.href='shortcutList.cut';
			});
		},
		error		:function(request, error){
			alert("fail");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
    });
}
function changeProgram(code) {
	if(code == 1) {
		$('#divProgram').text("MS오피스");
		$('#TITLE').attr("placeholder", "[프로그램] 단축키 ");
	} else if(code == 2) {
		$('#divProgram').text("윈도우");
		$('#TITLE').attr("placeholder", "단축키 ");
	} else if(code == 3) {
		$('#divProgram').text("이클립스");
		$('#TITLE').attr("placeholder", "단축키 ");
	} else if(code == 4) {
		$('#divProgram').text("한글");
		$('#TITLE').attr("placeholder", "단축키 ");
	} else if(code == 5) {
		$('#divProgram').text("노트패드++");
		$('#TITLE').attr("placeholder", "단축키 ");
	}
}
</script>
<script>
$(document).ready(function(){
	$('textarea[name="editor"]').ckeditor();
	
	let initS = $('#initSTYPE').val();
	if(initS == 'M') {
		changeProgram(1);
	} else if(initS == 'W') {
		changeProgram(2);
	} else if(initS == 'E') {
		changeProgram(3);
	} else if(initS == 'H') {
		changeProgram(4);
	} else if(initS == 'N') {
		changeProgram(5);
	}
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
				    <h4>단축키 게시판 수정</h4>
				</div>
					<div class="card-content">
						<div class="card-body">
			    			<div class="col-12">
			    				<fieldset>
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="btn-group dropdown me-1 mb-1">
												<button type="button" class="btn btn-primary">프로그램</button>
												<%-- 해당 단축키 게시글의 초기 프로그램 선택 & IDX--%>
												<input type="hidden" id="initSTYPE" value="${shortItem.STYPE}">
												<input type="hidden" id="SHORTIDX" value="${shortItem.SHORTIDX}">
												<button type="button"
													class="btn btn-primary dropdown-toggle dropdown-toggle-split"
													data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
													data-reference="parent">
													<span id="divProgram" class="sr-only">-----------</span>
												</button>
												<div class="dropdown-menu">
													<a class="dropdown-item" href="javascript:changeProgram(1)">MS오피스</a>
													<a class="dropdown-item" href="javascript:changeProgram(2)">윈도우</a>
													<a class="dropdown-item" href="javascript:changeProgram(3)">이클립스</a>
													<a class="dropdown-item" href="javascript:changeProgram(4)">한글</a>
													<a class="dropdown-item" href="javascript:changeProgram(5)">노트패드++</a>
												</div>
											</div>
										</div>
					           			<div class="position-relative" style="line-height:30%; width:85%">
											<input type="text" class="form-control" placeholder="단축키" id="TITLE" maxlength="30"
												value="${shortItem.TITLE}"><br>
				            			</div>
									</div>
				            	</fieldset>			            		
			    			</div>
							<div class="row" style="line-height:30%;">
                                <div class="col-12">
                                    <fieldset>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">관리자</span>
                                            </div>
											<input type="text" class="form-control" id="inputId" 
												value="관리자" disabled>
                                        </div>
                                    </fieldset><br>
                                </div>
                            </div>
						    <div id="classic">
								<textarea id="CONTENT" name="editor" maxlength="400">${shortItem.CONTENT}</textarea>
	    					</div>
   						<br>
	    				<div class="col-12 d-flex justify-content-end">
                           <button type="submit" onclick="validation()"
                               class="btn btn-primary me-1 mb-1">수정</button>
						</div>
					</div>
   				 </div>
    		</div>
	    </div>
	</div>
</body>
</html>