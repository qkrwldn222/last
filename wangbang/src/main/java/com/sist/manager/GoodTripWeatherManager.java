package com.sist.manager;
import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import com.sist.vo.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

@Repository
public class GoodTripWeatherManager {
	Set<String> cateSet = new HashSet<String>();
	Map<String, String> wfMap = new HashMap<String, String>();
	Map<Integer, String> urlMap = new HashMap<Integer, String>();
	
	public GoodTripWeatherManager() {
		cateSet.add("TMN");
		cateSet.add("TMX");
		cateSet.add("POP");
		cateSet.add("SKY");
		cateSet.add("PTY");
		
		wfMap.put("맑음", "0,1");
		wfMap.put("구름많음", "0,3");
		wfMap.put("구름많고 비", "1,3");
		wfMap.put("구름많고 눈", "3,3");
		wfMap.put("구름많고 비/눈", "2,3");
		wfMap.put("구름많고 눈/비", "2,3");
		wfMap.put("흐림", "0,4");
		wfMap.put("흐리고 비", "1,4");
		wfMap.put("흐리고 눈", "3,4");
		wfMap.put("흐리고 비/눈", "2,4");
		wfMap.put("흐리고 눈/비", "2,4");
		
		urlMap.put(1, "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); // 동네예보
		urlMap.put(2, "http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleTemperature"); // 중기예보 - 기온
		urlMap.put(3, "http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleLandWeather"); // 중기예보 - 기후
	}
	
	public JSONObject reJsonObj(int key) {
		JSONObject obj = null;
		// 값 초기화
		String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String servicekey = "pfpbjJdxpHOqhhSt2onlislGkQtMAlEPhRhheTbQVEU%2BxzS81uv15IKOONJlarc07hn291SEM0vs%2BOf1bQpBZQ%3D%3D";
		String basedate = day;
		String basetime = "0500";
		String nx = "98";
		String ny = "76";
		String numOfRows = "250";
		String type = "json";
		String regId = "";
		String tmFc = day + "0600";
		
		StringBuilder urlBuild = new StringBuilder("");
		
		switch (key) {
			case 1:
				urlBuild.append(urlMap.get(1));
				urlBuild.append("?ServiceKey=" + servicekey);
				urlBuild.append("&base_date=" + basedate);
				urlBuild.append("&base_time=" + basetime);
				urlBuild.append("&nx=" + nx);
				urlBuild.append("&ny=" + ny);
				urlBuild.append("&numOfRows=" + numOfRows);
				urlBuild.append("&_type=" + type);
				break;
			case 2:
				regId = "11H20201";
				urlBuild.append(urlMap.get(2));
				urlBuild.append("?ServiceKey=" + servicekey);
				urlBuild.append("&regId=" + regId);
				urlBuild.append("&tmFc=" + tmFc);
				urlBuild.append("&_type=" + type);
				break;
			case 3:
				regId = "11H20000";
				urlBuild.append(urlMap.get(3));
				urlBuild.append("?ServiceKey=" + servicekey);
				urlBuild.append("&regId=" + regId);
				urlBuild.append("&tmFc=" + tmFc);
				urlBuild.append("&_type=" + type);
				break;
			default:break;
		}
		
		// JSON 파싱
		try {
			URL url = new URL(urlBuild.toString());
			StringBuffer sb = new StringBuffer();
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn != null) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line;
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			}
			
			JSONParser parser = new JSONParser();
			JSONObject obj_parse = (JSONObject)parser.parse(sb.toString());
			JSONObject obj_response = (JSONObject)obj_parse.get("response");
			JSONObject obj_body = (JSONObject)obj_response.get("body");
			JSONObject obj_items = (JSONObject)obj_body.get("items");
			obj = obj_items;
			
		} catch (Exception ex) {}
		
