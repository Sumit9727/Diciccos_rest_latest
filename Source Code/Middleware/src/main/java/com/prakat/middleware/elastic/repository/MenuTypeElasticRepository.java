package com.prakat.middleware.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.prakat.middleware.entity.MenuType;

public interface MenuTypeElasticRepository extends ElasticsearchRepository<MenuType, Integer>{
	public List<MenuType> findByRestaurantId(int restaurantId);
}
