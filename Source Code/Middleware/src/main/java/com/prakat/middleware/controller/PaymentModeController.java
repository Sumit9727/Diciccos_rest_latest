package com.prakat.middleware.controller;

import java.net.URI;
import java.util.List;

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

import com.prakat.middleware.entity.PaymentMode;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.PaymentModeRequest;
import com.prakat.middleware.requestbuilder.PaymentModeRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.PaymentModeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
* @see PaymentModeController
* @author  Amit Bhagat
* @version 1.0
*/
@RestController
@Api(tags = "payment-modes")
public class PaymentModeController {
	public static final String getPaymentModeByIdNote = "Get PaymentMode records by PaymentModeId otherwise Not Found Error";
	public static final String getPaymentModeNote = "Get List of PaymentMode records otherwise Not Found Error ";
	public static final String savePaymentModeNote = "Save PaymentMode records in the repository";
	public static final String saveAllPaymentModeNote = "Save List of PaymentMode records in the repository at once";
	public static final String updatePaymentModeNote = "Update PaymentMode records in the repository";
	public static final String deletePaymentModeNote = "Delete PaymentMode records from the repository for PaymentModeId";
	
	@Autowired
	PaymentModeService service;
	
	@Autowired
	PaymentModeRequestBuilder requestBuilder;
	
	
	@GetMapping(value = "/payments/modes/{paymentmodeid}")
	@ApiOperation(value = "Get PaymentMode record by ID",notes = getPaymentModeByIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getPaymentModeById(@PathVariable Integer paymentmodeid) throws BussinessException {
		SuccessResponse response = service.getPaymentModeById(paymentmodeid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/payments/modes")
	@ApiOperation(value = "Get PaymentMode record List", notes = getPaymentModeNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getMenus() throws BussinessException {
		SuccessResponse response = service.getPaymentModes();
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/payments/modes")
	@ApiOperation(value = "Save PaymentMode record",notes = savePaymentModeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> savePaymentMode(@Valid @RequestBody PaymentModeRequest paymentMode) throws BussinessException {
		PaymentMode request = requestBuilder.buildRequest(paymentMode);
		SuccessResponse response = service.savePaymentMode(request);
		PaymentMode entity = (PaymentMode)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getPaymentModeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/payments/modes/all")
	@ApiOperation(value = "Save All PaymentMode record",notes = savePaymentModeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllDishExtra(@Valid @RequestBody List<PaymentModeRequest> paymentModeList) throws BussinessException {
		List<PaymentMode> requestList = requestBuilder.buildRequest(paymentModeList);
		SuccessResponse response = service.saveAllPaymentMode(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/payments/modes")
	@ApiOperation(value = "Update PaymentMode record",notes = updatePaymentModeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updatePaymentMode(@Valid @RequestBody PaymentMode paymentMode) throws BussinessException {
		SuccessResponse response = service.updatePaymentMode(paymentMode);
		PaymentMode entity = (PaymentMode)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getPaymentModeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/payments/modes/{paymentmodeid}")
	@ApiOperation(value = "Delete PaymentMode record",notes = deletePaymentModeNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deletePaymentMode(@PathVariable Integer paymentmodeid) throws BussinessException {
		SuccessResponse response = service.deletePaymentMode(paymentmodeid);
		return ResponseEntity.ok(response);
	}
}
