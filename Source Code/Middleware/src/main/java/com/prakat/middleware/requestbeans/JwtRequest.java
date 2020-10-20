package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@NotNull
	@Email(message = "Enter Proper Email Id")
	@ApiModelProperty(value = "Email Id",required = true, example = "test@prakat.in")
	private String emailId;
	@NotNull
	@ApiModelProperty(value = "Password",required = true, example = "test")
	private String password;

	//need default constructor for JSON Parsing
	public JwtRequest(){

	}
	public JwtRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}