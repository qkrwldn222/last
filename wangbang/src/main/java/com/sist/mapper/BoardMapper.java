package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.vo.BoardVO;


public interface BoardMapper {
	@Select("SELECT * FROM s_board")
	public List<BoardVO> s_boardAllData();
}