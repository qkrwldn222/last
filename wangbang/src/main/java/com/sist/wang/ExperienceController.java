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
import com.sist.vo.ShoppingVO;
import com.sist.vo.StayVO;

import java.util.*;

@Controller
public class ExperienceController {
	@Autowired
	private ExperienceDAO dao;
	
	@Autowired
	private MainController main;
//체험
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
//숙박
			@RequestMapping("search/stay.do")
			public String stay(Model model, String page) {
				   if(page==null) page="1";
				   List<StayVO> list = dao.stay_data(Integer.parseInt(page));
				   System.out.println("컨트롤러의 stay리스트의값은?"+list);
				  for(StayVO vo:list) {
					  vo.getMainimgthumb();
					  vo.getDataTitle();
					  vo.getAddr();
					  vo.getPrice();
					  vo.getDataSid();
					  vo.getTrafln();
					  vo.getTraOut();
					  System.out.println("버스"+vo.getTraOut());
					  vo.getImg1thumb();
					  vo.getImg2thumb();
					  vo.getImg3thumb();
					  System.out.println("이미지1"+vo.getImg1thumb());
					  System.out.println("이미지2"+vo.getImg2thumb());
					  System.out.println("이미지3"+vo.getImg3thumb());
				  }
				  model.addAttribute("list", list);
				  main.realData(model);
				  return "search/stay";
				}
			@RequestMapping("search/stay_detail.do")
			public String stay_detail(Model model, String dataSid) {
				StayVO vo = dao.stay_detail(dataSid);
				System.out.println("!! getXgsx : " + vo.getXgsx());
				System.out.println("!! getYgsx : " + vo.getYgsx());
				System.out.println("!! getWgsx : " + vo.getWgsx());
				System.out.println("!! getYgsx : " + vo.getWgsy());
				
				model.addAttribute("vo", vo);
				main.realData(model);
				return "search/stay_detail";
			}
//쇼핑
			@RequestMapping("search/shopping.do")
			public String shopping(Model model, String page) {
				if(page==null) page="1";
				List<ShoppingVO> list = dao.shopping_data(Integer.parseInt(page));
				for(ShoppingVO vo:list) {
					vo.getMainimgthumb();
					vo.getDataTitle();
					vo.getAddr();
					vo.getPrice();
					vo.getDataSid();
					vo.getTime();
					vo.getTrafln();
					vo.getTraOut();
					vo.getImg1thumb();
					vo.getImg2thumb();
					vo.getImg3thumb();
				}
				model.addAttribute("list", list);
				main.realData(model);
				return "search/shopping";
			}
			@RequestMapping("search/shopping_detail.do")
			public String shopping_detail(Model model, String dataSid) {
				ShoppingVO vo = dao.shopping_detail(dataSid);
				model.addAttribute("vo", vo);
				main.realData(model);
				return "search/shopping_detail";
			}
}