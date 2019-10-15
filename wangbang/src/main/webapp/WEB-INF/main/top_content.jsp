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

$(function(){
	$('#toastr-success-top-right').click(function(){
		
	});
});
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
                    <div class="col-lg-3 col-sm-6">
                        <div class="card" style="background-color: #FFFFFF">
                            <div class="card-body">
								<h4><b>${time1 }</b></h4>
                                <div class="bootstrap-carousel">
                                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                        <div class="carousel-inner">
                                            <div class="carousel-item active">
                                            <h6 class="card-title"></h6>
                                                  <div style="float:left; width: 20%;">     
						                               <c:if test="${SKY0 eq '맑음' }">
						                                    <img src="../images/1.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY0 eq '구름많음' }">
						                                   	<img src="../images/2.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY0 eq '흐림' }">
						                                    <img src="../images/3.png" width="100%" height="100%">
						                                </c:if> 
					                                </div>
					                                <div style="float:left; width: 80%;"> 
					                                	<b>서울-${SKY0 }</b><br>
					                                 	기온 : <font style="color:red">${TEM0 } ˚</font> 강수량 : <font style="color:blue">${POP0 }%</font>
                               						</div>
                   							  </div>
                                            
                                            <div class="carousel-item">
                                            <h6 class="card-title"></h6>
                                                  <div style="float:left; width: 20%;">     
						                               <c:if test="${SKY1 eq '맑음' }">
						                                    <img src="../images/1.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY1 eq '구름많음' }">
						                                   	<img src="../images/2.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY1 eq '흐림' }">
						                                    <img src="../images/3.png" width="100%" height="100%">
						                                </c:if> 
					                                </div>
					                                <div style="float:left; width: 80%;"> 
					                                	<b>대전-${SKY1 }</b><br>
					                                 	기온 : <font style="color:red">${TEM1 } ˚</font> 강수량 : <font style="color:blue">${POP1 }%</font>
                               						</div>
                                            </div>
                                            <div class="carousel-item">
                                            <h6 class="card-title"></h6>
                                                  <div style="float:left; width: 20%;">     
						                               <c:if test="${SKY2 eq '맑음' }">
						                                    <img src="../images/1.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY2 eq '구름많음' }">
						                                   	<img src="../images/2.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY2 eq '흐림' }">
						                                    <img src="../images/3.png" width="100%" height="100%">
						                                </c:if> 
					                                </div>
					                                <div style="float:left; width: 80%;"> 
					                                	<b>대구-${SKY2 }</b><br>
					                                 	기온 : <font style="color:red">${TEM2 } ˚</font> 강수량 : <font style="color:blue">${POP2 }%</font>
                               						</div>
                                            </div>
                                            <div class="carousel-item">
                                            <h6 class="card-title"></h6>
                                                  <div style="float:left; width: 20%;">     
						                               <c:if test="${SKY3 eq '맑음' }">
						                                    <img src="../images/1.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY3 eq '구름많음' }">
						                                   	<img src="../images/2.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY3 eq '흐림' }">
						                                    <img src="../images/3.png" width="100%" height="100%">
						                                </c:if> 
					                                </div>
					                                <div style="float:left; width: 80%;"> 
					                                	<b>부산-${SKY3 }</b><br>
					                                 	기온 : <font style="color:red">${TEM3 } ˚</font> 강수량 : <font style="color:blue">${POP3 }%</font>
                               						</div>
                                            </div>
                                            <div class="carousel-item">
                                            <h6 class="card-title"></h6>
                                                  <div style="float:left; width: 20%;">     
						                               <c:if test="${SKY4 eq '맑음' }">
						                                    <img src="../images/1.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY4 eq '구름많음' }">
						                                   	<img src="../images/2.png" width="100%" height="100%">
						                                </c:if> 
						                                 <c:if test="${SKY4 eq '흐림' }">
						                                    <img src="../images/3.png" width="100%" height="100%">
						                                </c:if> 
					                                </div>
					                                <div style="float:left; width: 80%;"> 
					                                	<b>전북-${SKY4 }</b><br>
					                                 	기온 : <font style="color:red">${TEM4 } ˚</font> 강수량 : <font style="color:blue">${POP4 }%</font>
                               						</div>
                                            </div>
                                        </div><a class="carousel-control-prev" href="#carouselExampleControls" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span> </a><a class="carousel-control-next" href="#carouselExampleControls"
                                            data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    </div>
</body>
</html>