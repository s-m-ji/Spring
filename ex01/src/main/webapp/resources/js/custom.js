// window.onload = (e) => {
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
	 
	 
	// 체크박스들을 모두 선택/해제하는 함수
	 function toggleCheckboxes() {
	   var adminChkBox = document.getElementById('adminChkBox');
	   var isChecked = adminChkBox.checked;

	   var chkBox = document.getElementsByClassName('chkBox');

	   for (var i = 0; i < chkBox.length; i++) {
		   chkBox[i].checked = isChecked;
	   }
	 }
	 
	 // window 로딩되고 나면 버튼 클릭 이벤트 실행 가능 
	 window.addEventListener('load', function(){
		 
		 var adminChkBox = document.getElementById('adminChkBox');
		 adminChkBox.addEventListener('click', toggleCheckboxes);
		 
		 var allClick = document.getElementById('allClick');
		 allClick.addEventListener('click', addCheck);

	  });
	 
	 
	 // ▶▶▶  게시글 등록/수정 관련
	/*function requestAction(url) {
		postForm.action = url;
		postForm.submit();
	}*/

	 // ▶▶▶  게시글 페이지네이션
	function go(page){
		//e.preventDefault();
		document.searchForm.action = "./list";
		document.searchForm.pageNo.value=page;
		document.searchForm.submit();
	}
	
	function requestAction(url,bno) {
		//e.preventDefault();
		searchForm.action = url;
		searchForm.bno.value = bno;
		searchForm.submit();
	}
	
	// 한 페이지에 보여지는 리스트 개수 조정
	function changePageSize(value) {
    	var searchForm = document.forms.searchForm;
    		searchForm.amount.value = value;
    		searchForm.submit();
	}
// }


