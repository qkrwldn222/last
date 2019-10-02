package com.sist.wang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.MongoDAO;
import com.sist.vo.ExperienceVO;
import com.sist.vo.RestaurantVO;

@Controller
public class SearchController {
	private MongoDAO dao = new MongoDAO();
		
	   @RequestMapping("search/stay.do")
	   public String search_stay(Model model,String page) {
		   if(page==null) page="1";
			
		List<RestaurantVO> list	=dao.resAllData(Integer.parseInt(page), "test");
		//System.out.println(list.size());
		for(RestaurantVO vo:list){
			vo.getDataTtitle();
		}
		model.addAttribute("list",list);
		return "search/stay";
	   }
	   
	   @RequestMapping("search/experience.do")
	   public String search_place(Model model, String page) {
		   if(page==null) page="1";
		   
		   List<ExperienceVO> list = dao.ExAllData(Integer.parseInt(page), "Experience");
		  for(ExperienceVO vo:list) {
			  vo.getDataContent();
		  }
		  model.addAttribute("list", list);
		  return "search/experience";
	   }
}
