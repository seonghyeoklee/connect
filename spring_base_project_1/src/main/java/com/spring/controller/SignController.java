package com.spring.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.GsonBuilder;
import com.spring.common.Constant;
import com.spring.domain.User;
import com.spring.domain.UserAuth;
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

	@RequestMapping("/google")
	public void google() {

	}

	@PostMapping("/google")
	public void googlePost(String authResult, HttpServletRequest request) throws IOException {
		log.info(authResult);
		if (request.getHeader("X-Requested-With") == null) {
			  // Without the `X-Requested-With` header, this request could be forged. Aborts.
			}

			// Set path to the Web application client_secret_*.json file you downloaded from the
			// Google API Console: https://console.developers.google.com/apis/credentials
			// You can also find your Web application client ID and client secret from the
			// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
			// object.
			String CLIENT_SECRET_FILE = "C:\\client_secret_788806329174-6aufaqsdku9p51avh129kkusgk9i1v0t.apps.googleusercontent.com.json";

			// Exchange auth code for access token
			GoogleClientSecrets clientSecrets =
			    GoogleClientSecrets.load(
			        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
			GoogleTokenResponse tokenResponse =
			          new GoogleAuthorizationCodeTokenRequest(
			              new NetHttpTransport(),
			              JacksonFactory.getDefaultInstance(),
			              "https://www.googleapis.com/oauth2/v4/token",
			              clientSecrets.getDetails().getClientId(),
			              clientSecrets.getDetails().getClientSecret(),
			              authResult,
			              "http://localhost:8080")  // Specify the same redirect URI that you use with your web
			                             // app. If you don't have a web version of your app, you can
			                             // specify an empty string.
			              .execute();

			String accessToken = tokenResponse.getAccessToken();
			System.out.println("accessToken : " + accessToken);
			// Use access token to call API
			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
			/*Drive drive =
			    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
			        .setApplicationName("Auth Code Exchange Demo")
			        .build();
			File file = drive.files().get("appfolder").execute();*/

			// Get profile info from ID token
			GoogleIdToken idToken = tokenResponse.parseIdToken();
			GoogleIdToken.Payload payload = idToken.getPayload();
			String userId = payload.getSubject();  // Use this value as a key to identify a user.
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			System.out.println(userId);
			System.out.println(email);

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
	public String socialSignUp(UserAuth userAuth){
		log.info(userAuth.getCredential());
		userAuth.setType(3);
		signService.socialSignUp(userAuth);

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

		if(session.getAttribute(Constant.SESSION_LOGIN_USER_IDX) != null) {
			session.removeAttribute(Constant.SESSION_LOGIN_USER_IDX);
		}

		log.info(user);
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

		signService.updateSession(sessionUser.getName(), "none",sessionlimit);

		Cookie cookie = new Cookie(Constant.COOKIE_LOGIN, session.getId());
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		session.invalidate();
		return "redirect:/sign/login";
	}

}
