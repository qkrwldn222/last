<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	/* $.ajax({
		type:'post',
		url:'../course/blank.do',
		success:function(response)
		{
			$('#map').html(response);
		}
		
	}); */
	
	$('.genreBtn').click(function(){
		var no=$(this).attr("data-no");
		if(no==1){
			var color="success"
		}
		if(no==2){
			var color="danger"
		}
		if(no==3){
			var color="info"
		}
		$.ajax({
			type:'post',
			url:'../course/recommand_data.do',
			data:{no:no},
			success:function(response)
			{
				//alert(response);
				//console.log(response);
				var genre_data=response.split(",");
				var html="";
				for(i=0;i<genre_data.length;i++)
				{
					html+='<input type="button" class="btn btn-lg btn-'+color+'" data-no="'+no+'" onclick="recommand(\''+genre_data[i]+'\',\''+no+'\')" value='+genre_data[i]+'>&nbsp;';
				}
				
				$('#print').html(html);
			}
		})
	});
});
function recommand(rd,sno){
	$.ajax({
		type:'post',
		url:'../course/course_list_data.do',
		data:{fd:rd,sno:sno},
		success:function(response)
		{
			
			$('#map').html(response);
		}
		
	});
}
</script>
</head>
<body>
<div class="row">
   <div class="col-12">
         <div class="card">
            <div class="card-body">
            	<table>
            		<tr>
            			<td>
            				<input type=button class="btn btn-lg btn-success genreBtn" value="숙박" data-no="1">
	                		<input type=button class="btn btn-lg btn-danger genreBtn" value="맛집" data-no="2">
	                		<input type=button class="btn btn-lg btn-info genreBtn" value="놀거리" data-no="3">
            			</td>
            		</tr>
            		<tr>
            			<td>
            				<br>
            			</td>
            		</tr>
            		<tr>
            			<td id="print">
            			</td>
            		</tr>
            	</table>	

            <!-- <table>
            <tr width=10% class="text-left" style="margin: 10px;">
               <td>
				<a href="#"><input type="button" value="숙박" class="btn btn-md" style="background:#7571f9; color:white";></a>
               </td>
               <th width=90% class="text-left">
                  <input type="button" value="바다가보이는" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="호텔" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="야경" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="시설이 좋은" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="저렴한" class="btn btn-md btn-info" style="margin: 5px;">
               </th>
            </tr>
            <tr width=10% class="text-left" style="margin: 10px;">
               <td>
                  <a href="#"><input type="button" value="맛집" class="btn btn-md" style="background:#7571f9; color:white";></a>
               </td>
               <th width=90% class="text-left">
                  <input type="button" value="고기" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="해산물" class="btn btn-md btn-info" style="margin: 5px;">
                   <input type="button" value="뷔페" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="한식" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="양식" class="btn btn-md btn-info" style="margin: 5px;">
               </th>
            </tr>
            <tr width=10% class="text-left" style="margin: 10px;">
               <td>
               	<a href="#"><input type="button" value="관광" class="btn btn-md" style="background:#7571f9; color:white";></a>
               </td>
              <th width=90% class="text-left">
                  <input type="button" value="야경" class="btn btn-md btn-info" style="margin: 5px;" onclick="">
                  <input type="button" value="도보" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="꼭 가야할 명소" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="영화" class="btn btn-md btn-info" style="margin: 5px;">
                  <input type="button" value="레저스포츠" class="btn btn-md btn-info" style="margin: 5px;">
              </th>
            </tr>
         </table> -->
               <div class="table-responsive">
               </div>
            </div>
         </div>
      </div>
   </div>
<div class="row">
   <div class="col-6">
      <div class="card">
         <div class="card-body">
            <div id="map" style="width:auto; height:600px;"></div>
         </div>
      </div>
   </div>
   <div class="col-6" >
      <div class="col-3" style="display: inline-block;">
         <div class="card">
         
            <div class="card-body">
               <h4 class="card-title">숙박</h4>
                  <div class="table-responsive"></div>
            </div>
         </div>
      </div>
      <div class="col-3" style="display: inline-block;">
         <div class="card">
            <div class="card-body">
               <h4 class="card-title">맛집</h4>
                  <div class="table-responsive"></div>
            </div>
         </div>
      </div>
      <div class="col-3" style="display: inline-block;">
         <div class="card">
            <div class="card-body">
               <h4 class="card-title">관광</h4>
                  <div class="table-responsive"></div>
            </div>
         </div>
      </div>
      <div class="col-3">
         <div class="card">
            <div class="card-body">
               <h4 class="card-title">log</h4>
                  <div class="table-responsive"></div>
            </div>
         </div>
      </div>
   </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f1979c1b260c4a439f2975fd6f70c01"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = { 
        center: new kakao.maps.LatLng(35.179820, 129.074991), // 지도의 중심좌표
        level: 9 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

</script>

</body>
</html>