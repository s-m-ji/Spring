<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	안녕 세상아 ~~~ 🤗🤗
	<br>
	<a href="./requestMapping">헬로 월드야 ~~~ 🤗🤗</a>
	<br>
	<a href="./getMapping?name=냥냥&age=123">냥냥 월드야 ~~~ 🤗🤗</a>
	<br>
	<!-- 컨트롤러에서 Model 객체를 사용하지 않을거라면 EL표현식에서 param.을 붙여줘야함 -->
	<%-- name : ${param.name} & age : ${param.age} --%>	
	name : ${name } & age : ${age }	
	<br>
	<a href="./getMappingVO?name=뇽뇽&age=456">멤버 월드야 ~~~ 🤗🤗</a>
	<br>
	member.name : ${member.name } & member.age : ${member.age }	
	<br>
	<a href="./getMappingMSG">메세지 월드야 ~~~ 🤗🤗</a>
	<br>
	메세지 출력 msg : ${msg }
	<br>
	<a href="./getMappingArr?ids=1&ids=2&ids=3">배열 월드야 ~~~ 🤗🤗</a>
	<br>
	배열 출력 ids : ${ids }
	<!-- param.을 붙였을 때 화면에 출력되는 것을 보면 request 영역이 공유됨을 알 수 있음 -->
	<%-- 배열 출력 ids : ${param.ids } --%>
	<br>
	<a href="./getMappingList?ids=4&ids=5&ids=6">리스트 월드야 ~~~ 🤗🤗</a>
	<br>
	리스트 출력 ids : ${ids }
	<br>
	<a href="./getMappingVO?dueDate=2023/07/03">날짜 월드야 ~~~ 🤗🤗</a>
	<br>
	날짜 출력 member.dueDate : ${member.dueDate }
	
	<h3>객체 리스트를 파라미터로 전달해보장</h3>
	<pre> 파라미터 전달 방법 : 
	list[0].name=mimi&list[0].age=123&list[1].name=admin&list[1].age=456
		<!-- list[0].name=mimi&list[0].age=123&
			이런 식으로 값을 가져오는건 url 표현 가능한 방식에 맞지 않아서 안되야
			*대괄호 등등 일부 특수기호를 못 씀 !
			↓ 그래서 스크립트로 url 문자를 인코딩해서 사용
		 -->
	</pre>
	<script>
	function voList(){
		let url = "./getMappingMemberList" + 
		"?list[0].name=mimi&list[0].age=123&list[1].name=admin&list[1].age=456"
			url = encodeURI(url);
			console.log("url : " + url);
			alert("url : " + url);
			location.href = url;
	}
	</script>
	<br>
	<a href="#" onclick="voList();"> 멤버리스트 월드야 ~~~ 🤗🤗</a>
	<br>
	멤버리스트  출력 list : ${list }
	<br>
	<form action="/ex/mapping/requestMapping" method="post">
		<input type="submit" value="🤗🤗">
	</form>
</body>
</html>