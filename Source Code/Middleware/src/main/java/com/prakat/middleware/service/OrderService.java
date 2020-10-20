package com.prakat.middleware.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.DishDetails;
import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.entity.Order;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DishDetailsRepository;
import com.prakat.middleware.repository.OrderRepository;
import com.prakat.middleware.responsebeans.OrderResponse;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.OrderResponseBuilder;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private DishNameService dishNameService;
	@Autowired
	private DishExtraService dishExtraService;
	@Autowired
	private DishDetailsRepository dishDetailsRepository;
	@Autowired
	private OrderResponseBuilder orderResponseBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	
	public SuccessResponse getOrderById(Integer id) throws BussinessException{
		Optional<Order> orderOptional = repository.findById(id);
		Optional<OrderResponse> orderResponseOptional= orderResponseBuilder.buildResponse(orderOptional.orElse(null));
		SuccessResponse response;
		if(!orderResponseOptional.isPresent()) {
			response = responseBuilder.buildResponse("Order record not found", orderResponseOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("Order record found", orderResponseOptional.get(),null);
		return response;
	}
	
	public SuccessResponse getOrderByOrderReferenceId(String orderReferenceId) throws BussinessException{
		Optional<Order> orderOptional = repository.findByOrderReferenceId(orderReferenceId);
		Optional<OrderResponse> orderResponseOptional = orderResponseBuilder.buildResponse(orderOptional.orElse(null));
		SuccessResponse response;
		if(!orderResponseOptional.isPresent()) {
			response = responseBuilder.buildResponse("Order record not found", orderResponseOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("Order record found", orderResponseOptional.get(),null);
		return response;
	}
	
	public SuccessResponse getOrders(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("orderId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<Order> orderPage = repository.findAll(pageable);
		List<Order> orderList = orderPage.get().collect(Collectors.toList());
		List<OrderResponse> orderResponseList = orderResponseBuilder.buildResponse(orderList) ;
		SuccessResponse response;
		Pagination pagination = buildPagination(orderPage);
		if(orderResponseList.isEmpty()) {
			response = responseBuilder.buildResponse("Order record not found", orderResponseList,null);
		}
		else
			response = responseBuilder.buildResponse("Order record found", orderResponseList,pagination);
		return response;
	}

	public SuccessResponse getOrdersByUserId(Integer userId) throws BussinessException{
		List<Order> orderList = repository.findByUserId(userId);
		List<OrderResponse> orderResponseList = orderResponseBuilder.buildResponse(orderList);
		SuccessResponse response;
		if(!orderResponseList.isEmpty()) {
			response = responseBuilder.buildResponse("Order record not found", orderResponseList,null);
		}
		else
			response = responseBuilder.buildResponse("Order record found", orderResponseList,null);
		return response;
	}

	public SuccessResponse saveOrder(Order order) throws BussinessException{
		double sumAmount = calculateSumAmount(order);
		order.setSumAmount(sumAmount);
		double totalAmount = calculateTotalAmount(order);
		order.setTotalAmount(totalAmount);
		order.setTimeStamp(new Date());
		List<DishDetails> dishDetailsList  = order.getDishDetailsList();
		order.setDishDetailsList(null);
		Order entity = repository.save(order);
		addDishDetails(dishDetailsList, entity);
		if(entity == null) {
			throw new BussinessException("Order not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("Order record saved", entity,null);
		return response;
	}
	private double calculateTotalAmount(Order order) {
		double tax = order.getTax();
		double sumAmount = order.getSumAmount();
		double totalAmount = sumAmount + tax;
		return totalAmount;
	}
	private void addDishDetails(List<DishDetails> dishDetailsList, Order entity) {
		List<DishDetails> addresses = dishDetailsList.stream().map(address -> {
			address.setOrder(entity);
			return address;
		}).collect(Collectors.toList());
		dishDetailsRepository.saveAll(addresses);

	}
	
	public SuccessResponse updateOrder(Order order) throws BussinessException{
		double sumAmount = calculateSumAmount(order);
		order.setSumAmount(sumAmount);
		double totalAmount = calculateTotalAmount(order);
		order.setTotalAmount(totalAmount);
		List<DishDetails> dishDetailsList  = order.getDishDetailsList();
		order.setDishDetailsList(null);
		Order entity = repository.save(order);
		addDishDetails(dishDetailsList, entity);
		if(entity == null) {
			throw new BussinessException("Order not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("Order record updated", entity,null);
		return response;
	}
	public SuccessResponse deleteOrder(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("Order not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("Order record deleted", null,null);
		return response;
	}
	private double calculateSumAmount(Order order) {
		List<DishDetails> dishDetailsList = order.getDishDetailsList();
		double totalAmount = dishDetailsList.stream().map(dishDetails -> {
			DishName dishName = null;
			try {
				dishName = (DishName)dishNameService.getDishNameById(dishDetails.getDishNameId()).getData();
			} catch (BussinessException e) {		
				e.printStackTrace();
			}
			double dishPrice = dishName.getPrice();
			List<Integer> dishExtraList = dishDetails.getDishExtraIdList();
			double dishExtraAmount = dishExtraList.stream().map(dishExtraId -> {
				DishExtra dishExtra = null;
				try {
					dishExtra = (DishExtra)dishExtraService.getDishExtraById(dishExtraId).getData();
				} catch (BussinessException e) {
					e.printStackTrace();
				}
				return dishExtra.getPrice();
			}).reduce(0.0, (a,b)->a+b);
			BigDecimal bd = new BigDecimal(dishExtraAmount).setScale(2, RoundingMode.HALF_UP);
			double dishExtraAmountRoundedOff = bd.doubleValue();
			int quantity = dishDetails.getDishQuantity();
			double totalDishPrice = (dishPrice+dishExtraAmountRoundedOff)*quantity;
			dishDetails.setDishTotalAmount(totalDishPrice);
			return totalDishPrice ;
		}).reduce(0.0, (a,b)->a+b);
		BigDecimal bd = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP);
		double totalAmountRoundedOff = bd.doubleValue();
		return totalAmountRoundedOff;
	}
	
	private Pagination buildPagination(Page<Order> orderPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(orderPage.getNumberOfElements());
		pagination.setPageNumber(orderPage.getNumber());
		pagination.setLastPage(orderPage.isLast());
		pagination.setTotalPages(orderPage.getTotalPages());
		return pagination;
	}
}
