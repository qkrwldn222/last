package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;
import com.sist.vo.FavVO;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM s_member WHERE id = #{id}")
	public int memberIdCount(String id);
	
	@Select("SELECT pwd FROM s_member WHERE id = #{id}")
	public String memberGetPwd(String id);
	
	@Insert("INSERT INTO s_member VALUES(#{id}, #{pwd}, #{name})")
	public void memberInsert(MemberVO vo);
	
	@Select("SELECT name FROM s_member WHERE id = #{id}")
	public String memberGetName(String id);
	
	@Select("SELECT COUNT(*) FROM s_fav WHERE id=#{id} and datasid=#{sid}")
	public int favCount(Map map);
	
	@Insert("INSERT INTO s_fav VALUES("
		  + "(SELECT NVL(MAX(no)+1, 1) FROM s_fav ), "
		  + "#{id}, #{sid}, #{title}, #{link} )")
	public void favInsert(Map map);
	
	@Select("SELECT no FROM s_fav WHERE id=#{id} and datasid=#{sid}")
	public int favGetNo(Map map);
	
	@Delete("DELETE FROM s_fav WHERE no=#{no}")
	public void favDelete(int no);
	
	@Select("SELECT * FROM s_fav WHERE id=#{id}")
	public List<FavVO> favList(String id);
}
