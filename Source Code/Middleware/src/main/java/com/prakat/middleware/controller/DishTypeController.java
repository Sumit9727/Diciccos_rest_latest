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

import com.prakat.middleware.entity.DishType;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.DishTypeRequest;
import com.prakat.middleware.requestbuilder.DishTypeRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.service.DishTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
 * @see DishTypeController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "dish-types")
public class DishTypeController {
	public static final String searchDishTypeNote = "Search DishType record by text maching to DishType Name otherwise Not Found Error";
	public static final String getDishTypeByIdNote = "Get DishType record by DishTypeId otherwise Not Found Error";
	public static final String getDishTypeByMenuTypeIdNote = "Get DishType record by MenuTypeId otherwise Not Found Error";
	public static final String getDishTypeNote = "Get List of DishType record otherwise Not Found Error ";
	public static final String saveDishTypeNote = "Save DishType records in the repository";
	public static final String saveAllDishTypeNote = "Save List of DishType records in the repository at once";
	public static final String updateDishTypeNote = "Update DishType record in the repository";
	public static final String deleteDishTypeNote = "Delete DishType record from the repository for dishtypeid";

	@Autowired
	DishTypeService service;
	
	@Autowired
	DishTypeRequestBuilder requestBuilder;
	
	@Autowired
	SuccessResponseBuilder responseBuilder ;

	
	@GetMapping(value = "/dishes/types/search/{text}")
	@ApiOperation(value = "Search DishType record", tags ="search",notes = searchDishTypeNote)
	public ResponseEntity<SuccessResponse> searchDishType(@PathVariable String text,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.searchDishType(text,pageNo,pageSize);	
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/types/{dishtypeid}")
	@ApiOperation(value = "Get DishType record by ID",notes = getDishTypeByIdNote)
	public ResponseEntity<SuccessResponse> getDishTypeById(@PathVariable Integer dishtypeid) throws BussinessException {
		SuccessResponse response = service.getDishTypeById(dishtypeid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/types/menutypeid/{menutypeid}")
	@ApiOperation(value = "Get DishType record list by MenuType ID",notes = getDishTypeByMenuTypeIdNote)
	public ResponseEntity<SuccessResponse> getDishTypeByMenuTypeId(@PathVariable Integer menutypeid,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getDishTypeByMenuTypeId(menutypeid,pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/dishes/types")
	@ApiOperation(value = "Get DishType record List",notes = getDishTypeNote)
	public ResponseEntity<SuccessResponse> getDishTypes(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getDishTypes(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/dishes/types")
	@ApiOperation(value = "Save DishType record", notes = saveDishTypeNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveMenu(@Valid @RequestBody DishTypeRequest dishType) throws BussinessException {
		DishType request = requestBuilder.buildRequest(dishType);
		SuccessResponse response = service.saveDishType(request);
		DishType entity = (DishType)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishTypeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/dishes/types/all")
	@ApiOperation(value = "Save All DishType record",notes = saveAllDishTypeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllDishType(@Valid @RequestBody List<DishTypeRequest> dishTypeList) throws BussinessException {
		List<DishType> requestList = requestBuilder.buildRequest(dishTypeList);
		SuccessResponse response = service.saveAllDishType(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/dishes/types")
	@ApiOperation(value = "Update DishType record",notes = updateDishTypeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateDishType(@Valid @RequestBody DishType dishType) throws BussinessException {
		SuccessResponse response = service.updateDishType(dishType);
		DishType entity = (DishType)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getDishTypeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/dishes/types/{dishtypeid}")
	@ApiOperation(value = "Delete DishType record",notes =  deleteDishTypeNote,authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteDishType(@PathVariable Integer dishtypeid) throws BussinessException {
		SuccessResponse response = service.deleteDishType(dishtypeid);
		return ResponseEntity.ok(response);
	}
}
