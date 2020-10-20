package com.prakat.middleware.responsebeans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.entity.DeliveryAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@JsonPropertyOrder(value = {
		"userId","userName","email","userRole","profilePicUrl","latitude","longitude","provider",
		"providerId","deliveryAddresses"})
@ApiModel(description = "All details about the User")
public class UserResponse implements Serializable {
	
	private static final long serialVersionUID = -4216579365180659941L;
	@ApiModelProperty(notes = "User Id")
	private int userId;
	@ApiModelProperty(notes = "User Name")
	private String userName;
	@ApiModelProperty(notes = "Email Id")
	private String email;
	@ApiModelProperty(value= " Url of Profile Image")
	private String profilePicUrl;
	@ApiModelProperty(value = "Latitude")
	private Double latitude;
	@ApiModelProperty(value = "Longitude")
	private Double longitude;
	@ApiModelProperty(value = "Radius")
	private Integer radius;
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Auththentication Provider")
	private AuthProvider provider;
	@ApiModelProperty(value = "User id assigned by provider")
	private String providerId;
	@ApiModelProperty(notes = "List of Delivery Addresses")
	private List<DeliveryAddress> deliveryAddresses;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public AuthProvider getProvider() {
		return provider;
	}
	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public List<DeliveryAddress> getDeliveryAddresses() {
		return deliveryAddresses;
	}
	public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
	public Integer getRadius() {
		return radius;
	}
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
}
