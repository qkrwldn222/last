package com.sist.wang;

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
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;

@RestController
public class ExperienceRestController {
	private APITT api = new APITT();
	@Autowired
	private ExperienceDAO dao;
	
	@RequestMapping(value="search/ex_data.do",produces = "application/json; charset=utf8")
	public String ex_data(String page){
		String json="";
		if(Integer.parseInt(page)< 1) page="1";
		List<ExperienceVO> list = dao.ex_data(Integer.parseInt(page));
		JSONArray arr = new JSONArray();
		for(ExperienceVO vo:list){
			JSONObject obj = new JSONObject();
			obj.put("mainimgthumb", vo.getMainimgthumb());
			obj.put("dataTitle", vo.getDataTitle());
			obj.put("tel", vo.getTel());
			obj.put("addr", vo.getAddr());
			obj.put("useperiod", vo.getUseperiod());
			obj.put("dataSid", vo.getDataSid());
			arr.add(obj);
		}
		json = arr.toJSONString();
		//System.out.println("리액트ㅠ"+json);
		return json;
	}
	
	//search/stay_data.do
	@RequestMapping(value="search/stay_data.do",produces = "application/json; charset=utf8")
	public String stay_data(String page){
		System.out.println("==========================1");
		String json="";
		if(Integer.parseInt(page)< 1) page="1";
		List<StayVO> list = dao.stay_data(Integer.parseInt(page));
		JSONArray arr = new JSONArray();
		System.out.println("==========================2");
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
		System.out.println("이게나와야 된것"+json);
		//System.out.println("리액트ㅠ"+json);
		return json;
	}
}