package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.User;
import com.spring.service.SignService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sign")
@Log4j
public class SignController {

	@Autowired
	private SignService signService;

	@PostMapping("/in")
	public Object signInPOST(User user, HttpSession session) {

		log.info(user);

		int insertCount = signService.insertUser(user);

		log.info("insertCount : " + insertCount);
		log.info("user.getIdx() : " + user.getIdx());

		Map<String, Object> result = new HashMap<>();
		boolean succ = true;

		if(insertCount == 0) {
			succ = false;
		}

		result.put("succ", succ);

		return result;
	}

}
