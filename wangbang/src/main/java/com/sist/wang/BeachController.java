package com.sist.wang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BeachDAO;
import com.sist.manager.BeachManager;
import com.sist.vo.BeachBlogVO;
import com.sist.vo.BeachResVO;
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
		List<BeachBlogVO> tList = dao.beachBlogData();
		model.addAttribute("tList", tList);
		List<BeachResVO> rList = dao.beachResData();
		model.addAttribute("rList", rList);
		return "beach/beach";
	}
}
