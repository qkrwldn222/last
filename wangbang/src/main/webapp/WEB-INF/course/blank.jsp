<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<title></title>
<body>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f1979c1b260c4a439f2975fd6f70c01"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.179820, 129.074991), // 지도의 중심좌표
	        level: 9 // 지도의 확대 레벨
	    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


	// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
	var markers = [];

	// 마커 하나를 지도위에 표시합니다 
	addMarker(new kakao.maps.LatLng(35.179820,129.074991 ));
	<c:if test="${mList!=null }">
		<c:forEach var="vo" items="${mList}">
			addMarker(new kakao.maps.LatLng(<c:out value="${vo.wgsx}"/>, <c:out value="${vo.wgsy}"/>));
		</c:forEach>	
	</c:if> 
	// 마커를 생성하고 지도위에 표시하는 함수입니다
	function addMarker(position) {
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        position: position
	    });

	    // 마커가 지도 위에 표시되도록 설정합니다
	    marker.setMap(map);
	    
	    // 생성된 마커를 배열에 추가합니다
	    markers.push(marker);
	}

	// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
	function setMarkers(map) {
	    for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(map);
	    }            
	}

	// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
	function showMarkers() {
	    setMarkers(map)    
	}

	// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
	function hideMarkers() {
	    setMarkers(null);    
	}
</script>
	
</body>
</html>
