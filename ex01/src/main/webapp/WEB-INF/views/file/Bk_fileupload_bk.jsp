<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileupload.jsp</title>
</head>
<body>
	<h3>파일 업로로로로드으으</h3>
	메세지 : ${msg}
	<br>
	<form action="/file/fileupload" method="post"
		enctype="multipart/form-data">
		<b>파일 선택</b><br>
		<input type="file" name="files">
		<input type="file" name="files">
		<input type="file" name="files">
		<br><br><br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>