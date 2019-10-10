package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.ExperienceDAO;
import com.sist.dao.RestMongDAO;
import com.sist.vo.ExperienceVO;
import com.sist.vo.RestaurantVO;

import java.util.*;

@Controller
public class ExperienceController {
	@Autowired
	private ExperienceDAO dao;
			@RequestMapping("search/experience.do")
			public String search_place(Model model, String page) {
				   if(page==null) page="1";
				   List<ExperienceVO> list = dao.experience_data(Integer.parseInt(page));
				   System.out.println("리스트의값은?"+list);
				  for(ExperienceVO vo:list) {
					  vo.getMainimgthumb();
					  vo.getDataTitle();
					  vo.getAddr();
					  //vo.getDataContent();
					  System.out.println("이미지값은?"+vo.getMainimgthumb());
					  System.out.println(vo.getDataTitle());
					  System.out.println(vo.getAddr());
					 // System.out.println(vo.getData
				  }
			  model.addAttribute("list", list);
			  return "search/experience";
			}
}