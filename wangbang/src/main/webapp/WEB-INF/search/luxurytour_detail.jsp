<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var favCheck = false;
$(function() {       
	var id = $('#memId').val();
	var sid = $('#sid').val();
	var link = '../search/luxurytour_detail.do';
	var title = $('#title').val();
	
	$.ajax({
		type:'post',
		url:'../fav/favCheck.do',
		data:{id:id, sid:sid},
		success:function(res){
			if(res=="1") {
				$(".fave").toggleClass("animate");
				favCheck = true;
			}
		}
	});
	
	  $(".fave").click(function() { 
		if(!favCheck) {
			if(confirm("찜하시겠습니까?")) {
				$.ajax({
					type:'post',
					url:'../fav/favInsert.do',
					data:{id:id, sid:sid, link:link, title:title},
					success:function(res) {
						$(".fave").toggleClass("animate");
						favCheck = true;
					}
				});
			}else {
				return;
			}
		} else {
			if(confirm("찜 삭제하시겠습니까?")) {
				$.ajax({
					type:'post',
					url:'../fav/favDelete.do',
					data:{id:id, sid:sid},
					success:function(res) {
						$(".fave").toggleClass("animate");
						favCheck = true;
					}
				});
			}else {
				return;
			}
		}
	  });
});
</script>
<style type="text/css">
.fave {
	width: 70px;
	margin-right: 70px;
	height: 50px;
	background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/202839/add-to-favorites.png);
	background-position: 0 0;
}
.fave:hover {
	cursor: pointer;
}
.animate {
	background-position: -3519px 0;
	transition: background .8s steps(55);
}
</style>
</head>
<body>
<input type="hidden"  id="XY" value="${vo.wgsx}">
<input type="hidden"  id="YY" value="${vo.wgsy}">
<input type="hidden" id="memId" value="${sessionScope.id }" >
<input type="hidden" id="sid" value="${vo.dataSid }" >
<input type="hidden" id="title" value="${vo.dataTitle }" >

  <div class="card shadow mb-4">
		<div class="card-header py-3">
			<h3 class="m-0 font-weight-bold text-primary">명품 관광 상세보기 [${vo.dataTitle }]</h3>
		</div>
		<div class="container-fluid">
		<div class="row">
		  <div class="col-12">
		   <table class="card">
		   <ul class="metismenu" id="menu">
		    <tr>
		     <td class="text-center" width=30% rowspan="7">
		    	<img src="${vo.mainimgthumb }">
		     </td>
		     <td class="text-left" colspan="2"><h4 class="text-center"><b>${vo.dataTitle }</b></h4></td>
		    </tr>
			    <tr>
			     <td width=8% class="text-right"><i class="fa fa-won"></i>&nbsp;&nbsp;금　　액&nbsp;&nbsp;&nbsp;</td>
				 <c:if test="${vo.price!='-' }">
				    <td colspan="3">${vo.price}</td>
				 </c:if>
				 <c:if test="${vo.price=='-' }">
				 	<td colspan="3">채팅 문의 바랍니다.</td>
				 </c:if>
			    </tr>
		    <tr>
		  	  <td width=8% class="text-right"><i class="icon-map menu-icon">&nbsp;</i>주　　소&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.addr}</td>
		    </tr>
		    <tr>
		      <td width=8% class="text-right"><i class="icon-map menu-icon">&nbsp;</i>상세주소&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.detail}</td>  
		    </tr>
		    <tr>
		     <td width=8% class="text-right"><i class="icon-phone menu-icon">&nbsp;</i>전화번호&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.tel}</td>
		    </tr>
		    <tr>
		     <td width=8% valign="top" align="right"><i class="icon-list menu-icon">&nbsp;</i>정　　보&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3" style="white-space:pre-line;" valign="top">${vo.dataContent}</td>
		    </tr>
		    <c:if test="${sessionScope.id!=null }" >
			    <tr>
			      <td colspan="3" ></td>
			      <td width="10%" >
			        <div class="text-right" >
			          <section id="favorite" class="fave" ></section> <br>
			        </div>
			      </td>
			    </tr>
			</c:if>
		    </ul>
		   </table>
		  </div>
		</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				 <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                            <ul class="metismenu" id="menu">
                                <h4 class="card-title"><i class="icon-camera menu-icon">&nbsp;</i>사진 더보기</h4>
                            </ul>
                                <p class="text-muted"><code></code>
                                </p>
                                <div id="accordion-two" class="accordion">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0" data-toggle="collapse" data-target="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1"><i class="fa" aria-hidden="true"></i>Photo One</h5>
                                        </div>
                                        <div id="collapseOne1" class="collapse show" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img1thumb}" width="100%" height="100%"></div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2"><i class="fa" aria-hidden="true"></i>Photo Two</h5>
                                        </div>
                                        <div id="collapseTwo2" class="collapse" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img2thumb}" width="100%" height="100%"></div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseThree3" aria-expanded="false" aria-controls="collapseThree3"><i class="fa" aria-hidden="true"></i>Photo Three</h5>
                                        </div>
                                        <div id="collapseThree3" class="collapse" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img3thumb}" width="100%" height="100%"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
         </div> 
         <div class="col-lg-6">
                  <div class="card">
                      <div class="card-body">
                      		<ul class="metismenu" >
                                <h4 class="card-title"><i class="fa fa-map-marker" ></i>&nbsp;&nbsp;오시는 길&nbsp;&nbsp;&nbsp;</h4>
                            </ul>
                      	   <!-- 지도 -->
				           <div id="map" style="width:auto; height:600px;"></div>
