package com.sist.wang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RestMongDAO;
import com.sist.manager.API;

@RestController
public class RestaurantRestController {
	private API api = new API();
	@Autowired
	private RestMongDAO dao;
	@RequestMapping(value="search/restaurant_data.do",produces = "application/json; charset=utf8")
	public String rest_data(int page){
		String json ="";
		if(page < 1) page=1;
//		if(no > dao.TotalPage()) no = dao.TotalPage();
		System.out.println("no"+page);
		try {
			List<String> sList = api.RestaurantList(Integer.toString(page));
			for(String name:sList){
				System.out.println(name);
			}
			json = api.RestaurantDetail(sList);
		} catch (Exception e) {}
		System.out.println("json:"+json);
		return json;
	}
}
