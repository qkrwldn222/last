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
	$('#logBtn').click(function(){
		var id = $('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			return;
		}
		var pwd = $('#pwd').val();
		if(pwd.trim()==""){
			$('#pwd').focus();
			return;
		}
		//ajax
		$.ajax({
			type:'post',
			url:'../member/login_ok.do',
			data:{id:id, pwd:pwd},
			success:function(response){
				var res = response.trim();
				if(res=="NOID"){
					alert("ID가 존재하지 않습니다.");
					$('#id').val("");
					$('#pwd').val("");
					$('#id').focus();
				} else if(res=="NOPWD"){
					alert("비밀번호가 틀립니다.");
					$('#pwd').val("");
					$('#pwd').focus();
				} else {
					location.href="../main/main.do";
				}
			}
		});
	});
});
</script>
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
                        <img src="../images/logo-text1.png" alt="">
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
                <c:if test="${sessionScope.id==null }">
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
	                                    <input type="text" class="form-control input-default" placeholder="ID" name="id" id="id">
	                                    
                                    </div>
                                    <div class="form-group">
                                    	<input type="password" class="form-control input-default" placeholder="PASSWORD" name="pwd" id="pwd">
                                    </div>
                                    <div class="form-group text-center">
                                    	<button type="button" class="btn mb-1 btn-info" id="logBtn">LOGIN</button>
                                    </div>
                                </div>
                            </div>
                            
                        </li>
                    </ul>
                </div>
                </c:if>
                <c:if test="${sessionScope.id!=null }">
                	<div class="header-right">
                    <ul class="clearfix">
                       <li class="icons dropdown"><a href="javascript:void(0)" data-toggle="dropdown">
                                <i class="mdi mdi-message-processing"></i>
                            </a>
                            <div class="drop-down animated fadeIn dropdown-menu">
                                <div class="dropdown-content-heading d-flex justify-content-between">
                                    <span class="">Chat</span>  
                                    <a href="javascript:void()" class="d-inline-block">
                                    </a>
                                </div>
                                <div class="dropdown-content-body">
                                    <ul>
                                        <li class="notification-unread">
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/1.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Saiful Islam</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="notification-unread">
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/2.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Adam Smith</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Can you do me a favour?</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/3.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Barak Obama</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/4.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Hilari Clinton</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hello</div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                    
                                </div>
                            </div>
                        </li>
                        <li class="icons dropdown d-none d-md-flex">
                            <a href="../member/mypage.do"><button type="button" class="btn btn-info m-b-10 m-1-5" id="toastr-success-top-right">마이페이지</button></a>
                            <a href="../member/logout_ok.do"><button type="button" class="btn btn-info m-b-10 m-1-5" id="toastr-success-top-right">로그아웃</button></a>
                        </li>
                    </ul>
                </div>
                </c:if>
  
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->
</body>
</html>