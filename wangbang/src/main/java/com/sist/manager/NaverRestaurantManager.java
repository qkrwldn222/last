package com.sist.manager;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component
public class NaverRestaurantManager {
	String clientId = "nFokN7ZJxGbn7lGPVBh0";
	String clientSecret = "7qTYUq7a7v";

	public String naver(String name) {

		StringBuffer response = new StringBuffer();
		List<String> list = new ArrayList<String>();
		/*list.add("1");
		list.add("101");
		list.add("201");
		list.add("301");*/
		try {
//			for (String n : list) {
				StringBuffer sb = new StringBuffer();
				String text = URLEncoder.encode("부산"+name+"추천", "UTF-8");

				String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&&query="+ text; // xml
				// 결과
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
				}
				String inputLine;

				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}
				br.close();
				response = response.append(sb);
				
			/*}*/
		} catch (Exception e) {}
		return response.toString().replaceAll("[0-9]", "");
	}
}
