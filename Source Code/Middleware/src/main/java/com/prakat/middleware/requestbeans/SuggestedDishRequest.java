package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Suggested Dish Name")
public class SuggestedDishRequest implements Serializable{

	private static final long serialVersionUID = 2936543594051309038L;
	@NotNull(message = "Dish Name id is Mandatory")
	@ApiModelProperty(value= "Id of Dish Name ",required = true)
	private int dishNameId;
	@NotNull(message = "Dish Type id is Mandatory")
	@ApiModelProperty(value = "Id of Dish Type",required = true)
	private int dishTypeId;
	@NotNull(message = "Restaurent id is Mandatory")
	@ApiModelProperty(value = "Id of Restaurent",required = true)
	private int restaurentId;
	public int getDishNameId() {
		return dishNameId;
	}
	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}
	public int getDishTypeId() {
		return dishTypeId;
	}
	public void setDishTypeId(int dishTypeId) {
		this.dishTypeId = dishTypeId;
	}
	public int getRestaurentId() {
		return restaurentId;
	}
	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}
}
