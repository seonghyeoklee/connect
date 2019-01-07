package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 프로그램 파일명 : Post.java
 *
 * 프로그램 설명 : 게시물 정보
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 7.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	private String idx;
	private String user_idx;
	private String title;
	private String content;
	private Date created_at;
	private Date updated_at;
}
