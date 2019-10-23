package com.sist.dao;
import com.mongodb.*;
import com.sist.manager.*;
import com.sist.vo.*;

import java.text.*;
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
	GoodTripCultureMng mng;
	
	@Autowired
	private MongoTemplate mt;
	
	@Autowired
	private GoodTripNewsManager news;
	
	public List<GTWeatherVO> findWeatherData() {
		List<GTWeatherVO> list = new ArrayList<GTWeatherVO>();
		
		// 일주일 범위 찾아오기
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		String[] week = new String[7];
		for(int i = 0; i < 6; i++) {
			cal.add(Calendar.DATE, -1);
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
			Sort sort = new Sort(Sort.Direction.ASC, "date");
			query.with(sort);
			
			GTWeatherVO tmp = mt.findOne(query, GTWeatherVO.class, "gtWeather");
			list.add(tmp);
		}
		
		for(int i = 0; i < list.size(); i++) {
			GTWeatherVO vo = list.get(i);
			
			String monDate = vo.getDate();
			StringBuilder sb = new StringBuilder("");
			String sMon = monDate.substring(4, 6);
			sb.append(sMon); sb.append("월 ");
			String sDay = monDate.substring(6);
			sb.append(sDay); sb.append("일 ");
			switch (i) {
				case 0:
					sb.append("(일)"); break;
				case 1:
					sb.append("(월)"); break;
				case 2:
					sb.append("(화)"); break;
				case 3:
					sb.append("(수)"); break;
				case 4:
					sb.append("(목)"); break;
				case 5:
					sb.append("(금)"); break;
				case 6:
					sb.append("(토)"); break;
			}
			String lDate = sb.toString();
			vo.setDate(lDate);
			
			String amTem = vo.getAmTem();
			String pmTem = vo.getPmTem();
			
			if(amTem.contains(".")) {
				amTem = amTem.substring(0, amTem.lastIndexOf("."));
				vo.setAmTem(amTem);
			}
			if(pmTem.contains(".")) {
				pmTem = pmTem.substring(0, pmTem.lastIndexOf("."));
				vo.setPmTem(pmTem);
			}
		}
		return list;
	}
	
	public void newInsertAndUpdate() {
		DBCollection collection = mt.getCollection("gtWeather");
		List<GTWeatherVO> mlist = mgr.MiddleWeather();
		List<GTWeatherVO> flist = mgr.forecastSpace();
		
		for(GTWeatherVO vo : flist) {
			String date = vo.getDate();
			String amTem = vo.getAmTem();
			String pmTem = vo.getPmTem();
			String amRain = vo.getAmRain();
			String pmRain = vo.getPmRain();
			String amStatue = vo.getAmStatue();
			String pmStatue = vo.getPmStatue();
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("date", date);
			
			DBCursor cursor = collection.find(searchQuery);
			
			if(cursor.hasNext()) {
				BasicDBObject oldQuery = (BasicDBObject)cursor.next();
				BasicDBObject newQuery = new BasicDBObject();
				boolean amCheck = false;
				boolean pmCheck = false;
				if(!amTem.equals("") && amTem!=null) {
					amCheck = true;
					newQuery.put("amTem", amTem);
					newQuery.put("amRain", amRain);
					newQuery.put("amStatue", amStatue);
				}
				if(!pmTem.equals("") && pmTem!=null) {
					pmCheck = true;
					newQuery.put("pmTem", pmTem);
					newQuery.put("pmRain", pmRain);
					newQuery.put("pmStatue", pmStatue);
				}
				if(amCheck || pmCheck) {
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.put("$set", newQuery);
					collection.update(oldQuery, updateQuery);
				}
			} else {
				mt.insert(vo, "gtWeather");
			}
		}
		
		for(GTWeatherVO vo : mlist) {
			String date = vo.getDate();
			String amTem = vo.getAmTem();
			String pmTem = vo.getPmTem();
			String amRain = vo.getAmRain();
			String pmRain = vo.getPmRain();
			String amStatue = vo.getAmStatue();
			String pmStatue = vo.getPmStatue();
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("date", date);
			
			DBCursor cursor = collection.find(searchQuery);
			
			if(cursor.hasNext()) {
				BasicDBObject oldQuery = (BasicDBObject)cursor.next();
				BasicDBObject newQuery = new BasicDBObject();
				newQuery.put("amTem", amTem);
				newQuery.put("pmTem", pmTem);
				newQuery.put("amRain", amRain);
				newQuery.put("pmRain", pmRain);
				newQuery.put("amStatue", amStatue);
				newQuery.put("pmStatue", pmStatue);
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
		
	public List<CultureVO> getCulturelist() {
		List<CultureVO> list = mng.getList();
		return list;
	}
	
	public CultureDetailVO getCultureDetail(String cate, String id) {
		CultureDetailVO vo = mng.getDetail(cate, id);
		return vo;
	}
	
	public List<NewsVO> getNewsList() {
		List<NewsVO> tmp = news.newsListData();
		List<NewsVO> list = new ArrayList<NewsVO>();
		for(int i = 0; i < 5; i++) {
			list.add(tmp.get(i));
		}		
		return list;
	}
	
	public int getDayOfWeekNum() {
		int dow = 0;
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		dow = cal.get(Calendar.DAY_OF_WEEK);
		return dow;
	}
}











