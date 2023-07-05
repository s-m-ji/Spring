<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="/resources/css/style.css"> -->
<title>write.jsp</title>
<script>
	function requestAction(url) {
		writeForm.action=url;
		writeForm.submit();
	}
	
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
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<main class="container">
	<div class="bg-light p-5 rounded">
	<!-- c:if를 이용해서 bno가 0이면 등록 /아니면 수정의 형태로 작성 -->
	<c:if test="${ empty book.bno }" var="check">
		<h2>📚 도서 등록 하기 📚  </h2>
	</c:if>
	<c:if test="${ not check }">
		<h2>📚 도서 수정 하기 📚  </h2>
	</c:if>
		<form name="writeForm" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this);">
		  <input type="text" name="bno" value="${book.bno}">
		  <div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">제목</span>
			  <input type="text" name="title" value="${book.title}" class="form-control" aria-label="title" aria-describedby="basic-addon1">
		  </div>
		  <div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">작가</span>
			  <input type="text" name="writer" value="${book.writer}" class="form-control" aria-label="writer" aria-describedby="basic-addon1">
		  </div>
		  <div class="input-group">
			  <span class="input-group-text">내용</span>
			  <textarea class="form-control" name="content" rows="10" style="resize: none;" aria-label="With textarea">${book.content}</textarea>
		  </div>
		  <br>
		  <div class="mb-3">
			  <input class="form-control" type="file" id="formFile">
		  </div>
		  <c:if test="${ empty book.bno }" var="check">
		  	<button type="button" class="btn btn-dark" onclick="requestAction('./write');">작성 완료</button>
		  </c:if>
		  <c:if test="${ not check }">
		  	<button type="button" class="btn btn-dark" onclick="location.href='./view?bno=${book.bno}'">원래 글로 이동</button>
		  	<button type="button" class="btn btn-dark" onclick="requestAction('./edit');">수정 완료</button>
		  </c:if>
		  <button type="reset" class="btn btn-dark">취소</button>
		  <button type="button" class="btn btn-dark" onclick="location.href='./list'">목록 바로가기</button>
		</form>
	</div>
	</main>
</body>
</html>