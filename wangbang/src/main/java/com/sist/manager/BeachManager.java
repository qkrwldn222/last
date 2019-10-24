package com.sist.manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.sist.vo.BeachVO;

@Component
public class BeachManager {
	
	@Autowired
	private NaverBlogManager mgr;
	
	/*public static void main(String[] args) {
		BeachManager b = new BeachManager();
		b.beach_like();
	}*/
	
	public void getData(){
		try {
			
			StringBuffer s = new StringBuffer();
			int i=0;
			FileReader fr = new FileReader("c:\\sample_data\\beach.json");
			while((i=fr.read())!=-1){
				s.append(String.valueOf((char)i));
			}
			fr.close();
    		System.out.println(s.toString());
    		
    		JSONParser jp = new JSONParser();
    		JSONObject root = (JSONObject)jp.parse(s.toString());
    		JSONArray arr = (JSONArray)root.get("items");
    		StringBuffer sb = new StringBuffer();
    		for(int j=0;j<arr.size();j++)
    		{
    			JSONObject obj=(JSONObject)arr.get(j);
    			//System.out.println(obj.get("description"));
    			sb.append(obj.get("description")+"\n");
    		}
    		
    		FileWriter fw=new FileWriter("c:\\sample_data\\beach.txt");
    		fw.write(sb.toString());
    		fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<BeachVO> beach_like(Model model){
		List<BeachVO> list = new ArrayList<BeachVO>();
		mgr.beach_like_naver();
		getData();
		String[] arr = {"광안리", "해운대", "송도", "다대포", "송정", "일광", "임랑", "감지"};
		
		try {
			
			Pattern[] p = new Pattern[arr.length];
    		int i=0;
    		for(String s:arr)
    		{
    			//System.out.println(s);
    			p[i] = Pattern.compile(s);
    			i++;
    		}
			
			StringBuffer sb = new StringBuffer();
    		FileReader fr = new FileReader("c:\\sample_data\\beach.txt");
    		int k = 0;
    		while((k=fr.read())!=-1){
    			sb.append(String.valueOf((char)k));
    		}
    		fr.close();
    		
    		String[] blog_data = sb.toString().split("\n");
    		
    		Matcher[] m = new Matcher[arr.length];
    		int[] count=new int[arr.length];
    		
    		for(String ss:blog_data){
    			for(int j=0;j<m.length;j++)
    			{
    				m[j] = p[j].matcher(ss);
    				if(m[j].find())
    				{
    					count[j]++;
    				}
    			}
    		}
    		
    		for(int n=0; n<arr.length;n++){
    			String s = arr[n];
    			if(count[n] >= 1){
    			  //System.out.println(s + " : " + count[n]);
    			  BeachVO vo = new BeachVO();
    			  vo.setCount(count[n]);
    			  vo.setTitle(s);
    			  list.add(vo);
    			}
    		}
		} catch (Exception e) {}
		
		return list;
	}
}
