package com.sist.wang;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCourseController {

    @RequestMapping(value="course/recommand_data.do")//course/recommand_data.do
    public String chart_recommand_data(int no){
    	
    	no=no==0?1:no;
    	String result="";
    	if(no==1)
    		result="바다,호텔,야경,시설이 좋은,저렴한,조식";
    	else if(no==2)
    		result="고기,해산물,뷔페,한식,양식,저렴한,찌개,유명한,밀면,국수";
    	else if(no==3)
    		result="야경,도보,산책,명소,영화,드라마,레저,관광지,바다";
    	
    	return result;
    }
    
	
}
