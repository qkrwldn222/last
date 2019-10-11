package com.sist.wang;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.MemberDAO;

@RestController
public class MemberRestController {
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("member/login_ok.do")
	public String member_login_ok(String id, String pwd, HttpSession session){
		String result = "";
		result = dao.isLogin(id, pwd);
		if(result.equals(id)){
			session.setAttribute("id", id);
		}
		return result;
	}
	
	
}
