package com.spring.service;

import java.util.Date;

import com.spring.domain.User;

public interface SignService {

	public int signUp(User user);

	public User signIn(User user);

	public void autoLogin(String name, String sessionkey, Date sessionlimit);
}
