package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 프로그램 파일명 : PostLike.java
 *
 * 프로그램 설명 : 게시물 좋아요 정보
 *
 * 작 성 자 : seong hyeok lee
 *
 * 작 성 일 : 2019. 1. 8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {

	private int postIdx;
	private int userIdx;
	private Date createdAt;
	private int likeCount;
}
