package com.sist.wang;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.ExperienceDAO;
import com.sist.dao.RestMongDAO;
import com.sist.manager.APIWeather;
import com.sist.vo.ExperienceVO;
import com.sist.vo.RestaurantVO;

import java.util.*;

@Controller
public class ExperienceController {
	@Autowired
	private ExperienceDAO dao;
	
	@Autowired
	private MainController main;

			@RequestMapping("search/experience.do")
			public String ex(Model model, String page) {
				   if(page==null) page="1";
				   List<ExperienceVO> list = dao.ex_data(Integer.parseInt(page));
				   System.out.println("리스트의값은?"+list);
				  for(ExperienceVO vo:list) {
					  vo.getMainimgthumb();
					  vo.getDataTitle();
					  vo.getAddr();
					  vo.getUseperiod();
					  vo.getDataSid();
					  System.out.println("이미지값은?"+vo.getMainimgthumb());
					  System.out.println(vo.getDataTitle());
					  System.out.println(vo.getAddr());
					  System.out.println(vo.getUseperiod());
					  System.out.println(vo.getDataSid());
				  }
			  model.addAttribute("list", list);
			  main.realData(model);
			  return "search/experience";
			}
			@RequestMapping("search/experience_detail.do")
			public String ex_detail(Model model, String dataSid) {
				ExperienceVO vo = dao.ex_detail(dataSid);
				model.addAttribute("vo", vo);
				main.realData(model);
				return "search/experience_detail";
			}
/*			@RequestMapping("search/top_content.do")
			public String weather(Model model) {
				 JSONArray list = new JSONArray();
				 list = dao.weather();
				 org.json.JSONArray temp = (org.json.JSONArray) list.get(0);
				 
		        String SKY = "";
				 for(int i = 0 ; i < temp.length(); i++ ){
			        	JSONObject temp_i = (JSONObject)temp.get(i);
			        	System.out.println("baseDate : "+temp_i.get("baseDate"));
			        	System.out.println("fcstTime : "+temp_i.get("fcstTime"));
			        	System.out.println("fcstValue : "+temp_i.get("fcstValue"));
			        	System.out.println("nx : "+temp_i.get("nx"));
			        	System.out.println("ny : "+temp_i.get("ny"));
			        	System.out.println("category : "+temp_i.get("category"));
			        	System.out.println("baseTime : "+temp_i.get("baseTime"));
			        	System.out.println("fcstDate : "+temp_i.get("fcstDate"));
			        	
			        	String category = (String) temp_i.get("category");
			        	//String fcstValue = Integer.toString((int) temp_i.get("fcstValue"));
			        	int fcstValue = temp_i.getInt("fcstValue");
			        	if(category.equals("SKY")){
			        		if(fcstValue >= 0 && fcstValue <= 5){
			        			SKY = "맑음";
			        		} else if(fcstValue >=6 && fcstValue <= 8){
			        			SKY ="구름많음";
			        		} else {
			        			SKY ="흐림";
			        		}
			        	}
			        	if(category.equals("SKY")){
			        		if(Integer.parseInt(fcstValue) >= 0 && Integer.parseInt(fcstValue) <= 5){
			        			SKY = "맑음";
			        		} else if(Integer.parseInt(fcstValue) >=6 && Integer.parseInt(fcstValue) <= 8){
			        			SKY ="구름많음";
			        		} else {
			        			SKY ="흐림";
			        		}
			        	}
			        } 
				 System.out.println("oneki -- : " + list);
				 model.addAttribute("SKY", SKY);
				 System.out.println("스카이의값은"+SKY);
				 model.addAttribute("list", list);
				return "main";
			}*/
}