package com.spring.mapper;

import java.util.Map;

import com.spring.domain.User;

public interface SignMapper {

	public int insertUser(User user);

	public User selectUser(User user);

	public void autoLogin(Map<String, Object> map);

	public User checkUserWithSessionKey(String sessionkey);
}
