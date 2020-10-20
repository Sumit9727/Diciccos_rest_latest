package com.prakat.middleware.responsebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.entity.PopularDish;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.responsebeans.PopularDishResponse;
@Component
public class PopularDishResponseBuilder {
	@Autowired
	DishNameRepository repository;
	public Optional<PopularDishResponse> buildResponse(PopularDish popularDish) {
		Optional<DishName> dishNameOptional = repository.findById(popularDish.getDishNameId());
		if(dishNameOptional.isPresent()) {
			PopularDishResponse response = new PopularDishResponse();
			response.setPopularDishId(popularDish.getPopularDishId());
			response.setRestaurantId(popularDish.getRestaurantId());
			response.setDishName(dishNameOptional.get());
			return Optional.of(response);
		}
		return Optional.empty();
	}
	
	public List<PopularDishResponse> buildResponse(List<PopularDish> popularDishes) {
		List<PopularDishResponse> responseList = new ArrayList<>();
		popularDishes.stream().forEach(popularDish ->{
			Optional<PopularDishResponse> responseOptional = buildResponse(popularDish);
			if(responseOptional.isPresent())
				responseList.add(responseOptional.get());
		});
		return responseList;
	}

}
