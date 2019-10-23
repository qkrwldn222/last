package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RestMongDAO;

import com.sist.manager.KkmaManager;
import com.sist.manager.NaverRestaurantManager;
import com.sist.vo.RestaurantVO;
import com.sist.vo.ThemeTourVO;
import com.sist.vo.TouristattrVO;
import java.util.*;

@Controller
public class RestauranController {
	@Autowired
	private MainController main;
	
	@Autowired
	private RestMongDAO dao;
	@Autowired
	private NaverRestaurantManager nrt;
	
	@Autowired
	private KkmaManager km;
	
	@RequestMapping("search/restaurant.do")
	public String res_list(Model model,String type,String page){
		
		int total = dao.RestTotalPage();
		model.addAttribute("totalpage",total);
		main.realData(model);
		return "search/restaurant";
	}
	@RequestMapping("search/restaurant_detail.do")
	public String rest_detail(Model model,String dataSid){
		RestaurantVO vo = dao.rest_detail(dataSid);
		if(vo.getXgsx()==null){
			vo.setXgsx("33.450701");
			vo.setYgsx("126.570667");
		}
		model.addAttribute("vo",vo);
		model.addAttribute("x",vo.getXgsx());
		model.addAttribute("y",vo.getYgsx());
		
		main.realData(model);
		return "search/restaurant_detail";
	}
	@RequestMapping("search/themetour.do")
	public String themeTour(Model model){
		//
		main.realData(model);
		return "search/themetour";
	}
	
	@RequestMapping("search/touristattr.do")
	public String touristattr(Model model){
		main.realData(model);
		return "search/touristattr";
	}
	
	@RequestMapping("search/touristattr_detail.do")
	public String touristattr_detail(String dataSid,Model model){
		ThemeTourVO vo = dao.themeDetail(dataSid);
		main.realData(model);
		model.addAttribute("vo", vo);
		return "search/touristattr_detail";
	}
	
	@RequestMapping("search/themetour_detail.do")
	public String themetour_detail(String dataSid,Model model){
		TouristattrVO vo = dao.touristDetail(dataSid);
		main.realData(model);
		model.addAttribute("vo",vo);
		return "search/themetour_detail";
	}
	
	@RequestMapping("recommend/rest_recommend_data.do")
	public String rest_recommand_data(Model model,String fd,int no){
		if(no==1){
			List<RestaurantVO> list = dao.restSearchAll(fd);
			model.addAttribute("list",list);
		}else if(no==2){
			List<ThemeTourVO> list = dao.spotSearchAll(fd);
			model.addAttribute("list",list);
		}else if(no==3){
			List<TouristattrVO> list = dao.tourSearchAll(fd);
			model.addAttribute("list",list);
		}
		
		return "recommand_list";
	}
	
	@RequestMapping("recommend/graph.do")
	public String graph(Model model,String dataSid,int no){
		if(no==1){
			ThemeTourVO vo = dao.themeDetail(dataSid);
			System.out.println("林家:"+vo.getAddr());
			model.addAttribute("addr", vo.getAddr());
		}else if(no==2){
			ThemeTourVO vo = dao.themeDetail(dataSid);
			System.out.println("林家:"+vo.getAddr());
			model.addAttribute("addr", vo.getAddr());
		}else if(no==3){
			TouristattrVO vo = dao.touristDetail(dataSid);
			System.out.println("林家:"+vo.getAddr());
			model.addAttribute("addr", vo.getAddr());
		}
		return "map";
	}
	@RequestMapping("course/recommend.do")
	public String recommend(Model model){
	   main.realData(model);
	   return "recommend/recommend";
	}
	
}
