package com.sist.wang;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BoardDAO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board/list.do")
	public String board_list(Model model,String page){
		page=page==null?"1":page;
		System.out.println("hi");
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		model.addAttribute("page", page);
		return "board/list";
	}
}
