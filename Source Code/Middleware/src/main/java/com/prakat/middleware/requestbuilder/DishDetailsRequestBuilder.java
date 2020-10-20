package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishDetails;
import com.prakat.middleware.requestbeans.DishDetailsRequest;
@Component
public class DishDetailsRequestBuilder {

	public DishDetails buildRequest(DishDetailsRequest dishDetails) {
		DishDetails request = new DishDetails();
		request.setDishExtraIdList(dishDetails.getDishExtraIdList());
		request.setDishNameId(dishDetails.getDishNameId());
		request.setDishQuantity(dishDetails.getDishQuantity());
		request.setDishTotalAmount(dishDetails.getDishTotalAmount());
		return request;
	}

	public List<DishDetails> buildRequest(List<DishDetailsRequest> dishDetailsList) {
		List<DishDetails> requestList = dishDetailsList.stream().map(dishDetails -> buildRequest(dishDetails)).collect(Collectors.toList());
		return requestList;
	}

}
