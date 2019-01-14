package com.spring.service;

import java.util.Date;

import com.spring.domain.User;
import com.spring.domain.UserSignParam;

public interface SignService {

	public User signUp(User user);

	public User socialSignUp(UserSignParam param);

	public User signIn(User user);

	public void updateSession(String name, String sessionkey, Date sessionlimit);

}
