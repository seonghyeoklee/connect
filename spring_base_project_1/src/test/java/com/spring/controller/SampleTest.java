package com.spring.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.GsonBuilder;
import com.spring.domain.KakaoResultJson;
import com.spring.mapper.SignMapper;
import com.spring.util.Kakao;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Log4j
public class SampleTest {

	@Autowired
	private SignMapper signMapper;

	@Test
	public void test(){

		KakaoResultJson k = Kakao.getUserInfo("NgSpmL8COP7NL8x0_JnJCbDHYfIzNKf8uH5eDwopdtYAAAFoMcMQwg");
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(k));

	}
}
