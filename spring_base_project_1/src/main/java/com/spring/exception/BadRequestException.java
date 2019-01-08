package com.spring.exception;

import com.spring.common.ErrorCode;

public class BadRequestException extends RuntimeException{

	private int code;
	private String msg;

	public BadRequestException() {
	}

	public BadRequestException(ErrorCode error) {
		this.code = error.getCode();
		this.msg = error.getMsg();
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
