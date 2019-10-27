package com.sist.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sist.vo.LeisureVO;
import com.sist.vo.LuxuryTourVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.WalkingTourVO;

@Repository
public class RestllwDAO {
	@Autowired
	private MongoTemplate mt;
	
	 public int leisureTotalPage() {
         int total=0;
         Query query=new Query();
         int count=(int)mt.count(query, "Sport");
         total=(int)(Math.ceil(count/8.0)); 
         return total;
      }
       public List<LeisureVO> leisure_data(int page) {
          List<LeisureVO> list = new ArrayList<LeisureVO>();
          int rowSize=8;
          int skip = (page * rowSize) - rowSize;
          Query query = new Query();
          query.with(new Sort(Sort.Direction.ASC,"dataSid"));// dataSid를 정렬해서 가져오겠다.
          query.skip(skip).limit(rowSize);//skip 버리고 limit최대치
          list = mt.find(query, LeisureVO.class,"Sport");// find = select , findOne = selectOne(하나만가져올때)
          return list;
       }
       public LeisureVO leisure_detail(String dataSid) {
          LeisureVO vo = new LeisureVO();
          BasicQuery query = new BasicQuery("{dataSid:'"+dataSid+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
          vo=mt.findOne(query, LeisureVO.class,"Sport");
          return vo;
       }
       
       public int luxuryTourTotalPage() {
           int total=0;
           Query query=new Query();
           int count=(int)mt.count(query, "LuxuryTour");
           total=(int)(Math.ceil(count/8.0)); 
           return total;
        }
         public List<LuxuryTourVO> luxuryTour_data(int page) {
            List<LuxuryTourVO> list = new ArrayList<LuxuryTourVO>();
            int rowSize=8;
            int skip = (page * rowSize) - rowSize;
            Query query = new Query();
            query.with(new Sort(Sort.Direction.ASC,"dataSid"));// dataSid를 정렬해서 가져오겠다.
            query.skip(skip).limit(rowSize);//skip 버리고 limit최대치
            list = mt.find(query, LuxuryTourVO.class,"LuxuryTour");// find = select , findOne = selectOne(하나만가져올때)
            return list;
         }
         public LuxuryTourVO luxuryTour_detail(String dataSid) {
        	 LuxuryTourVO vo = new LuxuryTourVO();
            BasicQuery query = new BasicQuery("{dataSid:'"+dataSid+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
            vo=mt.findOne(query, LuxuryTourVO.class,"LuxuryTour");
            return vo;
         }
         
         public int walkingTourTotalPage() {
             int total=0;
             Query query=new Query();
             int count=(int)mt.count(query, "WalkingTour");
             total=(int)(Math.ceil(count/8.0)); 
             return total;
          }
           public List<WalkingTourVO> walkingTour_data(int page) {
              List<WalkingTourVO> list = new ArrayList<WalkingTourVO>();
              int rowSize=8;
              int skip = (page * rowSize) - rowSize;
              Query query = new Query();
              query.with(new Sort(Sort.Direction.ASC,"dataSid"));// dataSid를 정렬해서 가져오겠다.
              query.skip(skip).limit(rowSize);//skip 버리고 limit최대치
              list = mt.find(query, WalkingTourVO.class,"WalkingTour");// find = select , findOne = selectOne(하나만가져올때)
              return list;
           }
           public WalkingTourVO walkingTour_detail(String dataSid) {
        	   WalkingTourVO vo = new WalkingTourVO();
              BasicQuery query = new BasicQuery("{dataSid:'"+dataSid+"'}"); //그냥 Query, 쿼리문 조건가져올때 BasicQuery
              vo=mt.findOne(query, WalkingTourVO.class,"WalkingTour");
              return vo;
           }
           
           public List<RestaurantVO> restaurant_data(int page) {
               List<RestaurantVO> list = new ArrayList<RestaurantVO>();
               int rowSize=8;
               int skip = (page * rowSize) - rowSize;
               Query query = new Query();
               query.with(new Sort(Sort.Direction.ASC,"dataSid"));// dataSid를 정렬해서 가져오겠다.
               query.skip(skip).limit(rowSize);//skip 버리고 limit최대치
               list = mt.find(query, RestaurantVO.class,"Restaurant");// find = select , findOne = selectOne(하나만가져올때)
               return list;
            }
}
