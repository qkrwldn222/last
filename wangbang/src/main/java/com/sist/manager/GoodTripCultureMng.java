package com.sist.manager;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.sist.vo.*;

@Repository
public class GoodTripCultureMng {
	@Autowired
	private MongoTemplate mt;
	
	Map<Integer, String> urlMap = new HashMap<Integer, String>();
	Map<String, Integer> cateMap = new HashMap<String, Integer>();
	Map<String, String> cateStr = new HashMap<String, String>();
	
	public GoodTripCultureMng() {
		urlMap.put(1, "http://apis.data.go.kr/6260000/CultureInfoService/getFestivalList"); // 축제 목록
		urlMap.put(2, "http://apis.data.go.kr/6260000/CultureInfoService/getMusicalList"); // 연극/뮤지컬 목록
		urlMap.put(3, "http://apis.data.go.kr/6260000/CultureInfoService/getMusicDanceList"); // 음악/무용 목록
		urlMap.put(4, "http://apis.data.go.kr/6260000/CultureInfoService/getExhibitList"); // 전시/행사 목록
		
		urlMap.put(5, "http://apis.data.go.kr/6260000/CultureInfoService/getFestivalDetail"); // 축제 상세보기
		urlMap.put(6, "http://apis.data.go.kr/6260000/CultureInfoService/getMusicalDetail"); // 연극/뮤지컬 상세보기
		urlMap.put(7, "http://apis.data.go.kr/6260000/CultureInfoService/getMusicDanceDetail"); // 음악/무용 상세보기
		urlMap.put(8, "http://apis.data.go.kr/6260000/CultureInfoService/getExhibitDetail"); // 전시/행사 상세보기
		
		cateMap.put("kpu0403", 5);
		cateMap.put("kpu0402_1", 6);
		cateMap.put("kpu0402_3", 7);
		cateMap.put("kpu0402_2", 8);
		
		cateStr.put("kpu0403", "축제");
		cateStr.put("kpu0402_1", "연극/뮤지컬");
		cateStr.put("kpu0402_3", "음악/무용");
		cateStr.put("kpu0402_2", "전시/행사");
	}
	
	public JSONObject returnDetail(String categoryCode1, String dataSid) {
		JSONObject obj = null;
		
		// 값 초기화
		String servicekey = "pfpbjJdxpHOqhhSt2onlislGkQtMAlEPhRhheTbQVEU%2BxzS81uv15IKOONJlarc07hn291SEM0vs%2BOf1bQpBZQ%3D%3D";
		String data_sid = dataSid;
		int iUrl = cateMap.get(categoryCode1);
		String type = "json";
		
		StringBuilder urlBuild = new StringBuilder(urlMap.get(iUrl));
		urlBuild.append("?ServiceKey=" + servicekey);
		urlBuild.append("&data_sid=" + data_sid);
		urlBuild.append("&_type=" + type);
		
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
			obj = (JSONObject)obj_items.get("item");
			
		} catch (Exception ex) {}
				
