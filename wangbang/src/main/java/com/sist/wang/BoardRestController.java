package com.sist.wang;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping(value="board/list_json.do", produces = "application/json; charset=utf8") //콘솔에서 ??? 뜸 그래서 해줘본다.
	public String movie_find(String page){
			
		String result = "";
		
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
			vo.setDbday("");
			vo.setSubject("");
			vo.setSubject("");
			list.add(vo);
			if(i==10) break;
			i++;
		}
		
		JSONArray arr=new JSONArray();
		//[]: JSONArray {}: JSONObject
		  for(BoardVO vo2:list)
		   {
			   JSONObject obj=new JSONObject();
			   
			   obj.put("no", vo2.getNo());
			   obj.put("subject", vo2.getSubject());
			   obj.put("content", vo2.getContent());
			   obj.put("dbday", vo2.getDbday());
			   obj.put("hit", vo2.getHit());
			   obj.put("id", vo2.getId());
			   
			   arr.add(obj);
		   }
		   
		   result=arr.toJSONString();
		   System.out.println(result);
		   return result;
	   }
}
