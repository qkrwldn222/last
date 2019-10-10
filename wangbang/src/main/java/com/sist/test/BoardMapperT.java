package com.sist.test;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Repository
public interface BoardMapperT {
	@Select("SELECT * FROM s_board")
	public List<BoardVO> s_boardAllData();
}
