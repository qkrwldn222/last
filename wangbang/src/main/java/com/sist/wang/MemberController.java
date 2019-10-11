package com.sist.wang;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("member/signup.do")
	public String member_signup(){
		return "member/signup";
	}
	
	@RequestMapping("member/logout_ok.do")
	public String member_logout_ok(HttpSession session){
		session.invalidate();
		return "redirect:../main/main.do";
	}
}
