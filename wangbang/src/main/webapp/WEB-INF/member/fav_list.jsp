<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8" >
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.favDelBtn').click(function(){
		if(confirm("목록에서 삭제하시겠습니까?")) {
			var id = $(this).attr('data-id');
			var sid = $(this).attr('data-sid');
			
			$.ajax({
				type:'post',
				url:'../fav/favDelete.do',
				data:{id:id, sid:sid},
				success:function(res) {
					location.reload();
				}
			});
		} else
			return;
	});
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="card shadow mb-4">
	<div class="row">
		<div class="card-header py-3">
			<h2 class="m-0 font-weight-bold text-primary text-center">&nbsp;나의 찜 목록</h2>
		</div>
	</div>
	<br>
	<div class="container-fluid" >
		<p class="right" > <font color="lightgray" >찜 목록은 등록일이 오래된 것부터 출력됩니다.</font> </p>
		<table class="table" id="printFav" >
			<tr>
				<th class="text-center" width="5%" >번호</th>
				<th class="text-center" width="85%" >이름</th>
				<th width="10%" >&nbsp;</th>
			</tr>
			<c:if test="${fn:length(flist) == 0}" >
				<tr>
					<td colspan="3" style="font-weight: bold;" class="text-center" ><font color="gray">아직 찜하신 목록이 없네요 ㅠ.ㅠ</font></td>
				</tr>
			</c:if>
			<c:if test="${fn:length(flist) > 0}" >
				<c:forEach var="f" items="${flist }" varStatus="s" >
					<tr>
						<td class="text-center">${s.index+1 }</td>
						<td>
							<a href="${f.link }" target="_blank" >&nbsp;&nbsp;${f.title }</a>
						</td>
						<td>
							<input type="button" class="btn btn-xs btn-primary favDelBtn" value="삭제" data-id="${sessionScope.id }" data-sid="${f.dataSid }" >
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div style="margin-bottom: 50px;"></div>
	</div>
</div>
</body>
</html>