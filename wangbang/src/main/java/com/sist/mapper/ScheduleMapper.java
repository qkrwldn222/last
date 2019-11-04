package com.sist.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ScheduleVO;
import com.sist.vo.TimeVO;

public interface ScheduleMapper {
	
	@Insert("INSERT INTO s_schedule VALUES(s_schedule_no_seq.nextval, #{id},#{title},#{startday})")
	public void scheduleInsert(ScheduleVO vo);
	
	@Select("SELECT no FROM s_schedule WHERE id = #{id} AND title = #{title} AND startday = #{startday}")
	public int scheduleGetNo(ScheduleVO vo);
	
	@Insert("INSERT INTO s_time VALUES(s_time_no_seq.nextval, #{sno}, #{time},#{costitle},#{cosimg},#{cosaddr})")
	public void timeInsert(TimeVO vo);
	
	@Select("SELECT no,title,startday,cosimg,num "
			+ "FROM (SELECT no,title,startday,(SELECT cosimg FROM s_time WHERE id = #{id} AND sno = s.no AND rownum = 1) as cosimg,rownum as num "
			+ "FROM s_schedule s WHERE id = #{id}) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ScheduleVO> scheduleData(HashMap map);
	
	@Select("SELECT no,title,startday FROM s_schedule WHERE no = #{sno}")
	public ScheduleVO scheduleDetail(int sno);
	
	@Select("SELECT time,costitle,cosimg,cosaddr FROM s_time WHERE sno = #{sno}")
	public List<TimeVO> sTimeDetail(int sno);
	
	@Update("UPDATE s_schedule SET title = #{title},startday = #{startday} WHERE no = #{no}")
	public void scheduleUpdate(ScheduleVO vo);

	@Delete("DELETE FROM s_time WHERE sno = #{sno}")
	public void timeDelete(int sno);
}
