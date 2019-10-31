package com.sist.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.ScheduleVO;
import com.sist.vo.TimeVO;

public interface ScheduleMapper {
	
	@Insert("INSERT INTO s_schedule VALUES(s_schedule_no_seq.nextval, #{id},#{title},#{startday})")
	public void scheduleInsert(ScheduleVO vo);
	
	@Select("SELECT no FROM s_schedule WHERE id = #{id} AND title = #{title}")
	public int scheduleGetNo(ScheduleVO vo);
	
	@Insert("INSERT INTO s_time VALUES(s_time_no_seq.nextval, #{sno}, #{time},#{datasid},#{cosimg})")
	public void timeInsert(TimeVO vo);
	
	@Select("SELECT title,startday,cosimg,num "
			+ "FROM (SELECT title,startday,(SELECT cosimg FROM s_time WHERE id = #{id} AND sno = s.no AND rownum = 1) as cosimg,rownum as num "
			+ "FROM s_schedule s WHERE id = #{id}) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ScheduleVO> scheduleData(HashMap map);
}
