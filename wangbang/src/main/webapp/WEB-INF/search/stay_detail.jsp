<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="card shadow mb-4">
		<div class="card-header py-3">
			<h3 class="m-0 font-weight-bold text-primary">숙박 상세보기 [${vo.dataTitle }]</h3>
		</div>
		<div class="container-fluid">
		<div class="row">
		  <div class="col-12">
		   <table class="card">
		    <tr>
		     <td class="text-center" width=30% rowspan="7">
		    	<img src="${vo.mainimgthumb }">
		     </td>
		     <td class="text-left" colspan="2"><h4 class="text-center">${vo.dataTitle }</h4></td>
		    </tr>
		    		    <tr>
		     <td width=8% class="text-right">금액&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.price}</td>
		    </tr>
		    <tr>
		  	  <td width=8% class="text-right">주소&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.addr}</td>
		    </tr>
		    <tr>
		     <td width=8% class="text-right">전화번호&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.tel}</td>
		    </tr>
		    <tr>
		     <td width=8% class="text-right">정보&nbsp;&nbsp;&nbsp;</td>
		      <td colspan="3">${vo.dataContent}</td>
		    </tr>
		   </table>
		  </div>
		</div>
		</div>
		             <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">사진 더보기</h4>
                                <p class="text-muted"><code></code>
                                </p>
                                <div id="accordion-two" class="accordion">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0" data-toggle="collapse" data-target="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1"><i class="fa" aria-hidden="true"></i>Photo One</h5>
                                        </div>
                                        <div id="collapseOne1" class="collapse show" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img1thumb}"></div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2"><i class="fa" aria-hidden="true"></i>Photo Two</h5>
                                        </div>
                                        <div id="collapseTwo2" class="collapse" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img2thumb}"></div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseThree3" aria-expanded="false" aria-controls="collapseThree3"><i class="fa" aria-hidden="true"></i>Photo Three</h5>
                                        </div>
                                        <div id="collapseThree3" class="collapse" data-parent="#accordion-two">
                                            <div class="card-body"><img src="${vo.img3thumb}"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>

</body>
</html>