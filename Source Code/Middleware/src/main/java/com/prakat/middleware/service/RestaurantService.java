package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.Restaurant;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.RestaurantRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
/**
* @see RestaurantService
* @author  Amit Bhagat
* @version 1.0
*/
@Service
public class RestaurantService {
	@Autowired
	RestaurantRepository repository;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	public SuccessResponse getRestaurantById(Integer id) throws BussinessException{
		Optional<Restaurant> restaurentOptional = repository.findById(id);
		SuccessResponse response;
		if(!restaurentOptional.isPresent()) {
			response = responseBuilder.buildResponse("Restaurant record not found", restaurentOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("Restaurant record found", restaurentOptional.get(),null);
		return response;
	}

	public SuccessResponse getRestaurants(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("restaurantId");
		 PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<Restaurant> restaurantPage = repository.findAll(pageable);
		List<Restaurant> orderList = restaurantPage.toList();
		SuccessResponse response;
		Pagination pagination = buildPagination(restaurantPage);
		if(orderList.isEmpty()) {
			response = responseBuilder.buildResponse("Restaurant record not found", orderList,null);
		}
		else
			response = responseBuilder.buildResponse("Restaurant record found", orderList,pagination);
		return response;
	}
	
	public SuccessResponse saveRestaurant(Restaurant restaurant) throws BussinessException{
		Restaurant entity = repository.save(restaurant);
		if(entity == null) {
			throw new BussinessException("Restaurant not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("Restaurant record saved", entity,null);
		return response;
	}
	
	public SuccessResponse saveAllRestaurants(List<Restaurant> restaurantList) throws BussinessException{
		 List<Restaurant> entities = repository.saveAll(restaurantList);
		if(entities.isEmpty()) {
			throw new BussinessException("Restaurant List not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("Restaurant record saved", entities,null);
		return response;
	}

	public SuccessResponse updateRestaurant(Restaurant restaurant) throws BussinessException{
		Restaurant entity = repository.save(restaurant);
		if(entity == null) {
			throw new BussinessException("Restaurant not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("Restaurant record updated", entity,null);
		return response;
	}
	public SuccessResponse deleteRestaurant(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("Restaurant not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("Restaurant record deleted", null,null);
		return response;	
	}
	private Pagination buildPagination(Page<Restaurant> restaurantPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(restaurantPage.getNumberOfElements());
		pagination.setPageNumber(restaurantPage.getNumber());
		pagination.setLastPage(restaurantPage.isLast());
		pagination.setTotalPages(restaurantPage.getTotalPages());
		return pagination;
	}
}
