package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static String PACKAGE = "com.spring";

	@Bean
	public Docket api() {
		String title = "spring_base_project_1";
		String description = "Spring Maven 5.0.7";
		String version = "1.0";

		ApiInfo apiInfo = new ApiInfoBuilder()
				.title(title)
				.description(description)
				.version(version)
				.build();

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(PACKAGE))
				.paths(Predicates.not(PathSelectors.regex("/docs/index.html")))
				.build()
				.apiInfo(apiInfo);
	}
}
