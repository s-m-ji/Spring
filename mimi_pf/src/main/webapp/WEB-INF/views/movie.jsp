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
                //$("#boxoffice").one("click",".movie", function(){
                    let d = $(this);
                    let movieCd = d.attr("id");
                    
                    /*
                 // 클릭 이벤트가 이미 실행된 경우, 더 이상 처리하지 않음
                    if (d.data("clicked")) {
                        return;
                    }
                 */
                    let url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=a34977bfdaceeb14a018ad5af883c50c&movieCd="+movieCd;
                    
                    $.getJSON(url, function(res){
                        let movie = res.movieInfoResult.movieInfo;
                        d.append("<hr>");
                        d.append("개봉일 : "+movie.openDt+"<br>");
                        d.append("감독 : "+movie.directors[0].peopleNm+"<br>");
                        d.append("주연 : "+movie.actors[0].peopleNm+", "+movie.actors[1].peopleNm+", "+movie.actors[2].peopleNm);
                        d.append("<hr>");
                        
                       /*
                     // 클릭 이벤트가 실행되었음을 표시
                        d.data("clicked", true);
						*/
						
                    });
                     // 클릭 이벤트를 한 번만 실행하도록 이벤트 핸들러 제거
                        d.off("click");
                     // TODO 클릭이 계속 되는데요옵..? 그치만 추후 프론트 구현 시에 클릭으로 상세 화면을 보여줄게 아니기 때문에 이건 패스해도 괜찮을거라 생각쓰 
                });
                
                function getList(){
                	let d = today;
                	const regex = /-/g;
                    let d_str = d.replace(regex,"")
                	let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=a34977bfdaceeb14a018ad5af883c50c&targetDt="+d_str;
                    $.getJSON(url, function(data) {
                        let movieList = data.boxOfficeResult.dailyBoxOfficeList;
                        $("#boxoffice").empty();
                        $("#boxoffice").append(d+" 박스 오피스 순위 <sup>*출처 : 영화진흥위원회</sup><br><br>");
                        for(let i in movieList){
                            $("#boxoffice").append(
                            		"<div class='movie' id="+movieList[i].movieCd+">"
                            		+ (parseInt(i) + 1) 
                            		+ ". "+movieList[i].movieNm+" / 누적 관객 수 : "
                            		+ movieList[i].audiAcc+"명</div><hr>"
                            		);
                            console.log(movieList[i].movieCd);
                        }
                      });
                }
                
                // 화면 진입 시 바로 호출
            	getList();
                
            });//ready
        </script>

</head>
<body>
<input type="date" id="date"><button id="mybtn">확인</button>
<div id="boxoffice">
    박스 오피스 순위 <sup>*출처 : 영화진흥위원회</sup> <br>
    
</div>
</body>
</html>