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

import com.sist.dao.BeachDAO;
import com.sist.vo.BeachBlogVO;
import com.sist.vo.BeachResVO;
import com.sist.vo.BeachSpotVO;

@Component("bmm")
public class BeachInfoManager {
	
	@Autowired
	private BeachDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		BeachInfoManager mgr = (BeachInfoManager)app.getBean("bmm");
		//mgr.beachResListData();
		//mgr.beachAttListData();
		mgr.beachSpotListData();
	}
	
	public void beachResListData(){
		List<BeachResVO> list = new ArrayList<BeachResVO>();
		
		try {
			Document doc = Jsoup.connect("https://store.naver.com/attractions/detail?entry=plt&id=13491855&query=%EB%8B%A4%EB%8C%80%ED%8F%AC%ED%95%B4%EC%88%98%EC%9A%95%EC%9E%A5&tab=restaurants").get();
			Elements title = doc.select("div.sc_box li.list_item a.name");
			Elements info = doc.select("div.sc_box li.list_item div.txt");
			Elements image = doc.select("div.sc_box li.list_item img");
			
			for(int i=0; i<4; i++){
				System.out.println("力格 : " + title.get(i).text());
				System.out.println("家俺 : " + info.get(i).text());
				System.out.println(image.get(i).attr("src"));
				System.out.println("=======================");
				
				BeachResVO vo = new BeachResVO();
				vo.setNo(4);
				vo.setTitle(title.get(i).text());
				vo.setInfo(info.get(i).text());
				vo.setImage(image.get(i).attr("src"));
				dao.beachResInsert(vo);
			}
		} catch (Exception e) {e.printStackTrace();}

	}
	
	public void beachBlogListData(){
		List<BeachBlogVO> list = new ArrayList<BeachBlogVO>();
		
		try {
			Document doc = Jsoup.connect("https://store.naver.com/attractions/detail?id=13490865&query=%EA%B0%90%EC%A7%80%ED%95%B4%EB%B3%80&tab=fsasReview").get();
			Elements image = doc.select("div.review_tab div.thumb img");
			Elements title = doc.select("div.review_tab div.tit a.name");
			Elements link = doc.select("div.review_tab a.fl");
			Elements content = doc.select("div.review_tab div.ellp2");
			
			for(int i=0; i<4; i++){
				System.out.println(image.get(i).attr("src"));
				System.out.println("力格 : " + title.get(i).text());
				System.out.println("傅农 : " + link.get(i).attr("href"));
				System.out.println("郴侩 : " + content.get(i).text());
				System.out.println("=======================");
				
				BeachBlogVO vo = new BeachBlogVO();
				vo.setNo(8);
				vo.setImage(image.get(i).attr("src"));
				vo.setTitle(title.get(i).text());
				vo.setLink(link.get(i).attr("href"));
				vo.setContent(content.get(i).text());
				
				dao.beachBlogInsert(vo);
			}
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	public void beachSpotListData(){
		List<BeachSpotVO> list = new ArrayList<BeachSpotVO>();
		
		try {
			Document doc = Jsoup.connect("https://store.naver.com/attractions/detail?id=13490896&query=%EC%9D%BC%EA%B4%91%ED%95%B4%EC%88%98%EC%9A%95%EC%9E%A5&tab=attractions").get();
			Elements title = doc.select("div.nearbiz_tab span.tit_inner a.name");
			Elements info = doc.select("div.nearbiz_tab div.txt");
			Elements image = doc.select("div.nearbiz_tab div.thumb img");
			
			for(int i=0; i<4; i++){
				System.out.println("捞抚 : " + title.get(i).text());
				System.out.println("家俺 : " + info.get(i).text());
				System.out.println(image.get(i).attr("src"));
				System.out.println("================================");
				
				BeachSpotVO vo = new BeachSpotVO();
				vo.setNo(6);
				vo.setTitle(title.get(i).text());
				vo.setInfo(info.get(i).text());
				vo.setImage(image.get(i).attr("src"));
				
				dao.beachSpotInsert(vo);
			}
			
		} catch (Exception e) {e.printStackTrace();}
	}
}
