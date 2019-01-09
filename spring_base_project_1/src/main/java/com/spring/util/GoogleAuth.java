package com.spring.util;

import java.util.Collections;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleAuth {

	public final static String WEB_APPLICATION_TYPE_CLIENT_ID = "788806329174-6aufaqsdku9p51avh129kkusgk9i1v0t.apps.googleusercontent.com";


	public static Payload getPayload(String token){

	    	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),new JacksonFactory())
	    			.setAudience(Collections.singletonList(WEB_APPLICATION_TYPE_CLIENT_ID))
	    			.build();
	    	try{
	    		GoogleIdToken idToken = verifier.verify(token);
	    		if(idToken.verify(verifier)) {
		    		return idToken.getPayload();
	    		}
	    	}catch (Exception e) {
			e.printStackTrace();
		}
	    	return null;
    }

	public static String getId(Payload payload){
		return payload.getSubject();
	}

	public static String getEmail(Payload payload){
		Object o = payload.get("email");
		if( o instanceof String )
			return (String)o;
		return null;
	}
}
