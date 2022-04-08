/* 좋아요 증가 */
function addGood(userName, boardType, boardIdx, count) {
	/* 해당 게시판의 게시글에 대한 user의 좋아요나 싫어요가 있는지 Goodbad 테이블에서검색 */
	$.ajax({
		url 		: "/SC/selectGoodbad.cut",
		data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
		contentType	: "application/json",
		success		: function(data) {
			if(data.used == 'true') {
				// 검색된 기록이 있으면 오류메세지 출력
				swal({
					title				: data.message,
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				});				
			} else { // 검색된 기록이 없을 때
				// 게시판 Type에 따라 url 설정
				let goodUrl = "";
				if(boardType == 'SHO'){
					goodUrl = "/SC/shortGood.cut";
				} else if(boardType == 'FRE'){
					goodUrl = "/SC/freeGood.cut";
				} else if(boardType == 'INF'){
					goodUrl = "/SC/infoGood.cut";
				} else if(boardType == 'NEW'){
					goodUrl = "/SC/newsGood.cut";
				} 

				// 게시판의 좋아요 횟수 추가
				$.ajax({
					url 		: goodUrl,
					data		: {"IDX" : boardIdx},
					contentType	: "application/json",
					success		: function(data) {
						// Goodbad 테이블에 좋아요 기록 추가	
						$.ajax({
						url 		: "/SC/insertGood.cut",
						data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
						contentType	: "application/json",
						success		: function(data2) {
						
							// 게시글 상세보기 페이지에서 좋아요 카운트 증가 
							let newGood = count + 1;
							$('#goodValue').html(newGood);
							
							let newThumbsup = "<a href='#'>";
							newThumbsup += "<svg class='bi' width='1em' height='1em' fill='currentColor'>";
							newThumbsup += "<use xlink:href='assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-up-fill' />";
							newThumbsup += "</svg></a>";
														
							$('#goodButton').remove();
							$('#goodSpan').append(newThumbsup);							
							}				
						});	
					}				
				});				 
			}
		}				
	});
}

/* 싫어요 증가 */
function addBad(userName, boardType, boardIdx, count) {
	/* 해당 게시판의 게시글에 대한 user의 좋아요나 싫어요가 있는지 Goodbad 테이블에서검색 */
	$.ajax({
		url 		: "/SC/selectGoodbad.cut",
		data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
		contentType	: "application/json",
		success		: function(data) {
			if(data.used == 'true') {
				// 검색된 기록이 있으면 오류메세지 출력
				swal({
					title				: data.message,
					closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
					buttons				: {
						confirm : {
							text 		: '확인',
							value 		: true,
							className 	: 'btn btn-primary' 
						}
					}
				});				
			} else { // 검색된 기록이 없을 때
				// 게시판 Type에 따라 url 설정
				let badUrl = "";
				if(boardType == 'SHO'){
					badUrl = "/SC/shortBad.cut";
				} else if(boardType == 'FRE'){
					badUrl = "/SC/freeBad.cut";
				} else if(boardType == 'INF'){
					badUrl = "/SC/infoBad.cut";
				} else if(boardType == 'NEW'){
					badUrl = "/SC/newsBad.cut";
				} 

				// 게시판의 싫어요 횟수 추가
				$.ajax({
					url 		: badUrl,
					data		: {"IDX" : boardIdx},
					contentType	: "application/json",
					success		: function(data) {
						// Goodbad 테이블에 싫어요 기록 추가	
						$.ajax({
						url 		: "/SC/insertBad.cut",
						data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
						contentType	: "application/json",
						success		: function(data2) {
						
							// 게시글 상세보기 페이지에서 싫어요 카운트 증가 
							let newBad = count + 1;
							$('#badValue').html(newBad);
							
							let newThumbsdown = "<a href='#'>";
							newThumbsdown += "<svg class='bi' width='1em' height='1em' fill='currentColor'>";
							newThumbsdown += "<use xlink:href='assets/vendors/bootstrap-icons/bootstrap-icons.svg#hand-thumbs-down-fill' />";
							newThumbsdown += "</svg></a>";
														
							$('#badButton').remove();
							$('#badSpan').append(newThumbsdown);							
							}				
						});	
					}				
				});				 
			}
		}				
	});
}

