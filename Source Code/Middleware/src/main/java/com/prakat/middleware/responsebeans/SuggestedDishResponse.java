package com.prakat.middleware.responsebeans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prakat.middleware.entity.DishName;

public class SuggestedDishResponse implements Serializable{

	private int suggestionDishId;
	private DishName dishName;
	@JsonIgnore
	private int restaurantId;
	public int getSuggestionDishId() {
		return suggestionDishId;
	}
	public void setSuggestionDishId(int suggestionDishId) {
		this.suggestionDishId = suggestionDishId;
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
