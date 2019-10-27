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
	
	@Select("SELECT image, title, link, content FROM S_BLOG WHERE no = #{no}")
	public List<BeachBlogVO> beachBlogData(int no);
	
	@Select("SELECT title, info, image FROM s_res WHERE no = #{no}")
	public List<BeachResVO> beachResData(int no);
}
