/**
 * 댓글 관련 스크립트 모음
 */
	
console.log("-------------- reply.js 연결 완료! ----------------");

	function getReplyList(page){
		let bno = document.querySelector('#bno').value;
		
		// falsey : false, 0, "", NaN, undefined, null
		// falsey한 값 외의 것들이 들어있으면 true를 반환

		if(!page){ // page의 값이 없으면 1로 셋팅하기 위함
			page = 1;
		} 
		
		fetchGet(`/reply/list/${bno}/${page}`, replyView);
	}
	
	
	function replyView(map) {
	  let list = map.list;
	  let pDto = map.pDto;
	  console.log('list', list);
	 
	  // list가 비어있을 경우에 대한 처리 ! 
	  if(list.length == 0) {
		  replyDiv.innerHTML = "<h4>🙋‍♀️ 댓글이 아직 up9yo 🙋‍♀️</h4>";
		  
	  } else {
		  
		  replyDiv.innerHTML = "<h4>💬 댓글 목록 💬</h4>";

		  let replyDivStr =
		    '<table class="table text-break text-center">' 
		    +'   <thead class="table-dark">' 
		    +'     <tr>' 
		    +'       <th scope="col" class="col-1">관리자 모드</th>' 
		    +'       <th scope="col" class="col-6">내용</th>' 
		    +'       <th scope="col" class="col-3">작성자/날짜</th>' 
		    +'     </tr>' 
		    +'   </thead>' 
		    +'   <tbody>';

		  
		  list.forEach(rp => {
			  // 수정 시 화면에 날짜를 updatedate로 보여주기 
			  rpDate = new Date(rp.replydate);
			  upDate = new Date(rp.updatedate);
			  date = (rpDate.toDateString() !== upDate.toDateString()) ? rp.updatedate : rp.replydate ;
			
			  replyDivStr +=
			      '<tr id="trReply'+ rp.rno +'" data-value="'+ rp.reply +'" data-rno="'+ rp.rno +'">' 
			      +'  <td>'+ rp.seq +' &nbsp; <input type="checkbox" class="chkBox" name="bno" value="'+ rp.rno +'"></td>' 
			      +'  <td class="text-start"><b>'+ rp.reply +'</b>'
			      +'	<i id="btnEdit" class="bi bi-pencil-fill" onclick="rpEdit('+ rp.rno +');"></i>'
			      +'	<i id="btnDel" onclick="rpDelete('+ rp.rno +');" class="bi bi-trash3-fill"></i></td>' 
			      +'  <td><font color="#555"> <i class="bi bi-person-circle"></i> &nbsp;' + rp.replyer + ' </font><br> <font color="#999">'+ date +'</font></td>' 
			      +'</tr>';
		  });

		  replyDivStr +=
				    '</tbody>' 
				   + '</table>';

		  replyDiv.innerHTML += replyDivStr;
		  
		// [댓글 리스트 페이지네이션]
			let disabledP = (pDto.prev == false)? 'disabled':"";
			let disabledN = (pDto.next == false)? 'disabled':"";
			let goP = (pDto.prev == false)? 1 : (pDto.startNo - 1);
			let goN = (pDto.endNo + 1) > pDto.realEndNo ? pDto.realEndNo : (pDto.endNo + 1);
			
			var pageBlock = `<nav> <ul class="pagination justify-content-center">`;
			  
			//if(pDto.prev){
				pageBlock +=
				` <li class="page-item ${disabledP}" onclick="getReplyList(1)"><a class="page-link" > ◀◀ </a></li>`
			  + ` <li class="page-item ${disabledP}" onclick="getReplyList(${goP})"><a class="page-link" > ◀ </a></li>`
				  ;
			//}

				console.log('pDto.startNo : ', pDto.startNo);
				console.log('pDto.endNo : ', pDto.endNo);
			for (let i = pDto.startNo; i <= pDto.endNo; i++) {
				let active = (pDto.cri.pageNo == i)? 'active':"";
			  pageBlock +=
				  ` <li class="page-item ${active}" onclick="getReplyList(${i})"><a class="page-link">${i}</a></li>`
				  ;
			}

			//if(pDto.next){
				pageBlock +=
					` <li class="page-item ${disabledN}" onclick="getReplyList(${goN})"><a class="page-link"> ▶ </a></li>`
				+ ` <li class="page-item ${disabledN}" onclick="getReplyList(${pDto.realEndNo})"><a class="page-link"> ▶▶ </a></li>`
				;
			//}
			pageBlock +=
			   `  </ul> </nav>`
			  ;

			replyDiv.innerHTML += pageBlock;
	  	}
		  
	}

	
	// 댓글 등록하기
	function replyWrite(){
		let bno = document.querySelector('#bno').value;
		let reply = document.querySelector('#reply').value;
		let replyer = document.querySelector('#replyer').value;
		
		let obj = { // 전달할 객체로 생성 : JSON 문자열로 만드는 것 보다 JSON 객체로 하는게 쉽다구
			bno : bno	
			, reply : reply
			, replyer : replyer
		};
		console.log('obj', obj);
		
		fetchPost('/reply/insert', obj, replyRes);
	}
	
	// 댓글 삭제 처리
	function rpDelete(rno){
		fetchGet(`/reply/delete/${rno}`, replyRes);
	}
	
	
	// 댓글 일괄 삭제 관련
	 function deleteReply() {
	    const delList = document.querySelectorAll('[class=chkBox]:checked');
	    let delNo = Array.from(delList).map(e => e.value).join(',');
	    console.log(delNo);
	    
	    const delNoInput = document.getElementById('delNoInput');
	    delNoInput.value = delNo;
	    
	    rpDelete(delNo);
	}
	 
	 
	// 댓글 수정화면 보여주기
	function rpEdit(rno){
		let trReply = document.querySelector("#trReply"+rno);
		let replyTxt = trReply.dataset.value; 
		let replyRno = trReply.dataset.rno; 

		trReply.innerHTML = 
			` <td colspan="3"> `
			+ `  <div class="input-group">  `
			+ `  <span class="input-group-text">댓글 수정</span>   `
			+ `  <input type="text" id="reply${rno}" value="${replyTxt}" placeholder="내용" class="form-control">  `
			+ `  <input type="button" onclick="rpEditAction(${rno})" class="input-group-text" value="완료">  `
			+ `  </div> `
			+ ` </td> `
			;
	}
	
	// 댓글 수정하기
	function rpEditAction(rno){
		let reply = document.querySelector('#reply' + rno).value;
		
		let obj = {
				rno : rno	
				, reply : reply
		};
		console.log('obj', obj);
		
		let url = '/reply/update';
		fetchPost(url, obj, replyRes);
	}
	
	
	// 댓글 등록, 수정, 삭제의 결과를 처리 : 조회를 제외하고는 모두 반환이 int임
	function replyRes(map){
		// 성공 : 리스트 조회 및 출력
		// 실패 : 메세지 출력
		console.log('map', map);
		
		if (map.result == "success"){
			getReplyList();
			
			// 댓글 등록, 수정 후 화면 조회 시 입력창 초기화
			document.querySelector('#reply').value = "";
			document.querySelector('#replyer').value = "";
		} else {
			alert(map.message);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	