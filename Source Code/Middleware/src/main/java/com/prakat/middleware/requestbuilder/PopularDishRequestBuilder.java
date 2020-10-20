package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.PopularDish;
import com.prakat.middleware.requestbeans.PopularDishRequest;
@Component
public class PopularDishRequestBuilder {

	public PopularDish buildRequest(@Valid PopularDishRequest popularDish) {
		PopularDish request = new PopularDish();
		request.setDishNameId(popularDish.getDishNameId());
		request.setRestaurantId(popularDish.getRestaurentId());
		return request;
	}

	public List<PopularDish> buildRequest(@Valid List<PopularDishRequest> popularDishList) {
		List<PopularDish> requestList = popularDishList.stream().map(menuType -> buildRequest(menuType)).collect(Collectors.toList());
		return requestList;
	}

}
