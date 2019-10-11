package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RestMongDAO;
import com.sist.vo.RestaurantVO;


@Controller
public class RestauranController {
	
	@Autowired
	private RestMongDAO dao;
	@RequestMapping("search/restaurant.do")
	public String res_list(Model model,String type,String page){
		
		int total = dao.RestTotalPage();
		model.addAttribute("totalpage",total);
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
		System.out.println("y:"+vo.getYgsx()+"x:"+vo.getXgsx());
		return "search/restaurant_detail";
	}
	@RequestMapping("search/themetour.do")
	public String themeTour(Model model){
		//
		
		return "search/themetour";
	}
	
	@RequestMapping("search/touristattr.do")
	public String touristattr(Model model){
		//
		
		return "search/touristattr";
	}
}
