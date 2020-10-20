package com.prakat.middleware.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.entity.RefreshToken;
import com.prakat.middleware.entity.Role;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.RefreshTokenRepository;
import com.prakat.middleware.repository.UserRepository;
import com.prakat.middleware.responsebeans.JwtResponse;
import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.security.TokenProvider;
import com.prakat.middleware.security.oauth2.CustomOAuth2UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class LoginUtils {

	@Autowired
	CustomOAuth2UserService userService;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RefreshTokenRepository tokenRepository;
	@Autowired
	private GoogleTokenVerifier googleTokenVerifier;
	@Autowired
	CustomUserDetailsService service;
	@Value("${spring.security.oauth2.client.registration.google.clientId}")
	private String googleClientId ;
	@Value("${spring.security.oauth2.client.provider.google.authorizationUri}")
	private String googleAuthorizationUri ;
	@Value("${spring.security.oauth2.client.registration.facebook.clientId}")
	private String facebookClientId ;
	@Value("${spring.security.oauth2.client.provider.facebook.authorizationUri}")
	private String facebookAuthorizationUri ;

	public JwtResponse loginWithGoogle(String token) {
		GoogleResponse response;
		try {
			response = googleTokenVerifier.verifyToken(token, googleClientId);
		} catch (GeneralSecurityException e) {
			throw new BussinessException("unable to connect with google server due to security");
		} catch (IOException e) {
			throw new BussinessException("internal server error");
		};
		if(response !=null) {
			Optional<User> userOptional = userRepository.findByEmail(response.getEmail());
			User entity = null;
			if(!userOptional.isPresent()) {
				User user = new User();
				user.setEmail(response.getEmail());
				user.setEmailVerified(response.getEmail_verified());
				user.setImageUrl(response.getPicture());
				user.setName(response.getName());
				user.setProvider(AuthProvider.google);
				user.setProviderId(response.getUserId());
				user.setRole(Role.ROLE_USER);
				entity = userRepository.save(user);
			}
			else
				entity = userOptional.get();
			tokenProvider.createTokenForSocialSites(entity);
			return tokenProvider.createTokenForSocialSites(entity);
		}
		else 
			return null;
	}

	public JwtResponse loginWithFacebook(String token) {

		RestTemplate template = new RestTemplate();
		String url = "https://graph.facebook.com/v3.0/me";
		ResponseEntity<FacebookResponse> responseEntity;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("fields", "id,first_name,middle_name,last_name,name,email,verified,is_verified,picture.width(250).height(250)")
					.queryParam("access_token", token);
			HttpEntity entity = new HttpEntity(headers);
			responseEntity = template.exchange(builder.toUriString(), HttpMethod.GET, entity, FacebookResponse.class);
		} catch (RestClientException e) {
			throw new BussinessException("unable to connect with Facebook Server");
		}
		if(responseEntity.hasBody()) {
			FacebookResponse response = responseEntity.getBody();
			System.out.println(response);
			Optional<User> userOptional = userRepository.findByEmail(response.getEmail());
			User entity = null;
			if(!userOptional.isPresent()) {
				User user = new User();
				user.setEmail(response.getEmail());
				user.setImageUrl(response.getPicture().getData().getUrl());
				user.setName(response.getName());
				user.setProvider(AuthProvider.facebook);
				user.setProviderId(response.getId());
				user.setRole(Role.ROLE_USER);
				entity = userRepository.save(user);
			}
			else
				entity = userOptional.get();
			return tokenProvider.createTokenForSocialSites(entity);
		}
		else 
			return null;
	}

	public JwtResponse getRefreshToken(String refresh_token) {
		try {
			tokenProvider.validateToken(refresh_token);
		} catch (ExpiredJwtException e) {
			Optional<RefreshToken> tokenOptional = tokenRepository.findByUserId(service.getUserId());
			if(tokenOptional.isPresent()) {
				tokenRepository.deleteById(tokenOptional.get().getId());
			}	
		}
		String uuid = tokenProvider.getIdFromToken(refresh_token);
		Optional<RefreshToken> optionalToken = tokenRepository.findByRefreshToken(uuid);
		if(optionalToken.isPresent()) {
			RefreshToken token = optionalToken.get();
			Optional<User> userOptional = userRepository.findById(token.getUserId());
			if(userOptional.isPresent()) {
				JwtResponse jwtResponse = tokenProvider.createTokenForSocialSites(userOptional.get());
				jwtResponse.setRefresh_token(refresh_token);
				return jwtResponse;
			}
			else {
				throw new BussinessException("User not present with id "+token.getUserId() );
			}
		}
		else {
			throw new BussinessException("Refresh_token either not registered or removed");
		}
	}

}
