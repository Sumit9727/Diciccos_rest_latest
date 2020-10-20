package com.prakat.middleware.utils;

public class GoogleResponse {
	private String userId ;
	private String iss;
	private String sub;
	private String azp;
	private String aud;
	private Long iat;
	private Long exp;
	private String email;
	private Boolean email_verified;
	private String name;
	private String picture;
	private String given_name;
	private String family_name;
	private String locale;

	public GoogleResponse() {
		super();
	}
	
	public GoogleResponse(String userId, String iss, String sub, String azp, String aud, Long iat, Long exp,
			String email, Boolean email_verified, String name, String picture, String given_name, String family_name,
			String locale) {
		super();
		this.userId = userId;
		this.iss = iss;
		this.sub = sub;
		this.azp = azp;
		this.aud = aud;
		this.iat = iat;
		this.exp = exp;
		this.email = email;
		this.email_verified = email_verified;
		this.name = name;
		this.picture = picture;
		this.given_name = given_name;
		this.family_name = family_name;
		this.locale = locale;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getAzp() {
		return azp;
	}
	public void setAzp(String azp) {
		this.azp = azp;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public Long getIat() {
		return iat;
	}
	public void setIat(Long iat) {
		this.iat = iat;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(Boolean email_verified) {
		this.email_verified = email_verified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GoogleResponse [iss=").append(iss).append(", sub=").append(sub).append(", azp=").append(azp)
		.append(", aud=").append(aud).append(", iat=").append(iat).append(", exp=").append(exp).append(", email=")
		.append(email).append(", email_verified=").append(email_verified).append(", name=").append(name)
		.append(", picture=").append(picture).append(", given_name=").append(given_name).append(", family_name=")
		.append(family_name).append(", locale=").append(locale).append("]");
		return builder.toString();
	}
}
