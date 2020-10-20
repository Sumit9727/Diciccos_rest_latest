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

import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.DishExtraRequest;
import com.prakat.middleware.requestbuilder.DishExtraRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.service.DishExtraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
* @see DishExtraController
* @author  Amit Bhagat
* @version 1.0
*/
@RestController
@Api(tags = "dish-extras")
public class DishExtraController {	
	public static final String searchDishExtraNote = "Search DishExtra record by text maching to DishExtra Name otherwise Not Found Error";
	public static final String getDishExtraByIdNote = "Get DishExtra record by DishExtraid otherwise Not Found Error";
	public static final String getDishExtraByDishNameIdNote = "Get DishExtra record by DishNameId otherwise Not Found Error";
	public static final String getDishExtraByParentIdNote = "Get DishExtra record by ParentId otherwise Not Found Error";
	public static final String getDishExtrasNote = "Get List of DishExtra records otherwise Not Found Error ";
	public static final String saveDishExtraNote = "Save DishExtra recordin the repository";
	public static final String saveAllDishExtraNote = "Save List of DishExtra records in the repository at once";
	public static final String updateDishExtraNote = "Update DishExtra record in the repository";
	public static final String deleteDishExtraNote = "Delete DishExtra record from the repository for DishExtraId";
	
	@Autowired
	DishExtraService service;
	@Autowired
	DishExtraRequestBuilder requestBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	@GetMapping(value = "/dishes/extras/search/{text}")
	@ApiOperation(value = "Get DishExtra record", tags ="search", notes = searchDishExtraNote)
	public ResponseEntity<SuccessResponse> searchDishExtra(@PathVariable String text,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.searchDishExtra(text,pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/dishes/extras/{dishextraid}")
	@ApiOperation(value = "Get DishExtra record by ID",notes = getDishExtraByIdNote)
	public ResponseEntity<SuccessResponse> getDishExtraById(@PathVariable Integer dishextraid) throws BussinessException {
		SuccessResponse response = service.getDishExtraById(dishextraid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/extras/dishnameid/{dishnameid}")
	@ApiOperation(value = "Get DishExtra record list by DishName ID",notes = getDishExtraByDishNameIdNote)
	public ResponseEntity<SuccessResponse> getDishExtraByDishNameId(@PathVariable Integer dishnameid) throws BussinessException {
		SuccessResponse response = service.getDishExtraByDishNameId(dishnameid);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/dishes/extras/parentid/{parentid}")
	@ApiOperation(value = "Get DishExtra record list by parent ID" ,notes = getDishExtraByParentIdNote)
	public ResponseEntity<SuccessResponse> getDishExtraByParentId(@PathVariable Integer parentid) throws BussinessException {
		SuccessResponse response = service.getDishExtraByParentId(parentid);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value = "/dishes/extras")
	@ApiOperation(value = "Get DishExtra record List", notes = getDishExtrasNote)
	public ResponseEntity<SuccessResponse> getDishExtras(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getDishExtras(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/dishes/extras")
	@ApiOperation(value = "Save DishExtra record",notes = saveDishExtraNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveDishExtra(@Valid @RequestBody DishExtraRequest dishExtra) throws BussinessException {
		DishExtra request = requestBuilder.buildRequest(dishExtra);
		SuccessResponse response = service.saveDishExtra(request);
		DishExtra entity = (DishExtra)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishExtraId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	@PostMapping(value = "/dishes/extras/all")
	@ApiOperation(value = "Save DishExtra record",notes = saveAllDishExtraNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllDishExtra(@Valid @RequestBody List<DishExtraRequest> dishExtraList) throws BussinessException {
		List<DishExtra> requestList = requestBuilder.buildRequest(dishExtraList);
		SuccessResponse response = service.saveAllDishExtra(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/dishes/extras")
	@ApiOperation(value = "Update DishExtra record",notes = updateDishExtraNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateDishExtra(@Valid @RequestBody DishExtra dishExtra) throws BussinessException {
		SuccessResponse response = service.updateDishExtra(dishExtra);
		DishExtra entity = (DishExtra)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishExtraId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/dishes/extras/{dishextraid}")
	@ApiOperation(value = "Delete DishExtra record",notes = deleteDishExtraNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteDishExtra(@PathVariable Integer dishextraid) throws BussinessException {
		SuccessResponse response = service.deleteDishExtra(dishextraid);
		return ResponseEntity.ok(response);
	}
}
