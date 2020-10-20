package com.prakat.middleware.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.prakat.middleware.entity.DishExtra;

public interface DishExtraElasticRepository extends ElasticsearchRepository<DishExtra, Integer>{
	public List<DishExtra> findByDishNameId(int dishNameId);
	public List<DishExtra> findByParentId(int parentId);
}
