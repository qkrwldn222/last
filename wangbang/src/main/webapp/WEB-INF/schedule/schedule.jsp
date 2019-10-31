<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37a7d7363032a119ca4e0bf582fc0ef5&libraries=services"></script>
<style>
#mask { 
  position:absolute;
  z-index:9000;
  background-color:#000;
  display:none;
  left:0;
  top:0;
}

#layerPopup{
  padding:20px; 
  border:4px solid #ddd; 
  position:absolute; 
  left:100px; 
  top:-200px; 
  width:1100px;
  height:1050px;
  background:#fff;
  z-index: 10000;
}

#layerPopup button{
  cursor:pointer;
}

.timetable tr{
	border: 1px solid #e3e3e3;
	font-size: large;
}

</style>
<script type="text/javascript">	
function wrapWindowByMask(){

	var maskHeight = $(document).height();  

	var maskWidth = $(window).width();

	$('#mask').css({'width':maskWidth,'height':maskHeight});  

      $('#mask').fadeTo("fast",0.8);    

}

$(function(){
	  $("#layerPopup").hide();
	  $("#contents > a").click(function(){
		  wrapWindowByMask();
	    $("#contents > a").blur();
	    $("#layerPopup").show();
	    $("#layerPopup a").focus();
	    return false;
	  });
	  $("#layerPopup a").keydown(function(e){
	    if(e.shiftKey && e.keyCode == 9){ // Shift + Tab 키를 의미합니다.
	      $("#contents > a").focus();
	      $("#layerPopup").hide();
	      return false;
	    }
	  });
	  
	  $("#layerPopup button").click(function(){
		  $('#mask').hide();
	    $("#contents > a").focus();
	    $("#layerPopup").hide();
	  });
	});

