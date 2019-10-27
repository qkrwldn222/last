package com.sist.wang;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.BeachDAO;

@RestController
public class BeachRestController {
	@Autowired
	private BeachDAO dao;
	
	@RequestMapping("beach/beach_location.do")
	public String beach_location(int no){
		
		String[] x = {"35.153106", "35.158732", "35.075437", "35.046815", "35.178610", "35.259314", "35.318485", "35.059857"};
		String[] y = {"129.118697", "129.160376", "129.016771", "128.962416", "129.199703", "129.233954", "129.264321", "129.077518"};
		String result = "";
		JSONArray arr = new JSONArray();
		for(int i = 0; i<x.length; i++){
			JSONObject obj = new JSONObject();
			obj.put("x", x[i]);
			obj.put("y", y[i]);
			
			arr.add(obj);
		}
		result = arr.toJSONString();
		return result;
	}
	
}
