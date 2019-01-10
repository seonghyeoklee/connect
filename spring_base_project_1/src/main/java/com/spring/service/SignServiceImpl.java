package com.spring.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.spring.common.Constant;
import com.spring.domain.KakaoResultJson;
import com.spring.domain.User;
import com.spring.domain.UserAuth;
import com.spring.mapper.SignMapper;
import com.spring.mapper.UserAuthMapper;
import com.spring.util.Kakao;
import com.spring.util.Sha256;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignMapper signMapper;

	@Autowired
	private UserAuthMapper userAuthMapper;

	@Override
	public User signUp(User user) {

		String password = Sha256.encrypt(user.getPassword());
		user.setPassword(password);

		signMapper.insertUser(user);
		return user;
	}

	@Override
	public User socialSignUp(UserAuth userAuth) {
		switch (userAuth.getType()) {
			case Constant.ACCOUNT_TYPE_EMAIL:
				break;

			case Constant.ACCOUNT_TYPE_GOOGLE:
				break;

			case Constant.ACCOUNT_TYPE_KAKAO:
				String access_token = userAuth.getCredential();
				KakaoResultJson kakaoResultJson = Kakao.getUserInfo(access_token);

				System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(kakaoResultJson));

				UserAuth auth = new UserAuth();
				auth.setType(Constant.ACCOUNT_TYPE_KAKAO);
				auth.setCredential(access_token);
				auth.setIdentification(""+kakaoResultJson.getId());

				/*if(userAuthMapper.selectUserAuth(auth) != null) {
					throw new AccessDeniedException();
				}*/

				User user = new User();
				user.setName(kakaoResultJson.getProperties().getNickname());
				user.setPassword("0");

				int insertCount = signMapper.insertUser(user);

				if(insertCount == 0) {
					return null;
				}

				auth.setUserIdx(user.getIdx());
				userAuthMapper.insertUserAuth(auth);

				return user;

			case Constant.ACCOUNT_TYPE_FACEBOOK:
				break;

			default:
				break;
		}

		return null;
	}

	@Override
	public User signIn(User user) {

		String password = Sha256.encrypt(user.getPassword());
		user.setPassword(password);

		return signMapper.selectUser(user);
	}

	@Override
	public void updateSession(String name, String sessionkey, Date sessionlimit) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("sessionkey", sessionkey);
		map.put("sessionlimit", sessionlimit);

		signMapper.updateSession(map);
	}

}
