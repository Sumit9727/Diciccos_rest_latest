package com.prakat.middleware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.SuggestedDish;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.SuggestedDishRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebeans.SuggestedDishResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.responsebuilder.SuggestionDishResponseBuilder;

@Service
public class SuggestedDishService {
	@Autowired
	SuggestedDishRepository repository;
	@Autowired
	SuggestionDishResponseBuilder dishResponseBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;

	public SuccessResponse getSuggestedDishById(Integer id) throws BussinessException{
		Optional<SuggestedDish> suggestionDishOptional = repository.findById(id);	
		SuccessResponse response;
		if(!suggestionDishOptional.isPresent()) {
			response = responseBuilder.buildResponse("SuggestedDish record not found", null,null);
		}
		else {
			Optional<SuggestedDishResponse> suggestionDishResponseOptional= dishResponseBuilder.buildResponse(suggestionDishOptional.get());
			response = responseBuilder.buildResponse("SuggestedDish record found", suggestionDishResponseOptional.get(),null);
		}
		return response;	
	}

	public SuccessResponse getSuggestedDishByRestaurentId(Integer id) throws BussinessException{
		List<SuggestedDish> suggestionDishes = repository.findByRestaurantId(id);		
		SuccessResponse response;
		if(suggestionDishes.isEmpty()) {
			response = responseBuilder.buildResponse("SuggestedDish record not found",  new ArrayList<>(),null);
		}
		else {
			List<SuggestedDishResponse> suggestionDishResponseList= dishResponseBuilder.buildResponse(suggestionDishes);
			response = responseBuilder.buildResponse("SuggestedDish record found",suggestionDishResponseList,null);
		}
		return response;	
	}

	public SuccessResponse getSuggestedDishs(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("suggestedDishId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<SuggestedDish> suggestionDishPage = repository.findAll(pageable);
		List<SuggestedDish> suggestionDishList = suggestionDishPage.get().collect(Collectors.toList());
		SuccessResponse response;
		Pagination pagination = buildPagination(suggestionDishPage);
		if(suggestionDishList.isEmpty()) {
			response = responseBuilder.buildResponse("SuggestedDish record not found",  new ArrayList<>(),null);
		}
		else {
			List<SuggestedDishResponse> suggestionDishResponseList= dishResponseBuilder.buildResponse(suggestionDishList);
			response = responseBuilder.buildResponse("SuggestedDish record found",suggestionDishResponseList,pagination);
		}
		return response;	
	}
	
	public SuccessResponse saveSuggestedDish(SuggestedDish suggestionDish) throws BussinessException{
		SuggestedDish entity = repository.save(suggestionDish);
		if(entity == null) {
			throw new BussinessException("SuggestedDish not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("SuggestedDish record saved",entity,null);
		return response ;
	}

	public SuccessResponse saveAllSuggestedDishs(List<SuggestedDish> suggestionDishList) throws BussinessException{
		List<SuggestedDish> entities = repository.saveAll(suggestionDishList);
		if(entities.isEmpty()) {
			throw new BussinessException("SuggestedDish List not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("SuggestedDish record saved",entities,null);
		return response ;
	}

	public SuccessResponse updateSuggestedDish(SuggestedDish suggestedDish) throws BussinessException{
		SuggestedDish entity = repository.save(suggestedDish);
		if(entity == null) {
			throw new BussinessException("SuggestedDish not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("SuggestedDish record updated",entity,null);
		return response ;
	}

	public SuccessResponse deleteSuggestionDish(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}	
		catch(Exception ex){
			throw new BussinessException("SuggestionDish not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("SuggestedDish record deleted",null,null);
		return response ;
	}
	
	private Pagination buildPagination(Page<SuggestedDish> suggestionDishPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(suggestionDishPage.getNumberOfElements());
		pagination.setPageNumber(suggestionDishPage.getNumber());
		pagination.setLastPage(suggestionDishPage.isLast());
		pagination.setTotalPages(suggestionDishPage.getTotalPages());
		return pagination;
	}
}
