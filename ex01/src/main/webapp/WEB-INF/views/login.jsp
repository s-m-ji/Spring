<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<style>
    	form { display: none; }
    	.on { display: block; }
    </style>
    <script src="/resources/js/common.js"></script>
    <script>
   	window.addEventListener('load', function(){
   		// 로그인 처리 버튼
   		btnLogin.addEventListener('click', function(e){
   			e.preventDefault(); // 원하는 로직을 다 처리하지 못하고 바로 form이 submit 되는 것을 방지하고자 함
   			let obj = {
   				id : logInputId.value
   				, pass : logInputPass.value
   			}
   			console.log('로그인 obj ', obj);
   			fetchPost('loginAction', obj, loginCheck);
   		});
   		
   		// 회원가입 처리 버튼
   		btnRegi.addEventListener('click', function(e){
   			e.preventDefault();
   			
			let id = regiInputId.value; 
			let pass = regiInputPass.value; 
			let name = regiInputName.value; 
   			
			if(!id){
   				getMessage2.innerHTML = '<b> id 값 입력~~~ </b>';
				return;
			}
			if(!pass){
   				getMessage2.innerHTML = '<b> pass 값 입력~~~ </b>';
				return;
			}
			if(!name){
   				getMessage2.innerHTML = '<b> name 값 입력~~~ </b>';
				return;
			}
			
			// 아이디 중복 체크 확인
			if(regiInputIdChk.value != '1'){
  				getMessage2.innerHTML = '<b>id 중복 체크를 안 했나봐아아요?</b>';
  				regiInputId.focus();
				return;
			}
			
			// 비밀번호 일치 확인
			if(regiInputIdChk2.value != '1'){
  				getMessage2.innerHTML = '<b>pw 일치하는지 다시 확인하면 좋겠다아아아</b>';
  				regiInputPassChk.focus();
				return;
			}

			let obj = {
   				id : id
   				, pass : pass
   				, name : name
   			}
   			console.log('회원가입 obj ', obj);
   			fetchPost('regiAction', obj, (map)=>{
   				if (map.result == "success"){
   					location.href="login?msg=" + map.msg;
   				} else {
   					getMessage2.innerHTML = '<b>' + map.msg + '</b>';
   				}
   			});
   		});
   		
   		// 회원가입 아이디 중복 체크
   		regiInputId.addEventListener('blur', function(e){ // blur는 focus가 out된 상태
   			// ※ 중복체크 버튼으로 하지 않는 이유는 체크 후에 id 값을 또 수정해서 입력 후 회원가입 버튼을 클릭할 수도 있기 때문...
   			// 입력 포커스를 나가는 순간 로직을 실행
   			let regiId = regiInputId.value;
   			if(!regiId){
   				getMessage2.innerHTML = '<b> id 값 입력은 해~야~~지~~~ </b>';
   				regiInputId.focus();
   				return;
   			}
   			//if(regiId != null && regiId != ""){
	   			let obj = {
	   	   				id : regiId
	   	   			}
   	   			console.log('obj', obj);
	   			// 콜백 함수를 분리하지않고, fetchPost 함수에 바로 넣기
   	   			fetchPost('idCheckAction', obj, (map) => {
	   	   	   		if (map.result == "success"){
	   	    			// id 사용 가능
	   	    			regiInputIdChk.value = "1";
	   	    			regiInputName.focus();
	   	    		} else {
	   	    			// id 사용 불가
	   	    			regiInputIdChk.value = "0";
	   	    			regiInputId.focus();
	   	    			regiInputId.value = ""; // 다시 기입할 수 있도록 입력 필드 초기화
	   	    		}
	   	 			getMessage2.innerHTML = '<b>' + map.msg + '</b>'; 
	   	    	});
   	   			
   			//} else {
   			//}
   		});
   		
		   		
   		// 회원가입 비밀번호 확인 체크
   		regiInputPassChk.addEventListener('blur', function(e){ 
   			if(!regiInputName.value){
   				getMessage2.innerHTML = '<b> 이름을 입력해주 ~ </b>';
   				return;
   			}
   			if(!regiInputPass.value){
   				getMessage2.innerHTML = '<b> 비밀번호를 입력해주 ~ </b>';
   				return;
   			}
   			if(!regiInputPassChk.value){
   				getMessage2.innerHTML = '<b> 비밀번호가 일치하는지 확인해주 ~ </b>';
   				return;
   			}
   			if(regiInputPass.value == regiInputPassChk.value){
   				regiInputIdChk2.value="1";
   				getMessage2.innerHTML = '';
   			} else {
   				regiInputIdChk2.value="0";
   				getMessage2.innerHTML = '<b> pw가 일치하지~않자나~~ </b>';
   				regiInputPassChk.focus();
   				regiInputPassChk.value="";
   			}
   		});
   		
   		// 비밀번호 텍스트 보기
   		eye.addEventListener('click', function(e){
   			let eye = document.querySelector('#eye').checked;
   			if (eye){
	   			regiInputPass.type= "text";
   				regiInputPassChk.type= "text";
   			} else {
	   			regiInputPass.type= "password";
   				regiInputPassChk.type= "password";
   			}
   		});
   		
   		// 로그인 폼 보기 버튼
   		btnLoginView.addEventListener('click', function(e){
   			//loginForm.style.display = "";
   			//RegiForm.style.display = "none";
   			loginForm.className += ' on';
   			RegiForm.className = '';
   		});
   		
   		// 회원가입 폼 보기 버튼
   		btnRegiView.addEventListener('click', function(e){
   			//loginForm.style.display = "none";
   			//RegiForm.style.display = "";
   			loginForm.className = '';
   			RegiForm.className += ' on';
   		});
   		
   		
   	
   	});
   	
   	/* function idCheck(map){
   		if (map.result == "success"){
   			// id 사용 가능
   			regiInputIdChk = "1";
   		} else {
   			// id 사용 불가
   			regiInputIdChk = "0";
   		}
		getMessage2.innerText = map.msg; 
   	} */
   	
   	function loginCheck(map){
   		console.log('map', map);
		// 로그인 성공 -> list로 이동
		// 실패 -> 메세지 처리
   		if (map.result == "success"){
   			// location.replace("/board/list");
   			// location.href="/board/list";
   			location.href= map.url; // 관리자 권한 여부에 따라 이동되는 페이지를 나눔
   			
   		} else {
   			getMessage.innerText = map.msg;
   		}
   	}
   
    </script>
    
    
  </head>
  <body class="text-center">
  
	<main class="form-signin w-100 m-auto">
	  <!-- 로그인 실패 시 메세지 처리할 공간 --><div id="getMessage">${param.msg}</div><br>
	  <!-- 로그인 폼 -->
	  
		<form method="post" action="loginAction" id="loginForm" class="<c:if test="${empty param.f}">on</c:if>">
		<div class="bg-crop">
			<img class="kero mb-4" alt="로고이미지" src="/resources/images/파일명.png">
		</div>
		    <div class="form-floating">
		      <input type="text" class="form-control input" id="logInputId" required="required">
		      <label for="logInputId">Id</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control endInput" id="logInputPass" required="required">
		      <label for="logInputPass">Password</label>
		    </div>
		    <div class="checkbox mb-3">
		      <label> <input type="checkbox" value="remember-me"> 7l 억 ㅎH 줘 ... ☆ </label>
		    </div>
		    <button class="w-100 btn btn-lg btn-dark" id="btnLogin" type="submit">로그인</button>
	  </form>
	  
	  <!-- 회원가입 폼 -->
      <form method="post" action="regiAction" id="RegiForm" class="<c:out value="${param.f}"/>" >
			<img class="kero mb-4" alt="로고이미지" src="/resources/images/파일명.png">
	    <label for="eye"><i class="bi bi-eye-fill" ></i> 비밀번호 보기</label>
	    <input type="checkbox" id="eye">
	    
      <!-- 회원가입 정보 입력 시 메세지 처리할 공간 --><div id="getMessage2"></div><br>
      	<input type="text" value="0" id="regiInputIdChk">
      	<input type="text" value="0" id="regiInputIdChk2">
      	<br>
		    <div class="form-floating">
		      <input type="text" class="form-control input" id="regiInputId" required="required">
		      <label for="regiInputId">New Id</label>
		    </div>
		    <div class="form-floating">
		      <input type="text" class="form-control midInput" id="regiInputName" required="required">
		      <label for="regiInputName">New Name </label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control midInput" id="regiInputPass" required="required">
		      <label for="regiInputPass">New Password </label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control endInput" id="regiInputPassChk" required="required">
		      <label for="regiInputPassChk">New Password Check </label>
		    </div>
		    <button class="w-100 btn btn-lg btn-dark" type="submit" id="btnRegi">회원가입</button>
	  </form>
	  
		<br>
	  <div class="btn-group w-100" role="group" aria-label="Basic mixed styles example">
		  <button type="button" class="btn btn-danger" id="btnRegiView">회원가입 하기</button>
		  <button type="button" class="btn btn-warning" id="btnLoginView">로그인 하기</button>
	</div>
	</main>
  </body>
</html>