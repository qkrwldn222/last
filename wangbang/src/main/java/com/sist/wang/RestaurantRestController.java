package com.sist.wang;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RestMongDAO;
import com.sist.manager.API;
import com.sist.manager.KkmaManager;
import com.sist.manager.NaverRestaurantManager;
import com.sist.vo.ThemeTourVO;

@RestController
public class RestaurantRestController {
	private API api = new API();
	@Autowired
	private RestMongDAO dao;
	
	@Autowired
	private NaverRestaurantManager nrt;
	
	@Autowired
	private KkmaManager kkm;
	
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
	
	@RequestMapping(value="search/themetour_data.do",produces = "application/json; charset=utf8")
	public String themetour_data(int page){
		String json="";
		if(page< 1) page=1;
		List<ThemeTourVO> list = dao.themeListData(page);
		JSONArray arr = new JSONArray(); // []
		for(ThemeTourVO vo:list){
			JSONObject obj = new JSONObject();
			obj.put("addr", vo.getAddr());
			obj.put("dataTitle", vo.getDataTitle());
			obj.put("mainimgthumb", vo.getMainimgthumb());
			obj.put("tel", vo.getTel());
			obj.put("info", vo.getInfo());
			obj.put("dataSid", vo.getDataSid());
			arr.add(obj); //{}
		}
		json = arr.toJSONString();
		System.out.println(json);
		return json;
	}
	@RequestMapping(value="search/touristattr_data.do",produces = "application/json; charset=utf8")
	public String touristattr_data(int page){
		String json="";
		if(page< 1) page=1;
		List<ThemeTourVO> list = dao.themeListData(page);
		JSONArray arr = new JSONArray(); // []
		for(ThemeTourVO vo:list){
			JSONObject obj = new JSONObject();
			obj.put("addr", vo.getAddr());
			obj.put("dataTitle", vo.getDataTitle());
			obj.put("mainimgthumb", vo.getMainimgthumb());
			obj.put("tel", vo.getTel());
			obj.put("info", vo.getInfo());
			obj.put("dataSid", vo.getDataSid());
			arr.add(obj); //{}
		}
		json = arr.toJSONString();
		System.out.println(json);
		return json;
	}
	
	/*@RequestMapping(value="recommend/rest_recommend.do", produces = "application/json; charset=utf8")
	public String RestRecommend(){
		String json ="";
		String content  = nrt.naverRest();
		List<RecommendVO> list = kkm.keTest(content);
		return json;
	}*/
	@RequestMapping(value="recommend/rest_recommend.do")
	public String rest_recommand_data(){
		String result = kkm.keTest(nrt.naver("부산 맛집"));
		
		return result;
	}
	
	@RequestMapping(value="recommend/tour_recommend.do")
	public String tour_recommand_data(){
		String result ="체험,명품,도보,테마,관광,공원";
		
		return result;
	}
	
	@RequestMapping(value="recommend/spot_recommend.do")
	public String spot_recommand_data(){
		String result ="호텔,게스트하우스,민박,바다";
		return result;
	}
}
