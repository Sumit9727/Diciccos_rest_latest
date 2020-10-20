package com.prakat.middleware.responsebeans;

import java.io.Serializable;
import java.util.List;

import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.entity.DishType;
import com.prakat.middleware.entity.MenuType;

public class SearchResponse implements Serializable {

	private static final long serialVersionUID = 8009052058170820975L;
	private SuccessResponse menuTypes ;
	private SuccessResponse dishTypes ;
	private SuccessResponse dishNames ;
	private SuccessResponse dishExtras ;
	public SuccessResponse getMenuTypes() {
		return menuTypes;
	}
	public void setMenuTypes(SuccessResponse menuTypes) {
		this.menuTypes = menuTypes;
	}
	public SuccessResponse getDishTypes() {
		return dishTypes;
	}
	public void setDishTypes(SuccessResponse dishTypes) {
		this.dishTypes = dishTypes;
	}
	public SuccessResponse getDishNames() {
		return dishNames;
	}
	public void setDishNames(SuccessResponse dishNames) {
		this.dishNames = dishNames;
	}
	public SuccessResponse getDishExtras() {
		return dishExtras;
	}
	public void setDishExtras(SuccessResponse dishExtras) {
		this.dishExtras = dishExtras;
	}
}
