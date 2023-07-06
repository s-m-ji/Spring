<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageNavi.jsp</title>
</head>
<body>
	<br>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    <li class="page-item ${pDto.prev? '' : 'disabled'}">
	      <a class="page-link" onclick="go(${pDto.startNo - 1})" href="#"><i class="bi bi-arrow-left-square-fill"></i></a>
	    </li>
	     <c:forEach begin="${pDto.startNo}" end="${pDto.endNo}" step="1" var="i">
   		 <li class="page-item"><a class="page-link ${i eq pDto.cri.pageNo ? 'active' : ''}" onclick="go(${i})" href="#">${i}</a></li>
	    </c:forEach>
	    <li class="page-item ${pDto.next? '' : 'disabled'}">
	      <a class="page-link" onclick="go(${pDto.endNo + 1})" href="#"><i class="bi bi-arrow-right-square-fill"></i></a>
	    </li>
	  </ul>
	</nav>
</body>
</html>