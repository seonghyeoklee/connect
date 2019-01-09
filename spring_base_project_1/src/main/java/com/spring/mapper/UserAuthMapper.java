package com.spring.mapper;

import com.spring.domain.User;
import com.spring.domain.UserAuth;

public interface UserAuthMapper {

	public User selectUserAuth(UserAuth userAuth);

	public int insertUserAuth(UserAuth userAuth);
}
