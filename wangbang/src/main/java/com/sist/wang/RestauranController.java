package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RestMongDAO;
import com.sist.vo.RestaurantVO;


@Controller
public class RestauranController {
	
	@Autowired
	private RestMongDAO dao;
	@RequestMapping("search/restaurant.do")
	public String res_list(Model model,String type,String page){
		
		int total = dao.TotalPage();
		model.addAttribute("totalpage",total);
		return "search/restaurant";
	}
	@RequestMapping("search/restaurant_detail.do")
	public String rest_detail(Model model,String dataSid){
		RestaurantVO vo = dao.rest_detail(dataSid);
		model.addAttribute("vo",vo);
		
		return "search/restaurant_detail";
	}
}
