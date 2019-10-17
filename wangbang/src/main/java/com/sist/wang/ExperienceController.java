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
import com.sist.vo.StayVO;

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
			
			@RequestMapping("search/stay.do")
			public String stay(Model model, String page) {
				if(page==null) page="1";
				List<StayVO> list = dao.stay_data(Integer.parseInt(page));
				for(StayVO vo:list) {
					vo.getMainimgthumb();
					vo.getDataTitle();
					vo.getPrice();
					vo.getAddr();
					vo.getDataSid();
					vo.getDataContent();
					//System.out.println("숙박이미지값은?"+vo.getMainimgthumb());
					//System.out.println(vo.getDataTitle());
					//System.out.println(vo.getPrice());
					//System.out.println(vo.getAddr());
					//System.out.println(vo.getDataSid());
					//System.out.println(vo.getDataContent());
				}
				model.addAttribute("list", list);
				main.realData(model);
				return "search/stay";
			}
/*			@RequestMapping("search/stay_detail.do")
			public String stay_detail(Model model, String dataSid) {
				StayVO vo = dao.stay_detail(dataSid);
				model.addAttribute("vo", vo);
				main.realData(model);
				return "search/stay_detail";
			}*/
}