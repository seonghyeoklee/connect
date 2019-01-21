package com.spring.mapper;

import java.util.Map;

import com.spring.model.User;

public interface SignMapper {

	public int insertUser(User user);

	public User selectUser(User user);

	public void updateSession(Map<String, Object> map);

	public User checkUserWithSessionKey(String sessionkey);

}
