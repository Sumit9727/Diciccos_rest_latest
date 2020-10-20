package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DeliveryAddress;
import com.prakat.middleware.requestbeans.DeliveryAddressRequest;
import com.prakat.middleware.security.CustomUserDetailsService;
@Component
public class DeliveryAddressRequestBuilder {
	@Autowired
	CustomUserDetailsService userService;
	public DeliveryAddress buildRequest(@Valid DeliveryAddressRequest deliveryAddress) {
		DeliveryAddress request = new DeliveryAddress();
		request.setUserId(userService.getUserId());
		request.setAddressLine1(deliveryAddress.getAddressLine1());
		request.setAddressLine2(deliveryAddress.getAddressLine2());
		request.setAddressType(deliveryAddress.getAddressType());
		request.setCity(deliveryAddress.getCity());
		request.setCountry(deliveryAddress.getCountry());
		request.setPhoneNumber(deliveryAddress.getPhoneNumber());
		request.setState(deliveryAddress.getState());
		request.setZipCode(deliveryAddress.getZipCode());
		request.setLatitude(deliveryAddress.getLatitude());
		request.setLongitude(deliveryAddress.getLongitude());
		request.setRadius(deliveryAddress.getRadius());
		return request;
	}
	
	public DeliveryAddress buildRequest(@Valid DeliveryAddressRequest deliveryAddress, Integer addressid) {
		DeliveryAddress request = new DeliveryAddress();
		request.setDeliveryAddressId(addressid);
		request.setUserId(userService.getUserId());
		request.setAddressLine1(deliveryAddress.getAddressLine1());
		request.setAddressLine2(deliveryAddress.getAddressLine2());
		request.setAddressType(deliveryAddress.getAddressType());
		request.setCity(deliveryAddress.getCity());
		request.setCountry(deliveryAddress.getCountry());
		request.setPhoneNumber(deliveryAddress.getPhoneNumber());
		request.setState(deliveryAddress.getState());
		request.setZipCode(deliveryAddress.getZipCode());
		request.setLatitude(deliveryAddress.getLatitude());
		request.setLongitude(deliveryAddress.getLongitude());
		return request;
	}

	public List<DeliveryAddress> buildRequest(@Valid List<DeliveryAddressRequest> deliveryAddressList) {
		List<DeliveryAddress> requestList = deliveryAddressList.stream().map(deliveryAddress -> buildRequest(deliveryAddress)).collect(Collectors.toList());
		return requestList;
	}

}