/* 즐겨찾기 추가/삭제 */
function updateBookmark(userName, boardType, boardIdx, process) {

	// process 값에 따라 즐겨찾기 추가 또는 삭제
	// process : 'ADD' 추가, process : 'DEL' 삭제 
	/* 즐겨찾기 추가 */
	let bookUrl ="";
	if(process == 'ADD'){
		bookUrl = "/SC/bookmark.cut";
	} else if(process == 'DEL') {
		bookUrl = "/SC/bookmarkDetailDelete.cut";
	}
	$.ajax({
		url 		: bookUrl,
		data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
		contentType	: "application/json",
		success		: function(data) {
		
			// 게시글 상세보기 페이지에서 즐겨찾기 모양 변화 
			let newStar = "";
			if(process == 'ADD'){
				newStar += "<a id='bookStar' href=\"javascript:updateBookmark('" + userName + "','FRE'," + boardIdx + ",'DEL')\">";  
				newStar += "<svg class='bi' width='1em' height='1em' fill='currentColor'>";
				newStar += "<use xlink:href='assets/vendors/bootstrap-icons/bootstrap-icons.svg#star-fill' /></svg></a>"; 				
			} else if(process == 'DEL') {
				newStar += "<a id='bookStar' href=\"javascript:updateBookmark('" + userName +"','FRE'," + boardIdx + ",'ADD')\">";  
				newStar += "<svg class='bi' width='1em' height='1em' fill='currentColor'>";
				newStar += "<use xlink:href='assets/vendors/bootstrap-icons/bootstrap-icons.svg#star' /></svg></a>"; 	
			}
													
			$('#bookStar').remove();
			$('#bookSpan').append(newStar);							
		}				
	});	
}

/* 게시글 수정 체크 */
// 비회원인지 회원인지에 따라서 수정폼으로 보내준다
function modifyDetailCheck(memberCheck, TYPE, IDX) {
	/* 회원일 경우 바로 리다이렉트 */
	if(memberCheck == 'member') {
		if(TYPE == 'FRE') {
			location.href = "/SC/freeModifyForm.cut?IDX="+IDX;	
		} else if(TYPE == 'INF') {
			location.href = "/SC/infoModifyForm.cut?IDX="+IDX;
		} else if(TYPE == 'NEW') {
			location.href = "/SC/newsModifyForm.cut?IDX="+IDX;
		} else if(TYPE == 'NOT') {
			location.href = "/SC/noticeModifyForm.cut?IDX="+IDX;
		} else if(TYPE == 'SHO') {
			location.href = "/SC/shortModifyForm.cut?IDX="+IDX;
		}
	/* 비회원일 경우 패스워드 검사 */
	} else {
		/* 비밀번호 입력 */
		swal({
			title				: '비밀번호 입력',
			dangerMode			: true, // 확인 버튼 빨갛게
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			content				: {
				element : 'input',
				attributes : {
					placeholder : '비밀번호를 입력해주세요.',
					type : 'password'					
				}
			},
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: false,
					className 	: 'btn btn-outline-primary' 
				}
			}
		}).then((result) => { // result : 입력한 비밀번호
		
			let passUrl ="";
			if(TYPE == 'FRE') {
				passUrl = "/SC/freeDetailPassword.cut";	
			} else if(TYPE == 'INF') {
				passUrl = "/SC/infoDetailPassword.cut";
			} else if(TYPE == 'NEW') {
				passUrl = "/SC/newsDetailPassword.cut";
			}		
			
			// 게시판의 게시글의 비밀번호를 읽어온다.
			$.ajax({
				url 		: passUrl,
				data		: {"IDX" : IDX},
				contentType	: "application/json",
				success		: function(data) {
					if(result == data) { // 패스워드가 일치
						if(TYPE == 'FRE') {
							location.href = "/SC/freeModifyForm.cut?IDX="+IDX;	
						} else if(TYPE == 'INF') {
							location.href = "/SC/infoModifyForm.cut?IDX="+IDX;
						} else if(TYPE == 'NEW') {
							location.href = "/SC/newsModifyForm.cut?IDX="+IDX;
						}							
					} else { // 패스워드가 일치하지 않음
						swal({
							title				: '비밀번호가 틀립니다.',
							dangerMode			: true, // 확인 버튼 빨갛게
							closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
							buttons				: {
								confirm : {
									text 		: '확인',
									value 		: false,
									className 	: 'btn btn-outline-primary' 
								}
							}
						});	
					}
				}				
			});	
		});		
	}
}

