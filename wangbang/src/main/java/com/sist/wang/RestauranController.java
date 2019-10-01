package com.sist.wang;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MongoDAO;

@Component
public class RestauranController {
	private MongoDAO dao = new MongoDAO();
	
	/*@RequestMapping("search/stay.do")
	public String res_list(Model model,String type,String page){
		
		
		return "search/stay";
	}*/
}
