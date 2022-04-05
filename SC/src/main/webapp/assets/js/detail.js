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
			location.href = "/SC/freeModifyForm.cut";	
		} else if(TYPE == 'INF') {
			location.href = "/SC/infoModifyForm.cut";
		} else if(TYPE == 'NEW') {
			location.href = "/SC/newsModifyForm.cut";
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
							location.href = "/SC/freeModifyForm.cut";	
						} else if(TYPE == 'INF') {
							location.href = "/SC/infoModifyForm.cut";
						} else if(TYPE == 'NEW') {
							location.href = "/SC/newsModifyForm.cut";
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
						}
					});
				}	
			});
		}
	});
} 