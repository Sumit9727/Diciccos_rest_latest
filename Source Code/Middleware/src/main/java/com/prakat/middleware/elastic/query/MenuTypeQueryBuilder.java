package com.prakat.middleware.elastic.query;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.MenuType;
import com.prakat.middleware.responsebeans.SearchResponse;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.service.MenuTypeService;

@Component
public class MenuTypeQueryBuilder implements QueryBuilder{
	@Autowired
	ElasticsearchOperations elasticsearchTemplate;
	@Autowired
	MenuTypeService service;
	@Override
	public void search(String text, SearchResponse searchResponse,int pageNo , int pageSize ){
		SuccessResponse menuTypes = service.searchMenuType(text, pageNo, pageSize);
		searchResponse.setMenuTypes(menuTypes);
	}

	public Page<MenuType> search(String text, Pageable pageable){
		MatchQueryBuilder query = QueryBuilders.matchQuery("menuName", text)
				.operator(Operator.AND)
				.fuzziness(Fuzziness.ONE)
				.prefixLength(3);
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(query)
				.withPageable(pageable)
				.build();
		List<Query> queryList = new ArrayList<>();
		queryList.add(searchQuery);
		IndexCoordinates index = elasticsearchTemplate.getIndexCoordinatesFor(MenuType.class);
		List<Page<MenuType>> pages = elasticsearchTemplate.queryForPage(queryList, MenuType.class, index);
		return pages.get(0);
	}
}
