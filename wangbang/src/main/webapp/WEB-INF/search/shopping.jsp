<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
$(function(){
   $('.thisajax').click(function(){
      var no = $(this).attr("data-no");
      var result = $('#img'+no).attr("src");
      var dataSid = $(this).find(".sidNo").val();
      $.ajax({
         type:"post",
         url:"../search/shopping_data.do",
         dataType:"json",
         data: {dataSid : dataSid}, // 앞에께 RestController : 뒤에께 JSP
         success: function(data) {
            // $('#shopping_detail').html(res);
/*         alert("! : "+data.DataSid );
            alert("! : "+data.DataTitle );
            alert("! : "+data.Mainimgthumb );
            alert("! : "+data.Addr );
            alert("! : "+data.Price ); */
            
            /* var temp="";
            temp ="<table>"
            +"<tr>"
            +"<td>"
            +data.DataTitle
            +"</td>"
            +"</tr>"
            +"</table>";
            
         $('#shopping_detail').html(temp); */
         $("#shopping_detail").show();
         $("#sidTitle").html(data.DataTitle);
         $("#sidImg").html("<img src='"+data.Mainimgthumb+"'>");
         $("#sidCon").html(data.DataContent);
         }
      });
   });
});
</script>

    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div class="card shadow mb-4">
        <div class="row">
            <div class="card-header py-3">
                 <h2 class="m-0 font-weight-bold text-primary text-center">쇼핑목록   </h2>
                 </div>
             </div>
	<div class="container-fluid">
	<div class="row">
       <div class="col-lg-6">
             <table class="table table-striped">
               <tr>
                  <th width="50%" class="text-center">&nbsp;&nbsp;&nbsp;<h4><b>관광기념품</b></h4></th>
               </tr>
               <c:forEach var="vo" items="${list }" varStatus="s">
               <tr class="text-left thisajax" data-no="${s.index }" >
                  <td width="50%" class="text-left">
                  <input type="image" class="sidNo" src="${vo.mainimgthumb }" width="10%" height="10%" id="img${s.index }"  value="${vo.dataSid }">
                                                         <%-- &nbsp;<img src="${vo.mainimgthumb }" width="10%" height="10%" id="img"> --%>
                                                         &nbsp;${vo.dataTitle }&nbsp;${vo.dataSid }</td>
                  <%-- <td width="80%" class="text-left">&nbsp;${vo.dataTitle }</td> --%>
               </tr>
               </c:forEach>
         </table>
      </div>
       <div class="col-lg-6" id="shopping_detail" style="display: none;">
<!--        1111
       <table>
       		<tr>
       			<td id="sidTitle">
       			</td>
       		</tr>
       		<tr>
       			<td id="sidImg" >
       			</td>
       		</tr>
       		<tr>
       			<td id="sidCon">
       			</td>
       		</tr>
       </table> -->
                        	<div class="card">
                                    <div class="img-fluid" id="sidImg" ></div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="sidTitle"></h5>
                                        <p class="card-text" id="sidCon"></p>
                                    </div>
                             </div>
      </div>
     </div>
     </div>
                   <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="bootstrap-pagination">
                                    <nav>
                                         <ul class="pagination justify-content-center">
                                        
                                           <c:if test="${curpage<1 }">
                                            <li class="page-item disabled"><a class="page-link" href="../search/shopping.do?page=${page-1 }">Previous</a>
                                            </li>      
                                           </c:if>
                                           <c:if test="${curpage>1 }">
                                            <li class="page-item"><a class="page-link" href="../search/shopping.do?page=${page-1 }">Previous</a>
                                            </li>      
                                           </c:if>
                                           
                                           <c:if test="${curpage != 1 && curpage >1 }">
                                            <li class="page-item"><a class="page-link" href="../search/shopping.do?page=${curpage-1}">${curpage-1}</a>
                                            </li>
                                            
                                           </c:if>
                                            <c:if test="${curpage >=1 }">
                                            <li class="page-item active"><a class="page-link" href="../search/shopping.do?page=${curpage}">${curpage} <span class="sr-only">(current)</span></a>
                                            </li>
                                            </c:if>
                                            <li class="page-item"><a class="page-link" href="../search/shopping.do?page=${curpage+1}">${curpage+1}</a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="../search/shopping.do?page=${page+1 }">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                                 <h4 class="card-title text-center"><font color="grey">&nbsp;&nbsp;&nbsp;&nbsp;${curpage } page / 20 pages</font></h4>
                            </div>
                        </div>
                    </div>
             
             <%-- 
            <div class="container-fluid" id="root">
            
            </div>
         <script type="text/babel">
class Shopping extends React.Component {
               constructor(props) {
                  super(props);
                  this.state ={
                     data_json:[],
                     fd:''
                  }
               }
               componentWillMount() {
                  var _this=this; 
                  axios.get('http://localhost:8080/mvc/search/shopping_data.do').then((res)=>{
                     _this.setState({data_json:res.data});
                     console.log(res);
                  });
            }
            render() {
               const html=this.state.data_json.map((m)=>
                  <div>
                     {m.dataTitle}
                  </div>
               );
               return(
                  <div>
                     {html}
                  </div>
               )
            }
            componentDidMount() {
            }
}
            ReactDOM.render(<Shopping />, document.getElementById('root'))
         </script>
          --%>
</div>
</body>
</html>