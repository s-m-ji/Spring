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
	<div class="text-center">
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    <li class="page-item ${pDto.prev? '' : 'disabled'}">
	      <a class="page-link" onclick="go(1)" href="#"><i class="fa-solid fa-circle-left"></i></a>
	    </li>
	    <li class="page-item" >
	      <a class="page-link" <c:if test="${pDto.prev}"> onclick="go(${pDto.startNo - 1})"</c:if>  href="#"><i class="fa-regular fa-circle-left"></i></a>
	    </li>
	     <c:forEach begin="${pDto.startNo}" end="${pDto.endNo}" step="1" var="i">
   		 <li class="page-item ${i eq pDto.cri.pageNo ? 'active' : ''}">
   		 	<a class="page-link ${i eq pDto.cri.pageNo ? 'active' : ''}" onclick="go(${i})" href="#"><c:out value="${i}"></c:out></a>
   		 </li>
	    </c:forEach>
	    <li class="page-item ${pDto.next? '' : 'disabled'}">
	      <a class="page-link" <c:if test="${pDto.next}"> onclick="go(${pDto.endNo + 1})"</c:if> href="#"><i class="fa-regular fa-circle-right"></i></a>
	    </li>
	    <li class="page-item" >
	      <a class="page-link" onclick="go(${pDto.realEndNo})" href="#"><i class="fa-solid fa-circle-right"></i></a>
	    </li>
	  </ul>
	</nav>
	</div>
</body>
</html>