package com.prakat.middleware.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "suggestion_dish_names")
@ApiModel(description = "All details about the Suggested Dish Name")
public class SuggestedDish implements Serializable{
	private static final long serialVersionUID = 2064581350809570403L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "suggestion_dish_id")
	private int suggestedDishId;
	@NotNull(message = "Dish Name id cannot be null")
	@ApiModelProperty(notes = "Dish Name id cannot be null")
	@Column(name = "dish_name_id")
	private int dishNameId;
	@NotNull(message = "Dish Type id cannot be null")
	@ApiModelProperty(notes = "Dish Type id cannot be null")
	@Column(name = "dish_type_id")
	private int dishTypeId;
	@Column(name = "restaurent_id")
	private int restaurantId;
	
	public int getSuggestedDishId() {
		return suggestedDishId;
	}
	public void setSuggestedDishId(int suggestedDishId) {
		this.suggestedDishId = suggestedDishId;
	}
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
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
}
