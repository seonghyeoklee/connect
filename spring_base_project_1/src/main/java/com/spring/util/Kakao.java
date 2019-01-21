package com.spring.util;

import java.io.IOException;

import com.spring.exception.NotFoundKakaoUserException;
import com.spring.model.KakaoResultJson;

public class Kakao {

	public static KakaoResultJson getUserInfo(String access_token) {

		KakaoAPI kakaoAPI = RetrofitManager.getKakaoAPI();

		try {
			KakaoResultJson kakaoResultJson = kakaoAPI.user("Bearer " + access_token).execute().body();

			if(kakaoResultJson == null) {
				throw new NotFoundKakaoUserException();
			}

			KakaoResultJson kakaoResultJson2 = kakaoAPI.userId("user_id", "" + kakaoResultJson.getId()).execute().body();

			if(kakaoResultJson2 == null) {
				throw new NotFoundKakaoUserException();
			}

			if(kakaoResultJson.getId() != kakaoResultJson2.getId()) {
				return null;
			}

			return kakaoResultJson2;

		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new RuntimeException();
	}
}
