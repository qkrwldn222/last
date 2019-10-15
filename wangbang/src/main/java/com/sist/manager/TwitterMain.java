package com.sist.manager;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.*;

public class TwitterMain {
	
	public static void main(String[] args) throws ParseException {
		JSONArray jarr = new JSONArray();
		TwitterStream ts = new TwitterStreamFactory().getInstance();
		ts.addListener(new TwitterListener(){
			@Override
			public void onStatus(Status status) {
				
				String msg = status.getUser().getScreenName()+"@"+status.getText();
				//System.out.println(msg);
				JSONObject jobj = new JSONObject();
				jobj.put("id", status.getUser().getScreenName());
				jobj.put("img", status.getUser().getProfileImageURL());
				jobj.put("text", status.getText());
				String time=new SimpleDateFormat("HH:mm:ss").format(status.getCreatedAt());
				jobj.put("time", time);
				jarr.add(0, jobj);
				
				FileWriter file;
				try {
					file = new FileWriter("c:\\jsonfile\\test9.json",false);
					file.write(jarr.toString());
					file.flush();
					file.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		String[] data = {"부산 관광","부산 숙박","부산 맛집","부산 문화"};
		FilterQuery fq = new FilterQuery();
		fq.track(data);
		ts.filter(fq);
		
		
	}
}
