package com.oconde.reactivo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class SwaggerConfig {

    @Bean
    @Profile("!prod")
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Reactiva con WebFlux")
                        .version("1.0.0")
                        .description("Documentación interactiva de endpoints reactivos"));
    }
}
