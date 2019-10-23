<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#weatherPrint tr td {
	border: 1px solid #FFFFFF;
}
#weatherPrint td {
	background-color: #E1F0F8;
	padding: 10px;
	font-size: 15px;
}
#wdetail tr td {
	border: 0px;
}
#wdetail tr td {
	padding: 3px;
	font-size: 18px;
	width: 14%;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js" ></script>
<script type="text/javascript">
$(function(){
	$('.getCulDetail').click(function(){
		var param = $(this).val().split(',');
		var cate = param[0];
		var id = param[1];
		console.log("cate : " + cate);
		console.log("id : " + id);
		$.ajax({
			type:'post',
			url:'../goodTrip/getCultureDetail.do',
			data:{cate:cate, id:id},
			success:function(res){
				console.log("success");
				$('#printCultureDetail').html("");
				$('#printCultureDetail').html(res);
				$('#printCultureDetail').show();
			}
		});
	});
});
</script>
</head>
<body>
<div class="card shadow mb-4">
	<div class="row">
		<div class="card-header py-3">
			<h2 class="m-0 font-weight-bold text-primary text-center">&nbsp;지금 부산은?</h2>
		</div>
	</div>
	<br>
	<div class="container-fluid" >
		<h4 class="m-0 font-weight-bold text-secondary">이번 주 부산 날씨</h4><br/>
		<table class="table" id="weatherPrint" >
			<tr>
				<c:forEach var="weather" items="${weathers }">
					<td class="text-center" >
						${weather.date }
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="weather" items="${weathers }">
					<td class="text-center" >
						<table class="table" id="wdetail" >
							<tr>
								<td class="text-center" >
									${weather.amTem }℃
									&nbsp;&nbsp;/&nbsp;&nbsp;
									${weather.pmTem }℃
								</td>
							</tr>
							<%-- <tr>
								<td class="text-center" >${weather.amRain }% / ${weather.pmRain }%</td>
							</tr> --%>
							<tr>
								<td class="text-center" >
									<img src="${statueIcons[weather.amStatue] }" width="30px" height="30px" />
									&nbsp;&nbsp;/&nbsp;&nbsp;
									<img src="${statueIcons[weather.pmStatue] }" width="30px" height="30px" />
								</td>
							</tr>
						</table>
					</td>
				</c:forEach>
			</tr>
		</table>
		<br> <hr/> <br>
	</div>
	<div class="container-fluid" >
		<h4 class="m-0 font-weight-bold text-secondary">지금 부산 행사/공연/전시</h4><br/>
		<div class="row">
			<c:if test="${fn:length(clist) == 0}" >
				<div class="col-lg-6">
					 <div class="card">
					 	<div class="card-body">
					 		<h4 class="card-title">진행중인 문화행사가 없네요 ㅜ.ㅜ</h4>
					 	</div>
					 </div>
				</div>
			</c:if>
			
			<c:if test="${fn:length(clist) > 0}" >
				<div class="col-lg-6">
					<div class="card">
					 	<div class="card-body">
					 		<div class="bootstrap-carousel">
					 			<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
					 				<ol class="carousel-indicators">
					 					<c:forEach var="cvo" items="${clist }" varStatus="s">
					 						<li data-target="#carouselExampleCaptions" data-slide-to="${s.index }" class="text-center ${s.index==0?'active':'' }" ></li>
					 					</c:forEach>
					 				</ol>
					 				<div class="carousel-inner">
					 					<c:forEach var="cvo" items="${clist }" varStatus="s">
					 						<div class="carousel-item ${s.index==0?'active':'' }">
					 							<div class="carousel-caption d-none d-md-block" style="position: static;" >
					 								<table class="table table-borderless" >
					 									<tr>
					 										<td class="text-left" width="70%" colspan="2" >
					 											<font color="black" size="4em" >&nbsp;${cvo.dataTitle }</font>
					 										</td>
					 										<td width="30%" colspan="2" ><font color="darkgray" >${cvo.cateKor }</font></td>
					 									</tr>
					 									<tr>
					 										<td colspan="4" class="text-left" >
					 											<font color="gray" >${cvo.dataContent }</font>
					 										</td>
					 									</tr>
					 									<tr>
					 										<td width="20%" ><font color="black" >시작일</font></td>
															<td width="30%" ><font color="black" >${cvo.startDate }</font></td>
					 										<td width="20%" ><font color="black" >종료일</font></td>
					 										<td width="30%" ><font color="black" >${cvo.endDate }</font></td>
					 									</tr>
					 									<tr>
					 										<td class="text-center" colspan="4" >
					 											<button type="button" class="btn btn-primary getCulDetail" value="${cvo.categoryCode1 },${cvo.dataSid}" >더보기...</button>
					 										</td>
					 									</tr>
					 								</table>
					 							</div>
					 						</div>
					 					</c:forEach>
					 				</div>
					 			</div>
					 			<a data-slide="prev" href="#carouselExampleCaptions" class="carousel-control-prev">
					 					<span class="carousel-control-prev-icon"></span>
										<span class="sr-only">Previous</span>
								</a>
								<a data-slide="next" href="#carouselExampleCaptions" class="carousel-control-next">
									<span class="carousel-control-next-icon"></span>
									<span class="sr-only">Next</span>
								</a>
					 		</div>
					 	</div>
					 </div>
				</div>
			</c:if>
			
			<div class="col-lg-6"  >
				<div class="card">
					<div class="card-body" id="printCultureDetail" style="display: hidden;" >
					</div>
				</div>
			</div>
		</div>
		<hr/>
	</div>

</div>
</body>
</html>