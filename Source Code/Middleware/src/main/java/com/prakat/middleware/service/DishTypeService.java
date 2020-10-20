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

import com.prakat.middleware.elastic.query.DishTypeQueryBuilder;
import com.prakat.middleware.elastic.repository.DishTypeElasticRepository;
import com.prakat.middleware.entity.DishType;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.DishTypeRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class DishTypeService {
	@Autowired
	DishTypeRepository repository;
	@Autowired
	DishTypeElasticRepository elasticRepository;
	@Autowired
	DishTypeQueryBuilder queryBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;

	@Transactional
	@PostConstruct
	public void loadAllToElasticServer(){	
		List<DishType> dishTypes = repository.findAll();
		elasticRepository.saveAll(dishTypes);
	}

	public SuccessResponse searchDishType(String text,int pageNo ,int pageSize) throws BussinessException{
		Sort sort = Sort.by("dishTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, pageSize,sort);
		Page<DishType> dishTypePage = queryBuilder.search(text,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishTypePage);
		if(dishTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishType record not found", dishTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishType record found", dishTypePage.toList(),pagination);
		return response ;
	}

	public SuccessResponse getDishTypeById(Integer id) throws BussinessException{
		Optional<DishType> dishTypeOptional = repository.findById(id);
		SuccessResponse response;
		if(!dishTypeOptional.isPresent()) {
			response = responseBuilder.buildResponse("DishType record not found", dishTypeOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("DishType record found", dishTypeOptional.get(),null);
		return response;
	}

	public SuccessResponse getDishTypes(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("dishTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<DishType> dishTypePage = repository.findAll(pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishTypePage);
		if(dishTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishType record not found", dishTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishType record found", dishTypePage.toList(),pagination);
		return response ;
	}

	public SuccessResponse saveDishType(DishType dishType) throws BussinessException{
		DishType entity = repository.save(dishType);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("DishType not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishType record saved", entity,null);
		return response ;
	}

	public SuccessResponse saveAllDishType(List<DishType> dishTypeList) throws BussinessException{
		List<DishType> entities = repository.saveAll(dishTypeList);
		elasticRepository.saveAll(entities);
		if(entities.isEmpty()) {
			throw new BussinessException("DishType not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishType record saved", entities,null);
		return response ;
	}

	public SuccessResponse updateDishType(DishType dishType) throws BussinessException{
		DishType entity = repository.save(dishType);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("DishType not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishType record saved", entity,null);
		return response ;
	}

	public SuccessResponse deleteDishType(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
			elasticRepository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("DishType not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("DishType record deleted", null,null);
		return response ;
	}

	public SuccessResponse getDishTypeByMenuTypeId(Integer id,int pageNo ,int PageSize) {
		Sort sort = Sort.by("dishTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<DishType> dishTypePage = repository.findByMenuTypeId(id,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(dishTypePage);
		if(dishTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("DishType record not found", dishTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("DishType record found", dishTypePage.toList(),pagination);
		return response ;
	}

	private Pagination buildPagination(Page<DishType> dishNamePage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(dishNamePage.getNumberOfElements());
		pagination.setPageNumber(dishNamePage.getNumber());
		pagination.setLastPage(dishNamePage.isLast());
		pagination.setTotalPages(dishNamePage.getTotalPages());
		return pagination;
	}
}
