package com.spring.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.User;
import com.spring.mapper.SignMapper;

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

		User user = new User();
		user.setName("newUser");
		user.setPassword("newUser");

		int insertCount = signMapper.insertUser(user);

		log.info("insertCount : " + insertCount);
		log.info("user.getIdx() : " + user.getIdx());
	}
}
