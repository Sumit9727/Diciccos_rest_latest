package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.requestbeans.UserRequest;
import com.prakat.middleware.security.CustomUserDetailsService;
@Component
public class UserRequestBuilder {
	@Autowired
	CustomUserDetailsService userService;
	public User buildRequest(@Valid UserRequest user) {
		User request = new User();
		request.setId(userService.getUserId());
		request.setEmail(user.getEmailId());
		request.setImageUrl(user.getProfilePicUrl());
		request.setLatitude(user.getLatitude());
		request.setLongitude(user.getLongitude());
		request.setName(user.getUserName());
		request.setPassword(user.getUserPassword());
		request.setProvider(AuthProvider.local);
		request.setRole(user.getUserRole());
		request.setRadius(user.getRadius());
		return request;
	}

	public List<User> buildRequest(@Valid List<UserRequest> userList) {
		List<User> requestList = userList.stream().map(user -> buildRequest(user)).collect(Collectors.toList());
		return requestList;
	}

}
