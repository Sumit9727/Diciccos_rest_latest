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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prakat.middleware.entity.Restaurant;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.RestaurantRequest;
import com.prakat.middleware.requestbuilder.RestaurentRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.RestaurantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
 * @see RestaurantController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "restaurants")
public class RestaurantController {
	public static final String searchRestaurantByIdNote = "Search Restaurant record by text otherwise Not Found Error";
	public static final String getRestaurantByIdNote = "Get Restaurant record by RestaurantId otherwise Not Found Error";
	public static final String getRestaurantNote = "Get List of Restaurant records otherwise Not Found Error ";
	public static final String saveRestaurantNote = "Save Restaurant recordin the repository";
	public static final String saveAllRestaurantNote = "Save List of Restaurant records in the repository at once";
	public static final String updateRestaurantNote = "Update Restaurant record in the repository";
	public static final String deleteRestauranteNote = "Delete Restaurant record from the repository for restaurantid";
	
	@Autowired
	RestaurantService service;
	
	@Autowired
	RestaurentRequestBuilder requestBuilder;
	
	
	@GetMapping(value = "/restaurants/{restaurantid}")
	@ApiOperation(value = "Get Restaurant record by ID", notes = getRestaurantByIdNote)
	public ResponseEntity<SuccessResponse> getRestaurantById(@PathVariable Integer restaurantid) throws BussinessException {
		SuccessResponse response = service.getRestaurantById(restaurantid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/restaurants")
	@ApiOperation(value = "Get Restaurent record List",notes = getRestaurantNote)
	public ResponseEntity<SuccessResponse> getRestaurants(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getRestaurants(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/restaurants")
	@ApiOperation(value = "Save Restaurant record", notes = saveRestaurantNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveRestaurant(@Valid @RequestBody RestaurantRequest restaurent) throws BussinessException {
		Restaurant request = requestBuilder.buildRequest(restaurent);
		SuccessResponse response = service.saveRestaurant(request);
		Restaurant entity = (Restaurant) response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getRestaurantId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/restaurants/all")
	@ApiOperation(value = "Save Restaurant record list", notes = saveAllRestaurantNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllRestaurant(@Valid @RequestBody List<RestaurantRequest> restaurentList) throws BussinessException {
		List<Restaurant> requestList = requestBuilder.buildRequest(restaurentList);
		SuccessResponse response = service.saveAllRestaurants(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	@PutMapping(value = "/restaurants")
	@ApiOperation(value = "Update Restaurant record",notes = updateRestaurantNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateRestaurant(@Valid @RequestBody Restaurant restaurent) throws BussinessException {
		SuccessResponse response = service.updateRestaurant(restaurent);
		Restaurant entity = (Restaurant) response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getRestaurantId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	@DeleteMapping(value = "/restaurants/{restaurantid}")
	@ApiOperation(value = "Delete Restaurant record",notes = deleteRestauranteNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteRestaurant(@PathVariable Integer restaurantid) throws BussinessException {
		SuccessResponse response = service.deleteRestaurant(restaurantid);
		return ResponseEntity.ok(response);
	}
}
