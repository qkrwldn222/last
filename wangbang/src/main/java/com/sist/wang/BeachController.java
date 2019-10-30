package com.sist.wang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.BeachDAO;
import com.sist.manager.BeachManager;
import com.sist.vo.BeachBlogVO;
import com.sist.vo.BeachResVO;
import com.sist.vo.BeachSpotVO;
import com.sist.vo.BeachVO;

@Controller
public class BeachController {
	@Autowired
	private BeachDAO dao;
	
	@Autowired
	private BeachManager bm;
	
	@RequestMapping("beach/beach.do")
	public String beach_beach(Model model){
		List<BeachVO> list = bm.beach_like(model);
		model.addAttribute("list", list);
		//List<BeachBlogVO> tList = dao.beachBlogData(no);
		//model.addAttribute("tList", tList);
		//List<BeachResVO> rList = dao.beachResData(no);
		//model.addAttribute("rList", rList);
		return "beach/beach";
	}
	
	@RequestMapping("beach/beach_blog.do")
	@ResponseBody
	public String beach_blog(int no){
		String html = "";
		StringBuilder sb = new StringBuilder("");
		List<BeachBlogVO> tList = dao.beachBlogData(no);
		
		sb.append("<h4 class='text-secondary' style='margin-bottom: 20px; margin-top: 30px;'><b>블로그 리뷰</b></h4>");
		sb.append("<div class='row' style='margin-top:20px;'>");
		for(BeachBlogVO bvo:tList){
			sb.append("<div class='col-lg-6'>");
			sb.append("<div class='card'>");
			sb.append("<div class='card-body'>");
			sb.append("<a href='" + bvo.getLink() + "'><img src='" + bvo.getImage() + "' style='float:left; margin-right:20px;'></a>");
			sb.append("<a href='" + bvo.getLink() + "'><h6 class='card-title'>" + bvo.getTitle() + "</h6></a>");
			sb.append("<p class='card-text'>" + bvo.getContent() + "</p>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}
		sb.append("</div>");
		html = sb.toString();
		
		return html;
	}
	
	@RequestMapping("beach/beach_res.do")
	@ResponseBody
	public String beach_res(int no){
		String html = "";
		StringBuilder sb = new StringBuilder("");
		List<BeachResVO> rList = dao.beachResData(no);
		
		if(!(no==2 || no==5 || no==8)){
			sb.append("<h4 class='text-secondary' style='margin-bottom: 20px; margin-top: 30px;'><b>주변 맛집</b></h4>");
			sb.append("<div class='row' style='margin-top:20px;'>");
			for(BeachResVO rvo:rList){
				sb.append("<div class='col-md-6 col-lg-3'>");
				sb.append("<div class='card'>");
				sb.append("<div class='card-body'>");
				sb.append("<img src='" + rvo.getImage() + "' style='margin-bottom:15px'>");
				sb.append("<h6 class='card-title'>" + rvo.getTitle() + "</h6>");
				sb.append("<p class='card-text'>" + rvo.getInfo() + "</p>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
			}
			sb.append("</div>");
			html = sb.toString();
		}
		
		return html;
	}
	
	@RequestMapping("beach/beach_spot.do")
	@ResponseBody
	public String beach_spot(int no){
		String html = "";
		StringBuilder sb = new StringBuilder("");
		List<BeachSpotVO> sList = dao.beachSpotData(no);
		
		if(no==1 || no==6 || no==4){
			sb.append("<h4 class='text-secondary' style='margin-bottom: 20px; margin-top: 30px;'><b>가볼만한 곳</b></h4>");
			sb.append("<div class='row' style='margin-top:20px;'>");
			for(BeachSpotVO svo:sList){
				sb.append("<div class='col-md-6 col-lg-3'>");
				sb.append("<div class='card'>");
				sb.append("<div class='card-body'>");
				sb.append("<img src='" + svo.getImage() + "' style='margin-bottom:15px'>");
				sb.append("<h6 class='card-title'>" + svo.getTitle() + "</h6>");
				sb.append("<p class='card-text'>" + svo.getInfo() + "</p>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
			}
			sb.append("</div>");
			html = sb.toString();
		}
		return html;
	}
}
