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
	// form.title.focus();
	
	function validateForm(form){
		if(form.name.value == ''){
			alert("⚠ 작성자를 입력해줭 ⚠"); 
			form.name.focus();
			return false;
		} 
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
		if (form.pass.value == "") {
            alert("⚠ 비밀번호를 입력해줭 ⚠");
            form.pass.focus();
            return false;
        }
	}
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<main class="container">
	<div class="bg-light p-5 rounded">
	<h2>📚 도서 수정 📚  </h2>
		<form action="./edit?bno=${book.bno}" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this);">
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
			  <textarea class="form-control" name="content" aria-label="With textarea">${book.content}</textarea>
		  </div>
		  <br>
		  <div class="mb-3">
			  <input class="form-control" type="file" id="formFile">
		  </div>
		  <button type="submit" class="btn btn-dark">수정 완료</button>
		  <button type="reset" class="btn btn-dark">취소</button>
		  <button type="button" class="btn btn-dark" onclick="location.href='./list'">목록 바로가기</button>
		</form>
	</div>
	</main>
</body>
</html>