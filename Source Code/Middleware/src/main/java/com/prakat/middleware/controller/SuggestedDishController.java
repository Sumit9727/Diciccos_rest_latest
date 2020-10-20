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

import com.prakat.middleware.entity.SuggestedDish;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.SuggestedDishRequest;
import com.prakat.middleware.requestbuilder.SuggestedDishRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.SuggestedDishService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
* @see SuggestionDishController
* @author  Amit Bhagat
* @version 1.0
*/
@RestController
@Api(tags = "suggested-dishes")
public class SuggestedDishController {
	public static final String getSuggestionDishByIdNote = "Get SuggestedDish records by SuggestedDishId otherwise Not Found Error";
	public static final String getSuggestionDishByRestaurentIdNote = "Get SuggestedDish records by RestaurentId otherwise Not Found Error";
	public static final String getSuggestionDishNote = "Get List of SuggestedDish records otherwise Not Found Error ";
	public static final String saveSuggestionDishNote = "Save SuggestedDish records in the repository";
	public static final String saveAllSuggestionDishNote = "Save List of SuggestedDish records in the repository at once";
	public static final String updateSuggestionDishNote = "Update SuggestedDish record in the repository";
	public static final String deleteSuggestionDishNote = "Delete SuggestedDish record from the repository for suggesteddishid";
	
	@Autowired
	SuggestedDishService service;
	
	@Autowired
	SuggestedDishRequestBuilder requestBuilder;
	
	@GetMapping(value = "/dishes/suggestion/{suggesteddishid}")
	@ApiOperation(value = "Get SuggestionDish record by ID")
	public ResponseEntity<SuccessResponse> getSuggestedDishById(@PathVariable Integer suggesteddishid) throws BussinessException {
		SuccessResponse response = service.getSuggestedDishById(suggesteddishid);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/dishes/suggestion/retaurentid/{restaurentid}")
	@ApiOperation(value = "Get SuggestionDish record by Restaurent ID",notes = getSuggestionDishByRestaurentIdNote)
	public ResponseEntity<SuccessResponse> getSuggestedDishByRestaurentId(@PathVariable(name = "restaurentid") Integer id) throws BussinessException {
		SuccessResponse response = service.getSuggestedDishByRestaurentId(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/dishes/suggestion")
	@ApiOperation(value = "Get SuggestionDish record List",notes = getSuggestionDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getSuggestedDishes(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		 SuccessResponse response = service.getSuggestedDishs(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/dishes/suggestion")
	@ApiOperation(value = "Save SuggestedDish record",notes = saveSuggestionDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveSuggestedDish(@Valid @RequestBody SuggestedDishRequest suggestedDish) throws BussinessException {
		SuggestedDish request = requestBuilder.buildRequest(suggestedDish);
		SuccessResponse response = service.saveSuggestedDish(request);
		SuggestedDish entity= (SuggestedDish)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/dishes/suggestion/all")
	@ApiOperation(value = "Save All SuggestedDish records",notes = saveAllSuggestionDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllSuggestedDish(@Valid @RequestBody List<SuggestedDishRequest> suggestedDishList) throws BussinessException {
		List<SuggestedDish> requestList = requestBuilder.buildRequest(suggestedDishList);
		SuccessResponse response = service.saveAllSuggestedDishs(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/dishes/suggestion")
	@ApiOperation(value = "Update SuggestedDish record",notes = updateSuggestionDishNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateSuggestedDish(@Valid @RequestBody SuggestedDish suggestionDish) throws BussinessException {
		SuccessResponse response = service.updateSuggestedDish(suggestionDish);
		SuggestedDish entity= (SuggestedDish)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/dishes/suggestion/{suggesteddishid}")
	@ApiOperation(value = "Delete SuggestedDish record by DishName id",notes = deleteSuggestionDishNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteSuggestedDish(@PathVariable Integer suggesteddishid) throws BussinessException {
		SuccessResponse response = service.deleteSuggestionDish(suggesteddishid);
		return ResponseEntity.ok(response);
	}
}
