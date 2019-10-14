package com.sist.wang;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("member/signup.do")
	public String member_signup(){
		return "signup";
	}
	
	@RequestMapping("member/logout_ok.do")
	public String member_logout_ok(HttpSession session){
		session.invalidate();
		return "redirect:../main/main.do";
	}
	
	@RequestMapping("member/member_over.do")
	public String member_over(String id, Model model){
		int res = 0;
		res = dao.overButton(id);
		model.addAttribute("res", res);
		return "over";
	}
}
