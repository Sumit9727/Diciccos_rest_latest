package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.requestbeans.DishExtraRequest;
@Component
public class DishExtraRequestBuilder {

	public DishExtra buildRequest(@Valid DishExtraRequest dishExtra) {
		DishExtra request = new DishExtra();
		request.setDescription(dishExtra.getDescription());
		request.setDishNameId(dishExtra.getDishNameId());
		request.setIsCheckbox(dishExtra.getIsCheckbox());
		request.setIsRadio(dishExtra.getIsRadio());
		request.setIsRequired(dishExtra.getIsRequired());
		request.setName(dishExtra.getName());
		request.setParentId(dishExtra.getParentId());
		request.setPrice(dishExtra.getPrice());
		request.setStatus(dishExtra.getStatus());
		return request;
	}
	public List<DishExtra> buildRequest(@Valid List<DishExtraRequest> dishExtraList) {
		List<DishExtra> requestList = dishExtraList.stream().map(dishExtra -> buildRequest(dishExtra)).collect(Collectors.toList());
		return requestList;
	}
}
