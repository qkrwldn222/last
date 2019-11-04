package com.sist.wang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.ExperienceDAO;
import com.sist.dao.RestMongDAO;
import com.sist.dao.RestllwDAO;
import com.sist.dao.ScheduleDAO;
import com.sist.manager.API;
import com.sist.manager.APITT;
import com.sist.vo.ExperienceVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.ScheduleVO;
import com.sist.vo.ShoppingVO;
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;
import com.sist.vo.TimeVO;
import com.sist.vo.WalkingTourVO;

@RestController
public class ScheduleRestController {
	
	@Autowired
	private ExperienceDAO jdao;
	
	@Autowired
	private RestMongDAO pdao;
	
	@Autowired
	private RestllwDAO kdao;
	
	@Autowired
	private ScheduleDAO sdao;
	
	@RequestMapping(value="schedule/stay_data.do",produces = "application/json; charset=utf8")
	public String stay_data(String page,int no){
		String json = "";
		JSONArray arr = new JSONArray();
		
		if(Integer.parseInt(page)< 1) page="1";
		switch (no) {
		case 1:
			List<WalkingTourVO> waList = kdao.walkingTour_data(Integer.parseInt(page));
			for(WalkingTourVO vo:waList){
				JSONObject obj = new JSONObject();
				obj.put("mainimgthumb", vo.getMainimgthumb());
				obj.put("dataTitle", vo.getDataTitle());
				obj.put("tel", vo.getTel());
		        obj.put("addr", vo.getAddr());
		        obj.put("price", vo.getPrice());
		        obj.put("dataContent", vo.getDataContent());
				arr.add(obj);
			}
			break;
		case 2:
			List<RestaurantVO> reList = kdao.restaurant_data(Integer.parseInt(page));
			for(RestaurantVO vo:reList){
				JSONObject obj = new JSONObject();
				obj.put("mainimgthumb", vo.getMainimgthumb());
				obj.put("dataTitle", vo.getDataTitle());
				obj.put("tel", vo.getTel());
		        obj.put("addr", vo.getAddr());
		        obj.put("price", "");
		        obj.put("dataContent", vo.getDataContent());
				arr.add(obj);
			}
			break;
		case 3:
			List<StayVO> stList = jdao.stay_data(Integer.parseInt(page));
			for(StayVO vo:stList){
				JSONObject obj = new JSONObject();
				obj.put("mainimgthumb", vo.getMainimgthumb());
				obj.put("dataTitle", vo.getDataTitle());
				obj.put("tel", vo.getTel());
		        obj.put("addr", vo.getAddr());
		        obj.put("price", vo.getPrice());
		        obj.put("dataContent", vo.getDataContent());
				arr.add(obj);
			}
			break;
		
		}
		json = arr.toJSONString();
		return json;
	}
	@RequestMapping(value="schedule/sch_insert.do",produces = "application/json; charset=utf8")
	public String sch_insert(String insertdata,String daydata,String schtitle,HttpSession session){
		ScheduleVO svo = new ScheduleVO();
		String id = (String) session.getAttribute("id");
		svo.setId(id);
		svo.setStartday(daydata.substring(3));
		svo.setTitle(schtitle);
		sdao.scheduleInsert(svo);
		
		StringTokenizer st = new StringTokenizer(insertdata,",");
		
		while (st.hasMoreTokens()) {
			String allst = st.nextToken();
			String time = allst.substring(0,allst.indexOf(":"));
			String cosname = allst.substring(allst.indexOf(":")+1,allst.indexOf("*"));
			String cosimg =  allst.substring(allst.indexOf("*")+1,allst.indexOf("^"));
			String cosaddr =  allst.substring(allst.indexOf("^")+1);
			TimeVO tvo = new TimeVO();
			
			tvo.setSno(sdao.scheduleGetNo(svo));
			tvo.setTime(Integer.parseInt(time));
			tvo.setCostitle(cosname);
			tvo.setCosimg(cosimg);
			tvo.setCosaddr(cosaddr);
			sdao.timeInsert(tvo);
		}
				
		return "¼º°ø";
	}
	
	@RequestMapping(value="schedule/schedule_data.do",produces = "application/json; charset=utf8")
	public String schedule_data(String page,HttpSession session){
		JSONArray arr = new JSONArray();
		String json = "";
		String id = (String) session.getAttribute("id");
		 if(page==null) page="1";
		 
		 int curpage=Integer.parseInt(page);
		   HashMap map=new HashMap();
		   int rowSize=8;
		   int start=(curpage*rowSize)-(rowSize-1);
		   int end=(curpage*rowSize);
		   
		   map.put("id", id);
		   map.put("start", start);
		   map.put("end", end);
		   
		   List<ScheduleVO> list = sdao.scheduleData(map);
		   
		   for(ScheduleVO vo:list){
				JSONObject obj = new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("schTitle", vo.getTitle());
				obj.put("startDay", vo.getStartday());
				obj.put("cosimg", vo.getCosimg());
				arr.add(obj);
			}
		   json = arr.toJSONString();
		   return json;
	}
	
	@RequestMapping(value="schedule/schedule_layerdetail.do",produces = "application/json; charset=utf8")
	public String schedule_layerdetail(String no){
		JSONArray arr = new JSONArray();
		String json = "";
		   
		   ScheduleVO svo = sdao.scheduleDetail(Integer.parseInt(no));
		   List<TimeVO> tlist = sdao.sTimeDetail(Integer.parseInt(no));
		   
		   for(TimeVO vo:tlist){
				JSONObject obj = new JSONObject();
				obj.put("time", vo.getTime());
				obj.put("cosTitle", vo.getCostitle());
				obj.put("cosImg", vo.getCosimg());
				obj.put("cosAddr", vo.getCosaddr());
				arr.add(obj);
			}
		   JSONObject obj = new JSONObject();
		   obj.put("schNo", svo.getNo());
		   obj.put("schTitle", svo.getTitle());
		   obj.put("startDay", svo.getStartday());
		   obj.put("timeData", arr);
		   
		   json = obj.toJSONString();
		   System.out.println(json);
		   return json;
	}
}
