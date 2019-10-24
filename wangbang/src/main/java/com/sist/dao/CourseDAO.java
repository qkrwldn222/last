package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.sist.vo.CourseVO;

@Repository
public class CourseDAO {
	@Autowired
	private MongoTemplate cs;
	
	public CourseVO course_list(String data){
		CourseVO vo = new CourseVO();
		 BasicQuery query = new BasicQuery("{data:'"+data+"'}");
		 vo=cs.findOne(query, CourseVO.class,"Course");
		 //System.out.println(vo.getData());
		 return vo;
	 }
}
