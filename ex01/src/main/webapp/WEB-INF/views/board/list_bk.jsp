<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/style.css">
<title>Insert title here</title>
</head>
<body> 
<!-- 글쓰기 알림 출력 -->
<%-- message : ${message}
<c:if test="${not empty message}">
  <script>
    alert('${message}');
  </script>
</c:if> --%>
	<h2>📚 목록 보기 📚  </h2>
	<!-- 글쓰기 버튼 -->
	<table>
		<tr><td><button onclick="location=href='../board/write'">글쓰기</button></td></tr>
	</table>
	<!-- 게시글 목록 -->
	<table border="1" class="listTable">
		<tr>
			<th>선택</th>		
			<th>번호</th>		
			<th width="35%">제목</th>		
			<th>내용</th>		
			<th>작가</th>		
			<th>작성일</th>		
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr><td colspan="6">🤷‍♀ 등록된 게시글이 없는걸 🤷‍♀️</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="book">
					<tr>
						<!-- 삭제용 체크박스 -->
						<td><input type="checkbox" name="delNo" value="${book.bno}"></td>
						<td>${book.bno}</td>
						<td><a href="../view?bno=${book.bno}">${book.title}</a></td>
						<td>${book.content}</td>
						<td>${book.writer}</td>
						<td>${book.regdate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>