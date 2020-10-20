package com.prakat.middleware.elastic.query;

import com.prakat.middleware.responsebeans.SearchResponse;

public interface QueryBuilder {
	public void search(String text, SearchResponse searchResponse, int pageNo , int pageSize);
}
