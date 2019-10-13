<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
  	/* $('#frm').submit(); <form action="../search/top_content.do" id="frm"*/
}

</script>
</head>
<body>
<div class="row">
                    <div class="col-lg-9 col-sm-9">
                        <div class="card gradient-1">
                            <div class="card-body">
                                <h3 class="card-title text-white">뉴스</h3>
                                <div class="d-inline-block">
                                    <h2 class="text-white">4565</h2>
                                    <p class="text-white mb-0">Jan - March 2019</p>
                                </div>
                                <span class="float-right display-5 opacity-5"><i class="fa fa-shopping-cart"></i></span>
                            </div>
                        </div>
                    </div>
                    <!-- 실시간날씨 -->
                     <div class="col-lg-3 col-sm-6" >
                        <!-- <div class="card gradient-4" style="background-color: ivory"> -->
                        <div class="card" style="background-color: white">
                            <div class="card-body">
                                <h4><b>현재 부산날씨-${SKY }</b></h4>
                                <span>
	                               <c:if test="${SKY eq '맑음' }">
	                                    <img src="../images/1.png" width="25%" height="25%">
	                                </c:if> 
	                                 <c:if test="${SKY eq '구름많음' }">
	                                   	<img src="../images/2.png">
	                                </c:if> 
	                                 <c:if test="${SKY eq '흐림' }">
	                                    <img src="../images/3.png">
	                                </c:if> 
	                                
	                                 기온 : <span style="color:red">${TEM } ˚</span> 강수량 : <span style="color:blue">${POP }%</span>
								    
                                </span>
                                <!-- <p class="text-white mb-0">Jan - March 2019</p> -->
                                
                            </div>
                            								<button type="button" class="btn btn-info m-b-10 m-1-5" id="toastr-success-top-right">더보기</button>
                        </div>
                    </div>
</body>
</html>