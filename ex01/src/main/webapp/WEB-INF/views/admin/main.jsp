<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<br><br><br>
	<h3>&nbsp; 눈 떠보니 이 세계에선 내가 관리자 ?!?!?</h3>
	<br>
	&nbsp; 아이디 : ${userId } <br>
	&nbsp; 권한    : ${member.role }
</body>
</html>