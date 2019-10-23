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
$(function(){
	$('.img').click(function(){	
		var data = $(this).attr("data-no");
		var no = $('#choice').val();
		$.ajax({
			type:'post',
			data:{dataSid:data,no:no},
			url:'../recommend/graph.do',
			success:function(res){
				$('#daumMap').html(res);
			}
		});
	});
});

</script>
</head>
<body>
	<div class="row">
	<c:forEach var="vo" items="${list}" varStatus="s">
	<c:if test="${s.index<4 }">
	<div class="col-md-6 col-lg-3">
		<div class="card">
			<img src="${vo.mainimgthumb }" alt="" width="360" height="300" />
			<div class="card-body">
				<h5 class="card-title"><span class="img" data-no="${vo.dataSid }">${vo.dataTitle }</span ></h5>
				<p class="card-text">${vo.tel }</p>
				<p class="card-text" id="addr${vo.dataSid }">${vo.addr }</p>
				<p class="card-text">
					<small class="text-muted">${vo.info }</small>
				</p>
			</div>
		</div>
	</div>
	</c:if>
	</c:forEach>
	</div>
</body>
</html>