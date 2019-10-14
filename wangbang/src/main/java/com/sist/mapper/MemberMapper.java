package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM s_member WHERE id = #{id}")
	public int memberIdCount(String id);
	
	@Select("SELECT pwd FROM s_member WHERE id = #{id}")
	public String memberGetPwd(String id);
	
	@Insert("INSERT INTO s_member VALUES(#{id}, #{pwd}, #{name})")
	public void memberInsert(MemberVO vo);
}
