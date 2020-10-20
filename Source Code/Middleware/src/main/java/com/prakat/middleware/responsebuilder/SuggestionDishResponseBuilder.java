package com.prakat.middleware.responsebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.entity.SuggestedDish;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.responsebeans.SuggestedDishResponse;
@Component
public class SuggestionDishResponseBuilder {
	@Autowired
	DishNameRepository repository;
	public Optional<SuggestedDishResponse> buildResponse(SuggestedDish suggestionDish) {
		Optional<DishName> dishNameOptional = repository.findById(suggestionDish.getDishNameId());
		if(dishNameOptional.isPresent()) {
			SuggestedDishResponse response = new SuggestedDishResponse();
			response.setSuggestionDishId(suggestionDish.getSuggestedDishId());
			response.setRestaurantId(suggestionDish.getRestaurantId());
			response.setDishName(dishNameOptional.get());
			return Optional.of(response);
		}
		return Optional.empty();
	}
	
	public List<SuggestedDishResponse> buildResponse(List<SuggestedDish> suggestionDishList) {
		List<SuggestedDishResponse> responseList = new ArrayList<>();
		suggestionDishList.stream().forEach(popularDish ->{
			Optional<SuggestedDishResponse> responseOptional = buildResponse(popularDish);
			if(responseOptional.isPresent())
				responseList.add(responseOptional.get());
		});
		return responseList;
	}

}
