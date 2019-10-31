package com.sist.vo;

import lombok.Getter;
import lombok.Setter;
/*
 * no NUMBER,
dno NUMBER,
time NUMBER NOT NULL,
datasid VARCHAR2(40) NOT NULL,
 */
@Getter
@Setter
public class TimeVO {
	private int no;
	private int sno;
	private int time;
	private String datasid;
	private String cosimg;
}
