package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishType;
import com.prakat.middleware.requestbeans.DishTypeRequest;
@Component
public class DishTypeRequestBuilder {

	public DishType buildRequest(@Valid DishTypeRequest dishType) {
		DishType request = new DishType();
		request.setDescription(dishType.getDescription());
		request.setDishTypeName(dishType.getDishTypeName());
		request.setDishTypeName(dishType.getDishTypeName());
		request.setImgUrl(dishType.getImgUrl());
		request.setStatus(dishType.getStatus());
		return request;
	}

	public List<DishType> buildRequest(@Valid List<DishTypeRequest> dishTypeList) {
		List<DishType> requestList = dishTypeList.stream().map(dishType -> buildRequest(dishType)).collect(Collectors.toList());
		return requestList;
	}

}
