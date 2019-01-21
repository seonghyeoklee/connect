package com.spring.util;

import com.spring.common.Constant;
import com.spring.model.KakaoResultJson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface KakaoAPI {

	String BASE_URL = "https://kapi.kakao.com";

	@GET("/v1/user/me")
	Call<KakaoResultJson> user(@Header("Authorization") String authorization);

	@FormUrlEncoded
	@POST("/v1/user/me")
	@Headers("Authorization: KakaoAK " + Constant.KAKAO_ADMIN_KEY)
	Call<KakaoResultJson> userId(
			@Field("target_id_type") String targetIdType,
			@Field("target_id") String targetId
			);
}
