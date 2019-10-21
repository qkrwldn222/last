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

    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div class="card shadow mb-4">
        <div class="row">
	         <div class="card-header py-3">
	              <h2 class="m-0 font-weight-bold text-primary text-center">쇼핑목록	</h2>
	              </div>
	          </div>
	          
	   <div class="col-12 m-b-30" id="root">                 
          <script type="text/babel">
		  class Shopping extends React.Component{
			  // 데이터 저장하는 변수 설정  => props(속성:불변) / state(상태:데이터 변경) 
			  constructor(props){
				  super(props);
				  // 변수 선언
				  this.state={
					data_json:[],
					page : 1
				  }
				this.prevHandler=this.prevHandler.bind(this);
				this.nextHandler=this.nextHandler.bind(this);
			  }
			  componentWillMount(){
				  var _this=this;
				  axios.get('http://localhost:8080/wang/search/shopping_data.do',{
					  params:{
						  page:1
					  }
				  }).then((response)=>{
					  _this.setState({data_json:response.data});
					  console.log(response.data);
				  });
			  }
		
		prevHandler(){
       		this.setState({page:this.state.page>1?this.state.page-1:this.state.page});
      		var _this=this;
			
       			axios.get('http://localhost:8080/wang/search/shopping_data.do',{
         	  	 params:{
         	     	  page:_this.state.page-1
            		}
        		}).then(function (response) {
         	   	_this.setState({data_json:response.data});
        		})
    	}  
		nextHandler(){
        	this.setState({page:this.state.page<300?this.state.page+1:this.state.page});
        	var _this=this;
        	axios.get('http://localhost:8080/wang/search/shopping_data.do',{
            	params:{
                	page:_this.state.page+1
            	}
        	}).then(function (response) {
            _this.setState({data_json:response.data});
        	})
    	}
			  render(){
                  const html=this.state.data_json.map((m)=>
                     <div className="col-md-6 col-lg-3">
                                <div className="card">
                                  <a href={"../search/shopping_detail.do?dataSid="+m.dataSid}><img className="img-fluid" src={m.mainimgthumb} alt=""/></a>
                                    <div className="card-body">
                                        <h5 className="card-title">{m.dataTitle }</h5>
                                        <p className="card-text">{m.tel }</p>
                                        <p className="card-text">{m.addr }</p>
                                        <p className="card-text">{m.time }</p>
                                    </div>
                                </div>
                            </div>
                  );
				  return (
                   <div className="row">
                            {html}
						<div className={"text-center"}>
                  			<input type={"button"} value={"이전"} className={"btn btn-lg btn-danger"} onClick={this.prevHandler}/>
							{this.state.page} page / 22 pages                   			

							<input type={"button"} value={"다음"} className={"btn btn-lg btn-primary"} onClick={this.nextHandler}/>
      					</div> 
                    </div>
				  )
			  }
		  }
         ReactDOM.render(<Shopping />,document.getElementById('root'));
		</script>                
       </div>
</div>
</body>
</html> 
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->
<script type="text/javascript">
$(function(){
	$('.clicks').click(function(){
		var dataSid=$(this).attr("value"); 
		alert("dataSid="+dataSid);
	});
});
</script>
</head>
<body>
  <div class="card shadow mb-4">
		<div class="card-header py-3">
			<h3 class="m-0 font-weight-bold text-primary">쇼핑리스트</h3>
		</div>
		<div class="container-fluid" id="app">
		  <div class="row">
		  <div class="col-sm-8">
		   <shoppingdata-office v-bind:shoppingdata="shopping_data"></shoppingdata-office>
		  </div>
		  <div class="col-sm-4">
		   <table class="table">
		    <tr>
		     <td class="text-center" width=30% rowspan="7">
		       <img :src="shopping_detail.mainimgthumb" width=100%>
		     </td>
		     <td class="text-left" colspan="2">{{shopping_detail.dataTitle}}</td>
		    </tr>
		    <tr>
		      <td width=30% class="text-right">시간</td>
		      <td width=40% class="text-left">{{shopping_detail.time}}</td>
		    </tr>
		    <tr>
		      <td colspan="3">{{shopping_detail.story}}</td>
		    </tr>
		   </table>
		  </div>
		</div>
		</div>
		</div>
		
		      constructor(props){
				  super(props);
				  // 변수 선언
				  this.state={
					  data_json:[],
					  data_detail:{},
                      isShow:false
				  }
				  // 이벤트 선언 
			  }
		
		<script>
		var eventBus=new Vue();
		Vue.component('shopping-office',{
			props:['shoppingdata'],
			template:'<table class="table table-hover">'
				     +'<thead>'
				     +'<tr>'
				         +'<th></th>'
				         +'<th>이름</th>'
				         +'<th>시간</th>'
				       +'</tr>'
				       +'<tr v-for="m in shoppingdata">'
				         +'<td><img :src="m.mainimgthumb" width="35" height="35"></td>'
				         +'<td v-on:click="showMovie(m.dataSid)">{{m.dataTitle}}</td>'
				       +'</tr>'
				     +'</thead>'
				   +'</table>',
		    methods:{
		    	
		        // showMovie(value)
		    	showMovie:function(value){
		    		//alert("value:"+value);
		    		eventBus.$emit('showMovieEvent',value);
		    	}
		    }
		});
		new Vue({
			el:'#app',
			data:{
				shopping_data:[],
				shopping_detail:{},
				dataSid:1
			},
			mounted:function(){
				var _this=this;
				axios.get('http://localhost:8080/mvc/search/shopping_data.do').then(function(response){
					_this.shopping_data=response.data;
				});
			},
			updated:function(){
				var _this=this;
				eventBus.$on('showMovieEvent',function(value){
					_this.dataSid=value;
					axios.get('http://localhost:8080/mvc/search/shopping_detail.do',{
						params:{
							dataSid:_this.dataSid
						}
					}).then(function(response){
						_this.shopping_detail=response.data
					});
				})
			}
		})
		</script>
</body>
</html>
 --%>