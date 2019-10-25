package com.sist.wang;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.MemberDAO;
import java.util.*;

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
			String name = dao.memberGetName(id);
			session.setAttribute("name", name);
		}
		return result;
	}

	@RequestMapping("fav/favCheck.do")
	public String fav_check(String id, String sid) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("sid", sid);
		int cnt = dao.favCount(map);
		String res = Integer.toString(cnt);
		return res;
	}
	
	@RequestMapping("fav/favInsert.do")
	public String fav_insert(String id, String sid, String link, String title) {
		String res = "";
		Map map = new HashMap();
		map.put("id", id);
		map.put("sid", sid);
		map.put("link", link);
		map.put("title", title);
		dao.favInsert(map);
		return res;
	}
	
	@RequestMapping("fav/favDelete.do")
	public String fav_delete(String id, String sid) {
		String res = "";
		Map map = new HashMap();
		map.put("id", id);
		map.put("sid", sid);
		int no = dao.favGetNo(map);
		dao.favDelete(no);
		return res;
	}
}
