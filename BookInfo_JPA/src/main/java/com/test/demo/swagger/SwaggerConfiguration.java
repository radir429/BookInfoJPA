package com.test.demo.swagger;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	public static final String BOOK_TAG = "Book Service Test";
	@Bean
	public Docket SwaggerApi(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes())
				.apiInfo(swaggerInfo())
				.groupName("TestGroupName")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.demo.controller"))
				.paths(PathSelectors.ant("/**"))				
				.build()				
				.useDefaultResponseMessages(false)
				.tags(new Tag(BOOK_TAG, "The Book API with Description api tag"));
	}
	
	private Set<String> getConsumeContentTypes(){
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}
	private Set<String> getProduceContentTypes(){
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}
	
	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder()
				.title("Test API Documentation")
				.description("Swagger UI 예제")
				.license("Tester")
				.licenseUrl("https://www.naver.com")
				.version("1.0")
				.build();
	}
}