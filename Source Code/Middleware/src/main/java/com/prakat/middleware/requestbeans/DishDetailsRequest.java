package com.prakat.middleware.requestbeans;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Order Dish")
public class DishDetailsRequest implements Serializable{

	private static final long serialVersionUID = -5722573875932449397L;
	@ApiModelProperty(value ="Dish Name Id", required = true)
	private int dishNameId;
	@NotNull(message = "Dish Name Id is Mandatory")
	@ApiModelProperty(value ="Dish Name Id")
	private List<Integer> dishExtraIdList;
	@NotNull(message = "Dish Quantity is Mandatory")
	@ApiModelProperty(value="Number of Dishes")
	private int dishQuantity;
	@NotNull(message = "Dish Total Amount is Mandatory")
	@ApiModelProperty(value="Total Amount per Dish")
	private double dishTotalAmount;
	public int getDishNameId() {
		return dishNameId;
	}
	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}
	public List<Integer> getDishExtraIdList() {
		return dishExtraIdList;
	}
	public void setDishExtraIdList(List<Integer> dishExtraIdList) {
		this.dishExtraIdList = dishExtraIdList;
	}
	public int getDishQuantity() {
		return dishQuantity;
	}
	public void setDishQuantity(int dishQuantity) {
		this.dishQuantity = dishQuantity;
	}
	public double getDishTotalAmount() {
		return dishTotalAmount;
	}
	public void setDishTotalAmount(double dishTotalAmount) {
		this.dishTotalAmount = dishTotalAmount;
	}
}
