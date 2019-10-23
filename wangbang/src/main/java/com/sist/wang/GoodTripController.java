package com.sist.wang;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("goodTrip")
public class GoodTripController {
	@Autowired
	private GoodTripDAO dao;
	
	@RequestMapping("goodTripday.do")
	public String goodTrip_goodTripday(Model model) {
		dao.newInsertAndUpdate(); // 날씨 데이터 업데이트
		List<GTWeatherVO> weathers = dao.findWeatherData();
		model.addAttribute("weathers", weathers);
		Map<String, String> statueIcons = dao.statueFileName();
		model.addAttribute("statueIcons", statueIcons);
		int dow = dao.getDayOfWeekNum();
		model.addAttribute("dow", dow);
		
		List<CultureVO> clist = dao.getCulturelist();
		model.addAttribute("clist", clist);
		
		List<NewsVO> nlist = dao.getNewsList();
		model.addAttribute("nlist", nlist);
		
		return "goodTrip/goodTripday";
	}
	
	@RequestMapping("getCultureDetail.do")
	@ResponseBody
	public String goodTrip_cultureDetail(String cate, String id) {
		String html = "";
		StringBuilder sb = new StringBuilder("");
		CultureDetailVO vo = dao.getCultureDetail(cate, id);
		sb.append("<h4 class='card-title'>"+vo.getDataTitle()+"</h4>");
		sb.append("<div>");
		sb.append("<table class='table table-borderless' >");
		sb.append("<tr>");
		sb.append("<td colspan='2' class='text-center' >");
		sb.append("<img src='"+vo.getMainimgthumb()+"' width='75%' height='75%' >");
		sb.append("</td>"); // 41line
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td colspan='2' >");
		sb.append("<font color='gray'size='1em'>");
		sb.append(vo.getDataContent());
		sb.append("</font>"); // 47line
		sb.append("</td>"); // 44line
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("위치 : " + vo.getPlace());
		sb.append("</td>"); // 53line
		sb.append("<td>");
		sb.append("전화 : " + vo.getTel());
		sb.append("</td>"); // 56line
		sb.append("</tr>"); // 52line
		sb.append("<tr>");
		sb.append("<td colspan='2' >");
		sb.append("시간 : " + vo.getTime());
		sb.append("</td>"); // 61line
		sb.append("</tr>"); // 60line
		sb.append("<tr>");
		sb.append("<td colspan='2' >");
		sb.append("교통편 : " + vo.getTrafin());
		sb.append("</td>"); // 66line
		sb.append("</tr>"); // 65line
		sb.append("<tr>");
		sb.append("<td colspan='2' >");
		sb.append("사이트 : " + vo.getUserHomepage());
		sb.append("</td>"); // 71line
		sb.append("</tr>"); // 70line
		sb.append("</table>"); // 39line
		sb.append("</div>"); // 38line
		html = sb.toString();
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");
		return html;
	}
}










