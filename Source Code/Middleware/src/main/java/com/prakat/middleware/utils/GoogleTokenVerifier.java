package com.prakat.middleware.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.prakat.middleware.exception.BussinessException;

@Component
public class GoogleTokenVerifier {

	public GoogleResponse verifyToken(String token, String CLIENT_ID) throws GeneralSecurityException, IOException {
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();	
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList(CLIENT_ID))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();

			// (Receive idTokenString by HTTPS POST)

			GoogleIdToken idToken = verifier.verify(token);
			if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  // Get profile information from payload
			  String email = payload.getEmail();
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");
			  String aud = (String) payload.get("aud");
			  String azp = (String) payload.get("azp");
			  Long iat = (Long) payload.get("iat");
			  String iss = (String) payload.get("iss");
			  Long exp = (Long) payload.get("exp");
			  

			  // Use or store profile information
			  // ...
			  GoogleResponse response  =  new GoogleResponse();
			  response.setUserId(userId);
			  response.setEmail(email);
			  response.setName(name);
			  response.setEmail_verified(emailVerified);
			  response.setPicture(pictureUrl);
			  response.setLocale(locale);
			  response.setFamily_name(familyName);
			  response.setAud(aud);
			  response.setAzp(azp);
			  response.setIat(iat);
			  response.setIss(iss);
			  response.setExp(exp);
			  return response;
			} else {
			  throw new BussinessException("Invalid Token");
			}
	}
}
