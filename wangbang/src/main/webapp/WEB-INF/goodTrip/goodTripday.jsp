<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="card shadow mb-4">
	<div class="row">
		<div class="card-header py-3">
			<h2 class="m-0 font-weight-bold text-primary text-center">이번 주엔 어디?</h2>
		</div>
		
		<div style="margin-top: 30px;" class="container-fluid" >
			<table class="table" >
				<c:forEach var="weather" items="${weathers }">
					<tr>
						<th class="text-center" >날짜&nbsp;</th>
						<th class="text-center" >
							<c:if test="${weather.time=='AM' }" >
								최저온도&nbsp;
							</c:if>
							<c:if test="${weather.time=='PM' }" >
								최고온도&nbsp;
							</c:if>
						</th>
						<th class="text-center" >강수확률&nbsp;</th>
						<th class="text-center" >상태&nbsp;</th>
					</tr>
					<tr>
						<td class="text-center" >${weather.date } ${weather.time }</td>
						<td class="text-center" >${weather.temperature }</td>
						<td class="text-center" >${weather.rain }</td>
						<td class="text-center" >
							<div class="text-center" style="width: 61px; height: 61px; margin: 0px auto;" >
								<img src="${statueIcons[weather.statue] }" />
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</div>
</div>
</body>
</html>