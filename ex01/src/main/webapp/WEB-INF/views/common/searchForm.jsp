<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchForm.jsp</title>
</head>
<body>
<%-- pDto.cri.sWord : ${pDto.cri.sWord } 이렇게 클래스 내에 선언한 필드명 그대로 소문자 s라고 썼더니 안 나옴 그런 속성을 찾을 수 없다고함... 엉엉 --%>
	<form action="./list" name="searchForm">
		<div class="row g-3 justify-content-center">
		  <div class="col-auto">
		    <select name="sField" class="form-select" aria-label="Default select example">
			  <option value="title" ${pDto.cri.SField eq 'title'? 'selected' : '' }>제목</option>
			  <option value="content" ${pDto.cri.SField eq 'content'? 'selected' : '' }>내용</option>
			  <option value="writer" ${pDto.cri.SField eq 'writer'? 'selected' : '' }>작성자</option>
			</select>
		  </div>
		  <div class="col-sm-6">
		    <input type="text" name="sWord" class="form-control" value="${pDto.cri.SWord}" placeholder="검색어">
		  </div>
		  <div class="col-auto">
		    <button type="submit" class="btn btn-primary mb-3" onclick="go(1);">검색</button>
			<input type="text" name="pageNo" value="${pDto.cri.pageNo}">
			<input type="text" name="bno" value="">
			<!-- <input type="text" name="pageNo" value="1"> -->
		  </div>
		 </div>
	</form>
</body>
</html>