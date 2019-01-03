package com.spring.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.User;
import com.spring.mapper.SignMapper;
import com.spring.util.Sha256;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignMapper signMapper;

	@Override
	public int signUp(User user) {

		String password = Sha256.encrypt(user.getPassword());
		user.setPassword(password);

		return signMapper.insertUser(user);
	}

	@Override
	public User signIn(User user) {

		String password = Sha256.encrypt(user.getPassword());
		user.setPassword(password);

		return signMapper.selectUser(user);
	}

	@Override
	public void autoLogin(String name, String sessionkey, Date sessionlimit) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("sessionkey", sessionkey);
		map.put("sessionlimit", sessionlimit);

		signMapper.autoLogin(map);
	}

}
