package com.prakat.middleware.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.requestbeans.UserRequest;
import com.prakat.middleware.requestbuilder.UserRequestBuilder;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.service.DeliveryAddressService;
import com.prakat.middleware.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
/**
 * @see UserController
 * @author  Amit Bhagat
 * @version 1.0
 */
@RestController
@Api(tags = "users")
public class UserController {
	public static final String getUserByIdNote = "Get User record by UserId otherwise Not Found Error";
	public static final String getUserByMeNote = "Get User record of current logged user otherwise Not Found Error";
	public static final String getAddressByUserIdNote = "Get DeliveryAddress record by UserId otherwise Not Found Error";
	public static final String getUserByRestaurentIdNote = "Get User record by MenuTypeId otherwise Not Found Error";
	public static final String getUserNote = "Get List of User records otherwise Not Found Error ";
	public static final String saveUserNote = "Save User records in the repository";
	public static final String saveAllUserNote = "Save List of User records in the repository at once";
	public static final String updateUserNote = "Update User record in the repository";
	public static final String updateUserImgUrlNote = "Update Image url of User record in the repository";
	public static final String updateUserPasswordNote = "Update Password of User record in the repository";
	public static final String deleteUserNote = "Delete User record from the repository for userid";
	
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRequestBuilder requestBuilder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Autowired
	DeliveryAddressService addressService;
	
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	
	@GetMapping(value = "/users/{userid}")
	@ApiOperation(value = "Get User record by ID",notes = getUserByIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getuserById(@PathVariable Integer userid) throws BussinessException {
		SuccessResponse response = service.getUserById(userid);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/users/me")
	@ApiOperation(value = "Get Current Logged User record",notes = getUserByMeNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getuser() throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = service.getUserById(userId);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/users")
	@ApiOperation(value = "Get User record List", notes = getUserNote,authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getOrders(@RequestParam int pageNo , @RequestParam int pageSize) throws BussinessException {
		SuccessResponse response = service.getUsers(pageNo, pageSize);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/users/addresses")
	@ApiOperation(value = "Get DeliveryAddress record List", notes = getAddressByUserIdNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> getDeliveryAddresses() throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = addressService.getDeliveryAddressesByUserId(userId);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value = "/users")
	@ApiOperation(value = "Save User record",notes = updateUserNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> saveUser(@Valid @RequestBody UserRequest user) throws BussinessException {
		User request = requestBuilder.buildRequest(user);
		String encryptedPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encryptedPassword);
		SuccessResponse response = service.saveUser(request);
		User entity = (User)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	// @PutMapping(value = "/users")
	// @ApiOperation(value = "Edit User record",notes = updateUserNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> editUser(@Valid @RequestBody User user) throws BussinessException {	
		SuccessResponse response = service.updateUser(user);
		User entity = (User)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/users/imageurl/{imageurl}")
	@ApiOperation(value = "Edit Image Url of User record",notes = updateUserImgUrlNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> editUserImageUrl(@PathVariable(required = true) String imageurl) throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = service.editUserImageUrl(userId, imageurl);
		User entity = (User)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@PutMapping(value = "/users/password/{password}")
	@ApiOperation(value = "Edit Password of User record",notes = updateUserPasswordNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> editUserPassword(@PathVariable(required = true) String password) throws BussinessException {
		Integer userId	= userService.getUserId();
		SuccessResponse response = service.editUserPassword(userId, passwordEncoder.encode(password));
		User entity = (User)response.getData();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();	
		return ResponseEntity.created(location).body(response);
	}
	
	
	@DeleteMapping(value = "/users/{userid}")
	@ApiOperation(value = "Delete User record",notes = deleteUserNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable Integer userid) throws BussinessException {
		SuccessResponse response = service.deleteUser(userid);
		return ResponseEntity.ok(response);
	}
}
