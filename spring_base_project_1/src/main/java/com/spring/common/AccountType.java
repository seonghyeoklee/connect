package com.spring.common;

public enum AccountType {

	ACCOUNT_TYPE_EMAIL(1),

	ACCOUNT_TYPE_GOOGLE(2),

	ACCOUNT_TYPE_KAKAO(3),

	ACCOUNT_TYPE_FACEBOOK(4);

	private final int typeCode;

	AccountType(int typeCode){
		this.typeCode = typeCode;
	}

	public int getTypeCode() {
		return typeCode;
	}

}
