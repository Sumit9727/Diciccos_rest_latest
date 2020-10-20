package com.prakat.middleware.responsebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "All details about the Dish Name Response")
public class DishNameResponse implements Serializable{
	@ApiModelProperty(notes = "Id of Dish Name Record")
	private int dishNameId;
	@ApiModelProperty(notes = "Dish Name")
	private String dishName;
	@ApiModelProperty(notes = "Dish Price in dollar")
	private Double price;
	@ApiModelProperty(notes = "Id of Dish Type")
	private int dishTypeId;	
	@ApiModelProperty(notes = "Dish Quantity")
	private int dishQuantity;
	private List<DishExtraResponse> dishExtra = new ArrayList<>();
	public int getDishNameId() {
		return dishNameId;
	}
	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getDishTypeId() {
		return dishTypeId;
	}
	public void setDishTypeId(int dishTypeId) {
		this.dishTypeId = dishTypeId;
	}
	public int getDishQuantity() {
		return dishQuantity;
	}
	public void setDishQuantity(int dishQuantity) {
		this.dishQuantity = dishQuantity;
	}
	public List<DishExtraResponse> getDishExtra() {
		return dishExtra;
	}
	public void setDishExtra(List<DishExtraResponse> dishExtra) {
		this.dishExtra = dishExtra;
	}
}
