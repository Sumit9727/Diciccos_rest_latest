package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakat.middleware.entity.PaymentMode;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.PaymentModeRepository;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
/**
* @see PaymentModeService
* @author  Amit Bhagat
* @version 1.0
*/
@Service
public class PaymentModeService {
	@Autowired
	PaymentModeRepository repository;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	public SuccessResponse getPaymentModeById(Integer id) throws BussinessException{
		Optional<PaymentMode> paymentModeOptional = repository.findById(id);
		SuccessResponse response;
		if(!paymentModeOptional.isPresent()) {
			response = responseBuilder.buildResponse("PaymentMode record not found", paymentModeOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("PaymentMode record found", paymentModeOptional.get(),null);
		return response;
	}
	
	public SuccessResponse getPaymentModes() throws BussinessException{	
		List<PaymentMode> paymentModeList = repository.findAll();
		SuccessResponse response;
		if(paymentModeList.isEmpty()) {
			response = responseBuilder.buildResponse("PaymentMode record not found", paymentModeList,null);
		}
		else
			response = responseBuilder.buildResponse("PaymentMode record found", paymentModeList,null);
		return response;
	}
	
	public SuccessResponse savePaymentMode(PaymentMode paymentMode) throws BussinessException{
		PaymentMode entity = repository.save(paymentMode);
		if(entity == null) {
			throw new BussinessException("PaymentMode not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("PaymentMode record saved", entity,null);
		return response;
	}

	public SuccessResponse saveAllPaymentMode(List<PaymentMode> paymentModeList) throws BussinessException{
		 List<PaymentMode> entities = repository.saveAll(paymentModeList);
		if(entities.isEmpty()) {
			throw new BussinessException("PaymentMode not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("PaymentMode record saved", entities,null);
		return response;
	}

	public SuccessResponse updatePaymentMode(PaymentMode paymentMode) throws BussinessException{
		PaymentMode entity = repository.save(paymentMode);
		if(entity == null) {
			throw new BussinessException("PaymentMode not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("PaymentMode record updated", entity,null);
		return response;
	}
	
	public SuccessResponse deletePaymentMode(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("PaymentMode not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("PaymentMode record deleted", null,null);
		return response;
	}
}
