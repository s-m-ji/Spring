<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<script>
	//window 로딩되고 나면 버튼 클릭 이벤트 실행 가능 
	window.addEventListener('load', function(){
		 var adminChkBox = document.getElementById('adminChkBox');
		 adminChkBox.addEventListener('click', toggleCheckboxes);
	 });
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
    <main class="container">
      <div class="bg-light p-5 rounded">
        <h1>📚 목록 보기 📚 </h1>
        <p class="lead"></p>
        <a class="btn btn-sm btn-dark" href="./write" role="button" target="_blank">글쓰기</a>
<%--         <a class="btn btn-sm btn-dark" href="#" onclick="requestAction('./write', ${pDto.cri.pageNo});" role="button" target="_blank">글쓰기</a> --%>
        <a class="btn btn-sm btn-dark" href="#" role="button" onclick="deleteBook();">선택한 글 삭제하기</a> 
      </div>
      
		<form id="deleteForm" action="./delete">
		    <input id="delNoInput" type="hidden" name="bno" value="">
		</form>

	<%@ include file="../common/searchForm.jsp" %>
      <div class="list-group w-auto">
      <c:choose>
			<c:when test="${empty list}">
			<div class="d-flex gap-2 w-100 justify-content-between">
		       <div><p>🤷‍♀ 등록된 게시글이 없는걸 🤷‍♀️</p></div>
		    </div>
			</c:when>
			<c:otherwise>
			 <div>
			 	✅ 전체 선택
              	<input type="checkbox" id="adminChkBox" value="">
            </div>
				<c:forEach items="${list}" var="book">
		        <a
		          <%-- href="./view?bno=${book.bno}&pageNo=${pDto.cri.pageNo}" --%>
		          href="#"
		          onclick="requestAction('./view', ${book.bno})"
		          class="list-group-item list-group-item-action d-flex gap-3 py-3"
		          aria-current="true"
		        >
		         
		          <div class="d-flex gap-2 w-100 justify-content-between">
		            <div><input type="checkbox" class="chkBox" name="bno" value="${book.bno}"></div>
		            <div><p class="mb-0 opacity-75">${book.bno}</p></div>
		            <div><h6 class="mb-0">${book.title}</h6></div>
		            <div><p class="mb-0 opacity-75">${book.content}</p></div>
		            <div><p class="mb-0 opacity-75">${book.writer}</p></div>
		            <small class="opacity-50 text-nowrap">${book.regdate}</small>
		            <small class="opacity-50 text-nowrap">${book.updatedate}</small>
		          </div>
		        </a>
        		</c:forEach>
			</c:otherwise>
		</c:choose>
      </div>
      <%@ include file="../common/pageNavi.jsp" %>
  </body>
</html>
