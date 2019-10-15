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
	@RequestMapping("course/course_list.do")
	public String course_list(Model model,String data){
		CourseVO vo =dao.course_list(data);
		if(vo.getXgsx()==null){
			vo.setXgsx("35.158240");
			vo.setYgsx("129.160362");
		}
		model.addAttribute("vo",vo);
		model.addAttribute("x",vo.getXgsx());
		model.addAttribute("y",vo.getYgsx());
		return "course/course_list";
	}
}
