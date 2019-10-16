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
		
		JSONObject weather;
		for(int i = 0; i < itemsArr.size(); i++) {
			weather = (JSONObject)itemsArr.get(i);
			
			Object fcstDate = weather.get("fcstDate");
			String date = fcstDate.toString();
			Object fcstTime = weather.get("fcstTime");
			String time = fcstTime.toString();
			String str = date + "," + time;
			
			if((time.equals("0600") || time.equals("1500")) && !dateSet.contains(str))
				dateSet.add(str);
		}
		
		Iterator<String> it = dateSet.iterator();
		while(it.hasNext()) {
			GTWeatherVO vo = new GTWeatherVO();
			StringTokenizer st = new StringTokenizer(it.next(), ",");
			String date = st.nextToken();
			String time = st.nextToken();
			vo.setDate(date);
			vo.setTime(time);
			
			list.add(vo);
		}
		
		for(int i = 0; i < list.size(); i++) {
			GTWeatherVO vo = list.get(i);
			String iDate = vo.getDate();
			String iTime = vo.getTime();
			
			String temperature = "";
			String rain = "";
			String statue = "";
			
			JSONObject weather2;
			for(int j = 0; j < itemsArr.size(); j++) {
				weather2 = (JSONObject)itemsArr.get(j);
				String jDate = weather2.get("fcstDate").toString();
				String jTime = weather2.get("fcstTime").toString();
				String cate = weather2.get("category").toString();
				
				if(jDate.equals(iDate) && jTime.equals(iTime) && cateSet.contains(cate)) {
					if(cate.equals("TMN") || cate.equals("TMX"))
						temperature = weather2.get("fcstValue").toString();
					if(cate.equals("PTY"))
						statue += weather2.get("fcstValue").toString();
					if(cate.equals("SKY"))
						statue += "," + weather2.get("fcstValue").toString();
					if(cate.equals("POP"))
						rain = weather2.get("fcstValue").toString();
				}
			}
			
			vo.setTemperature(temperature);
			vo.setRain(rain);
			vo.setStatue(statue);
			if(iTime.equals("0600"))
				vo.setTime("AM");
			else
				vo.setTime("PM");
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
		
		for(int i = 3; i <= 7; i++) {
			GTWeatherVO voAm = new GTWeatherVO();
			GTWeatherVO voPm = new GTWeatherVO();
			
			cal.setTime(date);
			cal.add(Calendar.DATE, i);
			String dateStr = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH)+1) + "" + cal.get(Calendar.DATE);
			voAm.setDate(dateStr);
			voAm.setTime("AM");
			voPm.setDate(dateStr);
			voPm.setTime("PM");
			
			// 기온
			Object tamin = item2.get("taMin" + i);
			Object tamax = item2.get("taMax" + i);
			// 강수확률
			Object rnStAm  = item3.get("rnSt"+i+"Am");
			Object rnStPm  = item3.get("rnSt"+i+"Pm");
			//날씨예보
			Object wfAm = item3.get("wf"+i+"Am");
			Object wfPm = item3.get("wf"+i+"Pm");
			
			voAm.setTemperature(tamin.toString()+".0");
			voAm.setRain(rnStAm.toString());
			String wfa = wfMap.get(wfAm.toString());
			voAm.setStatue(wfa);
			
			voPm.setTemperature(tamax.toString()+".0");
			voPm.setRain(rnStPm.toString());
			String wfp = wfMap.get(wfPm.toString());
			voPm.setStatue(wfp);
			
			list.add(voAm);
			list.add(voPm);
		}
		
		return list;
	}
	
}