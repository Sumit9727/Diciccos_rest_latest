package com.prakat.middleware.responsebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.DeliveryAddress;
import com.prakat.middleware.entity.DishDetails;
import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.entity.Order;
import com.prakat.middleware.entity.PaymentMode;
import com.prakat.middleware.entity.SuggestedDish;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DeliveryAddressRepository;
import com.prakat.middleware.repository.DishExtraRepository;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.repository.SuggestedDishRepository;
import com.prakat.middleware.responsebeans.DishNameResponse;
import com.prakat.middleware.responsebeans.OrderResponse;
import com.prakat.middleware.responsebeans.SuggestedDishResponse;
import com.prakat.middleware.service.PaymentModeService;
import com.prakat.middleware.service.SuggestedDishService;
@Component
public class OrderResponseBuilder {
	@Autowired
	DishNameRepository dishNameRepository;
	@Autowired
	DishExtraRepository dishExtraRepository;
	@Autowired
	DeliveryAddressRepository deliveryAddressRepository;
	@Autowired
	DishNameResponseBuilder dishNameResponseBuilder;
	@Autowired
	DishExtraResponseBuilder dishExtraResponseBuilder;
	@Autowired
	SuggestedDishRepository suggestionDishRepository;
	@Autowired
	SuggestedDishService suggestionDishService;
	@Autowired
	SuggestionDishResponseBuilder suggestionDishResponseBuilder;
	@Autowired
	PaymentModeService paymentModeService;

	public Optional<OrderResponse> buildResponse(Order order) throws BussinessException {
		if(!(order==null)) {
			OrderResponse response = new OrderResponse();
			List<DishNameResponse> dishnameResponseList= getDishnameResponseList(order);
			response.setDishNameList(dishnameResponseList);
			response.setDishTotalQuantity(order.getDishTotalQuantity());
			response.setOrderStatus(order.getOrderStatus());
			response.setOrderId(order.getOrderId());
			int paymentModeId = order.getPaymentModeId();
			PaymentMode paymentMode = (PaymentMode)paymentModeService.getPaymentModeById(paymentModeId).getData();
			if(paymentMode !=null)
			response.setPaymentMode(paymentMode.getPaymentModeName());
			response.setPaymentStatus(order.getPaymentStatus());
			response.setOrderReferenceId(order.getOrderReferenceId());
			response.setSuggestions(order.getSuggestions());
			response.setTimeStamp(order.getTimeStamp());
			response.setSumAmount(order.getSumAmount());
			response.setTotalAmount(order.getTotalAmount());
			response.setUserId(order.getUserId());
			List<SuggestedDishResponse> suggestionDishResponseList = getSuggestedDishes(order);
			response.setSuggestedDishList(suggestionDishResponseList);
			DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(order.getDeleveryAddressId()).orElse(null);
			response.setDeliveryAddress(deliveryAddress);
			return Optional.of(response);
		}
		return Optional.empty();
	}

	private List<DishNameResponse> getDishnameResponseList(Order order) {
		List<DishDetails> orderDetailsList = order.getDishDetailsList();
		List<DishNameResponse> dishNameResponseList = orderDetailsList.stream().map(orderDetails ->{
			DishName dishName = dishNameRepository.findById(orderDetails.getDishNameId()).orElse(null);
			List<DishExtra> dishExtraList = dishExtraRepository.findAllById(orderDetails.getDishExtraIdList());
			Optional<DishNameResponse> dishNameResponseOptional = dishNameResponseBuilder.buildResponse(dishName, dishExtraList);
			if(dishNameResponseOptional.isPresent()) {
				DishNameResponse dishNameResponse = dishNameResponseOptional.get();
				dishNameResponse.setDishQuantity(orderDetails.getDishQuantity());
			}
			return dishNameResponseOptional.orElse(null);
		}).collect(Collectors.toList());
		return dishNameResponseList;
	}

	public List<OrderResponse> buildResponse(List<Order> orderList) {
		List<OrderResponse> responseList = new ArrayList<>();
		orderList.stream().forEach(order ->{
			Optional<OrderResponse> responseOptional;
			try {
				responseOptional = buildResponse(order);
			} catch (BussinessException e) {
				responseOptional = Optional.of(null);
			}
			if(responseOptional.isPresent())
				responseList.add(responseOptional.get());
		});
		return responseList;
	}

	private List<SuggestedDishResponse> getSuggestedDishes(Order order) throws BussinessException{
		List<SuggestedDish> suggestionDishList= suggestionDishRepository.findAll();
		List<Integer> dishNameIdList = order.getDishDetailsList().stream().map(dishDetails -> dishDetails.getDishNameId()).collect(Collectors.toList());
		System.out.println(dishNameIdList);
		Predicate<SuggestedDish> suggestionDishPredicate =  suggestionDish -> !dishNameIdList.contains(suggestionDish.getDishNameId());
		List<SuggestedDish> responseList= suggestionDishList.stream()
				.filter(suggestionDishPredicate).collect(Collectors.toList());
		return suggestionDishResponseBuilder.buildResponse(responseList);
	}
}
