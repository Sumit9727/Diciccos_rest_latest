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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prakat.middleware.entity.DeliveryAddress;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.DeliveryAddressRequest;
import com.prakat.middleware.requestbuilder.DeliveryAddressRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.service.DeliveryAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
 * @see DeliveryAddressController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "delivery-addresses")
public class DeliveryAddressController {
	public static final String getAddressByIdNote = "Get DeliveryAddress record by addressid otherwise Not Found Error";
	public static final String getAddressByUserIdNote = "Get DeliveryAddress record by MenuTypeId otherwise Not Found Error";
	public static final String getAddressNote = "Get List of DeliveryAddress records otherwise Not Found Error ";
	public static final String saveAddressNote = "Save DeliveryAddress records in the repository";
	public static final String saveAllAddressNote = "Save List of DeliveryAddress records in the repository at once";
	public static final String updateAddressNote = "Update DeliveryAddress record in the repository";
	public static final String deleteAddressNote = "Delete DeliveryAddress record from the repository for addressid";
	@Autowired
	DeliveryAddressService service;
	@Autowired
	DeliveryAddressRequestBuilder requestBuilder;
	@Autowired
	CustomUserDetailsService userService;
	@GetMapping(value = "/addresses/{addressid}")
	@ApiOperation(value = "Get User record by ID",notes = getAddressByIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getDeliveryAddressById(@PathVariable Integer addressid) throws BussinessException {
		SuccessResponse response = service.getDeliveryAddressById(addressid);	
		return ResponseEntity.ok(response);
	}
	@GetMapping(value = "/addresses/user")
	@ApiOperation(value = "Get DeliveryAddress record List for User Id", notes = getAddressByUserIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getDeliveryAddressesByUserId() throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = service.getDeliveryAddressesByUserId(userId);
		return ResponseEntity.ok(response);
	}
	@GetMapping(value = "/addresses")
	@ApiOperation(value = "Get DeliveryAddress record List", notes = getAddressNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getDeliveryAddresses() throws BussinessException {
		SuccessResponse response= service.getDeliveryAddresses();
		return ResponseEntity.ok(response);
	}
	@PostMapping(value = "/addresses")
	@ApiOperation(value = "Create DeliveryAddress record",notes = saveAddressNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveDeliveryAddress(@Valid @RequestBody DeliveryAddressRequest deliveryAddress) throws BussinessException {
		DeliveryAddress request = requestBuilder.buildRequest(deliveryAddress);
		SuccessResponse response = service.saveDeliveryAddress(request);
		DeliveryAddress entity = (DeliveryAddress)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDeliveryAddressId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	@PutMapping(value = "/addresses/{addressid}")
	@ApiOperation(value = "Edit DeliveryAddress record",notes = updateAddressNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateDeliveryAddress(@PathVariable Integer addressid, @Valid @RequestBody DeliveryAddressRequest deliveryAddress) throws BussinessException {
		DeliveryAddress request = requestBuilder.buildRequest(deliveryAddress,addressid );
		SuccessResponse response = service.updateDeliveryAddress(request);
		DeliveryAddress entity = (DeliveryAddress)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDeliveryAddressId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	@DeleteMapping(value = "/addresses/{addressid}")	
	@ApiOperation(value = "Delete DeliveryAddress record",notes = deleteAddressNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> deleteDeliveryAddress(@PathVariable Integer addressid) throws BussinessException {
		SuccessResponse response = service.deleteDeliveryAddress(addressid);
		return ResponseEntity.ok(response);
	}
}
