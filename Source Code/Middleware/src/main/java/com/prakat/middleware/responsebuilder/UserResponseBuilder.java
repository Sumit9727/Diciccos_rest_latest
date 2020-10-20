package com.prakat.middleware.responsebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DeliveryAddress;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.repository.DeliveryAddressRepository;
import com.prakat.middleware.responsebeans.UserResponse;
@Component
public class UserResponseBuilder {
	@Autowired
	DeliveryAddressRepository repository;
	public Optional<UserResponse> buildResponse(User user) {
		if(!(user== null)) {
			UserResponse response = new UserResponse();
			List<DeliveryAddress> addresses = repository.findByUserId(user.getId());
			response.setDeliveryAddresses(addresses);
			response.setEmail(user.getEmail());
			response.setProfilePicUrl(user.getImageUrl());
			response.setUserName(user.getName());
			response.setLatitude(user.getLatitude());
			response.setLongitude(user.getLongitude());
			response.setProviderId(user.getProviderId());
			response.setProvider(user.getProvider());
			response.setUserId(user.getId());
			response.setRadius(user.getRadius());
			return Optional.of(response);
		}
		return Optional.empty();
	}
	
	public List<UserResponse> buildResponse(List<User> userList) {
		List<UserResponse> responseList = new ArrayList<>();
		userList.stream().forEach(user ->{
			Optional<UserResponse> responseOptional = buildResponse(user);
			if(responseOptional.isPresent())
				responseList.add(responseOptional.get());
		});
		return responseList;
	}

}
