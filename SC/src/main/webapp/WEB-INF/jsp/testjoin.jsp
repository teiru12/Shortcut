<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숏컷</title>
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<script>
	  window.onload = function(){
		  $('#modalBox').modal('show');
	  }
</script>
<body>
<!-- 모달 영역 -->
<div id="modalBox" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <!-- <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">ShortCut</h4>
      </div> -->
      <div class="modal-body">
        이메일 인증이 완료되었습니다.
      </div>
      <div class="modal-footer">
		<button type="button" class="btn btn-primary" id="confirmModalBtn">확인</button>
      </div>
    </div>
  </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<script type="text/javascript">
  $('#confirmModalBtn').on('click', function(){
    $('#modalBox').modal('hide');
    location.href="http://localhost:9003/SC/main.cut";
  });
</script>
</body>
</html>