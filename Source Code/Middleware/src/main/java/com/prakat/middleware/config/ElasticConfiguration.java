package com.prakat.middleware.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.prakat.middleware.repository")
@EnableElasticsearchRepositories(basePackages = "com.prakat.middleware.elastic.repository")
public class ElasticConfiguration {

	@Value("${elastic.search.location}")
	 private String EALASTIC_SERVER_LOCATION ;
	  @Bean
	    public RestHighLevelClient client() {
	        ClientConfiguration clientConfiguration 
	            = ClientConfiguration.builder()
	                .connectedTo(EALASTIC_SERVER_LOCATION)
	                .build();
	 
	        return RestClients.create(clientConfiguration).rest();
	    }
	 
	    @Bean
	    public ElasticsearchOperations elasticsearchTemplate() {
	        return new ElasticsearchRestTemplate(client());
	    }
}
