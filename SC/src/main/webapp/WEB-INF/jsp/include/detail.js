function addGood(userName, boardType, boardIdx) {
	
	/* 해당 게시판의 게시글에 대한 user의 좋아요나 싫어요가 있는지 Goodbad 테이블에서검색 */
	$.ajax({
		url 		: "/SC/selectGoodbad.cut",
		data		: {"ID" : userName, "TYPE" : boardType, "IDX" : boardIdx},
		contentType	: "application/json",
		success		: function(data) {
			if(data.used) {
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
					data		: {"IDX" : IDX},
					contentType	: "application/json",
					success		: function(data) {
						
						
						

					}				
				});				 
				
			
				// Goodbad 테이블에 좋아요 기록 추가				
			}
		}				
	});
}