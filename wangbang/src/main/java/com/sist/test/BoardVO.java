package com.sist.test;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int no;
	private String subject;
	private String content;
	private String id;
	private Date regdate;
	private int hit;
	
}
