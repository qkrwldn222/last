package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.ScheduleVO;
import com.sist.vo.TimeVO;

public interface ScheduleMapper {
	
	@Insert("INSERT INTO s_schedule VALUES(s_schedule_no_seq.nextval, #{id},#{title},#{startday})")
	public void scheduleInsert(ScheduleVO vo);
	
	@Select("SELECT no FROM s_schedule WHERE id = #{id} AND title = #{title}")
	public int scheduleGetNo(ScheduleVO vo);
	
	@Insert("INSERT INTO s_time VALUES(s_time_no_seq.nextval, #{sno}, #{time},#{datasid})")
	public void timeInsert(TimeVO vo);
}
