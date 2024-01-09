package com.jojoldu.book.springboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                title = "jojoldu book 복습 프로젝트",
                description = "조졸두 책 복습에 사용되는 API 명세서 입니다.",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {
    //private static final String BEARER_TOKEN_PREFIX = "Bearer";



}
