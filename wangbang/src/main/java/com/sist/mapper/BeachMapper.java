package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.BeachBlogVO;
import com.sist.vo.BeachResVO;

public interface BeachMapper {
	@Insert("INSERT INTO s_res VALUES(#{no}, #{title}, #{info}, #{iamge})")
	public void beachResInsert(BeachResVO vo);
	
	@Insert("INSERT INTO s_blog VALUES(#{no}, #{image}, #{title}, #{link}, #{content})")
	public void beachBlogInsert(BeachBlogVO vo);
	
	@Select("SELECT image, title, link, content FROM S_BLOG WHERE no = 1")
	public List<BeachBlogVO> beachBlogData();
	
	@Select("SELECT title, info, image FROM s_res WHERE no = 1")
	public List<BeachResVO> beachResData();
}
