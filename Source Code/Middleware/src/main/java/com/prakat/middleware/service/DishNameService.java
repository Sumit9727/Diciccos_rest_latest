package com.prakat.middleware.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakat.middleware.elastic.query.DishNameQueryBuilder;
import com.prakat.middleware.elastic.repository.DishNameElasticRepository;
import com.prakat.middleware.entity.DishName;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DishNameRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class DishNameService {
	@Autowired
	DishNameRepository repository;
	@Autowired
	DishNameElasticRepository elasticRepository;
	@Autowired
	DishNameQueryBuilder queryBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;

	@Transactional
	@PostConstruct
	public void loadAllToElasticServer(){	
		List<DishName> dishNames = repository.findAll();
		elasticRepository.saveAll(dishNames);
	}
	public SuccessResponse searchDishName(String text,int pageNo ,int pageSize) throws BussinessException{
		Sort sort = Sort.by("dishNameId");
		PageRequest pageable =  PageRequest.of(pageNo, pageSize, sort);
		Page<DishName> dishNamePage = queryBuilder.search(text,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishNamePage);
		if(dishNamePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishName record not found", dishNamePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishName record found", dishNamePage.toList(),pagination);
		return response ;
	}

	public SuccessResponse getDishNameById(Integer id) throws BussinessException{
		Optional<DishName> dishNameOptional = repository.findById(id);
		SuccessResponse response;
		if(!dishNameOptional.isPresent()) {
			response = responseBuilder.buildResponse("DishName record not found", dishNameOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("DishName record found",dishNameOptional.get(),null);
		return response ;
	}

	public SuccessResponse getDishNames(int pageNo ,int pageSize) throws BussinessException{
		Sort sort = Sort.by("dishNameId");
		PageRequest pageable =  PageRequest.of(pageNo, pageSize,sort);
		Page<DishName> dishNamePage = repository.findAll(pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishNamePage);
		if(dishNamePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishName record not found", dishNamePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishName record found", dishNamePage.toList(),pagination);
		return response ;
	}

	public SuccessResponse saveDishName(DishName dishName) throws BussinessException{
		DishName entity = repository.save(dishName);
		elasticRepository.save(entity);
		if(entity==null) {
			throw new BussinessException("DishName not saved");
		}		
		SuccessResponse response = responseBuilder.buildResponse("DishName record saved",entity,null);
		return response ;
	}

	public SuccessResponse saveAllDishName(List<DishName> dishNameList) throws BussinessException{
		List<DishName> entities = repository.saveAll(dishNameList);
		elasticRepository.saveAll(entities);
		if(entities.isEmpty()) {
			throw new BussinessException("DishName not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishName record saved",entities,null);
		return response ;
	}

	public SuccessResponse updateDishName(DishName dishName) throws BussinessException{
		DishName entity = repository.save(dishName);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("DishName not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishName record updated",entity,null);
		return response ;
	}
	
	public SuccessResponse deleteDishName(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
			elasticRepository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("DishName not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishName record deleted",null,null);
		return response ;
	}

	public SuccessResponse getDishNameByDishTypeId(Integer id, int pageNo ,int PageSize) {
		Sort sort = Sort.by("dishNameId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<DishName> dishNamePage = repository.findByDishTypeId(id,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishNamePage);
		if(dishNamePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishName record not found", dishNamePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishName record found", dishNamePage.toList(),pagination);
		return response ;
	}
	private Pagination buildPagination(Page<DishName> dishNamePage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(dishNamePage.getNumberOfElements());
		pagination.setPageNumber(dishNamePage.getNumber());
		pagination.setLastPage(dishNamePage.isLast());
		pagination.setTotalPages(dishNamePage.getTotalPages());
		return pagination;
	}
}
