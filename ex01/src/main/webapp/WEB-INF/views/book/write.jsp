<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더 -->
<%@ include file="../common/header_sb.jsp" %>
<!-- 메인 (등록) -->
<div id="page-wrapper">
        <div class="row">
           <div class="col-lg-12"><h2>📕📗📘📙📚 </h2></div></div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <h3>도서 등록하기</h3>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form">
						        <div class="form-group input-group">
						            <span class="input-group-addon">도서명</span>
						            <input type="text" class="form-control" value="${book.title}">
						        </div>
						        <div class="form-group input-group">
						            <span class="input-group-addon">작가명</span>
						            <input type="text" class="form-control" value="${book.writer}">
						        </div>
						        <div class="form-group input-group">
						            <span class="input-group-addon">출판사명</span>
						            <input type="text" class="form-control" value="${book.publisher}">
						        </div>
						     	<div class="form-group input-group">
						            <span class="input-group-addon">업로드</span>
						            <input type="file" class="form-control">
						        </div>
						    </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
	
<!-- 푸터 -->
<%@ include file="../common/footer.jsp" %>


