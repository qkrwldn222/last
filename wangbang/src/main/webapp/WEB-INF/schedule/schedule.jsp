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
#layerPopup{
  padding:20px; 
  border:4px solid #ddd; 
  position:absolute; 
  left:100px; 
  top:-200px; 
  width:1200px;
  height:1100px;
  background:#fff;
}

#layerPopup button{
  cursor:pointer;
}

.timetable tr{
	border: solid;
	font-size: large;
}

</style>
<script type="text/javascript">	
$(function(){
	  $("#layerPopup").hide();
	  $("#contents > a").click(function(){
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
	    $("#contents > a").focus();
	    $("#layerPopup").hide();
	  });
	});

</script>
</head>
<body>   
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<div id="contents">
						  <a href="#layerPopup">일정 추가</a>
						  <div id="layerPopup">
							  <div class=sch_title>
							    <button type="button" style="float: right;">닫기</button>
							    	<h2>나의 여행일정</h2>
							    <label>작성자 : ${name }</label>
							  </div>
						  	  <div class="sch_div" style="overflow: auto;">								
								  													 												          
											
	   <div class="aaaa" id="root">  </div>               
          <script type="text/babel">

		  class Stay extends React.Component{
			  constructor(props){
				  super(props);
				  this.state={
					data_json:[],
					page : 1,
					listData:[]
				  }
				this.prevHandler=this.prevHandler.bind(this);
				this.nextHandler=this.nextHandler.bind(this);
				this.drop=this.drop.bind(this);
			  }
			  componentWillMount(){
				  var _this=this;				  
				  axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
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
			
       			axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
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
        	axios.get('http://localhost:8080/wang/schedule/stay_data.do',{
            	params:{
                	page:_this.state.page+1
            	}
        	}).then(function (response) {
            _this.setState({data_json:response.data});
        	})
    	}

		insert(){
			var timedata = new Array();
			var insertdata = "";
			var daydata = document.getElementById("day1");
			for(var i=0;i<17;i++){
				timedata[i] = document.getElementById("time"+(i+8));
				if(timedata[i].innerHTML != "" && timedata[i].innerHTML != null){
					insertdata = insertdata+","+(String(i+8)+":"+timedata[i].innerHTML);				
				}
			}

        	axios.get('http://localhost:8080/wang/schedule/sch_insert.do',{
            	params:{
                	insertdata:insertdata,
					daydata:daydata.id
            	}
        	}).then(function (response) {
           alert("저장되었습니다.");
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
			var geocoder = new kakao.maps.services.Geocoder();
			
			let el = document.getElementById('map');
      		let map = new daum.maps.Map(el, {
       		center: new daum.maps.LatLng(33.450701,126.570667),
			level: 8	
      		});

			alert(addr);
    		ev.target.append(title);
			var aa=[];
            aa.push(addr)
            this.setState({listData:aa});	
            	
			alert(this.state.listData[0]);
			
	
			geocoder.addressSearch(addr, function(result, status) {
     			if (status === kakao.maps.services.Status.OK) {
       			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        		var marker = new kakao.maps.Marker({
            		map: map,
            		position: coords
        		});
				
        		var infowindow = new kakao.maps.InfoWindow({
            		content: '<div style="width:150px;text-align:center;padding:6px 0;">'+addr+'</div>'
        		});
        		infowindow.open(map, marker);

        		map.setCenter(coords);
    			} 
			});
		} 
				 
		 componentDidMount() {                                                    
      		let el = document.getElementById('map');
      		let map = new daum.maps.Map(el, {
       		center: new daum.maps.LatLng(33.450701,126.570667),
 			level: 8 	
      		});
    	 }
	
			  render(){
                  const html=this.state.data_json.map((m)=>                    

       <div id={m.dataTitle} data-img={m.mainimgthumb} data-addr={m.addr} draggable='true' onDragStart={this.drag.bind(this)} width="336" height="69">                            
                                  <img className="imgaa" src={m.mainimgthumb} alt="" style={{"width":"50","height":"50"}}/>
								  <h6>{m.dataTitle}</h6>
       </div>                                                                                  

                  );
				  return (
					<div>
					<div className="sch_top" style={{"height":"310px","border":"solid"}}>
								  	<div className="sch_left_top" style={{"width":"300px","height":"300px","border":"solid","float":"left"}}>
								  		<div className="sch_left_top_schbtn" style={{"width":"100%"}}>
									  		<button className="btn btn-primary">새 여행 일정등록</button>
									  		<button className="btn btn-primary">나의 여행 일정</button>
								  		</div>
								  		<ul>
								  			<li><label style={{"width":"15%"}}>제목 : </label>
								  			<input type="text" placeholder="신나는 부산여행" style={{"width":"80%"}}/></li>
								  			<li><label>기간</label>
								  			<input type="date" style={{"width":"45%"}} id="strdate"/>&nbsp;<input type="date" style={{"width":"45%"}} id="enddate"/>
								  			</li>
								  		</ul>
								  		<div className="sch_left_top_makebtn">				  			
								  			<center><input type="button" value="일정 만들기" className="btn btn-primary"/></center>
								  		</div>
								  	</div>
								  	
								  	<div id="map" style={{"width":"800px","height":"300px"}}></div>
								  																																			  	
								  	</div>
								  	
					<div className="sch_left_bottom">	
                   <div className="drag1">
<div className="sch_left_bottom_schbtn" style={{"width":"400px"}}>
									  		<button className="btn btn-primary">내가 찜한 여행지</button>
									  		<button className="btn btn-primary">여행지 검색</button>
								  		</div>
								  		
								  		<div className="scg_left_bottom_category" style={{"width":"400px","float":"left"}}>
								  		<a href="" className="active">전체</a>
								  		<a href="" className="a">관광지</a>
								  		<a href="" className="a">음식점</a>
								  		<a href="" className="a">숙소</a>
								  		<a href="" className="a">쇼핑</a>
                            {html}
						<div className={"text-center"}>						  					
                  			<input type={"button"} value={"이전"} className={"btn btn-lg btn-danger"} onClick={this.prevHandler}/>
							{this.state.page} page / 22 pages                   			
							<input type={"button"} value={"다음"} className={"btn btn-lg btn-primary"} onClick={this.nextHandler}/>
      					</div> 	
							  		</div>

<div className="sch_right_bottom">								 
								  		<table className="timetable">
								  			<tr>
												<th>Time</th>
												<th id="day1">Day 1</th>
											</tr>
								  			<c:forEach var="i" begin="8" end="24" step="1">								  			
								  				<tr data-time="${i}:00">
								  					<td>${i }:00</td>
								  					<td><div id="time${i}" onDragOver={this.allowDrop.bind(this)} onDrop={this.drop.bind(this)} style={{"border":"solid","width":"300px","height":"30px"}}></div></td>		
								  				</tr>
								  			</c:forEach>								  			
								  		</table>
								  	</div> 
  								</div>	
<input type={"button"} value={"저장"} className={"btn btn-lg btn-danger"} onClick={this.insert}/>
                    </div>
</div>
				  )
			  }
		  }
         ReactDOM.render(<Stay />,document.getElementById('root'));
		</script>                
       																						 
															 																	  
							  </div>
						  </div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>