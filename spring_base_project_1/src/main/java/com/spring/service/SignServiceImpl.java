package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.User;
import com.spring.mapper.SignMapper;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignMapper signMapper;

	@Override
	public int insertUser(User user) {

		return signMapper.insertUser(user);
	}

}
