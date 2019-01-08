package com.spring.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.google.gson.internal.LinkedTreeMap;

import lombok.extern.log4j.Log4j;

/**
 * 프로그램 파일명 : CommonExceptionAdvice.java
 *
 * 프로그램 설명 : 예외처리를 담당하는 클래스
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 2.
 */
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {

		log.error("Exception : " + ex.getMessage());

		model.addAttribute("exception", ex);

		return "error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFoundHandler() {

		return "error_404";
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handle(BadRequestException e) {
		Map<String, Object> map = new LinkedTreeMap<>();
		map.put("code", e.getCode());
		map.put("msg", e.getMsg());

		return map;
	}

}
