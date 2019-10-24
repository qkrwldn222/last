package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.CourseDAO;
import com.sist.vo.CourseVO;

@Controller
public class CourseController {
	@Autowired
	private CourseDAO dao;
	
	@RequestMapping("course/course_list.do")//
	public String course_list(Model model){
		
		String xgsx="35.158240";
		String ygsx="129.160362";

		model.addAttribute("x",xgsx);
		model.addAttribute("y",ygsx);
		return "course/course_list";
	}
}
