<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="/resources/css/style.css"> -->
<title>view.jsp</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<main class="container">
	<div class="bg-light p-5 rounded">
	<h2>📚 도서 상세 보기 📚  </h2>
		<form name="searchForm">
		<!-- <form name="postForm"> -->
		<input type="text" name="bno" value="${book.bno}">
		<div class="row">
		  <div class="col input-group mb-3">
			 <span class="input-group-text" id="basic-addon1">등록일</span>
		    <input type="text" class="form-control" value="${book.regdate}" readonly="readonly" placeholder="First name" aria-label="First name">
		  </div>
		  <div class="col input-group mb-3">
			 <span class="input-group-text" id="basic-addon1">수정일</span>
		    <input type="text" class="form-control" value="${book.updatedate}" readonly="readonly" placeholder="First name" aria-label="First name">
		  </div>
		</div>
		<div class="row">
		<div class="col">
		  <div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">제목</span>
			  <input type="text" name="title" value="${book.title}" class="form-control" readonly="readonly" aria-label="title" aria-describedby="basic-addon1">
		  </div>
		  <div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1">작가</span>
			  <input type="text" name="writer" value="${book.writer}" class="form-control" readonly="readonly" aria-label="writer" aria-describedby="basic-addon1">
		  </div>
		  <div class="input-group">
			  <span class="input-group-text">내용</span>
			  <textarea class="form-control" name="content" rows="10" style="resize: none;" aria-label="With textarea" readonly="readonly">${book.content}</textarea>
		  </div>
		</div>
		<div class="col row g-3  justify-content-center ">
		<div class="col-auto ">
			<img alt="${ book.title } 이미지" width="200px" src="/resources/images/파일명.png">
		</div>
		</div>
		</div>
		  <br>
		  <button type="button" class="btn btn-dark" onclick="requestAction('./edit', ${book.bno});">글 수정하기</button>
		  <button type="button" class="btn btn-dark" onclick="requestAction('./delete', ${book.bno});">글 삭제하기</button>
		  <!-- <button type="button" class="btn btn-dark" onclick="location.href='./delete'">글 삭제하기</button> -->
		  <button type="button" class="btn btn-dark" onclick="location.href='./list?pageNo=${param.pageNo}'">목록 바로가기</button>
		  <input type="text" name="pageNo" value="${param.pageNo}">
		</form>
		<br>
		  <button type="button" class="btn btn-dark" onclick="location.href='./view?bno=${book.bno -1}'">이전 글</button>
		  <button type="button" class="btn btn-dark" onclick="location.href='./view?bno=${book.bno +1}'">다음 글</button>
	</div>
	</main>
</body>
</html>