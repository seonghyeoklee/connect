package com.spring.common;

public enum ErrorCode {

	UNKNOWN(1000, "UNKNOWN");

	private final int code;
	private final String msg;

	ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
