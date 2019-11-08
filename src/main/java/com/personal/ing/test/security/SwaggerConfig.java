package com.personal.ing.test.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/v1/*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
	   @Bean
	   public Docket apiDocket() {
		   	log.debug("Starting Swagger");
	    		   return new Docket(DocumentationType.SWAGGER_2)   
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.personal.ing.test"))
	                .paths(PathSelectors.regex("/api/v1/.*"))
	                .build()
	                .apiInfo(apiInfo())
	                .securitySchemes(Arrays.asList(apiKey()))
	                .securityContexts(Collections.singletonList(securityContext()));
	   }
	   
	   private SecurityContext securityContext() {
	          return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/api/v1/.*")).build();
	        }

	      private List<SecurityReference> defaultAuth() {
	        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
	        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
	      }

	      private ApiKey apiKey() {
	        return new ApiKey("Bearer", "Authorization", "header");
	      } 
	    
	   private ApiInfo apiInfo() {
	       return new ApiInfo(
	         "Componente Backend para api Hacker News", 
	         "CRUD Basico de Articulos Hacker News, actualizado en la ultima hora     \n  ",
	         "API TOS", 
	         "Terms of service", 
	         new Contact("Jeans Navea", "jeanjn24@gmail.com", " "), 
	         "Make for inGERENCIA", "API license URL", Collections.emptyList());
	       
	   }
          
}
