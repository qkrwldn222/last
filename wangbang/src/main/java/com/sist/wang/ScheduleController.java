package com.sist.wang;

import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.ExperienceDAO;
import com.sist.dao.RestllwDAO;
import com.sist.dao.ScheduleDAO;
import com.sist.vo.ScheduleVO;
import com.sist.vo.TimeVO;

@Controller
public class ScheduleController {
	
	@Autowired
	private RestllwDAO kdao;
	 
	@Autowired
	private ExperienceDAO jdao;
	
	@Autowired
	private MainController main;
	
	@Autowired
	private ScheduleDAO sdao;
	 
	@RequestMapping("schedule/schedule.do")
	public String schedule_main(Model model){
		main.realData(model);
		model.addAttribute("walkTotalPage", kdao.walkingTourTotalPage());
		model.addAttribute("stayTotalPage", jdao.stayTotalPage());
		return "schedule/schedule";
	}
	@RequestMapping("schedule/sch_insert.do")
	public String schedule_sch_insert(Model model,String insertdata,String daydata,String schtitle,HttpSession session){
		main.realData(model);
		model.addAttribute("walkTotalPage", kdao.walkingTourTotalPage());
		model.addAttribute("stayTotalPage", jdao.stayTotalPage());
		
		ScheduleVO svo = new ScheduleVO();
		String id = (String) session.getAttribute("id");
		System.out.println(daydata);
		System.out.println(schtitle);
		System.out.println(id);
		svo.setId(id);
		svo.setStartday(daydata.substring(3));
		svo.setTitle(schtitle);
		sdao.scheduleInsert(svo);
		
		StringTokenizer st = new StringTokenizer(insertdata,",");
		
		while (st.hasMoreTokens()) {
			String allst = st.nextToken();
			String time = allst.substring(0,allst.indexOf(":"));
			String cosname = allst.substring(allst.indexOf(":")+1,allst.indexOf("*"));
			String cosimg =  allst.substring(allst.indexOf("*")+1,allst.indexOf("^"));
			String cosaddr =  allst.substring(allst.indexOf("^")+1);
			TimeVO tvo = new TimeVO();
			
			tvo.setSno(sdao.scheduleGetNo(svo));
			tvo.setTime(Integer.parseInt(time));
			tvo.setCostitle(cosname);
			tvo.setCosimg(cosimg);
			tvo.setCosaddr(cosaddr);
			sdao.timeInsert(tvo);
		}
		return "schedule/schedule";
	}
	@RequestMapping("schedule/sch_update.do")
	public String schedule_sch_update(Model model,String insertdata,String daydata,String schtitle,HttpSession session){
		main.realData(model);
		model.addAttribute("walkTotalPage", kdao.walkingTourTotalPage());
		model.addAttribute("stayTotalPage", jdao.stayTotalPage());
		
		ScheduleVO svo = new ScheduleVO();
		String id = (String) session.getAttribute("id");
		svo.setId(id);
		svo.setStartday(daydata.substring(3));
		svo.setTitle(schtitle);
		sdao.scheduleUpdate(svo);
	
		int sno = sdao.scheduleGetNo(svo);
		System.out.println(sno);
		sdao.timeDelete(sno);
		
		StringTokenizer st = new StringTokenizer(insertdata,",");
		while (st.hasMoreTokens()) {
			String allst = st.nextToken();
			String time = allst.substring(0,allst.indexOf(":"));
			String cosname = allst.substring(allst.indexOf(":")+1,allst.indexOf("*"));
			String cosimg =  allst.substring(allst.indexOf("*")+1,allst.indexOf("^"));
			String cosaddr =  allst.substring(allst.indexOf("^")+1);
			TimeVO tvo = new TimeVO();
			
			tvo.setSno(sno);
			tvo.setTime(Integer.parseInt(time));
			tvo.setCostitle(cosname);
			tvo.setCosimg(cosimg);
			tvo.setCosaddr(cosaddr);
			sdao.timeInsert(tvo);
		}
		
		return "schedule/schedule";
	}
}
