package com.prakat.middleware.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.prakat.middleware.config.AppProperties;
import com.prakat.middleware.entity.RefreshToken;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.RefreshTokenRepository;
import com.prakat.middleware.responsebeans.JwtResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenProvider {

	private AppProperties appProperties;
	@Autowired
	RefreshTokenRepository tokenRepository;
	public TokenProvider(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	public JwtResponse createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String refresh_token = registerRefreshToken(userPrincipal);
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		String jwt_token = Jwts.builder()
				.setSubject(Integer.toString(userPrincipal.getId()))
				.setClaims(getClaims(userPrincipal))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
				.compact();
		JwtResponse response = new JwtResponse(jwt_token, refresh_token);
		return response ;
	}

	public JwtResponse createTokenForSocialSites(User user) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		String refresh_token = registerRefreshToken(userPrincipal);

		String jwt_token = Jwts.builder()
				.setClaims(getClaims(userPrincipal))
				.setSubject(Integer.toString(userPrincipal.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
				.compact();

		JwtResponse response = new JwtResponse(jwt_token, refresh_token);
		return response ;
	}

	public String getIdFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(appProperties.getAuth().getTokenSecret())
				.parseClaimsJws(token)
				.getBody();
		return (String)claims.get("sub");
	}

	public Map<String, Object> getClaims(UserPrincipal userPrincipal){
		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> roles = userPrincipal.getAuthorities();
		claims.put("sub", Integer.toString(userPrincipal.getId()));
		if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
		}
		return claims;
	}
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			throw new BussinessException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new BussinessException("Invalid JWT token");
			/*
			 * } catch (ExpiredJwtException ex) { throw new
			 * BussinessException("Expired JWT token");
			 */
		} catch (UnsupportedJwtException ex) {
			throw new BussinessException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new BussinessException("JWT claims string is empty.");
		}
	}
	
	private String registerRefreshToken(UserPrincipal principal) {
		String uuid = UUID.randomUUID().toString();
		Optional<RefreshToken> optionalToken = tokenRepository.findByUserId(principal.getId());
		if(!optionalToken.isPresent()) {
			RefreshToken refreshToken = new RefreshToken();
			refreshToken.setRefreshToken(uuid);
			refreshToken.setUserId(principal.getId());
			RefreshToken entity2 = tokenRepository.save(refreshToken);
			if(entity2 == null) {
				throw new BussinessException("error while generating token");
			}
		}
		doGenerateRefreshToken(uuid);
		return doGenerateRefreshToken(uuid);
	}
	
	public String doGenerateRefreshToken(String subject) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpirationMsec());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", subject);
		return Jwts.builder()
				.setSubject(subject)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
				.compact();

	}
}
