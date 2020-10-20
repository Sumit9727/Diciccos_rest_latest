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

import com.prakat.middleware.elastic.query.MultiSearchQueryBuilder;
import com.prakat.middleware.entity.MenuType;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.MenuTypeRequest;
import com.prakat.middleware.requestbuilder.MenuTypeRequestBuilder;
import com.prakat.middleware.responsebeans.SearchResponse;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.service.MenuTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
/**
 * @see MenuTypeController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "menu-types")
public class MenuTypeController {
	public static final String searchNote = "Search across MenuTypes, DishTypes , Dish Names and DishExtras";
	public static final String searchMenuTypeNote = "Get MenuType record by text maching to MenuType Name otherwise Not Found Error";
	public static final String getMenuTypeByIdNote = "Get MenuType record by MenuTypeId otherwise Not Found Error";
	public static final String getMenuTypeByRestaurentIdNote = "Get MenuType record by Restaurant Id otherwise Not Found Error";
	public static final String getMenuTypeByRestaurentIdAndServiceTypeNote = "Get MenuType record by Restaurant Id and Service Type otherwise Not Found Error";
	public static final String getMenuTypeNote = "Get List of MenuType records otherwise Not Found Error ";
	public static final String saveMenuTypeNote = "Save MenuType recordin the repository";
	public static final String saveAllMenuTypeNote = "Save List of MenuType records in the repository at once";
	public static final String updateMenuTypeNote = "Update MenuType record in the repository";
	public static final String deleteMenuTypeNote = "Delete MenuType record from the repository for MenuTypeId";
	@Autowired
	MenuTypeService service;
	@Autowired
	MenuTypeRequestBuilder requestBuilder;
	@Autowired
	MultiSearchQueryBuilder queryBuilder ;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	@GetMapping(value = "/search/{text}")
	@ApiOperation(value = "Search", tags ="search",  notes = searchNote)
	public ResponseEntity<SearchResponse> search(@PathVariable String text, @RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {	
		SearchResponse searchResponse = queryBuilder.search(text, pageNo, pageSize);
			return ResponseEntity.ok(searchResponse);
	}

	
	@GetMapping(value = "/menus/types/search/{text}")
	@ApiOperation(value = "Get MenuType record", tags ="search", notes = getMenuTypeByIdNote)
	public ResponseEntity<SuccessResponse> searchMenuType(@PathVariable String text,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.searchMenuType(text,pageNo,pageSize);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value = "/menus/types/{menutypeid}")
	@ApiOperation(value = "Get MenuType record by ID", notes = getMenuTypeByIdNote)
	public ResponseEntity<SuccessResponse> getMenuTypeById(@PathVariable Integer menutypeid) throws BussinessException {
		SuccessResponse response = service.getMenuTypeById(menutypeid);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/menus/types/restaurentid/{restaurentid}")
	@ApiOperation(value = "Get MenuType record list by Restaurent Type ID", notes=getMenuTypeByRestaurentIdNote)
	public ResponseEntity<SuccessResponse> getMenuTypeByRestaurentId(@PathVariable Integer restaurentid,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getMenuTypeByRestaurentId(restaurentid, pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/menus/types/restaurentid/{restaurentid}/servicetype/{servicetype}")
	@ApiOperation(value = "Get MenuType record list by Restaurent Type ID and Service Type", notes = getMenuTypeByRestaurentIdAndServiceTypeNote)
	public ResponseEntity<SuccessResponse> getMenuTypeByRestaurentId(@PathVariable Integer restaurentid, @PathVariable String servicetype,@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getMenuTypeByRestaurentIdAndServiceType(restaurentid, servicetype, pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/menus/types")
	@ApiOperation(value = "Get Menu Type record List", notes = getMenuTypeNote)
	public ResponseEntity<SuccessResponse> getMenuTypes(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getMenuTypes(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/menus/types")
	@ApiOperation(value = "Save Menu Type record",notes = saveMenuTypeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveMenu(@Valid @RequestBody MenuTypeRequest menuType) throws BussinessException {
		MenuType request = requestBuilder.buildRequest(menuType);
		SuccessResponse response = service.saveMenuType(request);
		MenuType entity = (MenuType)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getMenuTypeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PostMapping(value = "/menus/types/all")
	@ApiOperation(value = "Save All MenuType record", notes = saveAllMenuTypeNote , authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveAllMenuType(@Valid @RequestBody List<MenuTypeRequest> menuTypeList) throws BussinessException {
		List<MenuType> requestList = requestBuilder.buildRequest(menuTypeList);
		SuccessResponse response = service.saveAllMenuType(requestList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/menus/types")
	@ApiOperation(value = "Update Menu Type record",notes = updateMenuTypeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> updateMenuType(@ApiParam("MenuType record to be updated")@Valid @RequestBody MenuType menuType) throws BussinessException {
		SuccessResponse response = service.updateMenuType(menuType);
		MenuType entity = (MenuType)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getMenuTypeId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/menus/types/{menutypeid}")
	@ApiOperation(value = "Delete Menu Type record",notes = deleteMenuTypeNote, authorizations = {@Authorization(value = "jwtToken")})
	public ResponseEntity<SuccessResponse> deleteOrder(@PathVariable Integer menutypeid) throws BussinessException {
		SuccessResponse response = service.deleteMenuType(menutypeid);
		return ResponseEntity.ok(response);
	}
}