<!-- 				           <div style="height: 100%; width: 100%">
				           	<div id="map"></div>
				           </div> -->
							<!-- 지도 -->
							<table class="card">
		  					 <ul class="metismenu" id="menu">
		  					 <!-- 
		  					 				 <c:if test="${vo.price!='-' }">
											    <td colspan="3">${vo.price}</td>
											 </c:if>
											 <c:if test="${vo.price=='-' }">
											 	<td colspan="3">채팅 문의 바랍니다.</td>
											 </c:if>
		  					  -->
								<tr>
							     <td width=20% valign="top" align="right"><i class="fa fa-subway"></i>&nbsp;&nbsp;지하철&nbsp;&nbsp;&nbsp;</td>
							      <c:if test="${vo.trafln!=null }">
							      	<td colspan="3" style="white-space:pre-line;" valign="top">${vo.trafln}</td>
							      </c:if>
							      <c:if test="${vo.trafln==null }">
									<td colspan="3">채팅 문의 바랍니다.</td>
									</c:if>
							    </tr>
								<tr>
							     <td width=20% valign="top" align="right"><i class="fa fa-bus"></i>&nbsp;&nbsp;버　스&nbsp;&nbsp;&nbsp;</td>
							      <c:if test="${vo.traOut!=null }">
								      <td colspan="3" style="white-space:pre-line;" valign="top">${vo.traOut}</td>
							      </c:if>
							      <c:if test="${vo.traOut==null }">
									<td colspan="3">채팅 문의 바랍니다.</td>
								</c:if>
							    </tr>
							</ul>
							</table>
                      </div>
                    </div>
          </div>
			</div> <!-- row -->
		</div> <!-- container-fluid -->
		                  
        
</div>


<!-- 
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c94e0a61762b6300bf074a911a06f61"></script>
			 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c94e0a61762b6300bf074a911a06f61&libraries=services,clusterer,drawing"></script>
			<script>
			
			var X = document.getElementById("XY").value;
			var Y = document.getElementById("YY").value;

			if(X != "-" || X != "-"){
			/* alert("1"); */
			var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
		        center : new kakao.maps.LatLng(X , Y), // 지도의 중심좌표 
		        level: 3 // 지도의 확대 레벨 
		    });
			var imageSrc ='../images/kakaoicon.png';
			//var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
		    imageSize = new kakao.maps.Size(50, 69), // 마커이미지의 크기입니다
		    imageOption = {offset: new kakao.maps.Point(X, Y)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		      
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		    markerPosition = new kakao.maps.LatLng(X, Y); // 마커가 표시될 위치입니다

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition, 
		    image: markerImage // 마커이미지 설정 
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);  
			/*  var clusterer = new kakao.maps.MarkerClusterer({
 			        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
			        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
			        minLevel: 10, // 클러스터 할 최소 지도 레벨  
			    styles: [{
			        width : '53px', height : '52px',
			        //background: 'url(cluster.png) no-repeat',
			        background: 'rgba(51, 204, 255, .8)',
			        color: '#000',
			        textAlign: 'center',
			        lineHeight: '54px'
			    }]
			 });

 			 var marker = new kakao.maps.Marker({
				    position: new kakao.maps.LatLng( X , Y )
				});
				clusterer.addMarker(marker);	 */
			}else{
				/* alert("2"); */
				var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
			        center : new kakao.maps.LatLng("35.114450" , "129.039313" ), // 지도의 중심좌표 
			        level: 5 // 지도의 확대 레벨 
			    });
			}
			</script>

</body>
</html>