package com.prakat.middleware.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	private static final Contact DEFAULT_CONTACT = new Contact("Prakat Solutions", "https://prakat.com/", "abc@prakat.in");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Middleware API",
			"Client for Toast API", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", 
			"http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json"));
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
	
	  @Bean public Docket api() {
	  
	  Docket docket = new Docket(DocumentationType.SWAGGER_2)
	  .groupName("middleware-api") .tags(new
	  Tag("authentication","Authentication related"), new
	  Tag("dish-extras","Dish Extra related"), new
	  Tag("search","Search related"), new
	  Tag("dish-names","Dish Name related"), new
	  Tag("dish-types","Dish Type related"), new
	  Tag("menu-types","Menu Type related"), new 
	  Tag("orders","Order related"), new
	  Tag("restaurants","Restaurant related"), new
	  Tag("popular-dishes","Popular Dish related"), new
	  Tag("payment-modes","Payment Mode related"), new
	  Tag("suggested-dishes","Suggested Dishes related"), new
	  Tag("delivery-addresses","Delivery Address related"), new
	  Tag("users","User related")) .apiInfo(DEFAULT_API_INFO)
	  .securitySchemes(Arrays.asList(apiKey()))
	  .securityContexts(Arrays.asList(securityContext()))
	  .produces(DEFAULT_PRODUCES_AND_CONSUMES)
	  .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
	  .select().apis(RequestHandlerSelectors.basePackage("com.prakat.middleware")).
	  build(); return docket;
	  
	  } private ApiKey apiKey() { return new ApiKey("jwtToken",
	  AUTHORIZATION_HEADER, "header"); }
	  
	  private SecurityContext securityContext() { return SecurityContext.builder()
	  .securityReferences(defaultAuth())
	  .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)) .build(); }
	  
	  List<SecurityReference> defaultAuth() { AuthorizationScope authorizationScope
	  = new AuthorizationScope("global", "accessEverything"); AuthorizationScope[]
	  authorizationScopes = new AuthorizationScope[1]; authorizationScopes[0] =
	  authorizationScope; return Arrays.asList(new SecurityReference("JWT",
	  authorizationScopes)); }
	 
}
