package com.sist.wang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
public class SearchController {

	   
	   @RequestMapping("search/experience.do")
	   public String search_place(Model model, String page) {
		   if(page==null) page="1";
//		   List<ExperienceVO> list = dao.ExAllData(Integer.parseInt(page), "Experience");
//		  for(ExperienceVO vo:list) {
//			  vo.getMainimgthumb();
//			  vo.getDataTitle();
//			  vo.getAddr();
//			  vo.getInfo();
//			  System.out.println(vo.getDataTitle());
//			  System.out.println(vo.getAddr());
//			  System.out.println(vo.getInfo());
//		  }
//		  model.addAttribute("list", list);
		  return "search/experience";
	   }
	   
	   @RequestMapping("search/leisure.do")
	   public String search_leisure(Model model, String page) {
		   if(page==null) page="1";
//		   List<LeisureVO> list = dao.leisureListData(Integer.parseInt(page), "leisure");
//		   System.out.println(list);
//		  
//		   for(LeisureVO vo:list) {
//			   vo.getAddr();
//			   vo.getMainimgthumb();
//			   vo.getTel();
//			   vo.getInfo();
//			   vo.getDataTitle();
//			   System.out.println(vo.getAddr());
//			   System.out.println(vo.getMainimgthumb());
//			   
//		   }
//		   model.addAttribute("list", list);
		   return "search/leisure";
	   }
}
