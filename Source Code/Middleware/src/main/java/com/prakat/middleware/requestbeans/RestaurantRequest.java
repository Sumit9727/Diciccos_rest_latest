package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Restaurent")
public class RestaurantRequest implements Serializable{

	private static final long serialVersionUID = -8419656005707561536L;
	@NotNull(message = "Restaurant Name is mandatory")
	@ApiModelProperty(value = "Restaurant Name",required = true)
	private String restaurantName;
	@Pattern(regexp = "open|close", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value = "Status",example = "open", allowableValues = "open,close")
	private String status;
	@Max(value = 30)
	@NotNull(message = "Address Line 1 is Mandatory")
	@ApiModelProperty(value = "Address Line 1",required = true)
	private String addressLine1;
	@Max(value = 30)
	@ApiModelProperty(value = "Address Line 2")
	private String addressLine2;
	@Max(value = 30)
	@NotNull(message = "City Name is Mandatory")
	@ApiModelProperty(value = "City Name",required = true)
	private String city;
	@Max(value = 30)
	@NotNull(message = "State Name is Mandatory")
	@ApiModelProperty(value = "State Name",required = true)
	private String state;
	@Max(value = 30)
	@NotNull(message = "Country Name is Mandatory")
	@ApiModelProperty(value = "Country Name",required = true)
	private String country;
	@ApiModelProperty(value = "Url of Image for Restaurent",required = true)
	private String imgUrl;
	@Pattern(regexp = "Delivery|Takeaway|Catering", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value = "Type of Services",required = true, allowableValues = "Delivery,Takeaway,Catering", example = "Delivery")
	private String serviceType;
	@ApiModelProperty(value = "Latitude",required = true)
	private Double latitude;
	@ApiModelProperty(value = "Longitude",required = true)
	private Double longitude;
	@ApiModelProperty(value = "Radius",required = true)
	private Integer radius;

	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
