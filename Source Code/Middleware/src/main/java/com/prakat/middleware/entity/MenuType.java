package com.prakat.middleware.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "menu_types")
@Document(indexName = "menu_types",  shards = 1)
@ApiModel(description = "All details about the Menu Type")
public class MenuType implements Serializable{
	private static final long serialVersionUID = 7383071354458321384L;
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_type_id")
	private int menuTypeId;
	@Column(name = "menu_name")
	private String menuName;
	@Column(name = "menu_type_description")
	private String description;
	@Column(name = "restaurent_id")
	private int restaurantId;
	@Column(name = "menu_status")
	private String status;
	@Column(name = "menu_type_img_url")
	private String imgUrl;
	@Column(name = "service_type")
	private String serviceType;
	public int getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(int menuTypeId) {
		this.menuTypeId = menuTypeId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
}
