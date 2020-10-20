package com.prakat.middleware.responsebeans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Dish Extra Response")
public class DishExtraResponse {
	@ApiModelProperty(notes = "Id of Dish Extra Record")
	private int dishExtraId;
	@ApiModelProperty(notes = "Dish Extra Name")
	private String extraName;
	@ApiModelProperty(notes = "Extra price in Dollar")
	private Double price;
	public int getDishExtraId() {
		return dishExtraId;
	}
	public void setDishExtraId(int dishExtraId) {
		this.dishExtraId = dishExtraId;
	}
	public String getExtraName() {
		return extraName;
	}
	public void setExtraName(String extraName) {
		this.extraName = extraName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
