package com.prakat.middleware.elastic.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.prakat.middleware.responsebeans.SearchResponse;

@Component
public class MultiSearchQueryBuilder {
	@Autowired
	ElasticsearchOperations elasticsearchTemplate;

	@Autowired
	DishExtraQueryBuilder dishExtraQuery;
	@Autowired
	DishNameQueryBuilder dishNameQuery;
	@Autowired
	DishTypeQueryBuilder dishTypeQuery;
	@Autowired
	MenuTypeQueryBuilder menuTypeQuery;

	public SearchResponse search(String text, int pageNo , int pageSize ){
		SearchResponse searchResponse = new SearchResponse();
		List<QueryBuilder> queryBuilders = Arrays.asList(dishExtraQuery ,dishNameQuery,dishTypeQuery, menuTypeQuery);
		queryBuilders.parallelStream().forEach(queryBuilder -> queryBuilder.search(text, searchResponse,pageNo,pageSize ));
		return searchResponse;
	}
}
