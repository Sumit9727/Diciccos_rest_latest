package com.prakat.middleware.responsebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.responsebeans.DishExtraResponse;
@Component
public class DishExtraResponseBuilder {
	@Autowired
	DishNameRepository repository;
	public Optional<DishExtraResponse> buildResponse(DishExtra dishExtra) {
		if(!(dishExtra== null)) {
			DishExtraResponse response = new DishExtraResponse();
			response.setDishExtraId(dishExtra.getDishExtraId());
			response.setExtraName(dishExtra.getName());
			response.setPrice(dishExtra.getPrice());
			return Optional.of(response);
		}
		return Optional.empty();
	}
	
	public List<DishExtraResponse> buildResponse(List<DishExtra> dishExtras) {
		List<DishExtraResponse> responseList = new ArrayList<>();
		dishExtras.stream().forEach(dishExtra ->{
			Optional<DishExtraResponse> responseOptional = buildResponse(dishExtra);
			if(responseOptional.isPresent())
				responseList.add(responseOptional.get());
		});
		return responseList;
	}

}
