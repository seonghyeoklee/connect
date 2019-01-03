package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
