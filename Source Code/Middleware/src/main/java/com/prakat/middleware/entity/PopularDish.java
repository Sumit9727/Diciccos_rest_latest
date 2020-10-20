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
@Table(name = "popular_dish_names")
@ApiModel(description = "All details about the Dish Name")
public class PopularDish implements Serializable{
	private static final long serialVersionUID = -9161469872884150710L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "popular_dish_id")
	private int popularDishId;
	@NotNull(message = "Dish Name id cannot be null")
	@ApiModelProperty(notes = "Dish Name id cannot be null")
	@Column(name = "dish_name_id")
	private int dishNameId;
	@Column(name = "restaurent_id")
	private int restaurantId;
	public int getPopularDishId() {
		return popularDishId;
	}
	public void setPopularDishId(int popularDishId) {
		this.popularDishId = popularDishId;
	}
	public int getDishNameId() {
		return dishNameId;
	}
	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
}
