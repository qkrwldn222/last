package com.sist.manager;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.MemberDAO;
import com.sist.vo.BeachAttVO;
import com.sist.vo.BeachResVO;

@Component("bm")
public class BeachInfoManager {
	
	@Autowired
	private MemberDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		BeachInfoManager mgr = (BeachInfoManager)app.getBean("bm");
		mgr.beachResListData();
		//mgr.beachAttListData();
	}
	
	public void beachResListData(){
		List<BeachResVO> list = new ArrayList<BeachResVO>();
		
		try {
			Document doc = Jsoup.connect("https://store.naver.com/attractions/detail?id=11491655&query=%EA%B4%91%EC%95%88%EB%A6%AC%ED%95%B4%EC%88%98%EC%9A%95%EC%9E%A5&tab=restaurants").get();
			Elements title = doc.select("div.sc_box li.list_item a.name");
			Elements info = doc.select("div.sc_box li.list_item div.txt");
			Elements image = doc.select("div.sc_box li.list_item img");
			
			for(int i=0; i<4; i++){
				System.out.println("力格 : " + title.get(i).text());
				System.out.println("家俺 : " + info.get(i).text());
				System.out.println(image.get(i).attr("src"));
				System.out.println("=======================");
				
				BeachResVO vo = new BeachResVO();
				vo.setNo(1);
				vo.setTitle(title.get(i).text());
				vo.setInfo(info.get(i).text());
				vo.setImage(image.get(i).attr("src"));
				dao.beachResInsert(vo);
			}
		} catch (Exception e) {e.printStackTrace();}

	}
	
	public void beachAttListData(){
		List<BeachAttVO> list = new ArrayList<BeachAttVO>();
		
		try {
			Document doc = Jsoup.connect("https://store.naver.com/attractions/detail?id=11491655&query=%EA%B4%91%EC%95%88%EB%A6%AC%ED%95%B4%EC%88%98%EC%9A%95%EC%9E%A5&tab=attractions").get();
			Elements title = doc.select("div.sc_box li.list_item a.name");
			Elements info = doc.select("div.sc_box li.list_item div.txt");
			Elements image = doc.select("div.sc_box li.list_item img");
			
			for(int i=0; i<4; i++){
				System.out.println("力格 : " + title.get(i).text());
				System.out.println("家俺 : " + info.get(i).text());
				System.out.println(image.get(i).attr("src"));
				System.out.println("=======================");
				
				BeachAttVO vo = new BeachAttVO();
				vo.setNo(1);
				vo.setTitle(title.get(i).text());
				vo.setInfo(info.get(i).text());
				vo.setImage(image.get(i).attr("src"));
				dao.beachAttInsert(vo);
			}
		} catch (Exception e) {e.printStackTrace();}
		
	}
}
