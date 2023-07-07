<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더 -->
<%@ include file="../common/header_sb.jsp" %>
<!-- 메인 (목록) -->
	<div id="page-wrapper">
        <div class="row">
           <div class="col-lg-12"><h2>📕📗📘📙📚 </h2></div></div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <h3>나의 대출 목록 조회</h3>
                           <i class="fa-solid fa-book"></i>
                           <span>총 <b>${pDto.total}</b>개</span> 
                           &nbsp;&nbsp; / &nbsp;&nbsp;
                           <i class="fa-solid fa-bars-staggered"></i>
                           <span>현재 화면에서 총 <b>${list.size()}</b>개</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%-- <%@ include file="../common/searchForm.jsp" %> --%>
                        	<form action="./list" name="searchForm">
                        	<div class="form-inline text-center">
                        	<div class="form-group">
                                <select class="form-control" name="sField">
                                    <option value="idx" ${pDto.cri.SField eq 'idx'? 'selected' : '' }>책 번호</option>
                                    <option value="title" ${pDto.cri.SField eq 'title'? 'selected' : '' }>도서명</option>
									  <option value="writer" ${pDto.cri.SField eq 'writer'? 'selected' : '' }>작가명</option>
									  <option value="publisher" ${pDto.cri.SField eq 'publisher'? 'selected' : '' }>출판사명</option>
                                </select>
                                <div class="form-group">
                                   <input name="sWord" class="form-control" id="disabledInput" type="text" placeholder="검색어" value="${pDto.cri.SWord}">
                               </div>
                               <button type="submit" class="btn btn-default" onclick="go(1);"><i class="fa-solid fa-magnifying-glass"></i></button>
                               <input type="text" name="pageNo" value="${pDto.cri.pageNo}">
								<input type="text" name="bno" value="">
                               <select class="form-control" name="amount" onchange="changePageSize(this.value)">
					            	<option value="10" ${ amount eq 10 ? "selected" : "" }>10개씩 보기</option>
					            	<option value="20" ${ amount eq 20 ? "selected" : "" }>20개씩 보기</option>
					            	<option value="50" ${ amount eq 50 ? "selected" : "" }>50개씩 보기</option>
                                </select>
                            </div>
                            </div>
                        	</form>
                        	<br>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <!-- <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"> -->
                                <thead>
                                    <tr>
                                        <th>선택</th>
                                        <th>책 번호</th>
                                        <th>도서명</th>
                                        <th>작가명</th>
                                        <th>출판사명</th>
                                        <th>대출여부</th>
                                        <th>대출일</th>		
										<th>반납기한</th>		
										<th>반납일</th>	
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:choose>
                                		<c:when test="${ empty list }">
                                		<tr class="odd gradeX">
                                			<td>🤷‍♀ 등록된 게시글이 없는걸 🤷‍♀️</td>
                                		</tr>
                                		</c:when>
                                		<c:otherwise>
                                		<c:forEach items="${ list }" var="book">
	                                    <tr class="even gradeX">
	                                        <td width="5%"><input type="checkbox" name="bno" value="${book.idx}"></td>
	                                        <td width="5%"><c:out value="${ book.idx }"></c:out></td>
	                                        <td width="25%">
	                                        	<a href="./view?idx=${ book.idx }&pageNo=${pDto.cri.pageNo}" target="_blank">
	                                        	<c:out value="${ book.title }"></c:out> &nbsp; <i class="fa-solid fa-square-arrow-up-right"></i>
	                                        	</a>
	                                        </td>
	                                        <td><c:out value="${ book.writer }"></c:out></td>
	                                        <td><c:out value="${ book.publisher }"></c:out></td>
	                                        <td><c:out value="${ book.rent_str }"></c:out></td>
	                                        <td>${empty book.rent_date ? '-' : book.rent_date}</td>
											<td>${empty book.return_exp_date ? '-' : book.return_exp_date}</td>
											<c:set value="${book.rest_date < 0 or book.rent_yn eq 'N' ? '-' : book.rest_date}" var="date"/>
											<td width="15%">
											    <c:if test="${book.return_date == null}" var="res">
											        <c:choose>
											            <c:when test="${book.rest_date > 0 and book.rest_date <= 5 }">
											                <span class="point rest orange"><font color="orangered">${date}</font> 일 남음</span>
											            </c:when>
											            <c:when test="${book.rest_date < 0}">
											                <span class="point rest red">기한 경과</span>
											                <sup>※ 대출 정지 <font color="red">${book.no_rent}일 </font></sup>
											            </c:when>
											            <c:otherwise>
											            	<c:if test="${empty book.rent_date}" var="check">
											            		<span>-</span>
											            	</c:if>
											            	<c:if test="${not check}">
											                	<span class="point rest">${date} 일 남음</span>
											            	</c:if>
											            </c:otherwise>
											        </c:choose>
											    </c:if>
											    <c:if test="${not res}">
											        <span class="point return">${book.return_date}</span>
											    </c:if>
											</td>
	                                    </tr>
                                		</c:forEach>
                                		</c:otherwise>
                                	</c:choose>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
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
        
<!-- 페이지 네비게이션 -->
<%@ include file="../common/pageNavi.jsp" %>
<!-- 푸터 -->
<%@ include file="../common/footer.jsp" %>


