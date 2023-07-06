/**
 * 
 */
	  function rentBook(){
		 delList = document.querySelectorAll('[name=delNo]:checked');
		 let delNo = "";
		 delList.forEach((e)=>{
			delNo += e.value + ','; 
		 });
		 delNo = delNo.substr(0, delNo.length-1);
		 
		 console.log(delNo);
		 
		 searchForm.action = "../book/rent.book";
		 searchForm.delNo.value = delNo;
		 searchForm.submit();
	 }
	 
	 function returnBook(){
		 delList = document.querySelectorAll('[name=delNo]:checked');
		 let delNo = "";
		 delList.forEach((e)=>{
			delNo += e.value + ','; 
		 });
		 delNo = delNo.substr(0, delNo.length-1);
		 
		 console.log(delNo);
		 
		 searchForm.action = "../book/return.book";
		 searchForm.delNo.value = delNo;
		 searchForm.submit();
	 }
	 
	 function basketBook(){
		 delList = document.querySelectorAll('[name=delNo]:checked');
		 let delNo = "";
		 delList.forEach((e)=>{
			delNo += e.value + ','; 
		 });
		 delNo = delNo.substr(0, delNo.length-1);
		 
		 console.log(delNo);
		 
		 searchForm.action = "../book/mypage.book";
		 searchForm.delNo.value = delNo;
		 searchForm.submit();
	 }
	 
	 // ▶▶▶  게시글 삭제 관련
	 function deleteBook() {
	    const delList = document.querySelectorAll('[name=bno]:checked');
	    let delNo = Array.from(delList).map(e => e.value).join(',');
	    console.log(delNo);
	    
	    const delNoInput = document.getElementById('delNoInput');
	    delNoInput.value = delNo;
	    
	    const deleteForm = document.getElementById('deleteForm');
	    deleteForm.submit();
	}
	 
	 
	 // ▶▶▶  게시글 등록/수정 관련
	/*function requestAction(url) {
		postForm.action = url;
		postForm.submit();
	}*/

	 // ▶▶▶  페이지네이션
	function go(page){
		document.searchForm.action = "./list";
		document.searchForm.pageNo.value=page;
		document.searchForm.submit();
	}
	
	function requestAction(url,bno) {
		searchForm.action = url;
		searchForm.bno.value = bno;
		searchForm.submit();
	}
	
	function changePageSize(value) {
    	var searchForm = document.forms.searchForm;
    		searchForm.searchAmount.value = value;
    		searchForm.submit();
	}
	 

