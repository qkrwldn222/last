package com.sist.wang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.CourseDAO;
import com.sist.dao.TourstayresDAO;
import com.sist.manager.RecommandManager;
import com.sist.vo.CourseVO;
import com.sist.vo.ExperienceVO;
import com.sist.vo.RecommandVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;

@Controller
public class CourseController {
	@Autowired
	private TourstayresDAO dao;
	
	@Autowired
	private RecommandManager rm;
	@RequestMapping("course/course_list.do")//
	public String course_list(Model model,String start,String end){
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "course/course_list";
	}
	@RequestMapping("course/test.do")//
	public String testMap(Model model){
		
		return "testMap";
	}
	@RequestMapping("course/course_type.do")
	public String course_type(Model model,String fd,String sno){
		/*List<RecommandVO> list=rm.recommand_find(fd,Integer.parseInt(sno));
		
		
		if(sno.equals("1")){
			List<StayVO> mList=new ArrayList<StayVO>();
			for(RecommandVO vo:list){
				StayVO mvo=dao.stay_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
			
		}else if(sno.equals("2")){
			List<RestaurantVO> mList=new ArrayList<RestaurantVO>();
			for(RecommandVO vo:list){
				RestaurantVO mvo=dao.res_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
		}else if(sno.equals("3")){
			List<ThemeTourVO> mList=new ArrayList<ThemeTourVO>();
			for(RecommandVO vo:list){
				ThemeTourVO mvo=dao.tour_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
		}
		*/
		
		
		return "type";
	}
	
	@RequestMapping("course/course_list_data.do")//
	public String course_list_data(Model model,String fd,String sno){	//fd:rd,sno:sno
		/*List<RecommandVO> list=rm.recommand_find(fd,Integer.parseInt(sno));
		
		
		if(sno.equals("1")){
			List<StayVO> mList=new ArrayList<StayVO>();
			for(RecommandVO vo:list){
				StayVO mvo=dao.stay_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
			
		}else if(sno.equals("2")){
			List<RestaurantVO> mList=new ArrayList<RestaurantVO>();
			for(RecommandVO vo:list){
				RestaurantVO mvo=dao.res_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
		}else if(sno.equals("3")){
			List<ThemeTourVO> mList=new ArrayList<ThemeTourVO>();
			for(RecommandVO vo:list){
				ThemeTourVO mvo=dao.tour_detail(vo.getTitle());
				if(mvo.getWgsx().equals("-")){
					continue;
				}
				System.out.println("=========");
				System.out.println(mvo.getDataTitle());
				System.out.println(mvo.getWgsx());
				System.out.println(mvo.getWgsy());
				System.out.println("@@@@@@@@@");
				mList.add(mvo);
			}
			model.addAttribute("mList", mList);
			model.addAttribute("list", list);
			
		}*/
		
		
		
		return "course/blank";
	}
}
