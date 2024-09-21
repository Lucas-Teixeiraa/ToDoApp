package br.com.luk.toDoApp.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig : WebMvcConfigurer {

    @Configuration
    class SwaggerConfig : WebMvcConfigurer {

        @Bean
        fun customOpenAPI(): OpenAPI {
            return OpenAPI().apply {
                info(
                    Info().title("Todo List API").version("v1")
                )
            }
        }

        @Bean
        fun groupOpenApi(): GroupedOpenApi {
            return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build()
        }
    }
}