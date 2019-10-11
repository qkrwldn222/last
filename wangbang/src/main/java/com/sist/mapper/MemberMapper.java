package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM s_member WHERE id = #{id}")
	public int memberIdCount(String id);
	
	@Select("SELECT pwd FROM s_member WHERE id = #{id}")
	public String memberGetPwd(String id);
}
