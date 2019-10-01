package com.sist.wang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.MongoDAO;
import com.sist.vo.RestaurantVO;


@Controller
public class MainController {
	private MongoDAO dao = new MongoDAO();
   @RequestMapping("main/main.do")
   public String main_main()
   {
	   return "main";
   }
   @RequestMapping("search/stay.do")
   public String search_stay(Model model,String page) {
	   if(page==null) page="1";
		
	List<RestaurantVO> list	=dao.resAllData(Integer.parseInt(page), "test");
	System.out.println(list.size());
	for(RestaurantVO vo:list){
		vo.getDataTtitle();
	}
	model.addAttribute("list",list);
	return "search/stay";
   }
   @RequestMapping("search/place.do")
   public String search_place() {
	   return "search/place";
   }
}
