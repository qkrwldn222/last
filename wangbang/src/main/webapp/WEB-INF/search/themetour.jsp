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
	              <h2 class="m-0 font-weight-bold text-primary text-center">
						맛집 정보
	              </h2>
	              	<div class="header-left">
	                    <div class="input-group icons">
	                        <input type="search" class="form-control" placeholder="Search Dashboard" aria-label="Search Dashboard">
	                         <a href="#"><button type="button" class="btn mb-1 btn-roundede btn-outline-primary" id="toastr-success-top-right">검색</button></a>
	                        
	                        <div class="drop-down d-md-none" id="search">
								<!-- <script type="text/babel">
									class Search extends React.Component{
										// 변수 선언
										constructor(props){
											super(props);
											this.state={
												movie_data : [],
												fd : ''
											}
										}
									}
										// 데이터 받기
										componentWillMount(){
											var _this = this;
					
										}
										//화면 출력
										render(){
					
										}
										// jquery, angularjs (연동)
										componentDidMount(){
					
										}
										ReactDOM.render(<Search/>, document.getElementById('search'));
								
								</script> -->
									<!-- <input type="text" class="form-control" placeholder="Search"> -->
								
	                        </div>
	                    </div>
	                </div>
			 </div>
         </div>
         <!-- for문 여기서부터 돌리기 -->
                   <div class="col-12 m-b-30" id="root">                 
                     <script type="text/babel">
		  class ThemeTour extends React.Component{
			  // 데이터 저장하는 변수 설정  => props(속성:불변) / state(상태:데이터 변경) 
			  constructor(props){
				  super(props);
				  // 변수 선언
				  this.state={
					data_json:[],
					page : 1,
					total : ''
				  }
				this.prevHandler=this.prevHandler.bind(this);
				this.nextHandler=this.nextHandler.bind(this);
			  }
			  //boxoffice_data.do?no=1
			  componentWillMount(){
				  var _this=this;
				  axios.get('http://localhost:8080/wang/search/themetour.do',{
					  params:{
						  page:1
					  }
				  }).then((response)=>{
					  _this.setState({data_json:response.data});
					  console.log(response.data);
				  });
				axios.get('http://localhost:8080/wang/search/themetour_total.do'
				  ).then((response)=>{
					  _this.setState({total:response.data});
				  });
			  }
		
		prevHandler(){
       		this.setState({page:this.state.page>1?this.state.page-1:this.state.page});
      		var _this=this;
			
       			axios.get('http://localhost:8080/wang/search/themetour.do',{
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
        	axios.get('http://localhost:8080/wang/search/search/themetour.do',{
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
                                    <a href={"../search/restaurant_detail.do?dataSid="+m.dataSid}><img className="img-fluid" src={m.mainimgthumb} alt=""/></a>
                                    <div className="card-body">
                                        <h5 className="card-title">{m.dataTitle }</h5>
                                        <p className="card-text">{m.tel }</p>
                                        <p className="card-text">{m.addr }</p>
                                        <p className="card-text"><small className="text-muted">{m.info }</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                  );
				  return (
                   <div className="row">
                            {html}
						<div className={"text-center"}>
                  			<input type={"button"} value={"이전"} className={"btn btn-lg btn-danger"} onClick={this.prevHandler}/>
							{this.state.page} page / {this.state.total} pages                   			

							<input type={"button"} value={"다음"} className={"btn btn-lg btn-primary"} onClick={this.nextHandler}/>
      					</div> 
                    </div>
				  )
				  
			  }
			  componentDidMount(){
				  
			  }
		  }
         ReactDOM.render(<Restaurant />,document.getElementById('root'));
		</script>                
       </div>
</div>
</body>
</html>