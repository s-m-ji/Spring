<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
i {
	cursor: pointer;
}

table {
	width: 100%;
}

.reply td {
	text-align: left;
}

figure:nth-child(odd) {
	background-color: lavender;
}

.page-item {
	cursor: pointer;
}

.pagination>li>a, .pagination>li>span {
	color: #6610f2;
}
</style>
<script src='/resources/js/reply.js'></script>
<title>test.jsp</title>

<script type="text/javascript">
	// 서버에  댓글 리스트를 요청 : 데이터를 구하는 부분을 함수로 맹글기
	function getList(){
		let bno = document.querySelector("#bno").value;
		let page = document.querySelector("#page").value;
		let url = "/reply/list/" + bno + "/" + page;
	
		// fetch 구문을 함수로 만들어서 재사용함
		fetchGet(url, replyView);
	}
	
	// list를 화면에 출력
	function replyView(map){
		let list = map.list; // 이건 컨트롤러에서 map.put으로 저장해둔 값.
		let pDto = map.pDto; 
		
		console.log('list', list);
		console.log('pDto', pDto);
		
		replyDiv.innerHTML = ''; // replyDiv 초기화 ~
		
		// [댓글 리스트]
		// 반복문  ▷ reply: 정보가 담긴 객체, index: list의 방번호
		list.forEach((reply, index) => {
			
			let nowDate = new Date(); // 현재 날짜/시각 가져오기
		    let replyDate = new Date(reply.replydate); // reply.replydate를 Date 객체로 변환
		    let date = (nowDate.toDateString() !== replyDate.toDateString()) ? reply.replydate : reply.updatedate; // 현재 날짜/시각과 reply.replydate 비교하여 값 결정
					
			replyDiv.innerHTML 
			+= '<figure id="reply'+ index +'" data-value="' + reply.reply + '" data-rno="' + reply.rno + '">' // 인덱스 값 활용하여 id를 다르게 부여 
			
			+ 	''
			+		'<input type="checkbox" name="adminDel" value="'+ reply.rno +'">'
			+		'<blockquote class="blockquote">'
			+ 		'<p>' + reply.reply + ' <font color="#6610f2">'
			+			' &nbsp; <i id="btnEdit" class="fa-solid fa-square-pen" onclick="rpEdit('+ index +', '+ reply.rno +')"></i>'
			+			' <i id="btnDelete" class="fa-solid fa-trash" onclick="rpDelete('+ reply.rno +')"></i>'
			+		'</font> </p>'
			+ 	'</blockquote>'
			
			+ 	'<figcaption class="blockquote-footer"> <font color="#6610f2">'
			+  		''+ reply.replyer + '</font> <cite title="Source Title"> &nbsp;' + date + '</cite>'
			+ 	'</figcaption>'
			
			+'</figure>';
		}); 
		
		replyDiv.innerHTML += '<button class="btn btn-outline-secondary" type="button" id="btnAdminDel" onclick="rpDelete('+ reply.rno +')">관리자 삭제</button>'; // replyDiv 초기화 ~
	
		// [댓글 리스트 페이지네이션]
		let disabledP = (pDto.prev == false)? 'disabled':"";
		let disabledN = (pDto.next == false)? 'disabled':"";
		let goP = (pDto.prev == false)? 1 : (pDto.startNo - 1);
		let goN = (pDto.endNo + 1) > pDto.realEndNo ? pDto.realEndNo : (pDto.endNo + 1);
		
		var pageBlock = '<nav aria-label="...">' +
		  '   <ul class="pagination justify-content-center">' +
		  '     <li class="page-item  '+ disabledP +'" onclick="goPage('+ goP +')"><a class="page-link" > ◀ </a></li>';

		for (var i = pDto.startNo; i <= pDto.endNo; i++) {
		let active = (pDto.cri.pageNo == i)? 'active':"";
		  pageBlock +=
		    '     <li class="page-item '+ active +'" onclick="goPage('+ i + ')"><a class="page-link">'+ i +'</a></li>';
		}

		pageBlock +=
		  '     <li class="page-item '+ disabledN +'" onclick="goPage('+ goN +')"><a class="page-link"> ▶ </a></li>' +
		  '   </ul>' +
		  ' </nav>';

		replyDiv.innerHTML += pageBlock;
	}

	
	// 댓글 리스트 페이지네이션 버튼 액션: n쪽 보여주기
	function goPage(page) {
		document.querySelector('#page').value = page;
		  getList(page);
	}

	
	// 댓글 수정 화면 보여주기
	function rpEdit(index){
		let editBox = document.querySelector("#reply" + index);
		let replyTxt = editBox.dataset.value;
		let replyRno = editBox.dataset.rno;
		
		editBox.innerHTML = ''
		+ 	'<div class="input-group mb-3">'
		+  		'<input type="text" class="form-control" value="'+ replyTxt +'" id="replyEdit'+ replyRno +'">'
		+  		'<button class="btn btn-outline-secondary" type="button" onclick="rpEditAction('+ replyRno +')";>댓글 수정</button>'
		+ 	'</div>'; 
	}
	
	
	// 댓글 수정 처리 
	function rpEditAction(rno){
		let reply = document.querySelector('#replyEdit' + rno).value;
		// let replyer = document.querySelector('#replyer').value;
		
		let replyObj = {
				rno : rno
				, reply : reply
			};
			
		fetchPost('/reply/update', replyObj, replyRes);
	}
	
	
	// 댓글 삭제 처리
	function rpDelete(rno){
		let url = "/reply/delete/" + rno;
		
		fetchGet(url, replyRes);
	}
	
	
	// 작업 결과 처리
	function replyRes(map){
		if(map.result == 'success'){
			// 댓글 등록 성공 -> 리스트 조회 및 화면 출력
			getList();
		} else {
			// 실패
			alert(map.message);
		}		
	}

	
	
	// 페이지 로딩 된 후에 실행될 함수들
	window.onload = function(){
		// 리스트 조회 및 출력
		getList(1); // 첫 화면에서는 무조건 댓글 1페이지 보여줌 
		
		btnWrite.addEventListener('click', function(){
			// 1. 파라미터 수집
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			// 2. 전송할 데이터를 javascript 객체로 생성
			let replyObj = {
				bno: bno // 이름 : 값
				, reply : reply
				, replyer : replyer	
			};
			
			/*
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
			*/
				fetchPost('/reply/insert', replyObj, replyRes);
			
		});
	
	}
	
</script>

</head>
<body>
	<br>
	<h3>댓그르르르 🤗🤗</h3>
	<!-- <input type="text" name="bno" id="bno" value="1" class="form-control"> -->
	<!-- 게시글 번호 : list 페이지에서 받아올거라 주석처리함 -->
	<input type="text" name="page" id="page" value="1" class="form-control">
	<!-- 댓글 리스트 페이지 번호 -->
	<input type="text" class="form-control" placeholder="미미" id="replyer">
	<div class="input-group mb-3">
		<input type="text" class="form-control" placeholder="아르르" id="reply">
		<button class="btn btn-outline-secondary" type="button" id="btnWrite">댓글
			작성</button>

	</div>
	<div id="replyDiv"></div>
</body>
</html>