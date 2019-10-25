package com.sist.manager;
import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.sist.dao.TourstayresDAO;
import com.sist.vo.RecommandVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class RecommandManager {
	/*public static void main(String[] args) {
		NaverBlogManager nbm=new NaverBlogManager();
		nbm.recommandNaverData("가을");
		RecommandManager rm=new RecommandManager();
		rm.getData();
	}*/
	@Autowired
	private TourstayresDAO dao;
	@Autowired
	private NaverBlogManager mgr;
    public void getData()
    {
    	try
    	{
    		/*JAXBContext jc=JAXBContext.newInstance(Rss.class);
    		Unmarshaller un=jc.createUnmarshaller();
    		Rss rss=(Rss)un.unmarshal(new File("c:\\naver_data\\movie.xml"));
    		List<Item> list=rss.getChannel().getItem();
    		StringBuffer sb=new StringBuffer();
    		for(Item i:list)
    		{
    			System.out.println(i.getDescription());
    			sb.append(i.getDescription()+"\n");
    		}
    		    =========
    		    XML,JSON 
    		    =========  react,vue => 형식(xml) , 데이터 (json)
    		    
    		*/
    		StringBuffer s=new StringBuffer();
    		int i=0;
    		FileReader fr=new FileReader("c:\\jsonfile\\search.json");
    		while((i=fr.read())!=-1)
    		{
    			s.append(String.valueOf((char)i));
    		}
    		
    		fr.close();
    		System.out.println(s.toString());
    		
    		JSONParser jp=new JSONParser();
    		JSONObject root=(JSONObject)jp.parse(s.toString());
    		JSONArray arr=(JSONArray)root.get("items");
    		StringBuffer sb=new StringBuffer();
    		for(int j=0;j<arr.size();j++)
    		{
    			JSONObject obj=(JSONObject)arr.get(j);
    			System.out.println(obj.get("description"));
    			sb.append(obj.get("description")+"\n");
    		}
    		
    		FileWriter fw=new FileWriter("c:\\jsonfile\\search.txt");
    		fw.write(sb.toString());
    		fw.close();
    	}catch(Exception ex){ex.printStackTrace();}
    }
    // where title='aaaa' where title like '%aaaa%'  where regexp_like(title,'^A')
    //   equals                    contains()            Pattern
    public List<RecommandVO> recommand_find(String fd,int type)//,Model model
    {
    	List<RecommandVO> list=new ArrayList<RecommandVO>();
    	mgr.recommandNaverData(fd);// json
    	getData();// json => parse => 필요한 데이터만 txt
    	if(type==1){
    		List<StayVO> tList=dao.stay_AllData();
        	try
        	{
        		Pattern[] p=new Pattern[tList.size()];
        		int i=0;
        		for(StayVO vo:tList)
        		{
        			//System.out.println(s);
        			String s=vo.getDataTitle();
        			if(s.contains("부산")){
        				s=s.replace("부산", "");
        			}
        			if(s.contains("해운대")){
        				s=s.replace("해운대", "");
        			}
        			if(s.contains("호텔")){
        				s=s.replace("호텔", "");
        			}
        			if(s.contains("모텔")){
        				s=s.replace("모텔", "");
        			}
        			if(s.contains("S(에스)모텔")){
        				s="S모텔";
        			}
        			if(s.contains(" - ")){
        				s=s.substring(0,s.indexOf(" - "));
        			}
        			if(s.contains("(")){
        				s=s.substring(0,s.indexOf("("))+s.substring(s.indexOf(")")+1);
        			}
        			System.out.println(s.trim());
        			p[i]=Pattern.compile(s.trim());
        			i++;
        		}
        		
        		StringBuffer sb=new StringBuffer();
        		FileReader fr=new FileReader("c:\\jsonfile\\search.txt");
        		int k=0;
        		while((k=fr.read())!=-1)
        		{
        			sb.append(String.valueOf((char)k));
        		}
        		fr.close();
        		
        		String[] blog_data=sb.toString().split("\n");
        		
        		Matcher[] m=new Matcher[tList.size()];
        		List<String> sList=new ArrayList<String>();
        		int[] count=new int[tList.size()];
        		for(String ss:blog_data)
        		{
        			for(int j=0;j<m.length;j++)
        			{
        				m[j]=p[j].matcher(ss);
        				if(m[j].find())
        				{
        					count[j]++;
        					sList.add(ss);
        				}
        			}
        		}
        		//model.addAttribute("sList", sList);
        		// 출력 
        		for(int n=0;n<tList.size();n++)
        		{
        			String s=tList.get(n).getDataTitle();
        			if(count[n]>=1)
        			{
        			  System.out.println(s+":"+count[n]);
        			  RecommandVO vo=new RecommandVO();
        			  vo.setCount(count[n]);
        			  vo.setTitle(s);
        			  list.add(vo);
        			}
        			
        		}
        		
        	}catch(Exception ex){ex.printStackTrace();}
        	
    	}else if(type==2){
    		List<RestaurantVO> tList=dao.res_AllData();
        	try
        	{
        		Pattern[] p=new Pattern[tList.size()];
        		int i=0;
        		for(RestaurantVO vo:tList)
        		{
        			//System.out.println(s);
        			String s=vo.getDataTitle();
        			if(s.contains("부산")){
        				s=s.replace("부산", "");
        			}
        			if(s.contains("해운대")){
        				s=s.replace("해운대", "");
        			}
        			if(s.contains("호텔")){
        				s=s.replace("호텔", "");
        			}
        			if(s.contains("모텔")){
        				s=s.replace("모텔", "");
        			}
        			if(s.contains("S(에스)모텔")){
        				s="S모텔";
        			}
        			if(s.contains(" - ")){
        				s=s.substring(0,s.indexOf(" - "));
        			}
        			if(s.contains("(")){
        				s=s.substring(0,s.indexOf("("))+s.substring(s.indexOf(")")+1);
        			}
        			System.out.println(s.trim());
        			p[i]=Pattern.compile(s.trim());
        			i++;
        		}
        		
        		StringBuffer sb=new StringBuffer();
        		FileReader fr=new FileReader("c:\\jsonfile\\search.txt");
        		int k=0;
        		while((k=fr.read())!=-1)
        		{
        			sb.append(String.valueOf((char)k));
        		}
        		fr.close();
        		
        		String[] blog_data=sb.toString().split("\n");
        		
        		Matcher[] m=new Matcher[tList.size()];
        		List<String> sList=new ArrayList<String>();
        		int[] count=new int[tList.size()];
        		for(String ss:blog_data)
        		{
        			for(int j=0;j<m.length;j++)
        			{
        				m[j]=p[j].matcher(ss);
        				if(m[j].find())
        				{
        					count[j]++;
        					sList.add(ss);
        				}
        			}
        		}
        		//model.addAttribute("sList", sList);
        		// 출력 
        		for(int n=0;n<tList.size();n++)
        		{
        			String s=tList.get(n).getDataTitle();
        			if(count[n]>=1)
        			{
        			  System.out.println(s+":"+count[n]);
        			  RecommandVO vo=new RecommandVO();
        			  vo.setCount(count[n]);
        			  vo.setTitle(s);
        			  list.add(vo);
        			}
        			
        		}
        		
        	}catch(Exception ex){ex.printStackTrace();}
        	
    	}else if(type==3){
    		List<ThemeTourVO> tList=dao.tour_AllData();
        	try
        	{
        		Pattern[] p=new Pattern[tList.size()];
        		int i=0;
        		for(ThemeTourVO vo:tList)
        		{
        			//System.out.println(s);
        			String s=vo.getDataTitle();
        			if(s.contains("부산")){
        				s=s.replace("부산", "");
        			}
        			if(s.contains("해운대")){
        				s=s.replace("해운대", "");
        			}
        			if(s.contains("호텔")){
        				s=s.replace("호텔", "");
        			}
        			if(s.contains("모텔")){
        				s=s.replace("모텔", "");
        			}
        			if(s.contains("S(에스)모텔")){
        				s="S모텔";
        			}
        			if(s.contains(" - ")){
        				s=s.substring(0,s.indexOf(" - "));
        			}
        			if(s.contains("(")){
        				s=s.substring(0,s.indexOf("("))+s.substring(s.indexOf(")")+1);
        			}
        			System.out.println(s.trim());
        			p[i]=Pattern.compile(s.trim());
        			i++;
        		}
        		
        		StringBuffer sb=new StringBuffer();
        		FileReader fr=new FileReader("c:\\jsonfile\\search.txt");
        		int k=0;
        		while((k=fr.read())!=-1)
        		{
        			sb.append(String.valueOf((char)k));
        		}
        		fr.close();
        		
        		String[] blog_data=sb.toString().split("\n");
        		
        		Matcher[] m=new Matcher[tList.size()];
        		List<String> sList=new ArrayList<String>();
        		int[] count=new int[tList.size()];
        		for(String ss:blog_data)
        		{
        			for(int j=0;j<m.length;j++)
        			{
        				m[j]=p[j].matcher(ss);
        				if(m[j].find())
        				{
        					count[j]++;
        					sList.add(ss);
        				}
        			}
        		}
        		//model.addAttribute("sList", sList);
        		// 출력 
        		for(int n=0;n<tList.size();n++)
        		{
        			String s=tList.get(n).getDataTitle();
        			if(count[n]>=1)
        			{
        			  System.out.println(s+":"+count[n]);
        			  RecommandVO vo=new RecommandVO();
        			  vo.setCount(count[n]);
        			  vo.setTitle(s);
        			  list.add(vo);
        			}
        			
        		}
        		
        	}catch(Exception ex){ex.printStackTrace();}
        	
    	}
    	return list;
    }
    
   
}













