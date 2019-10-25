<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/Nwagon.css" rel="stylesheet">
<script src="../js/Nwagon_no_vml.js"></script>
<script src="../js/Nwagon.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!--     <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        	['해수욕장명', '검색횟수'],
            <c:forEach var="vo" items="${list}">
            ['<c:out value="${vo.title}"/>',   <c:out value="${vo.count}"/>],
            </c:forEach>
        ]);

        var options = {
          title: '',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script> -->
</head>
<body>

<div class="row">
	<div class="col-lg-6 col-md-12">
		<div class="card">
			<div class="card-body">
				<h3 class="text-primary"><b>해수욕장 선호도 조사</b></h3>
				<!-- <div id="piechart_3d" style="width: 700px; height: 500px;"></div> -->
				<div id="chart"></div>
	<script>
		
		var options = {
			'dataset': {
				title: 'Web accessibility status',
				values:[<c:forEach var="vo" items="${list}"><c:out value="${vo.count}"/>, </c:forEach>],
				colorset: ['#56b4e9', '#e69f00', '#cc79a7', '#009e73', '#0072b2'],
				fields: [<c:forEach var="vo" items="${list}">'<c:out value="${vo.title}"/>', </c:forEach>] 
			},
			'donut_width' : 150, 
			'core_circle_radius':0,
			'chartDiv': 'chart',
			'chartType': 'pie',
			'chartSize': {width:700, height:500}
		};

		Nwagon.chart(options);
	</script>
			</div>
		</div>
	</div>  
	<div class="col-lg-6 col-md-12">
		<div class="card">
			<div class="card-body">
				<h3 class="text-primary"><b>Map</b></h3>
				<div id="map" style="height:500px;"></div>
			</div>
		</div>
	</div>  
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body pb-0 d-flex justify-content-between">
						<div class="card-content">
							<h3 class="text-primary" style="margin-bottom: 20px;"><b>Info</b></h3>
							
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">광안리</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">해운대</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">송도</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">다대포</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">송정</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">일광</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">임랑</button>
							<button type="button" class="btn mb-1 btn-outline-secondary" style="margin-right:15px;">감지</button>
							
							<h4 class="text-secondary" style="margin-bottom: 20px; margin-top: 30px;"><b>블로그 리뷰</b></h4>
							<div class="row" style="margin-top:20px;">
								<c:forEach var="bvo" items="${tList }">
		                            <div class="col-lg-6">
		                                <div class="card">
		                                    <div class="card-body">
		                                    	<a href="${bvo.link }"><img src="${bvo.image }" style="float:left; margin-right:20px;"></a>
		                                        <a href="${bvo.link }"><h6 class="card-title">${bvo.title }</h6></a>
		                                        <!-- <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6> -->
		                                        <p class="card-text">${bvo.content }</p>
		                                        <!-- <p class="card-text d-inline"><small class="text-muted">Last updated 3 mins ago</small> -->
		                                    </div>
		                                </div>
		                            </div>
		                        </c:forEach>
                            </div>
                            
							<h4 class="text-secondary" style="margin-bottom: 20px; margin-top: 30px;"><b>주변 맛집</b></h4>
							<div class="row" style="margin-top:20px;">
								<c:forEach var="rvo" items="${rList }">
		                            <div class="col-md-6 col-lg-3">
		                                <div class="card">
		                                    <div class="card-body">
		                                    	<img src="${rvo.image }" style="margin-bottom:15px">
		                                        <h6 class="card-title">${rvo.title }</h6>
		                                        <!-- <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6> -->
		                                        <p class="card-text">${rvo.info }</p>
		                                        <!-- <p class="card-text d-inline"><small class="text-muted">Last updated 3 mins ago</small> -->
		                                    </div>
		                                </div>
		                            </div>
		                        </c:forEach>
                            </div>
                            
                            
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a93962adc2c6bebc2cec42c74839b639"></script>
<script type="text/javascript">
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(35.1529523,129.1192152), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
</script>
</body>
</html>