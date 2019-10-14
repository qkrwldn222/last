package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private MemberMapper mapper;
	
	public String isLogin(String id, String pwd){
		String result = "";
		int count = mapper.memberIdCount(id);
		if(count==0){
			result = "NOID";
		} else {
			String db_pwd = mapper.memberGetPwd(id);
			if(pwd.equals(db_pwd)){
				result = id;
			}else {
				result = "NOPWD";
			}
		}
		return result;
	}
	
	public int overButton(String id){
		return mapper.memberIdCount(id);
	}
	
	public void memberInsert(MemberVO vo){
		mapper.memberInsert(vo);
	}
}
