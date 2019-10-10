package com.sist.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sist.vo.RestaurantVO;

@Repository
public class RestMongDAO {
	
	@Autowired
	private MongoTemplate mt;
	
	 public int TotalPage() {
	      int total=0;
	      //쿼리문없을때..일반쿼리
	      Query query=new Query();
	      int count=(int)mt.count(query, "Restaurant");
	      total=(int)(Math.ceil(count/12.0)); //총페이지 구하는 방법
	      return total;
	   }
	 
	 public RestaurantVO rest_detail(String dataSid){
		 RestaurantVO vo = new RestaurantVO();
		 BasicQuery query = new BasicQuery("{dataSid:'"+dataSid+"'}");
		 vo=mt.findOne(query, RestaurantVO.class,"Restaurant");
		 System.out.println(vo.getDataSid());
		 return vo;
	 }
	 

}
