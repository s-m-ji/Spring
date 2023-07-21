<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script src='/resources/js/reply.js'></script>
<script src='/resources/js/file.js'></script>
<script type="text/javascript">
	// window.onload = function(){  // 댓글 파일인 test.jsp 안에 적어둔 window.onload와 중복되면서 기능이 실행되지않음

	/* 
	스크립트로 액션을 제어하면 좋은 점: 버튼 클릭 액션을 한눈에 확인할 수도 있고, 파라미터 값을 넘겨야할 경우에도 href에 쿼리스트링을 길게 늘여쓰는 것보다는 form에 넣어서 전송하면 깔끔함
	 */
	window.addEventListener('load', function() {
		
		if(${userId eq book.writer}){
			
			// 수정 페이지로 이동
			btnEditView.addEventListener('click', function() {
				postForm.action = './edit';
				postForm.submit();
	
			});
	
			// 삭제 처리 
			btnDelView.addEventListener('click', function() {
				alert('진짜 삭제? 리얼리? 댓글과 파일이 모두 날아가도 괜찮나요 ?');
				postForm.action = './delete';
				postForm.submit();
			});
			
		}


		// 목록 페이지로 이동
		btnListView.addEventListener('click', function() {
			postForm.action = './list';
			postForm.submit();
		});

		// 댓글 등록 버튼
		btnReplyWrite.addEventListener('click', function() {
			replyWrite();
		});

		let bno = parseInt(document.querySelector('#bno').value);
		// 이전 글 이동 버튼
		btnPrevPost.addEventListener('click', function() {
			if (bno > 1) {
				document.querySelector('#bno').value = --bno;
				postForm.submit();
			}
		});

		// 다음 글 이동 버튼
		btnNextPost.addEventListener('click', function() {
			document.querySelector('#bno').value = ++bno;
			postForm.submit();
		});

		// 댓글 조회 및 출력
		getReplyList();
		
		// 파일 출력
		getFileList();

		//}
		var adminChkBox = document.getElementById('adminChkBox');
		adminChkBox.addEventListener('click', toggleCheckboxes);
		

		// document.querySelector('.bi-x-square-fill').style.display= "none";
	});
</script>
<style type="text/css">
/* .bi-x-square-fill {display: none;} */
</style>

<title>view.jsp</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<main class="container">
	<div class="bg-light p-5 rounded">
		<h2>📚 도서 상세 보기 📚</h2>
		<form name="postForm">
			<!-- 상세/수정 화면에서 다시 목록으로 돌아갈 때  필요한 값들을 input 태그로 form에 넣어두었음-->
			<input type="text" name="bno" id="bno" value="${param.bno}">
			<input type="text" name="pageNo" value="${param.pageNo}"> <input
				type="text" name="sField" value="${param.sField}"> <input
				type="text" name="sWord" value="${param.sWord}">
			<!--  다중 삭제 시 필요함 -->
			<input id="delNoInput" type="hidden" name="bno" value=""> <br>
			<div class="row">
				<div class="col input-group mb-3">
					<span class="input-group-text" id="basic-addon1">등록일</span> <input
						type="text" class="form-control" value="${book.regdate}"
						readonly="readonly" placeholder="First name"
						aria-label="First name">
				</div>
				<div class="col input-group mb-3">
					<span class="input-group-text" id="basic-addon1">수정일</span> <input
						type="text" class="form-control" value="${book.updatedate}"
						readonly="readonly" placeholder="First name"
						aria-label="First name">
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">제목</span> <input
							type="text" name="title" value="${book.title}"
							class="form-control" readonly="readonly" aria-label="title"
							aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">내용</span>
						<textarea class="form-control" name="content" rows="10"
							style="resize: none;" aria-label="With textarea"
							readonly="readonly">${book.content}</textarea>
					</div>
				</div>
				<!-- 등록 화면일 때 -->
				  <c:if test="${not empty book.writer}">
				  	<div class="input-group mb-3">
					  <span class="input-group-text" id="basic-addon1">작성자</span>
					  <input type="text" name="userWriter" value="${userId}" class="form-control">
				  </div>
				  </c:if>
				
				<div class="input-group mb-3 ">
					<span class="input-group-text" id="basic-addon1">등록된 파일</span>
					<div id="fileDiv" class="form-control">!!!!! 파일 !!!!!</div>
				</div>
			</div>
			<br>
		</form>
		<!-- userId == book.writer 그럴 때만 수정/삭제 버튼 노출되도록 수정 -->
		<c:if test="${userId eq book.writer  }">
			<%-- <button type="button" class="btn btn-dark" onclick="requestAction('./edit', ${book.bno});">글 수정하기</button> --%>
			<button type="button" id="btnEditView"
				class="btn btn-default btn-dark">수정하기</button>
			<%-- <button type="button" class="btn btn-dark" onclick="requestAction('./delete', ${book.bno});">글 삭제하기</button> --%>
			<button type="button" id="btnDelView" class="btn btn-default btn-dark">삭제하기</button>
		</c:if>
		<%-- <button type="button" class="btn btn-dark" onclick="location.href='./list?pageNo=${param.pageNo}'">목록 바로가기</button> --%>
		<button type="button" id="btnListView" class="btn btn-dark">목록 바로가기</button>
		<br> <br>
		<button type="button" class="btn btn-dark" id="btnPrevPost">이전글</button>
		<button type="button" class="btn btn-dark" id="btnNextPost">다음글</button>
	</div>

	<!-- 댓글 목록 -->
	<div class="btn-group" role="group"
		aria-label="Basic mixed styles example">
		<label class="btn btn-danger" for="adminChkBox">✅ 전체 선택</label> <input
			type="checkbox" id="adminChkBox" value="" style="display: none;">
		<button type="button" class="btn btn-warning" onclick="deleteReply();">❌
			일괄 삭제</button>
	</div>
	<br>
	<br>
	<div class="input-group">
		<span class="input-group-text">댓글 작성</span> <input type="text"
			id="reply" placeholder="내용" class="form-control"> <input
			type="text" id="replyer" value="${userId}" class="form-control">
		<input type="button" aria-label="Last name" id="btnReplyWrite"
			class="input-group-text" value="완료">
	</div>
	<br>
	<p>총 댓글 수 : ${book.replycnt}</p>
	<div id="replyDiv" class="${userId}"></div>
	<br>
	<br>
	<br>
	</main>

</body>
</html>