		return obj; // items까지 리턴
	}
	
	public List<GTWeatherVO> forecastSpace() { // 동네예보
		JSONObject obj_items = reJsonObj(1);
		JSONArray itemsArr = (JSONArray)obj_items.get("item");
		List<GTWeatherVO> list = new ArrayList<GTWeatherVO>();
		Set<String> dateSet = new HashSet<String>();
		
		JSONArray wlist = new JSONArray();
		JSONObject weather;
		for(int i = 0; i < itemsArr.size(); i++) {
			weather = (JSONObject)itemsArr.get(i);
			
			Object fcstDate = weather.get("fcstDate");
			String date = fcstDate.toString();
			Object fcstTime = weather.get("fcstTime");
			String time = fcstTime.toString();
			
			if(!dateSet.contains(date))
				dateSet.add(date);
			
			if(time.equals("0600") || time.equals("1500"))
				wlist.add(weather);
		}
		
		Iterator<String> it = dateSet.iterator();
		while(it.hasNext()) {
			GTWeatherVO vo = new GTWeatherVO();
			String date = it.next();
			vo.setDate(date);
			list.add(vo);
		}
		
		for(int i = 0; i < list.size(); i++) {
			GTWeatherVO vo = list.get(i);
			String iDate = vo.getDate();
			
			String amTem = "";
			String pmTem = "";
			String amRain = "";
			String pmRain = "";
			String amStatue = "";
			String pmStatue = "";
			
			JSONObject weather2;
			for(int j = 0; j < wlist.size(); j++) {
				weather2 = (JSONObject)wlist.get(j);
				String jDate = weather2.get("fcstDate").toString();
				String jTime = weather2.get("fcstTime").toString();
				String cate = weather2.get("category").toString();
				
				if(jDate.equals(iDate) && jTime.equals("0600") && cateSet.contains(cate)) {
					if(cate.equals("TMN") || cate.equals("TMX"))
						amTem = weather2.get("fcstValue").toString();
					if(cate.equals("PTY"))
						amStatue += weather2.get("fcstValue").toString();
					if(cate.equals("SKY"))
						amStatue += "," + weather2.get("fcstValue").toString();
					if(cate.equals("POP"))
						amRain = weather2.get("fcstValue").toString();
				} else if (jDate.equals(iDate) && jTime.equals("1500") && cateSet.contains(cate)) {
					if(cate.equals("TMN") || cate.equals("TMX"))
						pmTem = weather2.get("fcstValue").toString();
					if(cate.equals("PTY"))
						pmStatue += weather2.get("fcstValue").toString();
					if(cate.equals("SKY"))
						pmStatue += "," + weather2.get("fcstValue").toString();
					if(cate.equals("POP"))
						pmRain = weather2.get("fcstValue").toString();
				}
			}
			
			vo.setAmTem(amTem);
			vo.setAmStatue(amStatue);
			vo.setAmRain(amRain);
			vo.setPmTem(pmTem);
			vo.setPmStatue(pmStatue);
			vo.setPmRain(pmRain);
		}
		
		return list;
	}
	
	public List<GTWeatherVO> MiddleWeather() {
		List<GTWeatherVO> list = new ArrayList<GTWeatherVO>();
		JSONObject obj_items2 = reJsonObj(2);
		JSONObject obj_items3 = reJsonObj(3);
		JSONObject item2 = (JSONObject)obj_items2.get("item");
		JSONObject item3 = (JSONObject)obj_items3.get("item");
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		String amTem = "";
		String pmTem = "";
		String amRain = "";
		String pmRain = "";
		String amStatue = "";
		String pmStatue = "";
		
		for(int i = 3; i <= 7; i++) {
			GTWeatherVO vo = new GTWeatherVO();
			
			cal.setTime(date);
			cal.add(Calendar.DATE, i);
			String dateStr = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH)+1) + "" + cal.get(Calendar.DATE);
			vo.setDate(dateStr);
			
			// 기온
			amTem = item2.get("taMin" + i).toString();
			pmTem = item2.get("taMax" + i).toString();
			// 강수확률
			amRain  = item3.get("rnSt"+i+"Am").toString();
			pmRain  = item3.get("rnSt"+i+"Pm").toString();
			//날씨예보
			amStatue = item3.get("wf"+i+"Am").toString();
			pmStatue = item3.get("wf"+i+"Pm").toString();
			
			vo.setAmTem(amTem);
			vo.setAmStatue(wfMap.get(amStatue));
			vo.setAmRain(amRain);
			vo.setPmTem(pmTem);
			vo.setPmStatue(wfMap.get(pmStatue));
			vo.setPmRain(pmRain);
			
			list.add(vo);
		}
		
		return list;
	}
	
}