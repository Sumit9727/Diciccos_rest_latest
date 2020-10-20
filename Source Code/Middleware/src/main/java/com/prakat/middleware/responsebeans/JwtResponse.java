package com.prakat.middleware.responsebeans;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private  String access_token;
	private  String refresh_token;
	private String token_type ;
	public JwtResponse(String access_token, String refresh_token) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.token_type = "Bearer";
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
}