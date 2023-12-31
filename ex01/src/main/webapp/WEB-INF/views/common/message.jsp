<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>Insert title here</title>
<script>
  let passMsg = "${passMsg}";
  let failMsg = "${failMsg}";
  let passMsgPost = "${passMsgPost}";
  
  window.onload = function() {
	  
	//history.replaceState({},null,null); TODO 등록/삭제 작업 완료 후 브라우저에서 뒤로가기 버튼을 클릭하는 것에 대한 처리가 필요함
	  
    const myModal = new bootstrap.Modal('#myModal', {
      keyboard: true
    });

    const message = passMsg || failMsg || passMsgPost;
    const redirectUrl = passMsg ? "/board/list" : `/board/view?idx=${book.idx}&pageNo=${param.pageNo}`;

    if (message) {
      document.querySelector(".modal-body").innerHTML = message;
      myModal.show(200);

      const myModalEl = document.getElementById('myModal');

      myModalEl.addEventListener('hidden.bs.modal', () => {
        redirectUrl === (window.location.href = redirectUrl);
      });
    }
  };
</script>

</head>
<body>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Message</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- <script>
	  // 메세지 처리
	  let passMsg = "${passMsg}";
	  let failMsg = "${failMsg}";
	
	  if (failMsg !== null && failMsg !== "") {
	    alert(failMsg);
	    history.go(-1);
	  } 
	  else if (passMsg !== null && passMsg !== "") {
	    alert(passMsg);
	    window.location.href = "/board/list";
	  }
	</script> -->
</body>
</html>