package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 프로그램 파일명 : User.java
 *
 * 프로그램 설명 : 사용자 정보
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 2.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int idx;
	private String name;
	private String password;
	private int state;
	private Date createdAt;
	private boolean userCookie;
}
