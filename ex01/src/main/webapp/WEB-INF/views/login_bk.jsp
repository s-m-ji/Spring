<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login_bk.jsp</title>
<!-- <script>
window.onload = function() {
    const myModal = new bootstrap.Modal('#myModal', {
      keyboard: true
    });
    myModal.show(500);
}
</script> -->
</head>
<body>
<%@ include file="common/header.jsp" %>
    <main class="container">
      <div class="bg-light p-5 rounded ">
      <br><br><br><br>
      <!-- <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">Message</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body"> -->
		      <h3>로그인 할 새럼 소리질러 ~~~ 📢📢</h3>
		      <br><br>
		      	<form method="post" action="./loginAction" class="row row-cols-lg-auto g-3 align-items-center">
				  <div class="col-6">
				    <div class="input-group">
				      <div class="input-group-text"> ID </div>
				      <input type="text" name="id" value="test" class="form-control" id="inlineFormInputGroupUsername" placeholder="Username">
				    </div>
				  </div>
				
				  <div class="col-6">
				    <div class="input-group">
				      <div class="input-group-text"> PW </div>
				      <input type="text" name="pw" value="1234" class="form-control" id="inlineFormInputGroupUsername" placeholder="Username">
				    </div>
				  </div>
				
				  <div class="col-6">
				    <button type="submit" class="btn btn-dark">로그인</button>
				  </div>
				</form>
		      <!-- </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div> -->
	</div>
	</main>
</body>
</html>