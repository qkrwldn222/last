package com.sist.wang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("member/signup.do")
	public String member_signup(){
		return "member/signup";
	}
}
