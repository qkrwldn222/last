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
		</div>
</body>
</html>