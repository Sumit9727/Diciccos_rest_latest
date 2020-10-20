package com.prakat.middleware.requestbeans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the order Dish")
public class OtpRequest {
	@NotNull(message = "Otp is Mandatory")
	@ApiModelProperty(value = "OTP from Email")
	private int otp;
	@NotNull(message = "Email is Mandatory ")
	@Email
	@ApiModelProperty(value = "Email Id",example = "abc@xyz.com")
	private String email;
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
