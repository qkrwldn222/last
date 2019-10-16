package com.sist.wang;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		return "goodTrip/goodTripday";
	}
}
