package com.sist.wang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.MongoDAO;
import com.sist.vo.ExperienceVO;
import com.sist.vo.LeisureVO;
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
	   
	   @RequestMapping("search/leisure.do")
	   public String search_leisure(Model model, String page) {
		   if(page==null) page="1";
		   List<LeisureVO> list = dao.leisureListData(Integer.parseInt(page), "leisure");
		   System.out.println(list);
		  
		   for(LeisureVO vo:list) {
			   vo.getAddr();
			   vo.getMainimgthumb();
			   vo.getTel();
			   vo.getInfo();
			   vo.getDataTitle();
			   System.out.println(vo.getAddr());
			   System.out.println(vo.getMainimgthumb());
			   
		   }
		   model.addAttribute("list", list);
		   return "search/leisure";
	   }
}
