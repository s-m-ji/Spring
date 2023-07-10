<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/bc0f5040fb.js" crossorigin="anonymous"></script>
<style>
@font-face {
	  font-family: "SUITE-Regular";
	  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
	    format("woff2");
	  font-weight: 400;
	  font-style: normal;
	}
	:not(i) {
	  font-family: "SUITE-Regular" !important;
	} 
	body {
		padding: 10%;
	}
	i {
		cursor: pointer;
	}
</style>

<title>test.jsp</title>

<script type="text/javascript">
	// 댓글 리스트를 서버에 요청
	// 데이터를 구하는 부분을 함수로 맹글기
	function getList(){
		let bno = document.querySelector("#bno").value;
		let url = "/reply/list/" + bno;
		
		// url 요청 결과를 받아오기
		fetch(url)
		// response.json() : 요청 결과를 js object 형식으로 반환 : 그래야 list.속성으로 값을 이용할 수 있음
		.then(response => response.json())
		// 반환 받은 오브젝트를 이용하여 화면에 출력
		//.then(list => console.log(list)); // 'list'는 임의 작명 내 맘대루 ~~
		.then(list => replyView(list)); 
	}
	
	// list를 화면에 출력
	function replyView(list){
		console.log(list);
		
		// 반복문  ▷ reply: 정보가 담긴 객체, index: list의 방번호
		
		replyDiv.innerHTML = ''; // replyDiv 초기화 ~
		
		list.forEach((reply, index) => {
			console.log();
			/*  replyDiv.innerHTML += '<br>' + reply.rno;
			replyDiv.innerHTML += '<br>' + reply.bno;
			replyDiv.innerHTML += '<br>' + reply.reply;
			replyDiv.innerHTML += '<br>' + reply.replyer;
			replyDiv.innerHTML += '<br>' + reply.replydate;  */
			
			replyDiv.innerHTML 
			+= '<figure id="reply'+ index +'">' // 인덱스 값 활용하여 id를 다르게 부여
			
			+ 	'<blockquote class="blockquote">'
			+ 		'<p>' + reply.reply + ' <font color="#6610f2">'
			+			' <i class="fa-solid fa-square-pen" onclick="rpUpdate('+ reply.rno +')"></i>'
			+			' <i id="btnEdit" class="fa-solid fa-trash" onclick="rpDelete('+ reply.rno +')"></i>'
			+		'</font> </p>'
			+ 	'</blockquote>'
			
			+ 	'<figcaption class="blockquote-footer"> <font color="#6610f2">'
			+  		''+ reply.replyer + '</font> <cite title="Source Title"> &nbsp;' + reply.replydate + '</cite>'
			+ 	'</figcaption>'
			
			+'</figure>';
		}); 
		
	}
	
	
	function rpDelete(rno){
		let url = "/reply/delete/" + rno;
		fetch(url)
		.then(response => response.json())
		.then(list => replyRes(list));
	}
		
	
	window.onload = function(){
		// 리스트 조회 및 출력
		getList();
		
		btnWrite.addEventListener('click', function(){
			// 1. 파라미터 수집
			let bno = document.querySelector('#bno').value;
			let rno = document.querySelector('#rno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			
			if(btnWrite.classList.contains('edit')){
				// 2. 전송할 데이터를 javascript 객체로 생성
				let replyObj = {
					rno: rno // 이름 : 값
					, reply : reply
				};
				
				// 3. 객체(object)를 json 문자열 타입으로 변환
				let replyJson = JSON.stringify(replyObj);
				
				fetch('/reply/update'
						// get 방식이라면 method 적지 않아도 된다
						, {method : 'post' 
							// UTF-8 오류 처리
							, headers : {'Content-Type' : 'application/json'}
							, body : replyJson})
					
					// 5. 응답 처리
					.then(response => response.json())
					//.then(map => console.log(map));
					.then(map => replyRes(map));
					
				btnWrite.classList.remove('edit');

				
			} else {
			// 2. 전송할 데이터를 javascript 객체로 생성
			let replyObj = {
				bno: bno // 이름 : 값
				, reply : reply
				, replyer : replyer	
			};
			
			// 3. 객체(object)를 json 문자열 타입으로 변환
			let replyJson = JSON.stringify(replyObj);
				
				// 4. 서버에 요청
				fetch('/reply/insert'
						// get 방식이라면 method 적지 않아도 된다
						, {method : 'post' 
							// UTF-8 오류 처리
							, headers : {'Content-Type' : 'application/json'}
							, body : replyJson})
					
					// 5. 응답 처리
					.then(response => response.json())
					//.then(map => console.log(map));
					.then(map => replyRes(map));
				}
			
		});
	
	}
	
	
	function rpUpdate(rno){
		let url = "/reply/reply/" + rno;
		fetch(url)
		.then(response => response.json())
		.then(input => rpUpdateSet(input));
		
	}
	
	function rpUpdateSet(input){
		reply.value = input.reply;
		btnWrite.classList.add('edit');
	}
	
	
	function replyRes(map){
		if(map.result == 'success'){
			// 댓글 등록 성공 -> 리스트 조회 및 화면 출력
			getList();
		} else {
			// 실패
			alert(map.message);
		}		
		
	}
</script>

</head>
<body>
	<br>
	<h1>댓그르르르 🤗🤗</h1>	
		<input type="text" name="bno" id="bno" value="1" class="form-control">
		<input type="text" name="rno" id="rno" value="1" class="form-control">
		  <input type="text" class="form-control" placeholder="미미" id="replyer">
		<div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="아르르" id="reply">
		  <button class="btn btn-outline-secondary" type="button" id="btnWrite">댓글 작성</button>
		</div>
	<div id="replyDiv"></div>
</body>
</html>