package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.SuggestedDish;
import com.prakat.middleware.requestbeans.SuggestedDishRequest;
@Component
public class SuggestedDishRequestBuilder {

	public SuggestedDish buildRequest(@Valid SuggestedDishRequest suggestedDish) {
		SuggestedDish request = new SuggestedDish();
		request.setDishNameId(suggestedDish.getDishNameId());
		request.setDishTypeId(suggestedDish.getDishTypeId());
		request.setRestaurantId(suggestedDish.getRestaurentId());
		return request;
	}

	public List<SuggestedDish> buildRequest(@Valid List<SuggestedDishRequest> suggestedDishList) {
		List<SuggestedDish> requestList = suggestedDishList.stream().map(suggestedDish -> buildRequest(suggestedDish)).collect(Collectors.toList());
		return requestList;
	}

}
