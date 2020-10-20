package com.prakat.middleware.requestbuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DishDetails;
import com.prakat.middleware.entity.Order;
import com.prakat.middleware.requestbeans.OrderRequest;
import com.prakat.middleware.security.CustomUserDetailsService;
@Component
public class OrderRequestBuilder {
	@Autowired
	DishDetailsRequestBuilder requestBuilder ;
	@Autowired
	CustomUserDetailsService userService;
	public Order buildRequest(@Valid OrderRequest order) {
		Order request = new Order();
		request.setDeleveryAddressId(order.getDeleveryAddressId());
		List<DishDetails> dishDetailsList = requestBuilder.buildRequest(order.getDishDetailsList());
		request.setDishDetailsList(dishDetailsList);
		request.setDishTotalQuantity(order.getDishTotalQuantity());
		request.setOrderDiscount(order.getOrderDiscount());
		request.setOrderReferenceId(order.getOrderReferenceId());
		request.setOrderStatus(order.getOrderStatus());
		request.setPaymentModeId(order.getPaymentModeId());
		request.setPaymentStatus(order.getPaymentStatus());
		request.setSuggestions(order.getSuggestions());
		request.setSumAmount(order.getSumAmount());
		request.setTax(order.getTax());
		request.setTimeStamp(new Date());
		request.setTotalAmount(order.getTotalAmount());
		request.setUserId(userService.getUserId());

		return request;
	}

	public List<Order> buildRequest(@Valid List<OrderRequest> orderList) {
		List<Order> requestList = orderList.stream().map(order -> buildRequest(order)).collect(Collectors.toList());
		return requestList;
	}

}
