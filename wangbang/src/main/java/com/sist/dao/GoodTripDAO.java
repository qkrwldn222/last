package com.sist.dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoCursorNotFoundException;
import com.sist.manager.*;
import com.sist.vo.*;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GoodTripDAO {
	@Autowired
	GoodTripWeatherManager mgr;
	@Autowired
	private MongoTemplate mt;
	
	public List<GTWeatherVO> findWeatherData() {
		List<GTWeatherVO> list = new ArrayList<GTWeatherVO>();
		
		// 일주일 범위 찾아오기
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		String[] week = new String[7];
		for(int i = 0; i < 6; i++) {
			cal.add(Calendar.DATE, -i);
			if(cal.get(Calendar.DAY_OF_WEEK)==1) 
				break;
		}
		for(int i = 0; i < 7; i++) {
			int year = cal.get(Calendar.YEAR);
			int vMonth = cal.get(Calendar.MONTH)+1;
			String month = (vMonth / 10 > 0)? Integer.toString(vMonth) : ("0" + vMonth);
			int vDate = cal.get(Calendar.DATE);
			String date = (vDate / 10 > 0)? Integer.toString(vDate) : ("0" + vDate);
			week[i] = year + month + date;
			cal.add(Calendar.DATE, 1);
		}
		
		// 일주일치 데이터 가져오기
		for(String w : week) {
			Criteria cri = new Criteria("date");
			cri.is(w);
			Query query = new Query(cri);
			Sort sort = new Sort(Sort.Direction.ASC, "date", "time");
			query.with(sort);
			
			List<GTWeatherVO> tmp = mt.find(query, GTWeatherVO.class, "gtWeather");
			list.add(tmp.get(0));
			list.add(tmp.get(1));
		}
		return list;
	}
	
	public void newInsertAndUpdate() {
		DBCollection collection = mt.getCollection("gtWeather");
		List<GTWeatherVO> mlist = mgr.MiddleWeather();
		List<GTWeatherVO> flist = mgr.forecastSpace();
		
		for(GTWeatherVO vo : mlist) {
			String date = vo.getDate();
			String time = vo.getTime();
			String temperature = vo.getTemperature();
			String rain = vo.getRain();
			String statue = vo.getStatue();
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("date", date);
			searchQuery.put("time", time);
			
			DBCursor cursor = collection.find(searchQuery);
			
			if(cursor.hasNext()) {
				BasicDBObject oldQuery = (BasicDBObject)cursor.next();
				BasicDBObject newQuery = new BasicDBObject();
				newQuery.put("temperature", temperature);
				newQuery.put("rain", rain);
				newQuery.put("statue", statue);
				BasicDBObject updateQuery = new BasicDBObject();
				updateQuery.put("$set", newQuery);
				collection.update(oldQuery, updateQuery);
			} else {
				mt.insert(vo, "gtWeather");
			}
		}
		
		for(GTWeatherVO vo : flist) {
			String date = vo.getDate();
			String time = vo.getTime();
			String temperature = vo.getTemperature();
			String rain = vo.getRain();
			String statue = vo.getStatue();
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("date", date);
			searchQuery.put("time", time);
			
			DBCursor cursor = collection.find(searchQuery);
			
			if(cursor.hasNext()) {
				BasicDBObject oldQuery = (BasicDBObject)cursor.next();
				BasicDBObject newQuery = new BasicDBObject();
				newQuery.put("temperature", temperature);
				newQuery.put("rain", rain);
				newQuery.put("statue", statue);
				BasicDBObject updateQuery = new BasicDBObject();
				updateQuery.put("$set", newQuery);
				collection.update(oldQuery, updateQuery);
			} else {
				mt.insert(vo, "gtWeather");
			}
		}
	}
	
	public Map<String, String> statueFileName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0,1", "../icons/weather-png/Sun.png"); // 맑음
		map.put("0,3", "../icons/weather-png/Cloud-Sun.png"); // 구름많음
		map.put("1,3", "../icons/weather-png/Cloud-Rain-Sun.png"); // 구름많고 비
		map.put("2,3", "../icons/weather-png/Cloud-Hail-Sun.png"); // 구름많고 비/눈
		map.put("3,3", "../icons/weather-png/Cloud-Snow-Sun.png"); // 구름많고 눈
		map.put("0,4", "../icons/weather-png/Cloud.png"); // 흐림
		map.put("1,4", "../icons/weather-png/Cloud-Rain.png"); // 흐리고 비
		map.put("2,4", "../icons/weather-png/Cloud-Hail.png"); // 흐리고 비/눈
		map.put("3,4", "../icons/weather-png/Cloud-Snow.png"); // 흐리고 눈
		map.put("4,0", "../icons/weather-png/Umbrella.png"); // 소나기
		map.put("4,3", "../icons/weather-png/Umbrella.png"); // 소나기
		map.put("4,4", "../icons/weather-png/Umbrella.png"); // 소나기
		return map;
	}
}











