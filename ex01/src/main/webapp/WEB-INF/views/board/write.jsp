<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='/resources/js/file.js'></script>
<title>write.jsp</title>
<script>
	function validateForm(form){
		if(form.title.value == ''){
			alert("⚠ 제목을 입력해줭 ⚠"); 
			form.title.focus();
			return false;
		} 
		if (form.content.value == "") {
            alert("⚠ 내용을 입력해줭 ⚠");
            form.content.focus();
            return false;
        }
	}
	
	window.addEventListener('load', function(){
		
		btnList.addEventListener('click',  function(){
			searchForm.action='./list';
			searchForm.method='get';
			searchForm.submit();
		});
		
		
		// 원래 글 이동 버튼
		btnOrignPost.addEventListener('click', function() {
			searchForm.action='./view';
			searchForm.method='get';
			searchForm.submit();
		});
		
		function getFileList(){
			let bno = '${book.bno}'; 
			console.log('비엔오 ~ ' + bno);
			
			if(bno){ // bno 값이 있을 때만 글쓰기/수정 화면에서 파일 목록이 출력될 수 있도록 함
				// 사실 이 부분은 내가 아래 jps에서 < c : if 코드 안에 넣어두어서 이중으로 처리하는 것 같긴 함..!
				
			fetch('/file/list/' + bno)
				.then(response => response.json())
				.then(map => viewFileList(map));
			}
		}
		
		// 파일 출력
		getFileList();

	});
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<main class="container">
	<div class="bg-light p-5 rounded">
	<!-- c:if를 이용해서 bno가 0이면 등록 /아니면 수정의 형태로 작성 -->
	<c:if test="${ empty param.bno }" var="check">
<%-- 	<c:if test="${ empty book.bno }" var="check"> --%>
		<h2>📚 도서 등록 하기 📚  </h2>
	</c:if>
	<c:if test="${ not check }">
		<h2>📚 도서 수정 하기 📚  </h2>
	</c:if>
		<form name="searchForm" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this);">
		<!-- <form name="postForm" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this);"> -->
		  <!-- 페이지, 검색 정보를 유지하기 위한 input : 추후 hidden처리 필요 -->
			
			<%-- <input type="text" name="pageNo" value="${param.pageNo == ''? '1' : param.pageNo}"> --%>
			<input type="text" name="pageNo" value="<c:if test='${ empty param.pageNo }' var='pNo'>1</c:if><c:if test='${ not pNo}'>${param.pageNo}</c:if>"> 
			<input type="text" name="sField" value="${param.sField}">
			<input type="text" name="sWord" value="${param.sWord}">
		  <div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">제목</span>
			  <input type="text" name="title" value="${book.title}" class="form-control" aria-label="title" aria-describedby="basic-addon1">
		  </div>
		  <div class="input-group">
			  <span class="input-group-text">내용</span>
			  <textarea class="form-control" name="content" rows="10" style="resize: none;" aria-label="With textarea">${book.content}</textarea>
		  </div>
		  <br>
		  <div class="input-group">
			  <input class="form-control" type="file" name="files" id="formFile" multiple="multiple">
			  <!-- 여기서 name="files"이라고 하지않으면 컨트롤러에서 자동 수집을 못 함 ! 유의 -->
		  </div>
		  <br>
		  <!-- 등록 화면일 때 -->
		  <c:if test="${not empty book.writer}">
		  	<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">작성자</span>
			  <input type="text" name="userWriter" value="${userId}" class="form-control">
		  </div>
		  </c:if>
		  <c:if test="${ empty book.bno }" var="check">
		  	<!-- <button type="submit" class="btn btn-dark" >작성 완료</button> -->
			<button type="submit" class="btn btn-dark" onclick="requestAction('./write', ${book.bno});">작성 완료</button>
		  </c:if>
		  <c:if test="${ not check }">
		  
		  <div class="input-group mb-3 ">
					<span class="input-group-text" id="basic-addon1">등록된 파일</span>
					<div id="fileDiv" class="form-control">!!!!! 파일 !!!!!</div>
				</div>
		  <br>
		  	<button type="button" class="btn btn-dark" id="btnOrignPost">원래 글로 이동</button>
		  	<input type="text" name="bno" id="bno" value="${book.bno}">
		  	<button type="submit" class="btn btn-dark" onclick="requestAction('./edit', ${book.bno});">수정 완료</button>
		  </c:if>
		  <button type="reset" class="btn btn-dark">취소</button>
		  <button type="button" class="btn btn-dark" id="btnList" onclick="">목록 바로가기</button>
<%-- 		  <button type="button" class="btn btn-dark" onclick="location.href='./list?pageNo=${pDto.cri.pageNo}'">목록 바로가기</button> --%>
		</form>
	</div>
	</main>
</body>
</html>