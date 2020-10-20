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

import com.prakat.middleware.elastic.query.MenuTypeQueryBuilder;
import com.prakat.middleware.elastic.repository.MenuTypeElasticRepository;
import com.prakat.middleware.entity.MenuType;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.MenuTypeRepository;
import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;

@Service
public class MenuTypeService {
	@Autowired
	MenuTypeRepository repository;
	@Autowired
	MenuTypeElasticRepository elasticRepository;
	@Autowired
	MenuTypeQueryBuilder queryBuilder;
	@Autowired
	SuccessResponseBuilder responseBuilder ;
	
	@Transactional
	@PostConstruct
	public void loadAllToElasticServer(){	
		List<MenuType> menuTypes = repository.findAll();
		elasticRepository.saveAll(menuTypes);
	}

	public SuccessResponse searchMenuType(String text,int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("menuTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<MenuType> menuTypePage = queryBuilder.search(text,pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(menuTypePage);
		if(menuTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("MenuType record not found", menuTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("MenuType record found", menuTypePage.toList(),pagination);
		return response;
	}
	
	public SuccessResponse getMenuTypeById(Integer id) throws BussinessException{
		Optional<MenuType> menuTypeOptional = repository.findById(id);
		SuccessResponse response;
		if(!menuTypeOptional.isPresent()) {
			response = responseBuilder.buildResponse("MenuType record not found", menuTypeOptional.get(),null);
		}
		else
			response = responseBuilder.buildResponse("MenuType record found", menuTypeOptional.get(),null);
		return response;
	}
	
	public SuccessResponse getMenuTypes(int pageNo ,int PageSize) throws BussinessException{
		Sort sort = Sort.by("menuTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<MenuType> menuTypePage = repository.findAll(pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(menuTypePage);
		if(menuTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("MenuType record not found", menuTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("MenuType record found", menuTypePage.toList(),pagination);
		return response;
	}
	
	public SuccessResponse saveMenuType(MenuType menuType) throws BussinessException{
		MenuType entity = repository.save(menuType);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("MenuType not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("MenuType record saved", entity,null);
		return response;
	}
	
	public SuccessResponse saveAllMenuType(List<MenuType> menuTypeList) throws BussinessException{
		List<MenuType> entities = repository.saveAll(menuTypeList);
		elasticRepository.saveAll(entities);
		if(entities.isEmpty()) {
			throw new BussinessException("MenuType not saved");
		}
		SuccessResponse response = responseBuilder.buildResponse("MenuType record saved", entities,null);
		return response;
	}
	
	public SuccessResponse updateMenuType(MenuType menuType) throws BussinessException{
		MenuType entity = repository.save(menuType);
		elasticRepository.save(entity);
		if(entity == null) {
			throw new BussinessException("MenuType not updated");
		}
		SuccessResponse response = responseBuilder.buildResponse("MenuType record updated", entity,null);
		return response;
	}
	
	public SuccessResponse deleteMenuType(Integer id) throws BussinessException {
		try { 
			repository.deleteById(id);
			elasticRepository.deleteById(id);
		}
		catch(Exception ex){
			throw new BussinessException("MenuType not deleted");
		}
		SuccessResponse response = responseBuilder.buildResponse("MenuType record deleted", null,null);
		return response;
	}

	public SuccessResponse getMenuTypeByRestaurentId(Integer id, int pageNo ,int PageSize) {
		Sort sort = Sort.by("menuTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<MenuType> menuTypePage = repository.findByRestaurantId(id, pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(menuTypePage);
		if(menuTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("MenuType record not found", menuTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("MenuType record found", menuTypePage.toList(),pagination);
		return response;
	}
	public SuccessResponse getMenuTypeByRestaurentIdAndServiceType(Integer restaurantId, String serviceType, int pageNo ,int PageSize) {
		Sort sort = Sort.by("menuTypeId");
		PageRequest pageable =  PageRequest.of(pageNo, PageSize,sort);
		Page<MenuType> menuTypePage = repository.findByRestaurantIdAndServiceTypeIgnoreCase(restaurantId, serviceType, pageable);
		SuccessResponse response;
		Pagination pagination = buildPagination(menuTypePage);
		if(menuTypePage.isEmpty()) {
			response = responseBuilder.buildResponse("MenuType record not found", menuTypePage.toList(),null);
		}
		else
			response = responseBuilder.buildResponse("MenuType record found", menuTypePage.toList(),pagination);
		return response;
	}
	private Pagination buildPagination(Page<MenuType> menuTypePage) {
		Pagination pagination = new Pagination();
		pagination.setPageSize(menuTypePage.getNumberOfElements());
		pagination.setPageNumber(menuTypePage.getNumber());
		pagination.setLastPage(menuTypePage.isLast());
		pagination.setTotalPages(menuTypePage.getTotalPages());
		return pagination;
	}
}
