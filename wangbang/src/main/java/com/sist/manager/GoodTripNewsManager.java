package com.sist.manager;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;

@Repository
public class GoodTripNewsManager {
	public static void main(String[] args) {
		GoodTripNewsManager m = new GoodTripNewsManager();
		m.newsListData();
	}
	public List<NewsVO> newsListData() {
		List<NewsVO> list=new ArrayList<NewsVO>();
		try {
			// 연결 
			int k=1;
			for(int i = 1; i <= 5; i++) {
				try {
					Document doc=Jsoup.connect("http://www.busaninnews.co.kr/news/articleList.html?page="+i+"&total=343&sc_section_code=S1N3&sc_sub_section_code=S2N15&sc_serial_code=&sc_area=&sc_level=&sc_article_type=&sc_view_level=&sc_sdate=&sc_edate=&sc_serial_number=&sc_word=&sc_word2=&sc_andor=&sc_order_by=E&view_type=tm").get();
					Elements title=doc.select("div.auto-cnt strong.auto-fontA");
					Elements link=doc.select("div.auto-article a");
					Elements dd = doc.select("div.auto-cnt p");

					for(int j=0;j<title.size();j++) {
						//System.out.println(title.get(j).text());
						//System.out.println("http://www.busaninnews.co.kr" + link.get(j).attr("href"));
						String date = dd.get(j).text();
						date = date.substring(date.lastIndexOf("|")+2);
						//System.out.println(date);
   
						NewsVO vo=new NewsVO();
						vo.setNo(k);
						vo.setTitle(title.get(j).text());
						vo.setLink("http://www.busaninnews.co.kr" + link.get(j).attr("href"));
						vo.setDate(date);
						list.add(vo);
						k++;
					}
				}catch(Exception ex){}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}


