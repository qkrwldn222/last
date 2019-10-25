package com.sist.wang;

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
import com.sist.manager.API;
import com.sist.manager.APITT;
import com.sist.vo.ExperienceVO;
import com.sist.vo.ShoppingVO;
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;

@RestController
public class ScheduleRestController {
	
	@Autowired
	private ExperienceDAO dao;
	
	@RequestMapping(value="schedule/stay_data.do",produces = "application/json; charset=utf8")
	public String stay_data(String page){
		String json="";
		if(Integer.parseInt(page)< 1) page="1";
		List<StayVO> list = dao.stay_data(Integer.parseInt(page));
		
		JSONArray arr = new JSONArray();
		for(StayVO vo:list){
			JSONObject obj = new JSONObject();
			obj.put("mainimgthumb", vo.getMainimgthumb());
			obj.put("dataTitle", vo.getDataTitle());
			obj.put("tel", vo.getTel());
			obj.put("addr", vo.getAddr());
			obj.put("price", vo.getPrice());
			obj.put("dataSid", vo.getDataSid());
			obj.put("img1thumb", vo.getImg1thumb());
			obj.put("img2thumb", vo.getImg2thumb());
			obj.put("img3thumb", vo.getImg3thumb());
			arr.add(obj);
		}
		json = arr.toJSONString();
		//System.out.println("이게나와야 된것"+json);
		//System.out.println("리액트ㅠ"+json);
		return json;
	}
	@RequestMapping(value="schedule/sch_insert.do",produces = "application/json; charset=utf8")
	public String sch_insert(String insertdata,String daydata){
		StringTokenizer st = new StringTokenizer(insertdata,",");
		while (st.hasMoreTokens()) {
			String allst = st.nextToken();
			String time = allst.substring(0,allst.indexOf(":"));
			String cosname = allst.substring(allst.indexOf(":")+1);		
			System.out.println(time);
			System.out.println(cosname);
		}
		System.out.println(daydata.substring(3));
		
		return "성공";
	}
}
