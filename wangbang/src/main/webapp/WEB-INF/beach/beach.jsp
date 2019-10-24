<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
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
          title: 'My Daily Activities',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>

<div class="row">
	<div class="col-lg-6 col-md-12">
		<div class="card">
			<div class="card-body">
				<h3 class="text-primary"><b>해수욕장 선호도 조사</b></h3>
				<div id="piechart_3d" style="width: 700px; height: 500px;"></div>
			</div>
		</div>
	</div>  
	<div class="col-lg-6 col-md-12">
		<div class="card">
			<div class="card-body">
				<h3 class="text-primary"><b>Map</b></h3>
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
						<!-- <div class="card-content"> -->
							<h3 class="text-primary"><b>Info</b></h3>
						<!-- </div> -->
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
                

</body>
</html>