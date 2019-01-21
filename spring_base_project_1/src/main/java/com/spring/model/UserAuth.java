package com.spring.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

	private int idx;
	private int userIdx;
	private int type;
	private int state;
	private String identification;
	private String credential;
	private Date createdAt;

	private User user;
}
