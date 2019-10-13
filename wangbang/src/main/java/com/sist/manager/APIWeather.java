package com.sist.manager;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;

public class APIWeather {
	public static int INDENT = 4;
	public JSONArray APIWeather() throws  IOException, SAXException {
		JSONArray arr = new JSONArray();
		System.out.println("JSONArray APIWeather");
    // public static void main(String[] args) throws IOException {
		SimpleDateFormat day = new SimpleDateFormat ( "yyyyMMdd");		
		SimpleDateFormat time = new SimpleDateFormat ( "HHmm");		
		Date today = new Date();			
		String dresult = day.format(today);
		System.out.println(dresult);
		
		Date totime = new Date();
		String tresult = time.format(totime);
		System.out.println(tresult);
		
		String realtime ="";
		if(Integer.parseInt(tresult)>=800 && Integer.parseInt(tresult)<1100) {
			realtime="0800";
		} else if(Integer.parseInt(tresult)>=1100 && Integer.parseInt(tresult)<1400) {
			realtime="1100";
		} else if(Integer.parseInt(tresult)>=1400 && Integer.parseInt(tresult)<1700) {
			realtime="1400";
		} else if(Integer.parseInt(tresult)>=1700 && Integer.parseInt(tresult)<2000) {
			realtime="1700";
		} else {
			realtime="0200";
		}
		System.out.println("지금시간은"+realtime);
		
        StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
        urlBuilder.append("?serviceKey=UZY3s0p%2FOXcwMPHgYmVUhch70jVj19wX5XiUNd10VR689ETQ9uwC6clThszEn%2FEVlUWD0FNU15gDoQiKRvySTw%3D%3D");
        // urlBuilder.append("&base_date=20191012&base_time=0500&nx=60&ny=127&_type=xml"); 
        urlBuilder.append("&base_date="+dresult); 
        urlBuilder.append("&base_time="+realtime); 
        urlBuilder.append("&nx=98"); 
        urlBuilder.append("&ny=76"); 
        urlBuilder.append("&_type=xml"); 
       
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
        
        // sb.toString() 가져온 것이(API불러온것) XML형식이라 JSON형식으로 변환해주는 코드
        // 왜냐하면 JSON파싱을 하기위해 변환한것
        
        JSONObject xmljson = XML.toJSONObject(sb.toString());
        String jsonjsonjson = xmljson.toString(INDENT);
        //System.out.println(jsonjsonjson);
        try {
        	
        JSONObject list=(JSONObject)xmljson.get("response");
        JSONObject body = (JSONObject)list.get("body");
        JSONObject items = (JSONObject)body.get("items");
        org.json.JSONArray temp =  (org.json.JSONArray) items.get("item");
        for(int i = 0 ; i < temp.length(); i++ ){
        	JSONObject temp_i = (JSONObject)temp.get(i);
/*        	System.out.println("baseDate : "+temp_i.get("baseDate"));
        	System.out.println("fcstTime : "+temp_i.get("fcstTime"));
        	System.out.println("fcstValue : "+temp_i.get("fcstValue"));
        	System.out.println("nx : "+temp_i.get("nx"));
        	System.out.println("ny : "+temp_i.get("ny"));
        	System.out.println("category : "+temp_i.get("category"));
        	System.out.println("baseTime : "+temp_i.get("baseTime"));
        	System.out.println("fcstDate : "+temp_i.get("fcstDate"));*/
        }
        arr.add(temp);
		 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        return arr;
    }
}