</script>
</head>
<body>   
						<div id="mask"></div> 
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<div id="contents">
						  <h2 class="m-0 font-weight-bold text-primary text-center">나의 여행 일정</h2><a href="#layerPopup" class="btn mb-1 btn-flat btn-outline-primary">일정 추가</a>
						  <div id="layerPopup">
							  <div class=sch_title>
							    <button class="btn mb-1 btn-rounded btn-outline-dark btn-lg" type="button" style="float: right;">닫기</button>
							    	<h2>나의 여행일정</h2>
							    <label>작성자 : ${name }</label>
							  </div>
						  	  <div class="sch_div" style="overflow: auto;">																  													 												        										
	   <div class="root" id="root">  </div>               
          <script type="text/babel">

		  class Stay extends React.Component{
			  constructor(props){
				  super(props);
				  this.state={
					data_json:[],
					page : 1,
					no:1,
					listData:[],
					coordsData:[]
				  }
				this.prevHandler=this.prevHandler.bind(this);
				this.nextHandler=this.nextHandler.bind(this);
				this.drop=this.drop.bind(this);
			  }
			  componentWillMount(){
				  var _this=this;				  
				  axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
					  params:{
						  no:1,
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
			
       			axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
         	  	 params:{
         	     	  page:_this.state.page-1,
					  no:this.state.no
            		}
        		}).then(function (response) {
         	   	_this.setState({data_json:response.data});
        		})
    	}  
		nextHandler(){
        	this.setState({page:this.state.page<${walkTotalPage}?this.state.page+1:this.state.page});
        	var _this=this;
        	axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
            	params:{
                	page:_this.state.page+1,
					no:this.state.no
            	}
        	}).then(function (response) {
            _this.setState({data_json:response.data});
        	})
    	}

		insert(){
			var timedata = new Array();
			var insertdata = "";
			var schtitle = document.getElementById("schtitle");
			var daydata = document.getElementById("strdate");
			for(var i=0;i<17;i++){
				timedata[i] = document.getElementById("time"+(i+8));
				if(timedata[i].innerHTML != "" && timedata[i].innerHTML != null){
					insertdata = insertdata+","+(String(i+8)+":"+timedata[i].innerHTML+"*"+timedata[i].getAttribute('data-img'));				
				}
			}

        	axios.get('http://localhost:8080/wang/schedule/sch_insert.do',{
            	params:{
                	insertdata:insertdata,
					daydata:daydata.value,
					schtitle:schtitle.value
            	}
        	}).then(function (response) {
           alert("저장되었습니다.");
			location.href="../schedule/schedule.do";
        	})
    	}

		drag(ev) {
    		this.dragged = ev.currentTarget;
			ev.dataTransfer.effectAllowed = 'move';
 			ev.dataTransfer.setData('html',ev.target.id);

		}

		allowDrop(ev) {
 		  ev.preventDefault();

		}

		drop(ev){			
 			ev.preventDefault();
			var data = ev.dataTransfer.getData("html");
			var optionElement = document.getElementById(data);
			var addr = optionElement.getAttribute('data-addr');
	
			var img = optionElement.getAttribute('data-img');
			var title = optionElement.id;
    		ev.target.append(title);
			ev.target.setAttribute('data-img',img);

			var geocoder = new kakao.maps.services.Geocoder();
			
			let el = document.getElementById('map');
      		let map = new daum.maps.Map(el, {
       		center: new daum.maps.LatLng(33.450701,126.570667),
			level: 8	
      		});

			var addrData = this.state.listData;			
            addrData.push(addr)
            this.setState({listData:addrData});	

			var coordsArray = []; // 좌표담을 배열
			var distanceArray = [];
			var distance;

			addrData.forEach(function(addr, index) {
			geocoder.addressSearch(addr, function(result, status) {
     			if (status === kakao.maps.services.Status.OK) {
       			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				coordsArray.push(coords);			
    		
        		var marker = new kakao.maps.Marker({
            		map: map,
            		position: coords
        		});
				
				var linePath = [
    				coordsArray
				];

				var polyline = new daum.maps.Polyline({
    			path: linePath, // 선을 구성하는 좌표배열 입니다
    			strokeWeight: 5, // 선의 두께 입니다
    			strokeColor: '#FFAE00', // 선의 색깔입니다
    			strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    			strokeStyle: 'solid' // 선의 스타일입니다
				});
				
				polyline.setMap(map);

				distanceArray.push(Math.round(polyline.getLength()));
				distance = Math.round(polyline.getLength());
				
				document.getElementById("day").innerHTML = "총거리 : "+distance+"m"
        		var infowindow = new kakao.maps.InfoWindow({
            		content: '<div style="width:150px;text-align:center;padding:6px 0;">'+addrData[index]+'</div>'
        		});
        		infowindow.open(map, marker);
				if(index=addrData.length){
        		map.setCenter(coords);
				}
    			} 
			});
		});

		}

		listChange(no){
		
			this.setState({no:no});
        	var _this=this;
        	axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
            	params:{
                	no:no,
					page:1
            	}
        	}).then(function (response) {
            _this.setState({data_json:response.data});
        	})
		}
		detail(ev){
			var title = ev.target.getAttribute('data-title');
			var img = ev.target.getAttribute('data-img');
			var tel = ev.target.getAttribute('data-tel');
			var addr = ev.target.getAttribute('data-addr');
			var price = ev.target.getAttribute('data-price');
			var content = ev.target.getAttribute('data-content');

			document.getElementById("detail_img").src = img;
			document.getElementById("detail_title").innerHTML = title;
			document.getElementById("detail_addr").innerHTML = addr;
			document.getElementById("detail_tel").innerHTML = tel;
			document.getElementById("detail_price").innerHTML = price;
			document.getElementById("detail_content").innerHTML = content;
		}

		date(){
	
			let today = new Date(),
			day = today.getDate(),
    		month = today.getMonth()+1, 
    		year = today.getFullYear();
        		 if(day<10){
            		    day='0'+day
          		  } 
       		 if(month<10){
        		    month='0'+month
      		  }
       		 today = year+'-'+month+'-'+day;
        
			document.getElementById('strdate').min = today;
			
		}
		componentDidMount(){
			{this.date()};
			}

			  render(){
                  const html=this.state.data_json.map((m)=>                    
       <div id={m.dataTitle} data-img={m.mainimgthumb} data-addr={m.addr} draggable='true' onDragStart={this.drag.bind(this)} width="336" height="69" style={{"border":"1px solid #e3e3e3","clear":"both"}}>                            
                                  <img className="cosimg" src={m.mainimgthumb} alt="" style={{"width":"80","height":"60","float":"left"}}/>
								  <b>{m.dataTitle}</b><br/>{m.addr}<br/>
								  <div style={{"text-align":"right"}}>
								  <input className="btn mb-1 btn-rounded btn-outline-primary btn-sm" type="button" value="상세보기" onClick={this.detail.bind(this)} data-title={m.dataTitle} data-img={m.mainimgthumb} data-tel={m.tel} data-addr={m.addr} data-price={m.price} data-content={m.dataContent}/>
								  </div>								  
       </div>                                                                                  

                  );
				  return (
					<div>
					<div className="sch_top" style={{"height":"310px","border":"1px solid #e3e3e3"}}>
								  	<div className="sch_left_top" style={{"width":"300px","height":"300px","border":"1px solid #e3e3e3","float":"left"}}>
								  		<div className="sch_left_top_schbtn" style={{"width":"100%"}}>
									  		<h4>여행 일정 만들기</h4>
								  		</div>
								  		<ul>
								  			<li><label style={{"width":"15%"}}>제목 : </label>
								  			<input type="text" placeholder="신나는 부산여행" style={{"width":"80%"}} id="schtitle" /></li>
								  			<li><label>날짜 : </label>
								  			<input type="date" style={{"width":"45%"}} id="strdate"/>
								  			</li>															
								  		</ul>
								  		<div className="sch_left_top_makebtn">				  			
								  			
								  		</div>
								  	</div>
								  	
								  	<div id="map" style={{"width":"750px","height":"300px"}}></div>
								  																																			  	
								  	</div>
								  	
					<div className="sch_left_bottom" style={{"float":"left"}}>	
									  		하단의 일정을 드래그로 추가할수 있습니다.
                   <div className="drag1" style={{"border":"1px solid #e3e3e3"}}>												  		
								  		<div className="sch_left_bottom_category" style={{"width":"300px","overflow":"auto"}}>
										<div className="btn-group mb-2 btn-group-sm" style={{"text-align":"right"}}>
								  		<a href='javascript:void(0)' onClick={this.listChange.bind(this, 1)} className="btn btn-primary">관광지</a>
								  		<a href='javascript:void(0)' onClick={this.listChange.bind(this, 2)} className="btn btn-primary">음식점</a>
								  		<a href='javascript:void(0)' onClick={this.listChange.bind(this, 3)} className="btn btn-primary">숙소</a>
										</div>
							<div className="sch_left_bottom_result" style={{"width":"300px","height":"450px","overflow":"auto"}}>
                            {html}
							</div>
						<div className={"text-center"} style={{"height":"50","border-top":"solid 1px"}}><br></br>						  					
                  			<input type={"button"} value={"이전"} className={"btn mb-1 btn-outline-primary btn-xs"} onClick={this.prevHandler}/>
							&nbsp;{this.state.page} page / <b id="totalpage">${walkTotalPage}</b> pages&nbsp;                   			
							<input type={"button"} value={"다음"} className={"btn mb-1 btn-outline-primary btn-xs"} onClick={this.nextHandler}/>
      					</div> 						
							  		</div>
  								</div>	
                    </div>			
								<input type="hidden" id="totalday" className="totalday" value="3"/>
									<div className="sch_right_bottom">								 						
								  		<table className="timetable" style={{"float":"left"}}>
								  			<tr>
												<th>Time</th>
												<th id="day"></th>
											</tr>
								  			<c:forEach var="i" begin="8" end="24" step="1">								  			
								  				<tr data-time="${i}:00">
								  					<td>${i }:00</td>
								  					<td><div id="time${i}" onDragOver={this.allowDrop.bind(this)} onDrop={this.drop.bind(this)} style={{"border":"1px solid #e3e3e3","width":"300px","height":"30px"}} data-img=""></div></td>		
								  				</tr>
								  			</c:forEach>								  			
								  		</table>
										<div className="a">
                                			<div className="card">
                                 				 <img className="img" id="detail_img" src="../images/busan.png" alt="" style={{"width":"100%","height":"230"}}/>
                                   				 <div className="card-body">
                                        			<h5 className="card-title" id="detail_title"> - 상세보기를 눌러 조회해보세요!!</h5>
                                       				<p className="card-text icon-map menu-icon" id="detail_addr"> - 상세보기를 눌러 조회해보세요!!</p>
                                        			<p className="card-text icon-phone menu-icon" id="detail_tel"> - 상세보기를 눌러 조회해보세요!!</p>
                                        			<p className="card-text fa fa-won" id="detail_price"> - 상세보기를 눌러 조회해보세요!!</p>
                                        			<p className="card-text icon-list menu-icon" id="detail_content" style={{"width":"100%","height":"140","overflow":"auto"}}> - 상세보기를 통해 조회해보세요!!</p>
                                    			</div>
                                			</div>
                            			</div>										
								  	</div>
<div style={{"clear":"both","text-align":"center"}}> 
<input type={"button"} value={"저장"} className={"btn mb-1 btn-rounded btn-outline-primary btn-lg"} onClick={this.insert} style={{"margin":"auto"}}/>
</div>
</div>
				  )
			  }
		  }
         ReactDOM.render(<Stay />,document.getElementById('root'));
		</script>                
       																						 
															 																	  
							  </div>
						  </div>
						   <div class="col-12 m-b-30" id="root2">                 
          <script type="text/babel">
		  class Schdule extends React.Component{ 
			  constructor(props){
				  super(props);
				  this.state={
					data_json:[],
					page : 1,
					id:'ran'		
				  }
				this.prevHandler=this.prevHandler.bind(this);
				this.nextHandler=this.nextHandler.bind(this);
			  }
			  componentWillMount(){
				  var _this=this;
				  axios.get('http://localhost:8080/wang/schedule/schedule_data.do',{
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
			
       			axios.get('http://localhost:8080/wang/schedule/schedule_data.do',{
         	  	 params:{
         	     	  page:_this.state.page-1
            		}
        		}).then(function (response) {
         	   	_this.setState({data_json:response.data});
        		})
    	}  
		nextHandler(){
        	this.setState({page:this.state.page<3?this.state.page+1:this.state.page});
        	var _this=this;
        	axios.get('http://localhost:8080/wang/schedule/schedule_data.do',{
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
                                    <div className="card-body">
										<img className="img" src={m.cosimg} alt="" style={{"width":"100%","height":"230"}}/>
                                        <h5 className="card-title">{m.schTitle }</h5>
                                        <p className="card-text">201{m.startDay }</p>
                                    </div>
                                </div>
                            </div>
                  );
				  return (
                   <div className="row">
                            {html}
						<div className={"text-center"} style={{"margin":"auto"}}>
                  			<input type={"button"} value={"이전"} className={"btn mb-1 btn-outline-primary"} onClick={this.prevHandler}/>
							&nbsp;{this.state.page} page / 1 pages&nbsp;                   			

							<input type={"button"} value={"다음"} className={"btn mb-1 btn-outline-primary"} onClick={this.nextHandler}/>
      					</div> 
                    </div>
				  )
			  }
		  }
         ReactDOM.render(<Schdule />,document.getElementById('root2'));
		</script>                
       </div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>