package com.sist.wang;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board/list.do")
	public String board_list(Model model,String page){
		page=page==null?"1":page;
		
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		Map map=new HashMap();
		map.put("pStart", start);
		map.put("pEnd", end);
		List<BoardVO> list=dao.board_list(map);
		int i=list.size();
		BoardVO vo=new BoardVO();
		while(i!=10){
			vo.setNo(0);
			vo.setHit(0);
			vo.setId("");
			vo.setContent("");
			vo.setRegdate(new Date());
			vo.setSubject("");
			vo.setSubject("");
			list.add(vo);
			if(i==10) break;
			i++;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "board/list";
	}
}
