<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">
	<link href="../resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
	<link href="/resources/css/signin.css" rel="stylesheet">

    <script src="/resources/js/common.js"></script>
    <script>
   	window.addEventListener('load', function(){
   		btnLogin.addEventListener('click', function(e){
   			e.preventDefault();
   			
   			let obj = {
   				id : document.querySelector('#logInputId').value
   				, pass : document.querySelector('#logInputPass').value
   			}
   			
   			console.log('obj', obj);
   			
   			fetchPost('loginAction', obj, loginCheck);
   		});
   		
   		// 회원가입, 로그인 보기 버튼
   		btnLoginView.addEventListener('click', function(e){
   			loginForm.style.display = "";
   			RegiForm.style.display = "none";
   		});
   		
   		btnRegiView.addEventListener('click', function(e){
   			loginForm.style.display = "none";
   			RegiForm.style.display = "";
   		});
   	
   	});
   	
   	function loginCheck(map){
   		console.log('map', map);
		// 로그인 성공 -> list로 이동
		// 실패 -> 메세지 처리
   		if (map.result == "success"){
   			// location.replace("/board/list");
   			location.href="/board/list";
   		} else {
   			getMessage.innerText = map.msg;
   		}
   	}
    </script>
  </head>
  <body class="text-center">
	<main class="form-signin w-100 m-auto">
	  <!-- 로그인 실패 시 메세지 처리할 공간 --><div id="getMessage"></div> 
		<form method="post" action="loginAction" id="loginForm">
			<img class="kero mb-4" alt="로고이미지" src="/resources/images/파일명.png">
		    <div class="form-floating">
		      <input type="text" class="form-control input" id="logInputId" >
		      <label for="floatingInput">Id</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control endInput" id="logInputPass" >
		      <label for="floatingPassword">Password</label>
		    </div>
		    <div class="checkbox mb-3">
		      <label> <input type="checkbox" value="remember-me"> 7l 억 ㅎH 줘 ... ☆ </label>
		    </div>
		    <button class="w-100 btn btn-lg btn-dark" id="btnLogin" type="submit">로그인</button>
	  </form>
	  
      <form id="RegiForm" style="display:none;">
			<img class="kero mb-4" alt="로고이미지" src="/resources/images/파일명.png">
		    <div class="form-floating">
		      <input type="text" class="form-control input" id="regiInputId" >
		      <label for="id">New Id</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control input" id="regiInputPass">
		      <label for="pass">New Password</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control endInput" id="regiInputPassChk">
		      <label for="pass">New Password Check</label>
		    </div>
		    <button class="w-100 btn btn-lg btn-dark" type="submit">회원가입</button>
	  </form>
	  
		<br>
	  <div class="btn-group w-100" role="group" aria-label="Basic mixed styles example">
		  <button type="button" class="btn btn-danger" id="btnRegiView">회원가입 하기</button>
		  <button type="button" class="btn btn-warning" id="btnLoginView">로그인 하기</button>
	</div>
	</main>
  </body>
</html>