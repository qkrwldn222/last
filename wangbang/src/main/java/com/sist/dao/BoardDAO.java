package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import org.springframework.stereotype.Repository;

import com.sist.vo.BoardVO;


import java.util.*;

public class BoardDAO extends SqlSessionDaoSupport{
   
	public List<BoardVO> board_list(Map map){
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   	   
	   getSqlSession().update("boardListData",map);
	   list=(List<BoardVO>)map.get("pResult");
	   
	   return list;
   }
	

   
   public void board_insert(BoardVO vo,String id){
	   Map map=new HashMap();
	   map.put("pid", id);
	   map.put("pSubject", vo.getSubject());
	   map.put("pContent", vo.getContent());
	   
	   
	   getSqlSession().update("boardInsert",map);
   }
   
   //boardDelete
   public boolean board_delete(int no,String id){
	   boolean bCheck=false;
	   Map map=new HashMap();
	   map.put("pNo", no);
	   map.put("pid", id);
	   
	   getSqlSession().update("boardDelete",map);
	   String result=(String)map.get("pResult");
	   bCheck=Boolean.parseBoolean(result);
	   return bCheck;
   }
   /*public int board_total()
   {
	   Map map=new HashMap();
	   getSqlSession().update("boardTotalPage",map);
	   int total=(Integer)map.get("pTotal");
	   return total;
   }
   
   public BoardVO board_detail(int type,int no)
   {
	   Map map=new HashMap();
	   map.put("pType", type);
	   map.put("pNo", no);
	   
	   getSqlSession().update("boardDetailData",map);
	   List<BoardVO> vo=(List<BoardVO>)map.get("pResult");
	   
	   return vo.get(0);
	   
   }
   
   public void board_insert(BoardVO vo)
   {
	   Map map=new HashMap();
	   map.put("pName", vo.getName());
	   map.put("pSubject", vo.getSubject());
	   map.put("pContent", vo.getContent());
	   map.put("pPwd", vo.getPwd());
	   
	   getSqlSession().update("boardInsert",map);
   }
   
   public boolean board_update(BoardVO vo)
   {
	   boolean bCheck=false;
	   Map map=new HashMap();
	   map.put("pNo", vo.getNo());
	   map.put("pName", vo.getName());
	   map.put("pSubject", vo.getSubject());
	   map.put("pContent", vo.getContent());
	   map.put("pPwd", vo.getPwd());
	   
	   // 실행을 한다 ==> map에 결과값을 넘겨준다 
	   getSqlSession().update("boardUpdate",map);
	   String result=(String)map.get("pResult");
	   bCheck=Boolean.parseBoolean(result);
	   return bCheck;
   }
   
   public boolean board_delete(int no,String pwd)
   {
	   boolean bCheck=false;
	   Map map=new HashMap();
	   map.put("pNo", no);
	   map.put("pPwd", pwd);
	   
	   getSqlSession().update("boardDelete",map);
	   String result=(String)map.get("pResult");
	   bCheck=Boolean.parseBoolean(result);
	   return bCheck;
   }
   
   public List<BoardVO> board_find(String fs,String ss)
   {
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   Map map=new HashMap();
	   map.put("pFs", fs);
	   map.put("pSs", ss);
	   
	   getSqlSession().update("boardFind",map);
	   list=(List<BoardVO>)map.get("pResult");
	   return list;
   }*/
}







