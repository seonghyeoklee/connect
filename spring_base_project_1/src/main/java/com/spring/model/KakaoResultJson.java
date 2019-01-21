package com.spring.model;

import lombok.Data;

@Data
public class KakaoResultJson {

	private long id;
	private String kaccount_email;
	private boolean kaccount_email_verified;
	private properties properties;

	@Data
	public class properties{
		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}

}
