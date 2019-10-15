package com.sist.wang;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitterRestController {
	public static void main(String[] args) {
		
	}
	
	@RequestMapping(value="main/body_twitter.do",produces = "application/json; charset=utf8")
	public String twitter_data() throws ParseException{
		String json ="";		
		JSONParser parser = new JSONParser();
		JSONArray jarr = new JSONArray();
		
		try { 
		Object obj = parser.parse(new FileReader("c:\\jsonfile\\test9.json"));
		jarr = (JSONArray) obj;
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		if(jarr.size()>10){
			json = "[";
			for(int i=0;i<10;i++){
				
				json += jarr.get(i);
				if(i<9){
					json += ",";
				}
			}
			json += "]";
		}else{
			json = jarr.toJSONString();
		}
		json = json.replace("\\/", "/");
		//System.out.println(json);
		
		return json;
					
		} 
	}

