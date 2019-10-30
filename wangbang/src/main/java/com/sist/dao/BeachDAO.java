package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.BeachMapper;
import com.sist.vo.BeachBlogVO;
import com.sist.vo.BeachResVO;
import com.sist.vo.BeachSpotVO;

@Repository
public class BeachDAO {
	@Autowired
	private BeachMapper mapper;
	
	public void beachResInsert(BeachResVO vo){
		mapper.beachResInsert(vo);
	}
	
	public void beachBlogInsert(BeachBlogVO vo){
		mapper.beachBlogInsert(vo);
	}
	
	public void beachSpotInsert(BeachSpotVO vo){
		mapper.beachSpotInsert(vo);
	}
	
	public List<BeachBlogVO> beachBlogData(int no){
		return mapper.beachBlogData(no);
	}
	
	public List<BeachResVO> beachResData(int no){
		return mapper.beachResData(no);
	}
	
	public List<BeachSpotVO> beachSpotData(int no){
		return mapper.beachSpotData(no);
	}
}
