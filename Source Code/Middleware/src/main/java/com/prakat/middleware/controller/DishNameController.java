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

import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.DishNameRequest;
import com.prakat.middleware.requestbuilder.DishNameRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.DishNameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
* @see DishNameController
* @author  Amit Bhagat
* @version 1.0
*/
@RestController
@Api(tags = "dish-names")
public class DishNameController {
	public static final String searchDishNameNote = "Search DishName record by text maching to Dish Name otherwise Not Found Error";
	public static final String getDishNameByIdNote = "Get DishName record by DishNameId otherwise Not Found Error";
	public static final String getDishNameByDishTypeIdNote = "Get DishName record by DishTypeId otherwise Not Found Error";
	public static final String getDishNameNote = "Get List of DishName records otherwise Not Found Error ";
	public static final String saveDishNameNote = "Save DishName recordin the repository";
	public static final String saveAllDishNameNote = "Save List of DishName records in the repository at once";
	public static final String updateDishNameNote = "Update DishName record in the repository";
	public static final String deleteDishNameNote = "Delete DishName record from the repository for DishNameId";
	
	@Autowired
	DishNameService service;
	
	@Autowired
	DishNameRequestBuilder requestBuilder;
	
	
	@GetMapping(value = "/dishes/names/search/{text}")
	@ApiOperation(value = "Search DishName record", tags ="search", notes = searchDishNameNote)
	public ResponseEntity<SuccessResponse> searchDishName(@PathVariable String text, @RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.searchDishName(text , pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/names/{dishnameid}")
	@ApiOperation(value = "Get DishName record by ID", notes = getDishNameByIdNote)
	public ResponseEntity<SuccessResponse> getDishNameById(@PathVariable Integer dishnameid) throws BussinessException {
		SuccessResponse response = service.getDishNameById(dishnameid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/names/dishtypeid/{dishtypeid}")
	@ApiOperation(value = "Get DishName record list by DishType ID", notes = getDishNameByDishTypeIdNote)
	public ResponseEntity<SuccessResponse> getDishNameByDishTypeId(@PathVariable Integer dishtypeid, @RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getDishNameByDishTypeId(dishtypeid, pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/dishes/names")
	@ApiOperation(value = "Get DishName record List",notes = getDishNameNote)
	public ResponseEntity<SuccessResponse> getDishNames(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getDishNames(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/dishes/names")
	@ApiOperation(value = "Save DishName record", notes = saveDishNameNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveDishName(@Valid @RequestBody DishNameRequest dishName) throws BussinessException {
		DishName request = requestBuilder.buildRequest(dishName);
		SuccessResponse response = service.saveDishName(request);
		DishName entity = (DishName)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/dishes/names/all")
	@ApiOperation(value = "Save All DishName record",notes = saveAllDishNameNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllDishName(@Valid @RequestBody List<DishNameRequest> dishNameList) throws BussinessException {
		List<DishName> requestList = requestBuilder.buildRequest(dishNameList);
		SuccessResponse response = service.saveAllDishName(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/dishes/names")
	@ApiOperation(value = "Update DishName record",notes = updateDishNameNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateDishName(@Valid @RequestBody DishName dishName) throws BussinessException {
		SuccessResponse response = service.updateDishName(dishName);
		DishName entity = (DishName)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishNameId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/dishes/names/{dishnameid}")
	@ApiOperation(value = "Delete DishName record",notes = deleteDishNameNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteDishName(@PathVariable(name = "dishnameid") Integer dishnameid) throws BussinessException {
		SuccessResponse response = service.deleteDishName(dishnameid);
		return ResponseEntity.ok(response);
	}
}
