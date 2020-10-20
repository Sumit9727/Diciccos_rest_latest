package com.prakat.middleware.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.prakat.middleware.entity.DishName;

public interface DishNameElasticRepository extends ElasticsearchRepository<DishName, Integer>{
	public List<DishName> findByDishTypeId(int dishTypeId);
	public List<DishName> findByDishNameIdIn(List<Integer> dishNameIdList);
}
