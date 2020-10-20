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

import com.prakat.middleware.entity.PopularDish;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.PopularDishRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.PopularDishResponse;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.PopularDishResponseBuilder;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class PopularDishService {
	@Autowired
	PopularDishRepository repository;
	@Autowired
	PopularDishResponseBuilder dishResponseBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	public SuccessResponse getPopularDishById(Integer id) throws BussinessException{
		Optional<PopularDish> popularDishOptional = repository.findById(id);
		
		SuccessResponse response;
		if(!popularDishOptional.isPresent()) {
			response = responseBuilder.buildResponse("PopularDish record not found", null,null);
		}
		else {
			Optional<PopularDishResponse> popularDishResponseOptional= dishResponseBuilder.buildResponse(popularDishOptional.get());
			response = responseBuilder.buildResponse("PopularDish record found", popularDishResponseOptional.get(),null);
		}
		return response;	
	}
	
	public SuccessResponse getPopularDishByRestaurentId(Integer id) throws BussinessException{
		List<PopularDish> popularDishes = repository.findByRestaurantId(id);
		SuccessResponse response;
		if(popularDishes.isEmpty()) {
			response = responseBuilder.buildResponse("PopularDish record not found",  new ArrayList<>(),null);
		}
		else {
			List<PopularDishResponse> popularDishResponseList= dishResponseBuilder.buildResponse(popularDishes);
			response = responseBuilder.buildResponse("PopularDish record found",popularDishResponseList,null);
		}
		return response;	
	}
	
	public SuccessResponse getPopularDishes(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("popularDishId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<PopularDish> popularDishPage = repository.findAll(pageable);
		List<PopularDish> popularDishes = popularDishPage.get().collect(Collectors.toList());
		SuccessResponse response;
		Pagination pagination = buildPagination(popularDishPage);
		if(popularDishes.isEmpty()) {
			response = responseBuilder.buildResponse("PopularDish record not found", new ArrayList<>(),null);
		}
		else {
			List<PopularDishResponse> popularDishResponseList= dishResponseBuilder.buildResponse(popularDishes);
			response = responseBuilder.buildResponse("PopularDish record found",popularDishResponseList,pagination);
		}
		return response;	
	}
	
	public SuccessResponse savePopularDish(PopularDish popularDish) throws BussinessException{
		PopularDish entity = repository.save(popularDish);
		if(entity == null) {
			throw new BussinessException("PopularDish not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("PopularDish record saved",entity,null);
		return response;	
	}
	
	public SuccessResponse saveAllPopularDishes(List<PopularDish> popularDishList) throws BussinessException{
		List<PopularDish> entities = repository.saveAll(popularDishList);
		if(entities.isEmpty()) {
			throw new BussinessException("PopularDish List not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("PopularDish record saved",entities,null);
		return response;	
	}

	public SuccessResponse updatePopularDish(PopularDish popularDish) throws BussinessException{
		PopularDish entity = repository.save(popularDish);
		if(entity == null) {
			throw new BussinessException("PopularDish not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("PopularDish record updated",entity,null);
		return response;	
	}
	
	public SuccessResponse deletePopularDish(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}	
		catch(Exception ex){
			throw new BussinessException("PopularDish not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("PopularDish record deleted",null,null);
		return response;	
	}
	private Pagination buildPagination(Page<PopularDish> popularDishPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(popularDishPage.getNumberOfElements());
		pagination.setPageNumber(popularDishPage.getNumber());
		pagination.setLastPage(popularDishPage.isLast());
		pagination.setTotalPages(popularDishPage.getTotalPages());
		return pagination;
	}
}
