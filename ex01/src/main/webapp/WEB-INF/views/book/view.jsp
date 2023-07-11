<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더 -->
<%@ include file="../common/header_sb.jsp" %>
<!-- 메인 (상세) -->
	<div id="page-wrapper">
		        <div class="row">
                <div class="col-lg-12">
                    <!-- <h1 class="page-header">Tables</h1> -->
					<h2>📕📗📘📙📚 </h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <h3>도서 상세 조회</h3>
                           <i class="fa-solid fa-book"></i>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
							<div class="col-lg-4">
		                    <div class="panel panel-yellow">
		                        <div class="panel-heading">
		                           ${ book.idx }, ${ book.title }
		                        </div>
		                        <div class="panel-body">
		                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue.</p>
		                        </div>
		                        <div class="panel-footer">
		                            ${ book.writer }, ${ book.publisher }
		                        </div>
		                    </div>
                        </div>
                        <br>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
	              <button type="button" class="btn btn-default" onclick="requestAction('./edit', ${book.idx});">글 수정하기</button>
				  <button type="button" class="btn btn-default" onclick="requestAction('./delete', ${book.idx});">글 삭제하기</button>
				  <button type="button" class="btn btn-default" onclick="location.href='./list?pageNo=${param.pageNo}'">목록 바로가기</button>
				  <input type="text" name="pageNo" value="${param.pageNo}">
				  <input type="text" name="bno" id="bno" value="${param.bno}">
                </div>
                <!-- /.col-lg-12 -->
			  <button type="button" class="btn btn-default" onclick="location.href='./view?idx=${book.idx -1}'">이전 글</button>
			  <button type="button" class="btn btn-default" onclick="location.href='./view?idx=${book.idx +1}'">다음 글</button>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
<%@ include file="../reply/test.jsp" %>
        </div>
<!-- 푸터 -->
<%@ include file="../common/footer.jsp" %>
<br><br><br>

