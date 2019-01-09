package com.spring.util;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager{

	public static KakaoAPI getKakaoAPI() {
		OkHttpClient client = new OkHttpClient.Builder().build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(KakaoAPI.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(new Gson()))
				.client(client)
				.build();

		return retrofit.create(KakaoAPI.class);
	}

}