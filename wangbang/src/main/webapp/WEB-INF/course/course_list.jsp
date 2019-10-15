<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>원, 선, 사각형, 다각형 표시하기</title>
    
</head>
<body>
<div id="map" style="width:600px;height:600px;"></div>    
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f1979c1b260c4a439f2975fd6f70c01"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(35.158240, 129.160362), // 지도의 중심좌표
        level: 9 // 지도의 확대 레벨
    };  
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
var linePath = [
    new kakao.maps.LatLng(35.075419, 129.016675),
    new kakao.maps.LatLng(35.153063, 129.118494),
    new kakao.maps.LatLng(35.158240, 129.160362) 
];

// 지도에 표시할 선을 생성합니다
var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: '#000069', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid' // 선의 스타일입니다
});

// 지도에 선을 표시합니다 
polyline.setMap(map);

</script>

<a href="https://map.kakao.com/?sName=부산송도해수욕장&eName=광안리해수욕장">song -> kwang</a>
</body>
</html>
