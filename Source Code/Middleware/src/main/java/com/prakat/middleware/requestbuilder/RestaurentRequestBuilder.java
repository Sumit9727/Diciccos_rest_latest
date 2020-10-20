package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.Restaurant;
import com.prakat.middleware.requestbeans.RestaurantRequest;
@Component
public class RestaurentRequestBuilder {

	public Restaurant buildRequest(@Valid RestaurantRequest restaurant) {
		Restaurant request = new Restaurant();
		request.setAddressLine1(restaurant.getAddressLine1());
		request.setAddressLine2(restaurant.getAddressLine2());
		request.setCity(restaurant.getCity());
		request.setImgUrl(restaurant.getImgUrl());
		request.setRestaurantName(restaurant.getRestaurantName());
		request.setState(restaurant.getState());
		request.setServiceType(restaurant.getServiceType());
		request.setLatitude(restaurant.getLatitude());
		request.setLongitude(restaurant.getLongitude());
		request.setStatus(restaurant.getStatus());
		request.setRadius(restaurant.getRadius());
		return request;
	}

	public List<Restaurant> buildRequest(@Valid List<RestaurantRequest> restaurentList) {
		List<Restaurant> requestList = restaurentList.stream().map(restaurent -> buildRequest(restaurent)).collect(Collectors.toList());
		return requestList;
	}

}
