package com.prakat.middleware.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "restaurents")
@Document(indexName = "restaurants",  shards = 1)
@JsonPropertyOrder(value = {"restaurentId","restaurentName","status","addressLine1","addressLine2","city",
		"state","country","serviceType","imgUrl","latitude","longitude"})
@ApiModel(description = "All details about the Restaurent")
public class Restaurant implements Serializable{
	private static final long serialVersionUID = -2298768641175402497L;
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurent_id")
	private int restaurantId;
	@Column(name = "restaurent_name")
	@ApiModelProperty(notes = "Dining Option Name should have characters between 2 to 30")
	private String restaurantName;
	@Column(name = "restaurent_status")
	@ApiModelProperty(notes = "Status should have characters between 2 to 30")
	private String status;
	@Column(name = "address_line_1")
	@ApiModelProperty(notes = "Address Line 1 should have maximum 50 characters")
	private String addressLine1;
	@Column(name = "address_line_2")
	@ApiModelProperty(notes = "Address Line 2 should have maximum 50 characters")
	private String addressLine2;
	@Column(name = "city")
	@ApiModelProperty(notes = "City Name should have characters between 2 to 30")
	private String city;
	@Column(name = "state")
	@ApiModelProperty(notes = "State Name should have characters between 2 to 30")
	private String state;
	@Column(name = "country")
	@ApiModelProperty(notes = "Country Name should have characters between 2 to 30")
	private String country;
	@Column(name = "restaurent_service_type")
	private String serviceType;
	@Column(name = "restaurent_img_url")
	private String imgUrl;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "radius")
	private Integer radius;
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
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
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
