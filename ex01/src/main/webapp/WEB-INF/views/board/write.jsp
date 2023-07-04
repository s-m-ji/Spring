<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/style.css">
<title>write.jsp</title>
<script>
	form.title.focus();
	
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
<!-- 헤더 -->
	<h2>📚 도서 등록 📚  </h2>
	<form action="../board/write" method="post" 
		enctype="multipart/form-data" onsubmit="return validateForm(this);">
		<table border="1" class="listTable writeTable">
			<%-- <tr>
				<th>ID</th><td><input type="text" name="id" size="50" value="${sessionScope.userId}" readonly="readonly"></td>
			</tr> --%>
			<tr>
				<th>도서명</th><td><input type="text" name="title" size="50" autofocus="autofocus" value="title"></td>
			</tr>
			<tr>
				<th>내용</th>	<td><input type="text" name="content" size="50" value="content"></td>
			</tr>	
			<tr>
				<th>작가명</th>	<td><input type="text" name="writer" size="50"  value="writer"></td>
			</tr>	
			<!-- <tr>
				<th>첨부 파일(책 이미지)</th>	<td><input type="file" name="book_img" multiple="multiple"></td>
			</tr> -->	
			<tr>
			<td colspan="2">
				<input type="submit" value="작성 완료">
				<input type="reset" value="RESET">
				<input type="button" value="목록 바로가기" onclick="location.href='../board/list'">
			</td></tr>	
		</table>
	</form>
</body>
</html>