/* 게시글 삭제 체크 */
// 비회원인지 회원인지에 따라서 삭제폼으로 보내준다
function deleteDetailCheck(memberCheck, TYPE, IDX) {
	
	let delUrl ="";
	if(TYPE == 'FRE') {
		delUrl = "/SC/freeDelete.cut";
	} else if(TYPE == 'INF') {
		delUrl = "/SC/infoDelete.cut";
	} else if(TYPE == 'NEW') {
		delUrl = "/SC/newsDelete.cut";
	} else if(TYPE == 'NOT') {
		delUrl = "/SC/noticeDelete.cut";
	} else if(TYPE == 'REP') {
		delUrl = "/SC/reportDelete.cut";
	} else if(TYPE == 'SHO') {
		delUrl = "/SC/shortDelete.cut";
	}
	
	/* 회원일 경우 삭제 확인 메세지 출력 후 삭제 */
	if(memberCheck == 'member') {
		delArticle(delUrl, TYPE, IDX);
	/* 비회원일 경우 패스워드 검사 */
	} else {
		/* 비밀번호 입력 */
		swal({
			title				: '비밀번호 입력',
			dangerMode			: true, // 확인 버튼 빨갛게
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			content				: {
				element : 'input',
				attributes : {
					placeholder : '비밀번호를 입력해주세요.',
					type : 'password'					
				}
			},
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: false,
					className 	: 'btn btn-outline-primary' 
				}
			}
		}).then((result) => { // result : 입력한 비밀번호
			let passUrl ="";
			if(TYPE == 'FRE') {
				passUrl = "/SC/freeDetailPassword.cut";	
			} else if(TYPE == 'INF') {
				passUrl = "/SC/infoDetailPassword.cut";
			} else if(TYPE == 'NEW') {
				passUrl = "/SC/newsDetailPassword.cut";
			}		
			
			// 게시판의 게시글의 비밀번호를 읽어온다.
			$.ajax({
				url 		: passUrl,
				data		: {"IDX" : IDX},
				contentType	: "application/json",
				success		: function(data) {
					if(result == data) { // 패스워드가 일치하면 삭제 확인 메세지 출력 후 삭제
						delArticle(delUrl, TYPE, IDX);
					} else { // 패스워드가 일치하지 않음
						swal({
							title				: '비밀번호가 틀립니다.',
							dangerMode			: true, // 확인 버튼 빨갛게
							closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
							buttons				: {
								confirm : {
									text 		: '확인',
									value 		: false,
									className 	: 'btn btn-outline-primary' 
								}
							}
						});	
					}
				}				
			});	
		});
	}
}

