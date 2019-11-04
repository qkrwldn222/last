package com.sist.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ScheduleMapper;
import com.sist.vo.LeisureVO;
import com.sist.vo.ScheduleVO;
import com.sist.vo.TimeVO;


@Repository
public class ScheduleDAO {
	
	@Autowired
	private ScheduleMapper mapper;
	
	 public void scheduleInsert(ScheduleVO vo){
		 List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		mapper.scheduleInsert(vo);				
	 }
	 
	 public int scheduleGetNo(ScheduleVO vo){
		 List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		 return mapper.scheduleGetNo(vo);				
	 }
	 
	 public void timeInsert(TimeVO vo){
		 List<TimeVO> list = new ArrayList<TimeVO>();
		 mapper.timeInsert(vo);				
	 }
	 
	 public List<ScheduleVO> scheduleData(HashMap map){
		 return mapper.scheduleData(map);
	 }
	 
	 public ScheduleVO scheduleDetail(int sno){
		 return mapper.scheduleDetail(sno);
	 }
	 
	 public List<TimeVO> sTimeDetail(int sno){
		 return mapper.sTimeDetail(sno);
	 }
	 
	 public void scheduleUpdate(ScheduleVO vo){
		 mapper.scheduleUpdate(vo);
	 }
	 
	 public void timeDelete(int sno){
		 mapper.timeDelete(sno);
	 }
}
