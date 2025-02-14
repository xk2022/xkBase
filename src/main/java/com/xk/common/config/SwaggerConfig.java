package com.xk.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // com.xk.exapmleFolder API 分组
    @Bean
    public GroupedOpenApi exampleApi() {
        return GroupedOpenApi.builder()
                .group("example") // Swagger UI 中的分组名称
                .pathsToMatch("/api/example/**") 
                .build();
    }
    
    // All com.xk API 分组
    @Bean
    public GroupedOpenApi api() {
    	return GroupedOpenApi.builder()
    			.group("all") // Swagger UI 中的分组名称
    			.pathsToMatch("/api/**") // 匹配 /admin/ 开头的 API
    			.build();
    }

    // com.xk.upms API 分组
    @Bean
    public GroupedOpenApi upmsApi() {
        return GroupedOpenApi.builder()
                .group("upms") // 另一个 Swagger UI 分组
                .pathsToMatch("/api/upms/**") // 匹配 /upms/ 开头的 API
                .build();
    }
    
}
