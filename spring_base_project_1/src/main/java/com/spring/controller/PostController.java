package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 프로그램 파일명 : PostController.java
 *
 * 프로그램 설명 : 게시물 관련 요청을 받아 처리하는 Controller
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 7.
 */
@RestController
@RequestMapping("/post")
public class PostController {

	@GetMapping("/list")
	public Object postListGET() {

		return null;
	}

}
