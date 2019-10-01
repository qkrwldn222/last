package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * dataSid number,
	addr varchar2(100),	
	dataContent varchar2(1000),
	dataTtitle varchar2(500),
	detail	varchar2(500),
	holiday	varchar2(100),
	parkest	varchar2(100),
	info varchar2(1000),
	tel	varchar2(1000),
	trafIn	varchar2(1000),
	traOut	varchar2(1000),
	userHompage	varchar2(1000),
	xgsx	varchar2(100),
	ygsx	varchar2(100),
	img1thmb	varchar2(1000),
	img2thmb	varchar2(1000),
	img3thmb	varchar2(1000),
	mainImagethumb	varchar2(1000),
 * 
 */

@Getter
@Setter
public class RestaurantVO {
	private String dataSid;
	private String dataTtitle;
	private String detail;
	private String parkest;
	private String info;
	private String tel;
	private String trafIn;
	private String traOut;
	private String userHompage;
	private String addr;
	private String mainImagethumb;
	private String img1thmb;
	private String img2thmb;
	private String img3thmb;
	private String xgsx;
	private String ygsx;
	private String dataContent;
	private String holiday;
}