		return obj;
	}
	
	public JSONArray returnArr(int key) {
		JSONArray arr = new JSONArray();
		// 값 초기화
		String date_yyyymm = new SimpleDateFormat("yyyyMM").format(new Date());
		String servicekey = "pfpbjJdxpHOqhhSt2onlislGkQtMAlEPhRhheTbQVEU%2BxzS81uv15IKOONJlarc07hn291SEM0vs%2BOf1bQpBZQ%3D%3D";
		String numOfRows = "250";
		String type = "json";
		
		StringBuilder urlBuild = new StringBuilder(urlMap.get(key));
		urlBuild.append("?ServiceKey=" + servicekey);
		urlBuild.append("&date_yyyymm=" + date_yyyymm);
		urlBuild.append("&numOfRows=" + numOfRows);
		urlBuild.append("&_type=" + type);
		
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
			arr = (JSONArray)obj_items.get("item");
			
		} catch (Exception ex) {ex.printStackTrace();}
		
		return arr;
	}
	
	public boolean isDate(String startDate, String endDate) {
		boolean bCheck = false;
		
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(new Date());
		
		String[] start = startDate.split("-");
		String[] end = endDate.split("-");
		
		int sYear = Integer.parseInt(start[0]);
		int sMon = Integer.parseInt(start[1]);
		int sDay = Integer.parseInt(start[2]);
		
		Calendar sCal = Calendar.getInstance();
		sCal.set(Calendar.YEAR, sYear);
		sCal.set(Calendar.MONTH, sMon-1);
		sCal.set(Calendar.DATE, sDay);
		
		int eYear = Integer.parseInt(end[0]);
		int eMon = Integer.parseInt(end[1]);
		int eDay = Integer.parseInt(end[2]);
		
		Calendar eCal = Calendar.getInstance();
		eCal.set(Calendar.YEAR, eYear);
		eCal.set(Calendar.MONTH, eMon-1);
		eCal.set(Calendar.DATE, eDay);
		
		int s = toCal.compareTo(sCal);
		int e = toCal.compareTo(eCal);
		
		bCheck = (s==0 || s==1) && (e==0 || e==-1);
		
		return bCheck;
	}
	
	public List<CultureVO> getList() {
		List<CultureVO> list = new ArrayList<CultureVO>();
		
		for(int i = 1; i <= 4; i++) {
			JSONArray jArr = returnArr(i);
			JSONObject obj;
			
			String dataSid = "";
			String dataTitle = "";
			String dataContent = "";
			String startDate = "";
			String endDate = "";
			String categoryCode1 = "";
			String cateKor = "";
			
			for(int j = 0; j < jArr.size(); j++) {
				CultureVO vo = new CultureVO();
				obj = (JSONObject)jArr.get(j);
				
				dataSid = obj.get("dataSid").toString();
				dataTitle = obj.get("dataTitle").toString();
				if(obj.get("dataContent")!=null)
					dataContent = obj.get("dataContent").toString();
				else
					dataContent = "(상세설명 없음)";
				startDate = obj.get("startDate").toString();
				endDate = obj.get("endDate").toString();
				categoryCode1 = obj.get("categoryCode1").toString();
				if(categoryCode1.contains(","))
					categoryCode1 = categoryCode1.substring(categoryCode1.indexOf(",")+1);
				cateKor = cateStr.get(categoryCode1);
				
				boolean bCheck = isDate(startDate, endDate);
				
				if(bCheck) {
					vo.setDataSid(dataSid);
					vo.setDataTitle(dataTitle);
					if(dataContent.length() > 30) {
						dataContent = dataContent.substring(0, 40);
						dataContent += "...";
					}
					vo.setDataContent(dataContent);
					vo.setStartDate(startDate);
					vo.setEndDate(endDate);
					vo.setCategoryCode1(categoryCode1);
					vo.setCateKor(cateKor);
					list.add(vo);
				}
			}
		}
		return list;
	}
	
	public CultureDetailVO getDetail(String categoryCode1, String dataSid) {
		CultureDetailVO vo = new CultureDetailVO();
		JSONObject obj = returnDetail(categoryCode1, dataSid);
		String dataContent = obj.get("dataContent")!=null?obj.get("dataContent").toString():"";
		String dataTitle = obj.get("dataTitle")!=null?obj.get("dataTitle").toString():"";
		String host = obj.get("host")!=null?obj.get("host").toString():"";
		String mainimgthumb = obj.get("mainimgthumb")!=null?obj.get("mainimgthumb").toString():"";
		String place = obj.get("place")!=null?obj.get("place").toString():"";
		String tel = obj.get("tel")!=null?obj.get("tel").toString():"";
		String time = obj.get("time")!=null?obj.get("time").toString():"";
		String trafin = obj.get("trafin")!=null?obj.get("trafin").toString():"";
		String useperiod = obj.get("useperiod")!=null?obj.get("useperiod").toString():"";
		String userHomepage = obj.get("userHomepage")!=null?obj.get("userHomepage").toString():"";
		String wgsx = obj.get("wgsx")!=null?obj.get("wgsx").toString():"";
		String wgsy = obj.get("wgsy")!=null?obj.get("wgsy").toString():"";
		
		vo.setDataContent(dataContent);
		vo.setDataTitle(dataTitle);
		vo.setHost(host);
		vo.setMainimgthumb(mainimgthumb);
		vo.setPlace(place);
		vo.setTel(tel);
		vo.setTime(time);
		vo.setTrafin(trafin);
		vo.setUseperiod(useperiod);
		vo.setUserHomepage(userHomepage);
		vo.setWgsx(wgsx);
		vo.setWgsy(wgsy);
		
		
		return vo;
	}
	
	public String cate(String categoryCode1) {
		String cate = cateStr.get(categoryCode1);
		return cate;
	}
	
}





