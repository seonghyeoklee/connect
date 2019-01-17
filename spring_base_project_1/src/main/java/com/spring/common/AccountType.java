package com.spring.common;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {

	ACCOUNT_TYPE_EMAIL(1),

	ACCOUNT_TYPE_GOOGLE(2),

	ACCOUNT_TYPE_KAKAO(3),

	ACCOUNT_TYPE_FACEBOOK(4);

	public static Optional<AccountType> get(final int intValue){
		return Arrays.stream(values()).filter(at->at.intValue() == intValue).findFirst();
	}

	private int value;

	AccountType(int value) {
		this.value = value;
	}

	public int intValue() {
		return value;
	}

}
