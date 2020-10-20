package com.prakat.middleware.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.prakat.middleware.entity.DishType;


public interface DishTypeElasticRepository extends ElasticsearchRepository<DishType, Integer>{
	public List<DishType> findByMenuTypeId(int menuTypeId);
}
