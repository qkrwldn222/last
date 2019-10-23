<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37a7d7363032a119ca4e0bf582fc0ef5&libraries=services"></script>
<script type="text/javascript">
$(function(){
	
	$('.restbtn').click(function(){
		$('#choice').val(1);
		$.ajax({
			type:'post',
			url:'../recommend/rest_recommend.do',
			success:function(res){
				var content= res.split(",");
				var html="";
				for(i=0; i <content.length; i++){
					html+='<input type=button class="btn btn-sm btn-warning" onclick="rest_recommand(\''+content[i]+'\')" value='+content[i]+'>'+'&nbsp;'
				}
				$('#print').html(html);
			}
		})
	});
	
	$('.tourbtn').click(function(){
		$('#choice').val(2);
		$.ajax({
			type:'post',
			url:'../recommend/tour_recommend.do',
			success:function(res){
				var content= res.split(",");
				var html="";
				for(i=0; i <content.length; i++){
					html+='<input type=button class="btn btn-sm btn-warning" onclick="tour_recommand(\''+content[i]+'\')" value='+content[i]+'>'+'&nbsp;'
				}
				$('#print').html(html);
			}
		})
	});
	
	$('.spotbtn').click(function(){
		$('#choice').val(3);
		$.ajax({
			type:'post',
			url:'../recommend/spot_recommend.do',
			success:function(res){
				var content= res.split(",");
				var html="";
				for(i=0; i <content.length; i++){
					html+='<input type=button class="btn btn-sm btn-warning" onclick="spot_recommand(\''+content[i]+'\')" value='+content[i]+'>'+'&nbsp;'
				}
				$('#print').html(html);
			}
		})
	});
	
});

function rest_recommand(rd){	
	$.ajax({
		type:'post',
		url:'../recommend/rest_recommend_data.do',
		data:{fd:rd,no:1},
		success:function(response){
			$('#list').html(response);
		}	
	});
}

function tour_recommand(rd){
	$.ajax({
		type:'post',
		url:'../recommend/rest_recommend_data.do',
		data:{fd:rd,no:2},
		success:function(response){
			$('#list').html(response);
		}	
	});
}

function spot_recommand(rd){
	$.ajax({
		type:'post',
		url:'../recommend/rest_recommend_data.do',
		data:{fd:rd,no:3},
		success:function(response){
			$('#list').html(response);
		}	
	});
}
</script>
</head>
<body>
		<div class="row">
		<div class="col-lg-12">
			<div class="card">
				
				<h2 class="m-0 font-weight-bold text-primary text-center">꼬꼬마
					형태소 분석기를 사용한 네이버 블로그 부산 여행 추천!!</h2>
				<table class="table">
					<tr>
						<td class="text-center"><input type="button"
							class="btn btn-lg btn-success restbtn" value="맛집" data-no="1">
							<input type="button" class="btn btn-lg btn-success tourbtn"
							value="관광지" data-no="2"> <input type="button"
							class="btn btn-lg btn-success spotbtn" value="숙박" data-no="3">
							<input type="hidden" id="choice">
						</td>
					</tr>
					<tr>
						<td id="print" class="text-center"></td>
					</tr>
				</table>
				
				<div class="container-fluid"><div id="list"></div></div>
				</div>
			</div>
		</div>
	

	<div class="card shadow mb-4">

		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Map</h4>
				<div id="daumMap"></div>
			</div>
		</div>

	</div>
	
</body>
</html>