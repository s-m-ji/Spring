<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// 파라메터 설정
	//조회일자
	String targetDt = request.getParameter ("targetDt")==null?"20120101":request.getParameter ("targetDt"); // 결과 수
	String itemPerPage = request.getParameter ("itemPerPage")==null?"10":request.getParameter("itemPerPage");
	
	String multiMovieYn = request.getParameter ("itemPerPage")-null?"10":request.getParameter("itemPerPage");
	request.getParameter("multiMovieYn") == null?"":request.getParameter("multiMovieYn");
	//K:: 한국영화 외국영화 (default: 전체)
	String repNationCd = request.getParameter ("repNationCd")==null?"":request.getParameter ("repNationCd"); //-0105000000~ 로서 조회된 지역코드
	String wideAreaCd = request.getParameter ("wideAreaCd") ==null?"":request.getParameter ("wideAreaCd"); // 발급키
	String key="430156241533f1d058c603178cc3ca0e";
	// KOBIS 오픈 API Rest Client를 통해 호출
	KobisOpenAPIRestService service new KobisOpenAPIRestService (key);
	// ARA HUA (boolean isJson, String targetDt
	//
	String dailyResponse
	, String itemPerPage, String multiMovieYn, String repNationCd, String wideAreaCd) service.getDailyBoxOffice (true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd)
	// Jeon 라이브러리를 통해 Handling
	ObjectMapper mapper = new ObjectMapper();
	HashMap<String, Object> dailyResult = mapper.readValue (dailyResponse, HashMap.class);
	request.setAttribute("dailyResult", dailyResult);
	// KOBIS API Rest Client 88H ABIA
	(boolean isJson, String comCode)
	String codeResponse service.getComCodeList (true, "0105000000");
	HashMap<String, Object> codeResult mapper.readValue (codeResponse, HashMap.class); request.setAttribute("codeResult", codeResult);
	%>
	<table border="1">
		<tr>
		<td></td>
		<td></td>
		<td>>H</td>
		<td>OH</td>
		<td>H</td>
		<td>매출액증감(전일대비)</td>
		<td></td>
		<td>24</td>
		<td>관객수증감(전일대비)</td>
		<td></td>
		<td></td>
		<td></td>
		<c:if test="$(not empty dailyResult.boxofficeResult.dailyBoxOfficeList}">
		<c:forEach items="$(dailyResult.boxofficeResult.dailyBoxOfficeList)" var="boxoffice"> <tr>
		<td><c:out value="${boxoffice.rank }"/></td>
		<td><c:out value="$(boxoffice.movieNm }"/></td> <td><c:out value="${boxoffice.openDt }"/></td> <td><c:out value="$(boxoffice.salesAmt }"/></td>
		<td><c:out value="${boxoffice.salesShare }"/></td>
		<td><c:out value="$(boxoffice.salesInten }"/>/<c:out value="$(boxoffice.salesChange )"/></td> <td><c:out value="${boxoffice.salesAce }"/></td>
		<td><c:out value="$(boxoffice.audiCnt }"/></td>
		<td><c:out value="$(boxoffice.audiInten }"/>/<c:out value="$(boxoffice.audiChange }"/></td> <td><c:out value="$(boxoffice.audiAcc }"/></td> <td><c:out value="$(boxoffice.scrnCnt }"/></td>
		<td><c:out value="${boxoffice.showCnt }"/></td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</body>
</html>