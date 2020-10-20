package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.requestbeans.DishNameRequest;
@Component
public class DishNameRequestBuilder {

	public DishName buildRequest(@Valid DishNameRequest dishName) {
		DishName request = new DishName();
		request.setDescription(dishName.getDescription());
		request.setDishName(dishName.getDishName());
		request.setDishTypeId(dishName.getDishTypeId());
		request.setExtra(dishName.getExtra());
		request.setImgUrl(dishName.getImgUrl());
		request.setPrice(dishName.getPrice());
		request.setStatus(dishName.getStatus());
		request.setTaxId(dishName.getTaxId());
		return request;
	}
	public List<DishName> buildRequest(@Valid List<DishNameRequest> dishNameList) {
		List<DishName> requestList = dishNameList.stream().map(dishName -> buildRequest(dishName)).collect(Collectors.toList());
		return requestList;
	}
}
