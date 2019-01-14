package com.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.common.Constant;
import com.spring.domain.User;
import com.spring.mapper.SignMapper;

/**
 * 프로그램 파일명 : LoginInterceptor.java
 *
 * 프로그램 설명 : 로그인시 세션과 쿠키를 이용하여 로그인 유무를 체크하는 인터셉터
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 2.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	SignMapper signMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Constant.SESSION_KEY_LOGIN_USER_IDX);

		if ( obj == null ){

			Cookie loginCookie = WebUtils.getCookie(request, Constant.COOKIE_LOGIN);
			if(loginCookie != null) {
				String sessionkey = loginCookie.getValue();

				User user = signMapper.checkUserWithSessionKey(sessionkey);

				if(user != null) {
					session.setAttribute(Constant.SESSION_KEY_LOGIN_USER_IDX, user);
					return true;
				}
			}

			response.sendRedirect("/sign/login");
			return false;
		}

		return true;
	}

}
