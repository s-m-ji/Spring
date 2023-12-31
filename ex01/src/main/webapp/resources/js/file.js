/**
 * 파일 업로드 관련 스크립트 모음
 */
console.log("-------------- file.js 연결 완료! ----------------");
window.addEventListener("load", function(){
		// getFileList();
		
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
		
		fetch('/file/delete/'+ uuid + '/' + bno)
		// fetch(`/file/delete/\${uuid}/\${bno}`) 
			// JSP파일에서는 $ { }이 부분이 EL표현식으로 처리되지 않도록 \역슬래시로 주석 처리가 필요함 ..?
				// 주석처리 의미를 잘 몰루겟넹.. 특수기호를 인식하기 위함이 아닌가용 ??? 
			// TODO board/view에서 파일 삭제가 안되기에 (500 에러 발생) 템플릿 리터럴 방식에서 바꿈 그치만 왜때무넹 안됑..?
		
			.then(response => response.json())
			.then(map => fileDeleteRes(map));
			//.then(map => console.log("삭제 성공 ! 희희"));
	}
	
	
	// ▶ 파일 삭제 후 메시지 처리 및 목록 재조회
	function fileDeleteRes(map){
		if(map.result == 'success'){
			console.log(map.msg);
			
			alert("파일 삭제 성공쓰 !");
			// divFileupDelMsg.innerText = map.msg;
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
		let list = map.list;
		console.log('list', list);
		
		if(list.length == 0) {
			fileDiv.innerHTML = "<h5>🙋‍♀️ 등록된 파일이 아직 up9yo 🙋‍♀️</h5>";
		} else {
			
		let content = "";
		
		if(map.list.length > 0){
			for (const item of map.list) {
			//map.list.forEach( function (item, index) { 
				const imageUrl = '/resources/images/' + item.t_savePath;
				const imageExists = await checkImageExists(imageUrl);
				
				let savePath = encodeURIComponent(item.savePath);
				
				if (imageExists) {
				content += "<a href='/resources/images/" + item.savePath + "' download='" + item.fileName + "'>"
						+ "<image alt='썸네일' src='" + imageUrl + "'>"
						+ "</a> &nbsp;"
						+ "<a href='/file/download?fileName="+ savePath +"'> 📥 "+ item.fileName +"  </a>" 
						// 파일 다운로드 이름은 String 문자열 처리해서 적용해보라고 하셨..! substring "_" 
						+ "<i class='bi bi-x-square-fill' id='deleteFile' onclick='attachFileDelete(this)' data-bno='"+ item.bno +"' data-uuid='"+ item.uuid +"'></i>"
						;
				} else {
	                // 대체 이미지 태그 생성
	                const alterImageUrl = '/resources/images/alternative_image.jpg'; // 대체 이미지의 경로
	                const alterImageAlt = '대체 이미지'; // 대체 이미지의 alt 속성

	                content += "<image alt='" + alterImageAlt + "' src='" + alterImageUrl + "' style='width:100px;'>"
	                + "<a href='/file/download?fileName="+ savePath +"'> 📥 "+ item.fileName +"  </a>" 
	                + "<i class='bi bi-x-square-fill' id='deleteFile' onclick='attachFileDelete(this)' data-bno='"+ item.bno +"' data-uuid='"+ item.uuid +"'></i>"
					;
	            }
				
				//});
			}
		} else {
			content = "등록된 파일이 없습니당~"
		}
		
		fileDiv.innerHTML = content;
		}
	}
	