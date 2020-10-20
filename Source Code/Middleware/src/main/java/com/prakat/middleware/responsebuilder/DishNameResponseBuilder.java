package com.prakat.middleware.responsebuilder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.responsebeans.DishExtraResponse;
import com.prakat.middleware.responsebeans.DishNameResponse;
@Component
public class DishNameResponseBuilder {
	@Autowired
	DishNameRepository repository;
	@Autowired
	DishExtraResponseBuilder dishExtraResponseBuilder ;
	public Optional<DishNameResponse> buildResponse(DishName dishName, List<DishExtra> dishExtraList) {
		if(!(dishName== null)) {
			DishNameResponse response = new DishNameResponse();
			response.setDishNameId(dishName.getDishNameId());
			response.setDishName(dishName.getDishName());
			response.setDishTypeId(dishName.getDishTypeId());
			response.setPrice(dishName.getPrice());
			List<DishExtraResponse> dishExtraResponseList = dishExtraResponseBuilder.buildResponse(dishExtraList);
			response.setDishExtra(dishExtraResponseList);
			return Optional.of(response);
		}
		return Optional.empty();
	}
}
