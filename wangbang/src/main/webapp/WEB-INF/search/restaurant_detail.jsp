<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">
                        <div class="col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">${vo.dataTitle }</h4>
                                    <img src="${vo.mainimgthumb }">
                                </div>
                            </div>
                            
                        </div>    
                        <div class="col-lg-6 col-md-12">
                            <div class="card card-widget">
                                <!-- <div class="card-body"> -->
                                <!-- <h4 class="card-title">Slides With indicators</h4> -->
                                <div class="bootstrap-carousel">
                                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                        <ol class="carousel-indicators">
                                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                                        </ol>
                                        <div class="carousel-inner">
                                            <div class="carousel-item active">
                                                <img class="d-block w-100" src="${vo.img1thumb }" alt="First slide" style="border-radius: 0.625rem;">
                                            </div>
                                            <div class="carousel-item">
                                                <img class="d-block w-100" src="${vo.img2thumb }" alt="Second slide" style="border-radius: 0.625rem;">
                                            </div>
                                            <div class="carousel-item">
                                                <img class="d-block w-100" src="${vo.img3thumb }" alt="Third slide" style="border-radius: 0.625rem;">
                                            </div>
                                        </div><a class="carousel-control-prev" href="#carouselExampleIndicators" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span> </a><a class="carousel-control-next" href="#carouselExampleIndicators"
                                            data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                                    </div>
                                </div>
                            <!-- </div> -->
                            </div>
                            
                        </div>
                       
                    </div>
                


                <div class="row">
                    <div class="col-xl-3 col-lg-6 col-sm-6 col-xxl-6">

                        <div class="card">
                            <div class="chart-wrapper mb-4">
                                <div class="px-4 pt-4 d-flex justify-content-between">
                                    <div>
                                        <h4>Sales Activities</h4>
                                        <p>Last 6 Month</p>
                                    </div>
                                    <div>
                                        <span><i class="fa fa-caret-up text-success"></i></span>
                                        <h4 class="d-inline-block text-success">720</h4>
                                        <p class=" text-danger">+120.5(5.0%)</p>
                                    </div>
                                </div>
                                <div>
                                        <canvas id="chart_widget_3"></canvas>
                                </div>
                            </div>
                            <div class="card-body border-top pt-4">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <ul>
                                            <li>5% Negative Feedback</li>
                                            <li>95% Positive Feedback</li>
                                        </ul>
                                        <div>
                                            <h5>Customer Feedback</h5>
                                            
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div id="chart_widget_3_1"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-sm-6 col-xxl-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">맛집 정보</h4>
                                <div id="activity">
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        
                                        <div class="media-body">
                                            <h5>주차 정보</h5>
                                            <p class="mb-0">I shared this on my fb wall a few months back,</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        
                                        <div class="media-body">
                                            <h5>쉬는 날</h5>
                                            <p class="mb-0">${vo.holiday }</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        
                                        <div class="media-body">
                                            <h5>주소</h5>
                                            <p class="mb-0">${vo.addr } - ${vo.detail }</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        <div class="media-body">
                                            <h5>오는 길</h5>
                                            <p class="mb-0">${vo.trafin }</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        <div class="media-body">
                                            <h5>가는 길</h5>
                                            <p class="mb-0">${vo.trafout }</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media border-bottom-1 pt-3 pb-3">
                                        <div class="media-body">
                                            <h5>홈페이지</h5>
                                            <p class="mb-0">${vo.userHomepage }</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                    <div class="media pt-3 pb-3">
                                        <div class="media-body">
                                            <h5>Facebook Post 30 Comments</h5>
                                            <p class="mb-0">I shared this on my fb wall a few months back,</p>
                                        </div><span class="text-muted "></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-12 col-sm-12 col-xxl-12">
                        <div class="card">
                            <div class="card-body">
                                    <h4 class="card-title mb-0">Location</h4>
                                <div id="map" style="width:700px;height:400px;">
                                </div>
                                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37a7d7363032a119ca4e0bf582fc0ef5"></script>
                               <script>
                               var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                               mapOption = {
                                   center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                                   level: 3 // 지도의 확대 레벨
                               };  

                           // 지도를 생성합니다    
                           var map = new kakao.maps.Map(mapContainer, mapOption); 

                           // 주소-좌표 변환 객체를 생성합니다
                           var geocoder = new kakao.maps.services.Geocoder();

                           // 주소로 좌표를 검색합니다
                           geocoder.addressSearch("서울특별시 월드컵 경기장", function(result, status) {

                               // 정상적으로 검색이 완료됐으면 
                                if (status === kakao.maps.services.Status.OK) {

                                   var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                   // 결과값으로 받은 위치를 마커로 표시합니다
                                   var marker = new kakao.maps.Marker({
                                       map: map,
                                       position: coords
                                   });

                                   // 인포윈도우로 장소에 대한 설명을 표시합니다
                                   var infowindow = new kakao.maps.InfoWindow({
                                       content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                                   });
                                   infowindow.open(map, marker);

                                   // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                                   map.setCenter(coords);
                               } 
                           }
</script>
                            </div>        
                        </div>
                    </div>
                </div>
      
        </body>
</html>