package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All Details about the Dish Type")
public class DishTypeRequest implements Serializable{
	
	private static final long serialVersionUID = -1341062611990971910L;
	@ApiModelProperty(value = "Discription of Dish Type")
	private String description;
	@NotNull(message = "Dish Type Name is Mandatory")
	@ApiModelProperty(value = "Name of Dish Type",required = true)
	private String dishTypeName;
	@ApiModelProperty(value = "Id of Menu Type")
	private int menuTypeId;
	@ApiModelProperty(value = "Stauts of Dish Type")
	private String status;
	@ApiModelProperty(value = "Url of Image for Dish Type")
	private String imgUrl;

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
