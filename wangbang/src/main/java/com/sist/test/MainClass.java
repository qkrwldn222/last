package com.sist.test;

import java.util.*;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.mapper.BoardMapper;

@Component
public class MainClass {
	@Autowired
	private BoardMapper mapper;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<com.sist.vo.BoardVO> list=mc.mapper.s_boardAllData();
		for(com.sist.vo.BoardVO vo:list){
			System.out.println(vo.getId()+" "+vo.getSubject()+" "+vo.getContent());
		}
	}
}
