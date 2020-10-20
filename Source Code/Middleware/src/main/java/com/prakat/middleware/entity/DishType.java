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
@Table(name = "dish_types")
@Document(indexName = "dish_types",  shards = 1)
@ApiModel(description = "All details about the Dish Type")
public class DishType implements Serializable{
	private static final long serialVersionUID = 6088685288400517718L;
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_type_id")
	private int dishTypeId;
	@Column(name = "dish_type_description")
	private String description;
	@Column(name = "dish_type_name")
	private String dishTypeName;
	@Column(name = "menu_type_id")
	private int menuTypeId;
	@Column(name = "dish_type_status")
	private String status;
	@Column(name = "dish_type_img_url")
	private String imgUrl;
	public int getDishTypeId() {
		return dishTypeId;
	}
	public void setDishTypeId(int dishTypeId) {
		this.dishTypeId = dishTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDishTypeName() {
		return dishTypeName;
	}
	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}
	public int getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(int menuTypeId) {
		this.menuTypeId = menuTypeId;
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
}
