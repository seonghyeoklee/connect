package com.spring.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.spring.model.GoogleResultJson;

import lombok.extern.log4j.Log4j;

@Log4j
public class GoogleAuth {

	public static GoogleResultJson getPayload(String authResult) throws FileNotFoundException, IOException {

		//파일경로
		String CLIENT_SECRET_FILE = "C:\\client_secret_788806329174-6aufaqsdku9p51avh129kkusgk9i1v0t.apps.googleusercontent.com.json";

		GoogleClientSecrets clientSecrets =
			GoogleClientSecrets.load(
					JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
				new NetHttpTransport(),
				JacksonFactory.getDefaultInstance(),
				"https://www.googleapis.com/oauth2/v4/token",
				clientSecrets.getDetails().getClientId(),
				clientSecrets.getDetails().getClientSecret(),
				authResult,
				"http://localhost:8080").execute();

		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();

		String userId = payload.getSubject();
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");

		GoogleResultJson googleResultJson = new GoogleResultJson();

		googleResultJson.setUserId(userId);
		googleResultJson.setEmail(email);
		googleResultJson.setEmailVerified(emailVerified);
		googleResultJson.setName(name);
		googleResultJson.setPictureUrl(pictureUrl);
		googleResultJson.setLocale(locale);

		return googleResultJson;
	}
}
