package com.prakat.middleware.responsebeans;

import java.io.Serializable;

import com.prakat.middleware.entity.DishName;

public class PopularDishResponse implements Serializable{

	private int popularDishId;
	private DishName dishName;
	private int restaurantId;
	public int getPopularDishId() {
		return popularDishId;
	}
	public void setPopularDishId(int popularDishId) {
		this.popularDishId = popularDishId;
	}
	public DishName getDishName() {
		return dishName;
	}
	public void setDishName(DishName dishName) {
		this.dishName = dishName;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
}
