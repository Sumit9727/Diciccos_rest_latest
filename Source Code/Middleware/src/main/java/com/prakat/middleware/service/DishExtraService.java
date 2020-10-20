package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.elastic.query.DishExtraQueryBuilder;
import com.prakat.middleware.elastic.repository.DishExtraElasticRepository;
import com.prakat.middleware.entity.DishExtra;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DishExtraRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
/**
 * @see DishExtraService
 * @author  Amit Bhagat
 * @version 1.0
 */
@Service
public class DishExtraService {
	@Autowired
	DishExtraRepository repository;
	@Autowired
	DishExtraElasticRepository elasticRepository;
	@Autowired
	DishExtraQueryBuilder queryBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	@Transactional
	@PostConstruct
	public void loadAllToElasticServer(){	
		List<DishExtra> dishExtras = repository.findAll();
		elasticRepository.saveAll(dishExtras);
	}
	public SuccessResponse searchDishExtra(String text,int pageNo ,int pageSize) throws BussinessException{
		Sort sort = Sort.by("dishExtraId");
		 PageRequest pageable =  PageRequest.of(pageNo, pageSize,sort);
		Page<DishExtra> dishExtraPage = queryBuilder.search(text,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishExtraPage);
		if(!dishExtraPage.hasContent()) {
			response = responseBuilder.buildResponse("DishExtra record not found", dishExtraPage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishExtra record found", dishExtraPage.toList(),pagination);
		return response;
	}
	
	public SuccessResponse getDishExtraById(Integer id) throws BussinessException{
		Optional<DishExtra> dishExtraOptional = repository.findById(id);
		SuccessResponse response;
		if(!dishExtraOptional.isPresent()) {
			response = responseBuilder.buildResponse("DishExtra record not found", dishExtraOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("DishExtra record found", dishExtraOptional.get(),null);
		return response;
	}

	public SuccessResponse getDishExtras(int pageNo ,int pageSize) throws BussinessException{
		Sort sort = Sort.by("dishExtraId");
		 PageRequest pageable =  PageRequest.of(pageNo, pageSize,sort);
		Page<DishExtra> dishExtraPage = repository.findAll(pageable);
		List<DishExtra> dishExtraList = dishExtraPage.get().collect(Collectors.toList());
		SuccessResponse response;
		Pagination pagination = buildPagination(dishExtraPage);
		if(dishExtraList.isEmpty()) {
			response = responseBuilder.buildResponse("DishExtra record not found", dishExtraList,null);
		}
		else
			response = responseBuilder.buildResponse("DishExtra record found", dishExtraList,pagination);
		return response;
	}
	
	public SuccessResponse saveDishExtra(DishExtra dishExtra) throws BussinessException{
		DishExtra entity = repository.save(dishExtra);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("DishExtra not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishExtra record saved", entity,null);
		return response ;
	}
	
	public SuccessResponse saveAllDishExtra(List<DishExtra> dishExtraList) throws BussinessException{
		List<DishExtra> entities = repository.saveAll(dishExtraList);
		elasticRepository.saveAll(entities);
		if(entities.isEmpty()) {	
			throw new BussinessException("DishExtra not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishExtra record saved", entities,null);
		return response ;
	}
	
	public SuccessResponse updateDishExtra(DishExtra dishExtra) throws BussinessException{
		DishExtra entity = repository.save(dishExtra);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("DishExtra not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishExtra record saved", entity,null);
		return response ;
	}
	
	public SuccessResponse deleteDishExtra(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
			elasticRepository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("DishExtra not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishExtra record deleted", null,null);
		return response ;
	}
	
	public SuccessResponse getDishExtraByDishNameId(Integer id) {
		List<DishExtra> dishExtraList = repository.findByDishNameId(id);
		SuccessResponse response;
		if(dishExtraList.isEmpty()) {
			response = responseBuilder.buildResponse("DishExtra record not found", dishExtraList,null);
		}
		else
			response = responseBuilder.buildResponse("DishExtra record found", dishExtraList,null);
		return response;
	}
	
	public SuccessResponse getDishExtraByParentId(Integer id) {
		List<DishExtra> dishExtraList = repository.findByParentId(id);
		SuccessResponse response;
		if(dishExtraList.isEmpty()) {
			response = responseBuilder.buildResponse("DishExtra record not found", dishExtraList,null);
		}
		else
			response = responseBuilder.buildResponse("DishExtra record found", dishExtraList,null);
		return response;
	}
	private Pagination buildPagination(Page<DishExtra> dishExtraPage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(dishExtraPage.getNumberOfElements());
		pagination.setPageNumber(dishExtraPage.getNumber());
		pagination.setLastPage(dishExtraPage.isLast());
		pagination.setTotalPages(dishExtraPage.getTotalPages());
		return pagination;
	}
}
