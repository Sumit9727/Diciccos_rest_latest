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

import com.prakat.middleware.entity.PopularDish;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.PopularDishRequest;
import com.prakat.middleware.requestbuilder.PopularDishRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.PopularDishService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
 * @see PopularDishController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "popular-dishes")
public class PopularDishController {
	public static final String getPopularDishByIdNote = "Get PopularDish record by PopularDishId otherwise Not Found Error";
	public static final String getPopularDishByRestaurentIdNote = "Get PopularDish record by RestaurentId otherwise Not Found Error";
	public static final String getPopularDishNote = "Get List of PopularDish records otherwise Not Found Error ";
	public static final String savePopularDishNote = "Save PopularDish records in the repository";
	public static final String saveAllPopularDishNote = "Save List of PopularDish records in the repository at once";
	public static final String updatePopularDishNote = "Update PopularDish record in the repository";
	public static final String deletePopularDishNote = "Delete PopularDish record from the repository for populardishid";

	@Autowired
	PopularDishService service;

	@Autowired
	PopularDishRequestBuilder requestBuilder;

	@GetMapping(value = "/dishes/popular/{populardishid}")
	@ApiOperation(value = "Get PopularDish record by ID",notes = getPopularDishByIdNote)
	public ResponseEntity<Object> getPopularDishById(@PathVariable Integer populardishid) throws BussinessException {
		SuccessResponse response = service.getPopularDishById(populardishid);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value = "/dishes/popular/retaurentid/{retaurentid}")
	@ApiOperation(value = "Get PopularDish record by Restaurent ID", notes = getPopularDishByRestaurentIdNote)
	public ResponseEntity<SuccessResponse> getPopularDishByRestaurentId(@PathVariable Integer retaurentid) throws BussinessException {
		SuccessResponse response = service.getPopularDishByRestaurentId(retaurentid);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value = "/dishes/popular")
	@ApiOperation(value = "Get PopularDish record List",notes = getPopularDishNote)
	public ResponseEntity<SuccessResponse> getPopularDishes(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getPopularDishes(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/dishes/popular")
	@ApiOperation(value = "Save PopularDish record",notes = savePopularDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> savePopularDish(@Valid @RequestBody PopularDishRequest popularDish) throws BussinessException {
		PopularDish request = requestBuilder.buildRequest(popularDish);
		SuccessResponse response = service.savePopularDish(request);
		PopularDish entity = (PopularDish)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/dishes/popular/all")
	@ApiOperation(value = "Save All PopularDish records",notes = saveAllPopularDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllPopularDish(@Valid @RequestBody List<PopularDishRequest> popularDishList) throws BussinessException {
		List<PopularDish> requestList = requestBuilder.buildRequest(popularDishList);
		SuccessResponse response = service.saveAllPopularDishes(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/dishes/popular")
	@ApiOperation(value = "Update PopularDish record",notes = updatePopularDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updatePopularDish(@Valid @RequestBody PopularDish popularDish) throws BussinessException {
		SuccessResponse response = service.updatePopularDish(popularDish);
		PopularDish entity = (PopularDish)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/dishes/popular/{populardishid}")
	@ApiOperation(value = "Delete PopularDish record by DishName id", notes = deletePopularDishNote,authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deletePopularDish(@PathVariable Integer populardishid) throws BussinessException {
		SuccessResponse response = service.deletePopularDish(populardishid);
		return ResponseEntity.ok(response);
	}
}
