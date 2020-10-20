package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.DeliveryAddress;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DeliveryAddressRepository;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class DeliveryAddressService {
	@Autowired
	DeliveryAddressRepository repository;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	public SuccessResponse getDeliveryAddressById(Integer id) throws BussinessException{
		Optional<DeliveryAddress> deliveryAddressOptional = repository.findById(id);
		SuccessResponse response;
		if(!deliveryAddressOptional.isPresent()) {
			response = responseBuilder.buildResponse("DeliveryAddress record not found", deliveryAddressOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("DeliveryAddress record found", deliveryAddressOptional.get(),null);
		return response ;
	}

	public SuccessResponse getDeliveryAddressesByUserId(Integer userId) throws BussinessException{
		List<DeliveryAddress> deliveryAddressList = repository.findByUserId(userId);
		SuccessResponse response;
		if(deliveryAddressList.isEmpty()) {
			response = responseBuilder.buildResponse("DeliveryAddress record not found", deliveryAddressList,null);
		}
		else
			response = responseBuilder.buildResponse("DeliveryAddress record found", deliveryAddressList,null);
		return response ;
	}

	public SuccessResponse getDeliveryAddresses() throws BussinessException{
		List<DeliveryAddress> deliveryAddressList = repository.findAll();
		SuccessResponse response;
		if(deliveryAddressList.isEmpty()) {
			response = responseBuilder.buildResponse("DeliveryAddress record not found", deliveryAddressList,null);
		}
		else
			response = responseBuilder.buildResponse("DeliveryAddress record found", deliveryAddressList,null);
		return response ;
	}
	public SuccessResponse saveDeliveryAddress(DeliveryAddress deliveryAddress) throws BussinessException{
		DeliveryAddress entity = repository.save(deliveryAddress);
		if(entity == null) {
			throw new BussinessException("DeliveryAddress not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DeliveryAddress record created", entity,null);
		return response ;
	}

	public SuccessResponse saveAllDeliveryAddress(List<DeliveryAddress> deliveryAddresses) throws BussinessException{
		List<DeliveryAddress> entities = repository.saveAll(deliveryAddresses);
		SuccessResponse response = responseBuilder.buildResponse("DeliveryAddress record created", entities,null);
		return response ;
	}

	public SuccessResponse updateDeliveryAddress(DeliveryAddress deliveryAddress) throws BussinessException{
		DeliveryAddress entity = repository.save(deliveryAddress);
		if(entity == null) {
			throw new BussinessException("DeliveryAddress not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DeliveryAddress record updated", entity,null);
		return response ;
	}
	public SuccessResponse deleteDeliveryAddress(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("DeliveryAddress not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("DeliveryAddress record deleted", null,null);
		return response ;
	}

}
