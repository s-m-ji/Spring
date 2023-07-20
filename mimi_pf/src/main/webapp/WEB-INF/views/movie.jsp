<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
            $(function() {
            	
                y = new Date();
                y.setDate(y.getDate()-1);
                
             // 오늘 날짜를 `yyyy-mm-dd` 형식으로 변환합니다.
             // 참고로 박스오피스 조회는 어제가 제일 최신
             
                today = y.toISOString().split('T')[0]; 
                $('#date').val(today);
                
                console.log(today);
                
                let str = y.getFullYear() + "-"
                + ("0" + (y.getMonth() + 1)).slice(-2) + "-"
                + ("0" + y.getDate()).slice(-2);
                $("#date").attr("max",str);

                // 버튼의 클릭 이벤트
                $("#mybtn").click(function() {
                    let d = $("#date").val();//YYYY-MM-dd
                    const regex = /-/g;
                    let d_str = d.replace(regex,"")//YYYYMMdd 

                    let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=a34977bfdaceeb14a018ad5af883c50c&targetDt="+d_str

                     $.getJSON(url, function(data) {
                         let movieList = data.boxOfficeResult.dailyBoxOfficeList;
                         $("#boxoffice").empty();
                         $("#boxoffice").append(d+" 박스 오피스 순위<br>");
                         for(let i in movieList){
                             $("#boxoffice").append("<div class='movie' id="+movieList[i].movieCd+">"+(parseInt(i)+1)+". "+movieList[i].movieNm+" / "+movieList[i].audiAcc+"명</div><hr>");
                             console.log(movieList[i].movieCd);
                         }
                        });
                });//button click
                
                //영화 제목 클릭시 영화 정보 출력
                $("#boxoffice").on("click",".movie", function(){
                    let d = $(this);
                    let movieCd = d.attr("id");

                    let url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=a34977bfdaceeb14a018ad5af883c50c&movieCd="+movieCd;
                    
                    $.getJSON(url, function(res){
                        let movie = res.movieInfoResult.movieInfo;
                        d.append("<hr>");
                        d.append("개봉일 : "+movie.openDt+"<br>");
                        d.append("감독 : "+movie.directors[0].peopleNm+"<br>");
                        d.append("주연 : "+movie.actors[0].peopleNm+", "+movie.actors[1].peopleNm+", "+movie.actors[2].peopleNm);
                        d.append("<hr>");
                     
                    });
                });
                
                function getList(){
                	let d = today;
                	const regex = /-/g;
                    let d_str = d.replace(regex,"")
                	let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=a34977bfdaceeb14a018ad5af883c50c&targetDt="+d_str;
                    $.getJSON(url, function (data) {
                    	  let movieList = data.boxOfficeResult.dailyBoxOfficeList;
                    	  $("#boxoffice").empty();
                    	  $("#boxoffice").append(
                    			  
                    			  
                    			  
                    	    "<table><thead><tr><th>#</th><th>박스오피스 유형</th><th>기간</th><th>순위</th><th>순위 변동</th><th>기존/신규</th><th>영화코드</th><th>영화명</th><th>개봉일</th><th>매출액</th><th>매출액 점유율</th><th>매출액 증감</th><th>매출액 변동율</th><th>누적매출액</th><th>관객수</th><th>관객수 증감</th><th>관객수 변동율</th><th>누적관객수</th><th>상영스크린수</th><th>상영횟수</th></tr></thead><tbody>"
                    	  );

                    	  for (let i in movieList) {
                    	    $("#boxoffice tbody").append(
                    	      "<tr><td>" +
                    	        (parseInt(i) + 1) +
                    	        "</td><td>" +
                    	        movieList[i].boxofficeTyp +
                    	        "</td><td>" +
                    	        movieList[i].showRange +
                    	        "</td><td>" +
                    	        movieList[i].rank +
                    	        "</td><td>" +
                    	        movieList[i].rankInten +
                    	        "</td><td>" +
                    	        movieList[i].rankOldAndNew +
                    	        "</td><td>" +
                    	        movieList[i].movieCd +
                    	        "</td><td>" +
                    	        movieList[i].movieNm +
                    	        "</td><td>" +
                    	        movieList[i].openDt +
                    	        "</td><td>" +
                    	        movieList[i].salesAmt +
                    	        "</td><td>" +
                    	        movieList[i].salesShare +
                    	        "</td><td>" +
                    	        movieList[i].salesInten +
                    	        "</td><td>" +
                    	        movieList[i].salesChange +
                    	        "</td><td>" +
                    	        movieList[i].salesAcc +
                    	        "</td><td>" +
                    	        movieList[i].audiCnt +
                    	        "</td><td>" +
                    	        movieList[i].audiInten +
                    	        "</td><td>" +
                    	        movieList[i].audiChange +
                    	        "</td><td>" +
                    	        movieList[i].audiAcc +
                    	        "</td><td>" +
                    	        movieList[i].scrnCnt +
                    	        "</td><td>" +
                    	        movieList[i].showCnt +
                    	        "</td></tr>"
                    	    );
                    	  }

                    	  $("#boxoffice").append("</tbody></table>");
                    	});

                }
                
                // 화면 진입 시 바로 호출
            	getList();
                
            });//ready
        </script>
        <!-- 
        <script>
	    $(document).ready(function() {
	      var url = 'http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&nation=대한민국';
	      var queryParams = '?ServiceKey=MFNY08550JVCVDU9909W&val001=2018&val002=01';
	
	      $.ajax({
	        url: url + queryParams,
	        method: 'GET',
	        dataType: 'json',
	        success: function(responseData) {
	          // 성공적으로 데이터를 받아왔을 때 처리
	          // responseData는 JSON 객체로 전달됩니다.
	          console.log(responseData);
	          // 원하는 방식으로 데이터를 활용하여 출력하면 됩니다.
	        },
	        error: function(xhr, status, error) {
	          // 오류 처리
	          console.error(status, error);
	        }
	      });
	    });
	  </script> -->
	  
	  <script type="text/javascript">
	  
	  var xhr = new XMLHttpRequest(); 
	  var url = 'http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&nation=대한민국'; 
	  url = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&ServiceKey=MFNY08550JVCVDU9909W&query='엘리멘탈'";
	  // 위의 url로 요청하면 정보를 보내주긴함..!
	  var queryParams = '?' + encodeURIComponent('ServiceKey=MFNY08550JVCVDU9909W'); 
	  queryParams += '&' + encodeURIComponent('val001') + '=' + encodeURIComponent('2018'); 
	  queryParams += '&' + encodeURIComponent('val002') + '=' + encodeURIComponent('01'); 
	  xhr.open('GET', url + queryParams); 

	  xhr.onreadystatechange = function () {
	    if (this.readyState == 4) {
	      if (this.status == 200) {
	        // 요청이 성공하면 데이터를 JSP 페이지로 전달
	        var responseData = JSON.parse(this.responseText);
	        var dataToPass = JSON.stringify(responseData);
	        window.location.href = 'your-jsp-page.jsp?data=' + encodeURIComponent(dataToPass);
	      } else {
	        alert('Error: ' + this.status);
	      }
	    }
	  };

	  xhr.send();

	  </script>
  
</head>
<body>
	<input type="date" id="date"><button id="mybtn">확인</button>
	<!-- <div id="boxoffice">
	    박스 오피스 순위 <sup>*출처 : 영화진흥위원회</sup> <br>
	</div> -->
	<div id="boxdetail"></div>
	
	<%
    // 전달된 데이터 파라미터 가져오기
    String data = request.getParameter("data");
    
    // JSON 데이터를 자바 객체로 파싱
    // 이때, 반드시 JSON 라이브러리가 필요합니다. (예: JSON.simple 라이브러리 사용)
    // JSON.simple 라이브러리를 사용할 경우 아래와 같이 데이터를 파싱할 수 있습니다.
    // org.json.simple.JSONObject 객체를 사용하는 것으로 가정합니다.
    org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
    try {
      org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
      jsonObject = (org.json.simple.JSONObject) parser.parse(data);
    } catch (org.json.simple.parser.ParseException e) {
      e.printStackTrace();
    }
    
    // 여기서부터는 데이터를 활용하여 원하는 방식으로 출력하면 됩니다.
    // 예시: API로부터 가져온 JSON 데이터의 내용 출력
    out.println(jsonObject.toJSONString());
  %>
</body>
</html>



















