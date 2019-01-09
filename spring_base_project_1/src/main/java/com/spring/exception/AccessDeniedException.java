package com.spring.exception;

@SuppressWarnings("serial")
public class AccessDeniedException extends RuntimeException{

	public AccessDeniedException() {
	}

	public AccessDeniedException(String msg) {
		super(msg);
	}
}
