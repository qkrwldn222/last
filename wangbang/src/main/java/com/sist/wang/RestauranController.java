package com.sist.wang;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MongoDAO;

@Controller
public class RestauranController {
	private MongoDAO dao = new MongoDAO();
	
	@RequestMapping("search/restaurant.do")
	public String res_list(Model model,String type,String page){
		
		
		return "search/restaurant";
	}
	@RequestMapping("search/restaurant_detail.do")
	public String rest_detail(Model model,int dataSid){
		
		
		
		return "search/restaurant_detail";
	}
}
