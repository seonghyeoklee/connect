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

	private int idx;
	private int authorIdx;
	private String title;
	private String content;
	private int state;
	private Date createdAt;
	private Date updatedAt;

	private User author;
	private PostLike postLike;

}
