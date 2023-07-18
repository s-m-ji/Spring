<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileupload.jsp</title>
<script type="text/javascript">
	window.addEventListener("load", function(){
		
		btnList.addEventListener("click", function(){
			getFileList();
		});
	});
	
	function getFileList(){
		let bno_tset = document.querySelector("#bno").value; 
		
		bno = bno.value; // 변수를 let으로 선언하면 id 속성 값으로 바로 불러올 수 없기에..
		
		fetch('/file/list/'+bno)
			.then(response => response.json())
			.then(map => viewFileList(map));
	}
	
	function viewFileList(map){
		console.log('map', map);
		
		let content = "";
		
		if(map.list.length > 0){
			map.list.forEach(function (item, index) {
				// 여기서 백틱으로 쓸 수 없는 이유는 javascript가 아니라 jsp 파일이기 때무넹 !
				// content += "💾 &nbsp; " + item.fileName +" (파일 경로: " + item.savePath + " )<br>"
				// + "🔳 &nbsp; " + item.fileName +" (썸네일 경로: " + item.t_savePath + " )<br>"
				content += "<image alt='썸네일' src='/resources/images/" + item.t_savePath + "'>";
				// TODO 썸네일이 없을 경우 대체하는 이미지를 넣어둘 필요가 있음
			});
		} else {
			content = "등록된 파일이 없습니당~"
		}
		
		divFileupload.innerHTML = content;
	}
	
</script>
</head>
<body>
<%@ include file = "../common/header.jsp"%>
	<main class="container">
	<div class="bg-light p-5 rounded">
			<h3>📤 파일 업로드 📤</h3>
			<hr>
			<form method="post" action="/file/fileUploadAction" 
					name="fileUploadForm" enctype="multipart/form-data">
			<h4>파일 선택</h4>
			<input type="text" name="bno" value="3" id="bno"> *bno
			<input type="file" name="files" multiple="multiple"> *multiple
			<!-- 참고로 multiple 속성은 브라우저 버전에 따라 오류 발생할 수도 있음..! -->
			<input type="file" name="files">
			<input type="file" name="files">
			<button type="submit">파일 업로드</button>
			</form>
			<br>
			결과 : ${param.msg}
			<hr>
			<h3>📋 파일 리스트 조회 📋</h3>
			
			아래에 목록 보기 -> <button type="button" id="btnList">조회</button>
			<div id="divFileupload"></div>
		</div>
	</main>
</body>
</html>












