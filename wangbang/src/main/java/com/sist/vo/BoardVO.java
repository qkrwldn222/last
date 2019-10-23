package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int no;
	private String subject;
	private String content;
	private Date regdate;
	private String dbday;
	private int hit;
	private String id;
	private int repl;
	
}
