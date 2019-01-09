package com.spring.exception;

public class NotFoundKakaoUserException extends RuntimeException{

	public NotFoundKakaoUserException() {
	}

	public NotFoundKakaoUserException(String msg) {
		super(msg);
	}
}
