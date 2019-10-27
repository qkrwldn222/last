package com.sist.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ScheduleMapper;
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
	 
	 public int scheduleno(ScheduleVO vo){
		 List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		 return mapper.scheduleGetNo(vo);				
	 }
	 
	 public void timeInsert(TimeVO vo){
		 List<TimeVO> list = new ArrayList<TimeVO>();
		 mapper.timeInsert(vo);				
	 }
}
