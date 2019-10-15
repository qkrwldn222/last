<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<title>Insert title here</title>
</head>
<body>
                <div class="row">
                	<div class="col-lg-9 col-md-9">
                            <div class="card">
                                    <img class="img-fluid" src="../images/busan.png" style="border-radius: 0.625rem;">
                            </div>
                            
                        </div> 
                        
                        <div class="col-xl-3 col-lg-6 col-sm-6 col-xxl-6">
                        <div class="card">
                        <div class="social-graph-wrapper widget-twitter">
                                    <span class="s-icon"><i class="fa fa-twitter"></i></span>
                                </div>
                            <div class="card-body" id="card-body">
                                <h4 class="card-title">실시간 부산 SNS</h4>                    
                                 <div id="activity">                 
                                   
                 <script type="text/babel">
		  class Twitter extends React.Component{
			  // 데이터 저장하는 변수 설정  => props(속성:불변) / state(상태:데이터 변경) 
			  constructor(props){
				  super(props);
				  // 변수 선언
				  this.state={
					data_json:[]
				  }
				this.refresh=this.refresh.bind(this);
			  }
			  componentWillMount(){
				  var _this=this;
				  axios.get('http://localhost:8080/wang/main/body_twitter.do').then((response)=>{
					  _this.setState({data_json:response.data});
					  
				  });
			  }
			  refresh(){
				
				  var _this=this;
				  axios.get('http://localhost:8080/wang/main/body_twitter.do').then((response)=>{
					  _this.setState({data_json:response.data});
					 
				  });
			  }
				    
			  render(){
                  const html=this.state.data_json.map((m)=>
                    <div className="media border-bottom-1 pt-3 pb-3">
                                        <img width="35" src={m.img} className="mr-3 rounded-circle"></img>
                                        <div className="media-body">
                                            <h5>{m.id}</h5>
                                            <p className="mb-0">{m.text}</p>
                                        </div><span className="text-muted ">{m.time}</span>
                                    </div>
                  );
				  return (
					 <div className="row" style={{"overflow": "auto", "height": "430px"}}>
                            {html}
					</div>
				)
				  
			  }
			  componentDidMount(){
				   this.timerID = setInterval(
                     () => this.refresh(),
                       3000
                       );
			  }
 			componentWillUnmount() {
   				 clearInterval(this.timerID);
  			}
		  }
         ReactDOM.render(<Twitter />,document.getElementById('activity'));
		</script>            
                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                        <div class="social-graph-wrapper widget-googleplus">
                                    <span class="s-icon"><i class="fa fa-youtube"></i></span>
                                </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <img src="../images/users/8.jpg" class="rounded-circle" alt="">
                                    <h5 class="mt-3 mb-1">Ana Liem</h5>
                                    <p class="m-0">Senior Manager</p>
                                    <!-- <a href="javascript:void()" class="btn btn-sm btn-warning">Send Message</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                        <div class="social-graph-wrapper widget-googleplus">
                                    <span class="s-icon"><i class="fa fa-youtube"></i></span>
                                </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <img src="../images/users/5.jpg" class="rounded-circle" alt="">
                                    <h5 class="mt-3 mb-1">John Abraham</h5>
                                    <p class="m-0">Store Manager</p>
                                    <!-- <a href="javascript:void()" class="btn btn-sm btn-warning">Send Message</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                        <div class="social-graph-wrapper widget-googleplus">
                                    <span class="s-icon"><i class="fa fa-youtube"></i></span>
                                </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <img src="../images/users/7.jpg" class="rounded-circle" alt="">
                                    <h5 class="mt-3 mb-1">John Doe</h5>
                                    <p class="m-0">Sales Man</p>
                                    <!-- <a href="javascript:void()" class="btn btn-sm btn-warning">Send Message</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                        <div class="social-graph-wrapper widget-googleplus">
                                    <span class="s-icon"><i class="fa fa-youtube"></i></span>
                                </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <img src="../images/users/1.jpg" class="rounded-circle" alt="">
                                    <h5 class="mt-3 mb-1">Mehedi Titas</h5>
                                    <p class="m-0">Online Marketer</p>
                                    <!-- <a href="javascript:void()" class="btn btn-sm btn-warning">Send Message</a> -->
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
</body>
</html>