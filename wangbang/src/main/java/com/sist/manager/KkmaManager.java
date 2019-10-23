package com.sist.manager;

import java.util.*;
import java.io.FileReader;
import java.util.List;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.snu.ids.ha.ma.MExpression;
import org.snu.ids.ha.ma.MorphemeAnalyzer;
import org.snu.ids.ha.ma.Sentence;
import org.snu.ids.ha.util.Timer;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.sist.vo.KeywordListVO;

@Component
public class KkmaManager {
	public String keTest(String content){
		
		String strToExtrtKwrd = content;
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
		List<KeywordListVO> list = new ArrayList<KeywordListVO>();
		for(int i=0; i < kl.size(); i++){
			if(kl.get(i).getString().length()>2){
				KeywordListVO vo = new KeywordListVO();
				vo.setCnt(kl.get(i).getCnt());
				vo.setTitle(kl.get(i).getString());
				list.add(vo);
			}
		}
		int temp = 0 ;
		String strTemp ="";
		Collections.sort(list, new Comparator<Object>() 
        { 
			public int compare(Object o1, Object o2) {
				KeywordListVO b1=(KeywordListVO)o1;
				KeywordListVO b2=(KeywordListVO)o2;
				int no=0;
				if(b1.getCnt() > b2.getCnt())
					no=1;
				else if(b1.getCnt() < b2.getCnt())
					no=-1;
				else
					no=0;
				return no;
			}
        });
		String result = list.get(0).getTitle();
		for(int j=1; j<5; j++){
			KeywordListVO vo = new KeywordListVO();
			vo.setCnt(list.get(j).getCnt());
			vo.setTitle(list.get(j).getTitle());
			System.out.println(list.get(j).getCnt()+list.get(j).getTitle());
			result +=","+list.get(j).getTitle();
		}
		return result;
	}
public List<KeywordListVO> kegrahp(String content){
		
		String strToExtrtKwrd = content;
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
		List<KeywordListVO> list = new ArrayList<KeywordListVO>();
		for(int i=0; i < kl.size(); i++){
			if(kl.get(i).getString().length()>2){
				KeywordListVO vo = new KeywordListVO();
				vo.setCnt(kl.get(i).getCnt());
				vo.setTitle(kl.get(i).getString());
				list.add(vo);
			}
		}
		int temp = 0 ;
		String strTemp ="";
		Collections.sort(list, new Comparator<Object>() 
        { 
			public int compare(Object o1, Object o2) {
				KeywordListVO b1=(KeywordListVO)o1;
				KeywordListVO b2=(KeywordListVO)o2;
				int no=0;
				if(b1.getCnt() > b2.getCnt())
					no=1;
				else if(b1.getCnt() < b2.getCnt())
					no=-1;
				else
					no=0;
				return no;
			}
        });
		List<KeywordListVO> rlist = new ArrayList<KeywordListVO>();
		String result = list.get(0).getTitle();
		for(int j=1; j<5; j++){
			KeywordListVO vo = new KeywordListVO();
			vo.setCnt(list.get(j).getCnt());
			vo.setTitle(list.get(j).getTitle());
			rlist.add(vo);
			System.out.println(list.get(j).getCnt()+list.get(j).getTitle());
			result +=","+list.get(j).getTitle();
		}
		return rlist;
	}
}