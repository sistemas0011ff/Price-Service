package com.ecommerce.priceservice.infrastructure;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    GroupedOpenApi publicApi() { 
        return GroupedOpenApi.builder() 
                .group("springshop-public")
                .pathsToMatch("/api/**")
                .build(); 
    }
}
