<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
            </svg>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->

    
    <!--**********************************
        Main wrapper start
    ***********************************-->
   

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header">
            <div class="brand-logo">
                <a href="../main/main.do">
                    <b class="logo-abbr"><img src="../images/logo.png" alt=""> </b>
                    <span class="logo-compact"><img src="../images/logo-compact.png" alt=""></span>
                    <span class="brand-title">
                        <img src="../images/logo-text.png" alt="">
                    </span>
                </a>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">    
            <div class="header-content clearfix">
                
                <div class="nav-control">
                    <div class="hamburger">
                        <span class="toggle-icon"><i class="icon-menu"></i></span>
                    </div>
                </div>
                 <div class="header-right">
                    <ul class="clearfix">
                       
                        <li class="icons dropdown d-none d-md-flex">
                            <a href="../member/signup.do"><button type="button" class="btn btn-info m-b-10 m-1-5" id="toastr-success-top-right">회원가입</button></a>
                            <a href="javascript:void(0)" data-toggle="dropdown"><button type="button" class="btn btn-info m-b-10 m-1-5" id="toastr-success-top-right">로그인</button></a>
                            
                            <div class="drop-down animated fadeIn dropdown-menu">
                                <div class="dropdown-content-heading d-flex justify-content-between">
                                    <span class="">Login</span>  
                                </div>
                                <div class="dropdown-content-body" style="padding: 0 1.25rem;">
                                	<div class="form-group" style="margin-top: 20px;">
	                                    <input type="text" class="form-control input-default" placeholder="ID">
	                                    
                                    </div>
                                    <div class="form-group">
                                    	<input type="password" class="form-control input-default" placeholder="PASSWORD">
                                    </div>
                                    <div class="form-group text-center">
                                    	<button type="button" class="btn mb-1 btn-info">LOGIN</button>
                                    </div>
                                </div>
                            </div>
                            
                        </li>
                    </ul>
                </div>
  
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->
</body>
</html>