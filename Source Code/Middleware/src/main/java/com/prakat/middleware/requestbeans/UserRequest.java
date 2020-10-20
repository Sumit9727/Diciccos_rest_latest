package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.prakat.middleware.entity.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the User")
public class UserRequest implements Serializable{
	
	private static final long serialVersionUID = -4763650564314470852L;

	@Length(min = 3, max = 15,message = "User Name size must have 3 to 15 characters")
	private String userName;
	
	@Email(message = "Enter proper and unique Email Address")
	@ApiModelProperty(value= "Email Id of User ",required = true)
	private String emailId;
	@NotNull(message = "Password is Mandatory")
	@Length(min = 3, message = "Password size must have Minimum 3 Characters")
	@ApiModelProperty(value= " Password",required = true)
	private String userPassword;
	@NotNull(message = "User role is mandatory")
	@Pattern(regexp = "ROLE_USER|ROLE_ADMIN")
	@ApiModelProperty(value = "Role",required = true, allowableValues = "ROLE_USER,ROLE_ADMIN", example = "ROLE_USER")
	@Enumerated(EnumType.STRING)
	private Role userRole;
	@ApiModelProperty(value= " Url of Profile Image")
	private String profilePicUrl;
	@ApiModelProperty(value = "Latitude")
	private Double latitude;
	@ApiModelProperty(value = "Longitude")
	private Double longitude;
	@ApiModelProperty(value = "Radius")
	private Integer radius;
	
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getRadius() {
		return radius;
	}
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
}
