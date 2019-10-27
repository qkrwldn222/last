package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RestllwDAO;
import com.sist.vo.LeisureVO;
import com.sist.vo.LuxuryTourVO;
import com.sist.vo.WalkingTourVO;

@Controller
public class LeisureController {
	 @Autowired
	 private RestllwDAO dao;
	 
	 @Autowired
	 private MainController main;
	 
	 @RequestMapping("search/leisure.do")
     public String leisure(Model model) {
		 main.realData(model);
		 model.addAttribute("totalPage", dao.leisureTotalPage());
         return "search/leisure";		 
	 }
	 
	 @RequestMapping("search/leisure_detail.do")
     public String leisure_detail(Model model, String dataSid) {
        LeisureVO vo = dao.leisure_detail(dataSid);
        model.addAttribute("vo", vo);
        main.realData(model);
        return "search/leisure_detail";
     }
	 
	 @RequestMapping("search/luxurytour.do")
     public String luxuryTour(Model model) {
		 main.realData(model);
		 model.addAttribute("totalPage", dao.luxuryTourTotalPage());
         return "search/luxurytour";		 
	 }
	 
	 @RequestMapping("search/luxurytour_detail.do")
     public String luxuryTour_detail(Model model, String dataSid) {
		LuxuryTourVO vo = dao.luxuryTour_detail(dataSid);
        model.addAttribute("vo", vo);
        main.realData(model);
        return "search/luxurytour_detail";
     }
	 
	 @RequestMapping("search/walkingtour.do")
     public String walkingTour(Model model) {
		 main.realData(model);
		 model.addAttribute("totalPage", dao.walkingTourTotalPage());
         return "search/walkingtour";		 
	 }
	 
	 @RequestMapping("search/walkingtour_detail.do")
     public String walkingTour_detail(Model model, String dataSid) {
		WalkingTourVO vo = dao.walkingTour_detail(dataSid);
        model.addAttribute("vo", vo);
        main.realData(model);
        return "search/walkingtour_detail";
     }
}
