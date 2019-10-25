package com.sist.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sist.vo.ExperienceVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.StayVO;
import com.sist.vo.ThemeTourVO;

@Repository
public class TourstayresDAO {

	@Autowired
	private MongoTemplate mt;
	
	public List<StayVO> stay_AllData() {
		
		List<StayVO> list = new ArrayList<StayVO>();
		Query query = new Query();
		list = mt.find(query, StayVO.class,"stay");// find = select , findOne = selectOne(하나만가져올때)
		return list;
	}
	public List<RestaurantVO> res_AllData() {
		
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		Query query = new Query();
		list = mt.find(query, RestaurantVO.class,"Restaurant");// find = select , findOne = selectOne(하나만가져올때)
		return list;
	}
	public List<ThemeTourVO> tour_AllData() {
		
		List<ThemeTourVO> list = new ArrayList<ThemeTourVO>();
		Query query = new Query();
		list = mt.find(query, ThemeTourVO.class,"ThemeTour");// find = select , findOne = selectOne(하나만가져올때)
		return list;
	}
	
	public StayVO stay_detail(String dataTitle) {
		 StayVO vo = new StayVO();
		 BasicQuery query = new BasicQuery("{dataTitle:'"+dataTitle+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
		 
		 vo=mt.findOne(query, StayVO.class,"stay");
		 return vo;
	 }
	
	public ThemeTourVO tour_detail(String dataTitle) {
		ThemeTourVO vo = new ThemeTourVO();
		 BasicQuery query = new BasicQuery("{dataTitle:'"+dataTitle+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
		 
		 vo=mt.findOne(query, ThemeTourVO.class,"ThemeTour");
		 return vo;
	 }
	public RestaurantVO res_detail(String dataTitle) {
		RestaurantVO vo = new RestaurantVO();
		 BasicQuery query = new BasicQuery("{dataTitle:'"+dataTitle+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
		 
		 vo=mt.findOne(query, RestaurantVO.class,"Restaurant");
		 return vo;
	 }
}
