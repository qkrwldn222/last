<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="overflow:scroll; width:auto; height:290px;">

<ul>
<!-- 	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	<li><input type="button" value="해운대" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('해운대')"></li>
	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	<li><input type="button" value="해운대" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('해운대')"></li>
	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	<li><input type="button" value="해운대" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('해운대')"></li>
	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	<li><input type="button" value="해운대" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('해운대')"></li>
	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	<li><input type="button" value="해운대" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('해운대')"></li>
	<li><input type="button" value="부산시청" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('부산시청')"></li>
	
	
 -->
<c:forEach var="vo" items="${mList}">
	<li><input type="button" value="${vo.dataTitle }" class="btn mb-1 btn-rounded btn-outline-primary" onclick="course('${vo.dataTitle }')"></li>
</c:forEach>
</ul>
</div>
</body>
</html>