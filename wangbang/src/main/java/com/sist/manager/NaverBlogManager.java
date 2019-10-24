package com.sist.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component
public class NaverBlogManager {
	public static void main(String[] args) {
        NaverBlogManager b = new NaverBlogManager();
        b.beach_like_naver();
    }
	
	public void beach_like_naver(){
		String clientId = "elxhXLgpQSateHfmYPaD";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "ON1dniB3av";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("부산 해수욕장", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            
            File dir = new File("c:\\sample_data");
            if(!dir.exists()){
            	dir.mkdir();
            }
            FileWriter fw = new FileWriter("c:\\sample_data\\beach.json");
            fw.write(response.toString());
            fw.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
