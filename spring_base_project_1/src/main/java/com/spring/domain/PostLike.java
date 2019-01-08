package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {

	private int postIdx;
	private int userIdx;
	private Date createdAt;
	private int likeCount;
}
