<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileupload.jsp</title>
<script type="text/javascript">
	window.addEventListener("load", function(){
		
		//  ▶ 파일 목록 조회
		btnList.addEventListener("click", function(){
			getFileList();
		});
		
		//  ▶ 파일 업로드
		btnFetchFU.addEventListener("click", function(){
			// 웹 개발에서 HTML 폼 데이터를
			// JavaScript로 쉽게 조작하고 전송하는 방법을 제공하는 API.
			let formData = new FormData(fileUploadForm); // 폼 데이터 생성 시 매개변수로 폼 이름을 넣어줌
			// 값을 직접 넣을  수도 있음
			formData.append("name", "mimi");
			
			//console.log("formData : " , formData);
			
			// formData에 어떤 값이 들어있나 확인
			for(var pair of formData.entries()){
				//console.log("pair : " , pair);
				//console.log(pair[0] +" : "+ pair[1]);
				if(typeof(pair[1]) == 'object'){ // object 타입이라면?
						let fileName = pair[1].name; 
						let fileSize = pair[1].size;
					console.log('*** fileName : ', fileName); // 파일명과
					console.log('*** fileSize : ', fileSize); // 사이즈를 콘솔에 찍어줘잉
					
					// 파일 확장자, 크기 체크
					// 서버에 전송 가능한 형식인지 ? , 최대 전송 가능한 용량을 초과하지는 않는지 ?
					if(!checkExtention(fileName, fileSize)){
						return false;
					};
				}
			}
			
			fetch('/file/fileUploadActionFetch'
					, { method:'post'
						, body: formData 
					})
				.then(response => response.json()) 
				.then(map => fileuploadRes(map));
				/*
				.then((map)=>{
					console.log("map : ", map);
				});
				*/
		});
		
	});
	
	
	function checkExtention(fileName, fileSize){
		
		// 정규표현식 : 특정 규칙을 가진 문자열을 검색하거나 치환할 때 사용
		// 문자열에 정규식 패턴을 만족하는 값이 있으면 true, 아니면 false를 리턴.
		// .exe, .sh, .zip, .alz로 끝나는 문자열을 판단함 		
		let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); 
		if(regex.test(fileName)){
			alert("어우 이런 파일 형식은 안돼 얘");
			return false;
		}
		
		// 최대 크기를 넘어서는 파일을 업로드 시도할 경우 false를 리턴.
		let maxSize = 1024 * 1024 * 10;
		if(maxSize <= fileSize){
			alert("어우 파일이 너무 크다 얘");
			return false;
		}
		return true; // 이렇게 조건 만족 후 true를 반환해야 !!! 정상적으로 파일을 업로드할 수 있음
	}
	
	
	// 파일 업로드 성공 시 메세지 호출 ! 
	function fileuploadRes(map){
		if(map.result == 'success'){
			divFileuploadRes.innerHTML += map.msg;
			divFileuploadRes.innerHTML += "";
		}
	}
	
	
	// ▶ 파일 목록 조회 요청
	function getFileList(){
		let bno = document.querySelector("#bno").value; 
		// bno = bno.value; // 변수를 let으로 선언하면 id 속성 값으로 바로 불러올 수 없기에..
		
		fetch('/file/list/' + bno)
			.then(response => response.json())
			.then(map => viewFileList(map));
	}

	// ▶ 파일 삭제
	function attachFileDelete(e){ 
		/* onclick='attachFileDelete(this) -> e:이벤트로 넘겨옴 */
		// /file/delete/{uuid}/{bno}
		(e.dataset.test)? 'true' : 'false'; // 만약 값이 false라면 undefined라고 나옴
		
		let bno = e.dataset.bno; 
		let uuid = e.dataset.uuid; 
		let test = e.dataset.test; 
		
		console.log("bno : ", bno, " | uuid : ", uuid, " | test : ", test);
		
		//fetch('/file/delete/'+ uuid + '/' + bno)
		fetch(`/file/delete/\${uuid}/\${bno}`) 
			// JSP파일에서는 $ { }이 부분이 EL표현식으로 처리되지 않도록 \역슬래시로 주석 처리가 필요함 ..?
				// 주석처리 의미를 잘 몰루겟넹.. 특수기호를 인식하기 위함이 아닌가용 ??? TODO 이 부분 검색
			.then(response => response.json())
			.then(map => fileDeleteRes(map));
			//.then(map => console.log("삭제 성공 ! 희희"));
	}
	
	
	// ▶ 파일 삭제 후 메시지 처리 및 목록 재조회
	function fileDeleteRes(map){
		if(map.result == 'success'){
			console.log(map.msg);
			divFileupDelMsg.innerText = map.msg;
			getFileList();
			
		} else {
			alert(map.msg);
		}
	}
	
	
	// 썸네일 이미지가 실제로 존재하는지에 대한 여부를 판단
	function checkImageExists(imageUrl) {
	    return new Promise(function (resolve, reject) {
	        const img = new Image();
	        img.onload = function () {
	            // 이미지가 로딩된 경우
	            resolve(true);
	        };
	        img.onerror = function () {
	            // 이미지가 로딩되지 않은 경우
	            resolve(false);
	        };
	        img.src = imageUrl;
	    });
	}
	
	
	// ▶ 파일 목록 조회 - 화면 구현
	async function viewFileList(map){
		console.log('map', map);
		
		let content = "";
		
		if(map.list.length > 0){
			for (const item of map.list) {
			//map.list.forEach( function (item, index) { 
				const imageUrl = '/resources/images/' + item.t_savePath;
				
				let savePath = encodeURIComponent(item.savePath);
				
				content += "<a href='/resources/images/" + item.savePath + "' download='" + item.fileName + "'>"
						+ "<image alt='썸네일' src='" + imageUrl + "'>"
						+ "</a> &nbsp;"
						+ "<i class='bi bi-pencil-square' id='editFile'></i>"
						+ "<i class='bi bi-x-square' id='deleteFile' onclick='attachFileDelete(this)' data-bno='"+ item.bno +"' data-uuid='"+ item.uuid +"'></i>"
						
+ "<br><a href='/file/download?fileName="+ savePath +"' class='point'>"+ item.fileName +"</a><br>";
						// 다운로드 이름은 String 문자열 처리해서 적용해보라고 하셨..! substring "_" 
				//});
			}
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
			<button type="button" id="btnFetchFU">파일 업로드(Fetch)</button>
			</form>
			<br>
			결과(일반 업로드) : ${param.msg}
			<div id="divFileuploadRes">결과(fetch 업로드) : </div>
			<hr>
			<h3>📋 파일 리스트 조회 📋</h3>
			
			아래에 목록 보기 -> <button type="button" id="btnList">조회</button>
			<div id="divFileupDelMsg"></div>
			<div id="divFileupload"></div>
		</div>
	</main>
</body>
</html>











