package com.sist.wang;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
		System.out.println("no="+no);
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
				obj.put("addr", vo.getAddr());
				arr.add(obj);
			}
			break;
		case 2:
			List<RestaurantVO> reList = kdao.restaurant_data(Integer.parseInt(page));
			for(RestaurantVO vo:reList){
				JSONObject obj = new JSONObject();
				obj.put("mainimgthumb", vo.getMainimgthumb());
				obj.put("dataTitle", vo.getDataTitle());
				obj.put("addr", vo.getAddr());
				arr.add(obj);
			}
			break;
		case 3:
			List<StayVO> stList = jdao.stay_data(Integer.parseInt(page));
			for(StayVO vo:stList){
				JSONObject obj = new JSONObject();
				obj.put("mainimgthumb", vo.getMainimgthumb());
				obj.put("dataTitle", vo.getDataTitle());
				obj.put("addr", vo.getAddr());
				arr.add(obj);
			}
			break;
		
		}
		json = arr.toJSONString();
		return json;
	}
	@RequestMapping(value="schedule/sch_insert.do",produces = "application/json; charset=utf8")
	public String sch_insert(String insertdata,String daydata,String schtitle){
		
		ScheduleVO svo = new ScheduleVO();
		String id = "ran";
		svo.setId(id);
		svo.setStartday(daydata.substring(3));
		svo.setTitle(schtitle);
		sdao.scheduleInsert(svo);
		
		StringTokenizer st = new StringTokenizer(insertdata,",");
		
		while (st.hasMoreTokens()) {
			String allst = st.nextToken();
			String time = allst.substring(0,allst.indexOf(":"));
			String cosname = allst.substring(allst.indexOf(":")+1);		
			System.out.println(time);
			System.out.println(cosname);
			TimeVO tvo = new TimeVO();
			
			tvo.setSno(sdao.scheduleno(svo));
			tvo.setTime(Integer.parseInt(time));
			tvo.setDatasid(cosname);
			sdao.timeInsert(tvo);
		}
		System.out.println(daydata.substring(3));
		
		
		
		return "¼º°ø";
	}
}
