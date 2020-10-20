package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.UserRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebeans.UserResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.responsebuilder.UserResponseBuilder;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	@Autowired
	UserResponseBuilder orderResponseBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;

	public SuccessResponse getUserById(Integer id) throws BussinessException{
		Optional<User> userOptional = repository.findById(id);
		Optional<UserResponse> responseOptional = null ;
		SuccessResponse response;
		if(!userOptional.isPresent()) {
			response = responseBuilder.buildResponse("User record not found", userOptional.get(),null);
		}
		else {
			responseOptional = orderResponseBuilder.buildResponse(userOptional.get());
			response = responseBuilder.buildResponse("User record found", responseOptional,null);
		}
			return response;
	}

	public SuccessResponse getUsers(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("id");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<User> userPage = repository.findAll(pageable);
		List<User> userList = userPage.get().collect(Collectors.toList());
		List<UserResponse> responseList = orderResponseBuilder.buildResponse(userList);		
		SuccessResponse response;
		Pagination pagination = buildPagination(userPage);
		if(responseList.isEmpty()) {
			response = responseBuilder.buildResponse("User record not found", responseList,null);
		}
		else {
			response = responseBuilder.buildResponse("User record found", responseList,pagination);
		}
			return response;
	}
	
	public SuccessResponse saveUser(User user) throws BussinessException{
		User entity = repository.save(user);
		if(entity == null) {
			throw new BussinessException("User not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("User record saved", entity,null);
		return response ;
	}

	public SuccessResponse updateUser(User user) throws BussinessException{
		User entity = repository.save(user);
		if(entity == null) {
			throw new BussinessException("User not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("User record updated", entity,null);
		return response ;
	}
	public SuccessResponse editUserImageUrl(Integer userId, String imageUrl) throws BussinessException{
		User user = repository.findById(userId).orElseThrow(()->new BussinessException("User not present"));
		user.setImageUrl(imageUrl);
		User entity = repository.save(user);
		if(entity == null) {
			throw new BussinessException("Image url is not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("Image url record updated", entity,null);
		return response ;
	}
	public SuccessResponse editUserPassword(Integer userId, String password) throws BussinessException{
		User user = repository.findById(userId).orElseThrow(()->new BussinessException("User not present"));
		user.setPassword(password);
		User entity = repository.save(user);
		if(entity == null) {
			throw new BussinessException("Password is not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("User record saved", entity,null);
		return response ;
	}
	public SuccessResponse deleteUser(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("User not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("User record deleted", null,null);
		return response ;
	}
	private Pagination buildPagination(Page<User> userPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(userPage.getNumberOfElements());
		pagination.setPageNumber(userPage.getNumber());
		pagination.setLastPage(userPage.isLast());
		pagination.setTotalPages(userPage.getTotalPages());
		return pagination;
	}
}
