package com.sist.vo;

import lombok.Getter;
import lombok.Setter;
/*
 * no NUMBER,
id VARCHAR2(20),
title VARCHAR2(40) NOT NULL,
startday VARCHAR2(40) NOT NULL,
inwon VARCHAR2(20) NOT NULL,
together VARCHAR2(20) NOT NULL,
open VARCHAR2(20) NOT NULL
 */
@Getter
@Setter
public class ScheduleVO {
	private int no;
	private String id;
	private String title;
	private String startday;
	private String inwon;
	private String together;
	private String open;
}
