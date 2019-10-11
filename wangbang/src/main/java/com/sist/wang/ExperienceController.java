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
			public String ex(Model model, String page) {
				   if(page==null) page="1";
				   List<ExperienceVO> list = dao.ex_data(Integer.parseInt(page));
				   System.out.println("리스트의값은?"+list);
				  for(ExperienceVO vo:list) {
					  vo.getMainimgthumb();
					  vo.getDataTitle();
					  vo.getAddr();
					  vo.getUseperiod();
					  vo.getDataSid();
					  System.out.println("이미지값은?"+vo.getMainimgthumb());
					  System.out.println(vo.getDataTitle());
					  System.out.println(vo.getAddr());
					  System.out.println(vo.getUseperiod());
					  System.out.println(vo.getDataSid());
				  }
			  model.addAttribute("list", list);
			  return "search/experience";
			}
			@RequestMapping("search/experience_detail.do")
			public String ex_detail(Model model, String dataSid) {
				ExperienceVO vo = dao.ex_detail(dataSid);
				model.addAttribute("vo", vo);
				return "search/experience_detail";
			}
}