function delArticle(delUrl, TYPE, IDX) {
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
			/* 게시글 삭제 */
			$.ajax({
				url 		: delUrl,
				data		: {"IDX" : IDX},
				contentType	: "application/json",
				success		: function(data) {

					/* 삭제 후 게시판 리스트로 리다이렉트 */
					swal({
						title				: '삭제하였습니다',
						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
						buttons				: {
							confirm : {
								text 		: '확인',
								value 		: true,
								className 	: 'btn btn-primary' 
							}
						}
					}).then((result) => {
						if(TYPE == 'FRE') {
							location.href="/SC/freeList.cut";
						} else if(TYPE == 'INF') {
							location.href="/SC/infoList.cut";
						} else if(TYPE == 'NEW') {
							location.href="/SC/newsList.cut";
						} else if(TYPE == 'NOT') {
							location.href="/SC/noticeList.cut";
						} else if(TYPE == 'REP') {
							location.href="/SC/reportList.cut";
						} else if(TYPE == 'SHO') {
							location.href="/SC/shortList.cut";
						}
					});
				}	
			});
		}
	});
} 

function comWrite(ID, TYPE, ARTICLEIDX, reType, COMIDX, currentPage) { // reType : 0 이면 일반 댓글, reType 이 값을 가지면 해당 댓글 그룹 

	let content = $('#reply').val().trim();
	let	content2;
	if($('#reply' + COMIDX).val() == null) {
		content2 = "";
	} else {
		content2 = $('#reply' + COMIDX).val().trim();
	}
	
	if(content == "" && content2 == "") {
		swal({
			title				: "본문을 입력해주세요.",
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
		if(content =="") {
			content = content2;
		}
		
		let comWriteUrl ="";
		if(TYPE == 'SHO') {
			comWriteUrl = "/SC/shortComWrite.cut";	
		} else if(TYPE == 'FRE') {
			comWriteUrl = "/SC/freeComWrite.cut";	
		} else if(TYPE == 'INF') {
			comWriteUrl = "/SC/infoComWrite.cut";
		} else if(TYPE == 'REP') {
			comWriteUrl = "/SC/reportComWrite.cut";
		}		
		let inputData ="";
		
		if(ID != '비회원') { // 로그인 회원이 댓글을 달 때
			inputData = {"ARTICLEIDX" : ARTICLEIDX, "CONTENT" : content , 'ID' : ID, "PASSWORD" : "", "RETYPE" : reType, "COMIDX" : COMIDX, "currentPage" : currentPage};
			
			comWriteProcess(comWriteUrl, inputData);
		} else {
			/* 비밀번호 입력 */
			swal({
				title				: '비밀번호 입력',
				dangerMode			: true, // 확인 버튼 빨갛게
				closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
				content				: {
					element : 'input',
					attributes : {
						placeholder : '비밀번호를 입력해주세요.',
						type : 'password'					
					}
				},
				buttons				: {
					confirm : {
						text 		: '확인',
						value 		: false,
						className 	: 'btn btn-outline-primary' 
					}
				}
			}).then((result) => { // result : 입력한 비밀번호
			
				if(result == null || result == "") {
					swal({
						title				: "비밀번호를 입력해주세요.",
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
					inputData = {"ARTICLEIDX" : ARTICLEIDX, "CONTENT" : content , 'ID' : '비회원', "PASSWORD" : result, "RETYPE" : reType, "COMIDX" : COMIDX, "currentPage" : currentPage};						
		
					comWriteProcess(comWriteUrl, inputData);				
				}
			});
		}		
	}
}

function comWriteProcess(comWriteUrl, inputData) {
	$.ajax({
		url 		: comWriteUrl,
		data		: inputData,
		contentType	: "application/json",
		success		: function(data) {
			// 게시글 내의 댓글 리스트 새로 구성
			$('#comDiv').remove();
			$('#comBody').append(data.newComList);		
			
			$("#pageBody").remove();
			$("#pagination").append(data.newComPage);
			
			// 입력창 초기화
			$('#reply').val("");
		}				
	});	
}

function comModify(ID, TYPE, COMIDX, index, MODDEL){
	
	let comModifyUrl ="";
	if(MODDEL == 'MOD') {
		if(TYPE == 'SHO') {
			comModifyUrl = "/SC/shortComModify.cut";	
		} else if(TYPE == 'FRE') {
			comModifyUrl = "/SC/freeComModify.cut";	
		} else if(TYPE == 'INF') {
			comModifyUrl = "/SC/infoComModify.cut";
		} else if(TYPE == 'REP') {
			comModifyUrl = "/SC/reportComModify.cut";
		}
	} else {
		if(TYPE == 'SHO') {
			comModifyUrl = "/SC/shortComDelete.cut";	
		} else if(TYPE == 'FRE') {
			comModifyUrl = "/SC/freeComDelete.cut";	
		} else if(TYPE == 'INF') {
			comModifyUrl = "/SC/infoComDelete.cut";
		} else if(TYPE == 'REP') {
			comModifyUrl = "/SC/reportComDelete.cut";
		}
	}
	
	if(ID != '비회원') { // 로그인 회원이 댓글을 수정할 때
		comModifyProcess(comModifyUrl, COMIDX, index, MODDEL);
	} else {
		/* 비밀번호 입력 */
		swal({
			title				: '비밀번호 입력',
			dangerMode			: true, // 확인 버튼 빨갛게
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			content				: {
				element : 'input',
				attributes : {
					placeholder : '비밀번호를 입력해주세요.',
					type : 'password'					
				}
			},
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: false,
					className 	: 'btn btn-outline-primary' 
				}
			}
		}).then((result) => { // result : 입력한 비밀번호
		
			if(result == null || result == "") {
				swal({
					title				: "비밀번호를 입력해주세요.",
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
				/* 비밀번호가 일치하는지 검사 */
				let passUrl ="";
				if(TYPE == 'SHO') {	
					passUrl = "/SC/shortComPassword.cut";	
				} else if(TYPE == 'FRE') {
					passUrl = "/SC/freeComPassword.cut";		
				} else if(TYPE == 'INF') {
					passUrl = "/SC/infoComPassword.cut";	
				} else if(TYPE == 'REP') {
					passUrl = "/SC/reportComPassword.cut";	
				}	
				$.ajax({
					url 		: passUrl,
					data		: {"COMIDX" : COMIDX},
					contentType	: "application/json",
					success		: function(data) {
						if(data == result) { // 비밀번호가 일치 수정 프로세스
							comModifyProcess(comModifyUrl, COMIDX, index, MODDEL);		
						} else { // 비밀번호가 일치하지 않음
							swal({
								title				: "비밀번호가 일치하지 않습니다.",
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
					}
				});	
			}
		});
	}
}

function comModifyProcess(comModifyUrl, COMIDX, index, MODDEL) {
	if(MODDEL == 'MOD') {
		swal({
			title				: '댓글 수정',
			dangerMode			: true, // 확인 버튼 빨갛게
			closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게
			content				: {
				element : 'input',
				attributes : {
					placeholder : '댓글을 입력해주세요.',
					type : 'text'					
				}
			},
			buttons				: {
				confirm : {
					text 		: '확인',
					value 		: false,
					className 	: 'btn btn-outline-primary' 
				}
			}
		}).then((result) => { // result : 입력한 댓글
			if(result.trim() == ""){
				swal({
					title				: "댓글을 입력해주세요",
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
				$.ajax({
					url 		: comModifyUrl,
					data		: {"COMIDX" : COMIDX, "CONTENT" : result},
					contentType	: "application/json",
					success		: function(data) {
						// 수정 후 내용 반영
						$('#comCONTENT' + index).html(result);
					},
				});				
			}
	
		});
	} else {
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
				/* 뎃글 삭제 */
				$.ajax({
					url 		: comModifyUrl,
					data		: {"COMIDX" : COMIDX},
					contentType	: "application/json",
					success		: function(data) {
	
						/* 삭제 후 게시판 제목 내용 삭제함으로 변경 */
						$('#comID' + index).html("삭제");
						$('#comCONTENT' + index).html("삭제된 댓글입니다.");
					}	
				});
			}
		});
	}
}

/* 대댓글 클릭시 숨겨진 입력폼 보이기 */
function openComWriteForm(index) {
	$('#reForm'+index).css("display", "block");
}	