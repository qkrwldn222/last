package com.sist.wang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
   @RequestMapping("main/main.do")
   public String main_main()
   {
	   return "main";
   }
   @RequestMapping("search/stay.do")
   public String search_stay() {
	   return "search/stay";
   }
   @RequestMapping("search/place.do")
   public String search_place() {
	   return "search/place";
   }
}
