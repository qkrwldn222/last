package com.sist.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sist.vo.RestaurantVO;

public class MongoDAO {
	private MongoClient mc; //Connection;
	private DB db; // ORCL => mydb
	private DBCollection dbc; // table ->몽고에선 콜렉션
	
	public MongoDAO(){
		try {
			// 연결
			mc = new MongoClient("localhost",27017); // IP, PORT 번호
			db=mc.getDB("mydb");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<RestaurantVO> resAllData(int page,String type){
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		try {
			int rowSize = 10;
			int skip = (page*rowSize) - rowSize;
			//ResultSet
			dbc=db.getCollection(type);
			DBCursor cursor = dbc.find().skip(0).limit(1);
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				RestaurantVO vo =new RestaurantVO();
				vo.setDataTtitle(obj.getString("dataContent"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
