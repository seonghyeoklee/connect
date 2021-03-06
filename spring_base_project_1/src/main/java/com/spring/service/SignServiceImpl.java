package com.spring.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.spring.common.AccountType;
import com.spring.exception.AccessDeniedException;
import com.spring.mapper.SignMapper;
import com.spring.mapper.UserAuthMapper;
import com.spring.model.GoogleResultJson;
import com.spring.model.KakaoResultJson;
import com.spring.model.User;
import com.spring.model.UserAuth;
import com.spring.model.UserSignParam;
import com.spring.util.GoogleAuth;
import com.spring.util.Kakao;
import com.spring.util.MailUtil;
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
	public User socialSignUp(UserSignParam param) {
		AccountType accountType;

		accountType = AccountType.get(param.getType()).get();

		switch (accountType) {
			case ACCOUNT_TYPE_EMAIL:
				String fromName = "스프링";
				String to = "dltjdgur327@naver.com";
				String title = "[스프링] 이메일 주소 확인 요청";

				String content = "<h2> 메일인증 </h2><a href='http://localhost:8080/v1/sign/emailAuth'>email 인증확인</a>";

				MailUtil mailUtil = new MailUtil();
				mailUtil.send(fromName, to, title, content);
				//메일 인증 후 가입 프로세스 추가

				return null;

			case ACCOUNT_TYPE_GOOGLE:
				String code = param.getCredential();

				try {
					GoogleResultJson googleResultJson = GoogleAuth.getPayload(code);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;

			case ACCOUNT_TYPE_KAKAO:
				String access_token = param.getCredential();
				KakaoResultJson kakaoResultJson = Kakao.getUserInfo(access_token);

				System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(kakaoResultJson));

				UserAuth auth = new UserAuth();
				auth.setType(3);
				auth.setCredential(access_token);
				auth.setIdentification(""+kakaoResultJson.getId());

				if(userAuthMapper.selectUserAuth(auth) != null) {
					throw new AccessDeniedException();
				}

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

			case ACCOUNT_TYPE_FACEBOOK:
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
