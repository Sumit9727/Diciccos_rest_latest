package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All Details about the Delivery Address")
public class DeliveryAddressRequest implements Serializable {
	
	private static final long serialVersionUID = 3606000206830269794L;
	@NotNull(message = "Address Type is mandatory")
	@ApiModelProperty(example = "Home", value ="Address Type")
	private String addressType;
	@NotNull(message = "Address Line 1 is Mandatory")
	@Size(max = 50 , message = "Address Line 1 should have Maximum 50 characters")
	@ApiModelProperty(value ="Address Line 1", required = true)
	private String addressLine1;
	@Size(max = 50 , message = "Address Line 2 should have Maximum 50 characters")
	@ApiModelProperty(value ="Address Line 2")
	private String addressLine2;
	@NotNull(message = "City Name is Mandatory")
	@Size(max = 50 , message = "City Name should have Maximum 50 characters")
	@ApiModelProperty(value ="City Name", required = true)
	private String city;
	@NotNull(message = "State Name is Mandatory")
	@Size(max = 50 , message = "State Name should have Maximum 50 characters")
	@ApiModelProperty(value ="State Name", required = true)
	private String state;
	@NotNull(message = "Zip Code is Mandatory")
	@Size(max = 50 , message = "Address Line 2 should have Maximum 50 characters")
	@ApiModelProperty(value ="Zip Code", required = true)
	private String zipCode;
	@NotNull(message = "Country Name is Mandatory")
	@Size(max = 50 , message = "Address Line 2 should have Maximum 50 characters")
	@ApiModelProperty(value ="Country Name", required = true)
	private String country;
	@NotNull(message = "Phone Number is Mandatory")
	@Size(max = 50 , message = "Phone Number should have Maximum 50 characters")
	@ApiModelProperty(value ="Phone Number", required = true)
	private String phoneNumber;
	@ApiModelProperty(value = "Latitude")
	private Double latitude;
	@ApiModelProperty(value = "Longitude")
	private Double longitude;
	@ApiModelProperty(value = "Radius")
	private Integer radius;
	
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
