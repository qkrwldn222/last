<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!--**********************************
            Sidebar start
        ***********************************-->
        <div class="nk-sidebar">           
            <div class="nk-nav-scroll">
                <ul class="metismenu" id="menu">
                    <li class="nav-label">MENU</li>
                    <li class="mega-menu mega-menu-sm">
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-anchor menu-icon"></i><span class="nav-text">관광지</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="#">명품관광</a></li>
                            <li><a href="../search/touristattr.do">관광명소</a></li>
                            <li><a href="../search/experience.do">체험여행</a></li>
                            <li><a href="#">도보여행</a></li>
                            <li><a href="../search/themetour.do">테마여행</a></li>
                            <li><a href="../course/course_list.do">추천여행코스</a></li>
                        </ul>
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-basket menu-icon"></i><span class="nav-text">숙박 & 쇼핑</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="../search/stay.do">숙박정보</a></li>
                            <li><a href="../search/shopping.do">쇼핑명소</a></li>
                        </ul>
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-cup menu-icon"></i><span class="nav-text">맛집 & 레저</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="../search/restaurant.do">맛집</a></li>
                            <li><a href="../search/leisure.do">스포츠 & 레저</a></li>
                        </ul>
                    </li> 
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-plane menu-icon"></i> <span class="nav-text">이번 주는 어디?</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="../goodTrip/goodTripday.do">이번 주는 어디?</a></li>
                            <%-- <li><a href="./email-read.html">그래프~</a></li>
                            <li><a href="./email-compose.html">기타~</a></li> --%>
                        </ul>
                    </li>


                    <li>
<!--                  <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-grid menu-icon"></i><span class="nav-text">코스추천</span>
                        </a> -->
                       <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-like menu-icon"></i><span class="nav-text">코스추천</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="../course/course_list.do">당신을위한 추천</a></li>                  
                      </ul>  
                       <!--
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-layers menu-icon"></i><span class="nav-text">Components</span>
                        </a>-->
                        <!-- <ul aria-expanded="false"> 
            
                        </ul>
                    </li>
                    <li> -->
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-note menu-icon"></i><span class="nav-text">게시판</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="../board/list.do">공지사항</a></li>
<!--                             <li><a href="./form-validation.html">Form Validation</a></li>
                            <li><a href="./form-step.html">Step Form</a></li>
                            <li><a href="./form-editor.html">Editor</a></li>
                            <li><a href="./form-picker.html">Picker</a></li> -->
                        </ul>
                    </li>
<!--                     <li class="nav-label">Table</li> -->
<!--                     <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-menu menu-icon"></i><span class="nav-text">Table</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="./table-basic.html" aria-expanded="false">Basic Table</a></li>
                            <li><a href="./table-datatable.html" aria-expanded="false">Data Table</a></li>
                        </ul>
                    </li> -->
<!--                     <li class="nav-label">Pages</li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-notebook menu-icon"></i><span class="nav-text">Pages</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="./page-login.html">Login</a></li>
                            <li><a href="./page-register.html">Register</a></li>
                            <li><a href="./page-lock.html">Lock Screen</a></li>
                            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Error</a>
                                <ul aria-expanded="false">
                                    <li><a href="./page-error-404.html">Error 404</a></li>
                                    <li><a href="./page-error-403.html">Error 403</a></li>
                                    <li><a href="./page-error-400.html">Error 400</a></li>
                                    <li><a href="./page-error-500.html">Error 500</a></li>
                                    <li><a href="./page-error-503.html">Error 503</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li> -->
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->

</body>
</html>