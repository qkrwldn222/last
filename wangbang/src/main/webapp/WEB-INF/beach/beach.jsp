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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.bBtn').click(function(){
		var no=$(this).attr("data-no");
		$("#bChoose").hide();
		//location.href="../beach/beach.do?no=" + no;
		
		$.ajax({
			type:'post',
			url:'../beach/beach_blog.do',
			data:{no:no},
			success:function(res){
				//alert(res);
				//console.log("success");
				$('#blogprint').html("");
				$('#blogprint').html(res);
				$('#blogprint').show();
			}
		});
		
		$.ajax({
			type:'post',
			url:'../beach/beach_res.do',
			data:{no:no},
			success:function(res){
				//alert(res);
				//console.log("success");
				$('#resprint').html("");
				$('#resprint').html(res);
				$('#resprint').show();
			}
		});
		
		$.ajax({
			type:'post',
			url:'../beach/beach_location.do',
			data:{no:no},
			success:function(response)
			{
				var result = JSON.parse(response);
				//alert(result);
				container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
				options = { //지도를 생성할 때 필요한 기본 옵션
					center: new kakao.maps.LatLng(result[no-1].x, result[no-1].y), //지도의 중심좌표.
					level: 4 //지도의 레벨(확대, 축소 정도)
				};

				map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
				
				//마커를 표시할 위치와 title 객체 배열입니다 
				var positions = [
				    {
				        title: '광안리 해수욕장', 
				        latlng: new kakao.maps.LatLng(35.153106, 129.118697)
				    },
				    {
				        title: '해운대 해수욕장', 
				        latlng: new kakao.maps.LatLng(35.158732, 129.160376)
				    },
				    {
				        title: '송정 해수욕장', 
				        latlng: new kakao.maps.LatLng(35.178610, 129.199703)
				    },
				    {
				        title: '송도 해수욕장',
				        latlng: new kakao.maps.LatLng(35.075437, 129.016771)
				    },
				    {
				        title: '다대포 해수욕장',
				        latlng: new kakao.maps.LatLng(35.046815, 128.962416)
				    },
				    {
				        title: '감지 해변',
				        latlng: new kakao.maps.LatLng(35.059857, 129.077518)
				    },
				    {
				        title: '임랑 해수욕장',
				        latlng: new kakao.maps.LatLng(35.318485, 129.264321)
				    },
				    {
				        title: '일광 해수욕장',
				        latlng: new kakao.maps.LatLng(35.259314, 129.233954)
				    }
				];

				// 마커 이미지의 이미지 주소입니다
				var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
				    
				for (var i = 0; i < positions.length; i ++) {
				    
				    // 마커 이미지의 이미지 크기 입니다
				    var imageSize = new kakao.maps.Size(24, 35); 
				    
				    // 마커 이미지를 생성합니다    
				    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
				    
				    // 마커를 생성합니다
				    var marker = new kakao.maps.Marker({
				        map: map, // 마커를 표시할 지도
				        position: positions[i].latlng, // 마커를 표시할 위치
				        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				        image : markerImage // 마커 이미지 
				    });
				}
			}
		});
	});
});
</script>
<!-- <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> -->

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
				title: '해수욕장 선호도 그래프',
				values:[<c:forEach var="vo" items="${list}"><c:out value="${vo.count}"/>, </c:forEach>],
				colorset: ['#80acd9', '#e36c6e', '#f2a473', '#a6c48e', '#f8de8a', '#b182ae', '#e6b6b2', '#8b9499'],
				fields: [<c:forEach var="vo" items="${list}">'<c:out value="${vo.title}"/>', </c:forEach>] 
			},
			'donut_width' : 190, 
			'core_circle_radius':0,
			'chartDiv': 'chart',
			'chartType': 'pie',
			'chartSize': {width:700, height:455}
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
				<div id="map" style="height:470px;"></div>
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
							
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=1>광안리</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=2>해운대</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=3>송도</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=4>다대포</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=5>송정</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=6>일광</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=7>임랑</button>
							<button type="button" class="btn mb-1 btn-outline-secondary bBtn" style="margin-right:15px;" data-no=8>감지</button>
							
							<div id="blogprint"></div>
							<div id="resprint"></div>
							<h4 class="text-secondary" id="bChoose" style="margin-bottom: 20px; margin-top: 30px;"><b>정보를 표시할 해수욕장을 선택해주세요!</b></h4>
							<%-- <h4 class="text-secondary" style="margin-bottom: 20px; margin-top: 30px;"><b>블로그 리뷰</b></h4>
								<div class="row" style="margin-top:20px;">
								<c:forEach var="bvo" items="${tList }">
		                            <div class="col-lg-6">
		                                <div class="card">
		                                    <div class="card-body">
		                                    	<a href="${bvo.link }"><img src="${bvo.image }" style="float:left; margin-right:20px;"></a>
		                                        <a href="${bvo.link }"><h6 class="card-title">${bvo.title }</h6></a>
		                                        <p class="card-text">${bvo.content }</p>
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
		                                        <p class="card-text">${rvo.info }</p>
		                                    </div>
		                                </div>
		                            </div>
		                        </c:forEach>
                            </div> --%>
                            
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
	center: new kakao.maps.LatLng(35.1664796,129.0855049), //지도의 중심좌표.
	level: 9 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
    {
        title: '광안리 해수욕장', 
        latlng: new kakao.maps.LatLng(35.153106, 129.118697)
    },
    {
        title: '해운대 해수욕장', 
        latlng: new kakao.maps.LatLng(35.158732, 129.160376)
    },
    {
        title: '송정 해수욕장', 
        latlng: new kakao.maps.LatLng(35.178610, 129.199703)
    },
    {
        title: '송도 해수욕장',
        latlng: new kakao.maps.LatLng(35.075437, 129.016771)
    },
    {
        title: '다대포 해수욕장',
        latlng: new kakao.maps.LatLng(35.046894, 128.966245)
    },
    {
        title: '감지 해변',
        latlng: new kakao.maps.LatLng(35.059857, 129.077518)
    },
    {
        title: '임랑 해수욕장',
        latlng: new kakao.maps.LatLng(35.318485, 129.264321)
    },
    {
        title: '일광 해수욕장',
        latlng: new kakao.maps.LatLng(35.262502, 129.233721)
    }
];

// 마커 이미지의 이미지 주소입니다
var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지 
    });
}

</script>
</body>
</html>