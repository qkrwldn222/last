package com.sist.manager;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;

public class APIWeather2 {
	public static int INDENT = 4;
    public static void main(String[] args) throws IOException {
    	/*
    	StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData");  // URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=UZY3s0p%2FOXcwMPHgYmVUhch70jVj19wX5XiUNd10VR689ETQ9uwC6clThszEn%2FEVlUWD0FNU15gDoQiKRvySTw%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("TEST_SERVICE_KEY", "UTF-8")); //서비스 인증
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + "20191012"); 
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + "0500"); 
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + "60"); 
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + "127"); 
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + "10"); 
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + "1"); 
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8"));  // 파일구분 -ODAM: 동네예보실황 -VSRT: 동네예보초단기 -SHRT: 동네예보단기
        */
        
        StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
        urlBuilder.append("?serviceKey=UZY3s0p%2FOXcwMPHgYmVUhch70jVj19wX5XiUNd10VR689ETQ9uwC6clThszEn%2FEVlUWD0FNU15gDoQiKRvySTw%3D%3D");
        urlBuilder.append("&base_date=20191012&base_time=0500&nx=60&ny=127&_type=xml"); 
        urlBuilder.append("&base_date=20191012"); 
        urlBuilder.append("&base_time=0500"); 
        urlBuilder.append("&nx=60"); 
        urlBuilder.append("&ny=127"); 
        urlBuilder.append("&_type=xml"); 
       
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        System.out.println("list : " + list);
        JSONObject body = (JSONObject)list.get("body");
        System.out.println("body : " + body);
        JSONObject items = (JSONObject)body.get("items");
        
        System.out.println("items : " + items);
        System.out.println("item : " +  items.get("item"));
        
        org.json.JSONArray temp =  (org.json.JSONArray) items.get("item");
        System.out.println(temp);
             
        System.out.println(temp.get(0));
        for(int i = 0 ; i < temp.length(); i++ ){
        	JSONObject temp_i = (JSONObject)temp.get(i);
        	System.out.println("baseDate : "+temp_i.get("baseDate"));
        	System.out.println("fcstTime : "+temp_i.get("fcstTime"));
        	System.out.println("fcstValue : "+temp_i.get("fcstValue"));
        	System.out.println("nx : "+temp_i.get("nx"));
        	System.out.println("ny : "+temp_i.get("ny"));
        	System.out.println("category : "+temp_i.get("category"));
        	System.out.println("baseTime : "+temp_i.get("baseTime"));
        	System.out.println("fcstDate : "+temp_i.get("fcstDate"));
        }
		 
        /*
			JSONParser jp=new JSONParser();
        	JSONObject root=(JSONObject)jp.parse(sb.toString());
        	JSONObject list=(JSONObject)root.get("response");
        	JSONArray body=(JSONArray)list.get("body");
        	for(int i=0;i<body.size();i++)
        	{
        		JSONObject obj=(JSONObject)body.get(i); //obj가 배열하나 
        		//System.out.println(obj.toJSONString());
        		System.out.println("pageNo : "+obj.get("pageNo"));
        		System.out.println("totalCount : "+obj.get("totalCount"));
        		JSONArray a =(JSONArray)obj.get("items");
        		for(int j=0; j<a.size(); j++) {
        			JSONObject o = (JSONObject)a.get(j);
        			System.out.println("baseDate : "+o.get("baseDate"));
        		}
        	}
        	*/
        		System.out.println("============================");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}