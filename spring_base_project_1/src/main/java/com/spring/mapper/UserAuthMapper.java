package com.spring.mapper;

import com.spring.model.UserAuth;

public interface UserAuthMapper {

	public UserAuth selectUserAuth(UserAuth userAuth);

	public int insertUserAuth(UserAuth userAuth);
}
