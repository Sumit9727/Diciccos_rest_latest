package com.prakat.middleware.requestbeans;

public class TokenRequest {
	private String token;
	private int issuedAt;
	private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(int issuedAt) {
		this.issuedAt = issuedAt;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
