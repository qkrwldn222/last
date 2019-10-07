package com.sist.dao;

import java.util.ArrayList;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sist.vo.ExperienceVO;
import com.sist.vo.LeisureVO;
/*import com.sist.vo.ExperienceVO;*/
import com.sist.vo.RestaurantVO;

public class MongoDAO {
	private MongoClient mc; //Connection;
	private DB db; // ORCL => mydb
	private DBCollection dbc; // table ->������ �ݷ���
	
	public MongoDAO(){
		try {
			mc = new MongoClient("localhost",27017); // IP, PORT ��ȣ
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
	
	public List<ExperienceVO> ExAllData(int page,String type){
		List<ExperienceVO> list = new ArrayList<ExperienceVO>();
		try {
			int rowSize = 10;
			int skip = (page*rowSize) - rowSize;
			
			dbc=db.getCollection(type);
			DBCursor cursor = dbc.find().skip(skip).limit(rowSize);
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				ExperienceVO vo =new ExperienceVO();
				vo.setMainimgthumb(obj.getString("mainimgthumb"));
				vo.setDataTitle(obj.getString("dataTitle"));
				vo.setTel(obj.getString("tel"));
				vo.setAddr(obj.getString("addr"));
				vo.setInfo(obj.getString("info"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<LeisureVO> leisureListData(int page,String type){
		List<LeisureVO> list = new ArrayList<LeisureVO>();
		try {
			int rowSize = 10;
			int skip = (page*rowSize) - rowSize;
			
			dbc=db.getCollection(type);
			DBCursor cursor = dbc.find().skip(skip).limit(rowSize);
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				LeisureVO vo =new LeisureVO();
				vo.setAddr(obj.getString("addr"));
				vo.setMainimgthumb(obj.getString("mainimgthumb"));
				vo.setTel(obj.getString("tel"));
				vo.setInfo(obj.getString("info"));
				vo.setDataTitle(obj.getString("dataTitle"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
