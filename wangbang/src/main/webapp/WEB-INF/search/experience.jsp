<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="card shadow mb-4">
        <div class="row">
	         <div class="card-header py-3">
	              <h2 class="m-0 font-weight-bold text-primary text-center">
						부산체험지
	              </h2>
	              	<div class="header-left">
	                    <div class="input-group icons">
	                        <input type="search" class="form-control" placeholder="Search Dashboard" aria-label="Search Dashboard">
	                         <a href="#"><button type="button" class="btn mb-1 btn-roundede btn-outline-primary" id="toastr-success-top-right">검색</button></a>
	                        
	                        <div class="drop-down   d-md-none">
								<form action="#">
									<input type="text" class="form-control" placeholder="Search">
								</form>
	                        </div>
	                    </div>
	                </div>
	                
			 </div>
         </div>
         <!-- for문 여기서부터 돌리기 -->
       	   <c:forEach var="vo" items="${list }">
                   <div class="col-12 m-b-30">
                        <h4 class="d-inline">★★★☆☆별점</h4>
                     <!--    <p class="text-muted">This is 3 column contents</p> -->
                        <div class="row">
                            <div class="col-md-6 col-lg-3">
                                <div class="card">
                                    <img class="img-fluid" src="../images/big/img1.jpg" alt="">
                                    <div class="card-body">
                                        <h5 class="card-title"></h5>
                                        <p class="card-text">${vo.usefree } </p>
                                        <p class="card-text"><small class="text-muted">${vo.dataContent }</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <!-- End Col -->
                        </div>
                    </div>
                  </c:forEach>
</div>
</body>
</html>