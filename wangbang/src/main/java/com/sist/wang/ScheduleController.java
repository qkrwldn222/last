package com.sist.wang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleController {

	@RequestMapping("schedule/schedule.do")
	public String schedule_main(){
		
		return "schedule/schedule";
	}
}
