package com.spring.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;
import com.spring.common.Constant;
import com.spring.domain.GoogleResultJson;
import com.spring.domain.User;
import com.spring.domain.UserSignParam;
import com.spring.resolver.SessionLogin;
import com.spring.service.SignService;
import com.spring.util.GoogleAuth;

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

	@GetMapping("/login")
	public void login() {

	}

	@GetMapping("/google")
	public void google() {

	}

	@GetMapping("/facebook")
	public void facebook() {

	}

	@ResponseBody
	@PostMapping("/google")
	public ResponseEntity<GoogleResultJson> googlePost(String authResult, HttpServletRequest request) throws IOException {
		log.info("authResult : " + authResult);

		//X-Requested-With 존재하지 않을 경우 요청은 위조되어 있을 수 있음
		if (request.getHeader("X-Requested-With") == null) {
			return null;
		}

		GoogleResultJson googleResultJson = GoogleAuth.getPayload(authResult);

		return new ResponseEntity<GoogleResultJson>(googleResultJson, HttpStatus.OK);
	}

	/**
	 * 일반 사용자 회원가입
	 *
	 * @param user
	 */
	@PostMapping("/up")
	public String signUp(User user){
		signService.signUp(user);

		return "redirect:/board/list";
	}

	/**
	 * 소셜 사용자 회원가입
	 *
	 * @param userAuth
	 */
	@PostMapping("/social")
	public String socialSignUp(@RequestParam int type, @RequestParam String identification, @RequestParam(required = false) String credential){
		UserSignParam param = new UserSignParam();
		param.setType(type);
		param.setIdentification(identification);
		param.setCredential(credential);

		signService.socialSignUp(param);

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
	public String signIn(HttpSession session, User user, HttpServletResponse response, @SessionLogin Integer userIdx) {

		if(userIdx != null) {
			session.removeAttribute(Constant.SESSION_LOGIN_USER_IDX);
		}

		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(user));

		String returnUrl = "";
		User loginedUser = signService.signIn(user);

		if(loginedUser != null) {
			session.setAttribute(Constant.SESSION_LOGIN_USER_IDX, user);
			returnUrl = "redirect:/board/list";

			if(user.isUserCookie()) {
				int amount = 60 * 60 * 24 * 7;
				Cookie cookie = new Cookie(Constant.COOKIE_LOGIN, session.getId());
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

		User sessionUser = (User)session.getAttribute(Constant.SESSION_LOGIN_USER_IDX);
		Date sessionlimit = new Date(System.currentTimeMillis());
		System.out.println(sessionUser);

		signService.updateSession(sessionUser.getName(), "none",sessionlimit);

		Cookie cookie = new Cookie(Constant.COOKIE_LOGIN, session.getId());
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		session.invalidate();
		return "redirect:/sign/login";
	}

}
