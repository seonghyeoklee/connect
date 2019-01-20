package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

	@GetMapping("/docs/index.html")
	public String document() {

		return "redirect:/swagger-ui.html";
	}
}
