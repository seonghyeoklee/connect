package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleResultJson {

	private String userId;
	private String email;
	private boolean emailVerified;
	private String name;
	private String pictureUrl;
	private String locale;
}
