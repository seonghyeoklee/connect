package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

	@GetMapping("/list")
	public Object postListGET() {

		return null;
	}

	@PostMapping("")
	public Object insertPost() {

		return null;
	}

}
