package com.spring.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.GsonBuilder;
import com.spring.domain.User;
import com.spring.service.SignService;

import lombok.extern.log4j.Log4j;

/**
 * 프로그램 파일명 : SignController.java
 *
 * 프로그램 설명 : User의 회원가입, 로그인, 로그아웃 요청을 받아 처리하는 Controller
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 2.
 */
@Controller
@RequestMapping("/sign")
@Log4j
public class SignController {

	@Autowired
	private SignService signService;

	@RequestMapping("/login")
	public void login() {

	}

	/**
	 * 사용자 회원가입
	 *
	 * @param user
	 */
	@PostMapping("/up")
	public String signUp(User user){
		signService.signUp(user);

		return "redirect:/board/list";
	}

	/**
	 * 사용자 로그인
	 *
	 * @param session
	 * @param user
	 * @param response
	 */
	@PostMapping("/in")
	public String signIn(HttpSession session, User user, HttpServletResponse response) {

		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}

		log.info(user);
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(user));

		String returnUrl = "";
		User loginedUser = signService.signIn(user);

		if(loginedUser != null) {
			session.setAttribute("login", user);
			returnUrl = "redirect:/board/list";

			if(user.isUserCookie()) {
				int amount = 60 * 60 * 24 * 7;
				Cookie cookie = new Cookie("loginCookie", session.getId());
				cookie.setPath("/");
				cookie.setMaxAge(amount);

				response.addCookie(cookie);

				Date sessionlimit = new Date(System.currentTimeMillis() + (1000 * amount));
				signService.updateSession(user.getName(), session.getId(), sessionlimit);
			}
		} else {
			returnUrl = "/sign/login";
		}

		return returnUrl;
	}

	/**
	 * 사용자 로그아웃
	 *
	 * @param session
	 * @param response
	 */
	@GetMapping("/out")
	public String signOut(HttpSession session, HttpServletResponse response) {
		log.info("logout");

		User sessionUser = (User)session.getAttribute("login");
		Date sessionlimit = new Date(System.currentTimeMillis());

		signService.updateSession(sessionUser.getName(), "none",sessionlimit);

		Cookie cookie = new Cookie("loginCookie", session.getId());
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		session.invalidate();
		return "redirect:/sign/login";
	}

}
