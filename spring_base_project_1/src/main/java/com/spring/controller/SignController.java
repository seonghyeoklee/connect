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

import com.spring.domain.User;
import com.spring.service.SignService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sign")
@Log4j
public class SignController {

	@Autowired
	private SignService signService;

	@RequestMapping("/login")
	public void login() {

	}

	@PostMapping("/up")
	public String signUp(User user) {

		signService.signUp(user);

		return "redirect:/board/list";
	}

	@PostMapping("/in")
	public String signIn(HttpSession session, User user, HttpServletResponse response) {
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		System.out.println(user);
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
				signService.autoLogin(user.getName(), session.getId(), sessionlimit);
			}
		} else {
			returnUrl = "/sign/login";
		}

		return returnUrl;
	}

	@GetMapping("/out")
	public String signOut(HttpSession session, HttpServletResponse response) {
		session.invalidate();

		Cookie cookie = new Cookie("loginCookie", session.getId());
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return "redirect:/sign/login";
	}

}
