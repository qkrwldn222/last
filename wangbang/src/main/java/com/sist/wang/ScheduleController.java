package com.sist.wang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.ExperienceDAO;
import com.sist.dao.RestllwDAO;

@Controller
public class ScheduleController {
	
	@Autowired
	private RestllwDAO kdao;
	 
	@Autowired
	private ExperienceDAO jdao;
	
	@Autowired
	private MainController main;
	 
	@RequestMapping("schedule/schedule.do")
	public String schedule_main(Model model){
		main.realData(model);
		model.addAttribute("walkTotalPage", kdao.walkingTourTotalPage());
		model.addAttribute("stayTotalPage", jdao.stayTotalPage());
		return "schedule/schedule";
	}
}
