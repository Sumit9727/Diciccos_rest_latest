package com.prakat.middleware.utils;

public class FacebookResponse {
	private String id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String name;
	private String email;
	private Boolean verified;
	private String is_verified;
	private Picture picture;
	private String iat;
	private String exp;
	public FacebookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FacebookResponse(String id, String first_name, String middle_name, String last_name, String name,
			String email, Boolean verified, String is_verified, Picture picture, String iat, String exp) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.name = name;
		this.email = email;
		this.verified = verified;
		this.is_verified = is_verified;
		this.picture = picture;
		this.iat = iat;
		this.exp = exp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public String getIs_verified() {
		return is_verified;
	}
	public void setIs_verified(String is_verified) {
		this.is_verified = is_verified;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
}
