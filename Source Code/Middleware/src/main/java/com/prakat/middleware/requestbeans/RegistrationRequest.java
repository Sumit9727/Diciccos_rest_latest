package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@NotNull(message = "userName is Mandatory")
	@ApiModelProperty(value = "Name of User",required = true)
	private String userName;
	@NotNull(message = "Email id is Mandatory")
	@Email(message = "Enter Proper Email Address")
	@ApiModelProperty(value = "Email Id of User",required = true)
	private String emailId;
	@ApiModelProperty(value = "Password")
	private String password;
	@ApiModelProperty(value = "Url for Profile Picture")
	private String profilePicUrl;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
}