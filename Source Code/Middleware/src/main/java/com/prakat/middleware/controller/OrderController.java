package com.prakat.middleware.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prakat.middleware.entity.Order;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.OrderRequest;
import com.prakat.middleware.requestbuilder.OrderRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
* @see OrderController
* @author  Amit Bhagat
* @version 1.0
*/
@RestController
@Api(tags = "orders")
public class OrderController {
	public static final String getOrderByIdNote = "Get Order record by OrderId otherwise Not Found Error";
	public static final String getOrderByUserIdNote = "Get List of Order records by MenuTypeId otherwise Not Found Error";
	public static final String getOrderByReferenceIdNote = "Get Order record by MenuTypeId otherwise Not Found Error";
	public static final String getOrderNote = "Get List of Order records otherwise Not Found Error ";
	public static final String saveOrderNote = "Save Order record in the repository";
	public static final String saveAllOrderNote = "Save List of Order records in the repository at once";
	public static final String updateOrderNote = "Update Order record in the repository";
	public static final String deleteOrderNote = "Delete Order record from the repository for OrderId";
	
	@Autowired
	OrderService service;
	
	@Autowired
	OrderRequestBuilder requestBuilder;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@GetMapping(value = "/orders/{orderid}")
	@ApiOperation(value = "Get Order record by ID",notes = getOrderByIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getOrderById(@PathVariable Integer orderid) throws BussinessException {
		SuccessResponse response = service.getOrderById(orderid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/orders/user")
	@ApiOperation(value = "Get Order history of User by User ID",notes = getOrderByUserIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<Object> getOrderByUserId() throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = service.getOrdersByUserId(userId);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/orders/referenceid/{referenceid}")
	@ApiOperation(value = "Get Order by Reference ID",notes = getOrderByReferenceIdNote ,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getOrderByUserId(@PathVariable String referenceid) throws BussinessException {
		SuccessResponse response = service.getOrderByOrderReferenceId(referenceid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/orders")
	@ApiOperation(value = "Get Order record List",notes = getOrderNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getOrders(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getOrders(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/orders")
	@ApiOperation(value = "Save Order record",notes = saveOrderNote ,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveOrder(@Valid @RequestBody OrderRequest order) throws BussinessException {
		Order request = requestBuilder.buildRequest(order);
		SuccessResponse response = service.saveOrder(request);
		Order entity = (Order) response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getOrderId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/orders")
	@ApiOperation(value = "Update Order record",notes = updateOrderNote , authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> editOrder( @Valid @RequestBody Order order) throws BussinessException {
		SuccessResponse response = service.updateOrder(order);
		Order entity = (Order) response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getOrderId()).toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/orders/{orderid}")
	@ApiOperation(value = "Delete Order record",notes = deleteOrderNote , authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteOrder(@PathVariable Integer orderid) throws BussinessException {
		SuccessResponse response = service.deleteOrder(orderid);
		return ResponseEntity.ok(response);
	}
}
