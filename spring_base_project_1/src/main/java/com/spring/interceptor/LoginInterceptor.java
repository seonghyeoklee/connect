package com.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.domain.User;
import com.spring.mapper.SignMapper;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	SignMapper signMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("login");

		if ( obj == null ){

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				String sessionkey = loginCookie.getValue();

				User user = signMapper.checkUserWithSessionKey(sessionkey);

				if(user != null) {
					session.setAttribute("login", user);
					return true;
				}
			}

			response.sendRedirect("/sign/login");
			return false;
		}

		return true;
	}

}
