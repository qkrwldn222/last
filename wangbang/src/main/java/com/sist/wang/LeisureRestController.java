package com.sist.wang;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RestllwDAO;
import com.sist.vo.LeisureVO;
import com.sist.vo.LuxuryTourVO;
import com.sist.vo.WalkingTourVO;

@RestController
public class LeisureRestController {
	 @Autowired
	   private RestllwDAO dao;
	 
	 @RequestMapping(value="search/leisure_data.do",produces = "application/json; charset=utf8")
	   public String leisure_data(String page){
	      String json="";
	      if(Integer.parseInt(page)< 1) page="1";
	      List<LeisureVO> list = dao.leisure_data(Integer.parseInt(page));
	      JSONArray arr = new JSONArray();
	      for(LeisureVO vo:list){
	         JSONObject obj = new JSONObject();
	         obj.put("mainimgthumb", vo.getMainimgthumb());
	         obj.put("dataTitle", vo.getDataTitle());
	         obj.put("tel", vo.getTel());
	         obj.put("addr", vo.getAddr());
	         obj.put("price", vo.getPrice());
	         obj.put("dataSid", vo.getDataSid());
	         obj.put("img1thumb", vo.getImg1thumb());
	         obj.put("img2thumb", vo.getImg2thumb());
	         obj.put("img3thumb", vo.getImg3thumb());
	         arr.add(obj);
	      }
	      json = arr.toJSONString();
	      return json;
	   }
	 
	 @RequestMapping(value="search/luxurytour_data.do",produces = "application/json; charset=utf8")
	   public String luxuryTour_data(String page){
	      String json="";
	      if(Integer.parseInt(page)< 1) page="1";
	      List<LuxuryTourVO> list = dao.luxuryTour_data(Integer.parseInt(page));
	      JSONArray arr = new JSONArray();
	      for(LuxuryTourVO vo:list){
	         JSONObject obj = new JSONObject();
	         obj.put("mainimgthumb", vo.getMainimgthumb());
	         obj.put("dataTitle", vo.getDataTitle());
	         obj.put("tel", vo.getTel());
	         obj.put("addr", vo.getAddr());
	         obj.put("price", vo.getPrice());
	         obj.put("dataSid", vo.getDataSid());
	         obj.put("img1thumb", vo.getImg1thumb());
	         obj.put("img2thumb", vo.getImg2thumb());
	         obj.put("img3thumb", vo.getImg3thumb());
	         arr.add(obj);
	      }
	      json = arr.toJSONString();
	      return json;
	   }
	 
	 @RequestMapping(value="search/walkingtour_data.do",produces = "application/json; charset=utf8")
	   public String walkingTour_data(String page){
	      String json="";
	      if(Integer.parseInt(page)< 1) page="1";
	      List<WalkingTourVO> list = dao.walkingTour_data(Integer.parseInt(page));
	      JSONArray arr = new JSONArray();
	      for(WalkingTourVO vo:list){
	         JSONObject obj = new JSONObject();
	         obj.put("mainimgthumb", vo.getMainimgthumb());
	         obj.put("dataTitle", vo.getDataTitle());
	         obj.put("tel", vo.getTel());
	         obj.put("addr", vo.getAddr());
	         obj.put("price", vo.getPrice());
	         obj.put("dataSid", vo.getDataSid());
	         obj.put("img1thumb", vo.getImg1thumb());
	         obj.put("img2thumb", vo.getImg2thumb());
	         obj.put("img3thumb", vo.getImg3thumb());
	         arr.add(obj);
	      }
	      json = arr.toJSONString();
	      return json;
	   }
}
