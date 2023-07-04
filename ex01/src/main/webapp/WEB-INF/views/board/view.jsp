<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/style.css">
<script src='../js/custom.js'></script>
<script type="text/javascript">
	function setAction(action){
		 // viewForm.action="../book/rent";
		 // searchForm.submit();
		viewForm.action=action;
	}
</script>
</head>
<body>
<h2>📚 ${book.bno }번 상세보기 📚  </h2>
<form name="viewForm" method="post">
<%-- 대여번호 : <input type="text" name="rent_no" value="${book.rent_no }">
도서번호 : <input type="text" name="no" value="${book.no }"> --%>
<table border="1">
    <colgroup>
        <col width="30%"/> 
        <col width="15%"/> <col width="20%"/>
        <col width="15%"/> <col width="20%"/>
    </colgroup>

    <!-- 게시글 정보 -->
    <tr>
        <td rowspan="3">
        	
        	<%-- <img alt="${ book.title } 이미지" width="200px" src="../images/bookimg/${book.sfile}"> --%>
        </td>
        <th>도서명</th><td>${ book.title }</td>
        <th>작가</th> <td>${ book.writer }</td>
    	<th>내용</th>	<td>${ book.content }</td> 
    </tr>
    <%-- <tr>
        <td>대여</td> 
        
        	<c:choose>
        		
        		<c:when test="${empty book.rent_no}">
	        		<td colspan="5"><button onclick="setAction('./rent')">대여하기</button></td>
        		</c:when>
        		<c:when test="${book.id eq sessionScope.userId }"> 
        			<td><button onclick="setAction('./return')">반납하기</button></td>
        			<td>대여기간</td> <td  colspan="4">${ book.rent_date } ~ ${ book.return_exp_date }</td>
        		</c:when>
        		<c:otherwise>
        			<td>대여중</td>
        			<td>대여기간</td> <td  colspan="4">${ book.rent_date } ~ ${ book.return_exp_date }</td>
        		</c:otherwise>
        	</c:choose>
    </tr> --%>
    <tr>
    	<td height="200px">상세설명</td><td colspan="5"></td>
    </tr>
    <!-- 하단 메뉴(버튼) -->
    <tr>
        <td colspan="7" align="center">
            <button type="button" onclick="location.href='./edit?bno=${book.bno}';">
                수정하기
            </button>
            <button type="button" onclick="location.href='./delete?delNo=${ book.bno }';">
            	삭제하기
            </button>
            <button type="button" onclick="location.href='./list';">
                목록 바로가기
            </button>
           	<c:if test=""></c:if>
        </td>
    </tr>
    <tr>
        <td colspan="7" align="center">
            <button type="button" onclick="location.href='./view?bno=${book.bno-1}';">
                이전글
            </button>
            <button type="button" onclick="location.href='./view?bno=${book.bno+1}';">
            	다음글
            </button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
