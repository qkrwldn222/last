package com.sist.wang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.MongoDAO;
import com.sist.vo.RestaurantVO;


@Controller
public class MainController {
   @RequestMapping("main/main.do")
   public String main_main()
   {
	   return "main";
   }
}
