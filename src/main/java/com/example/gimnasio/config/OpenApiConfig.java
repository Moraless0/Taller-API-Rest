package com.example.gimnasio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gimnasioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gimnasio API")
                        .description("API REST para administrar clientes y rutinas de un gimnasio")
                        .version("1.0.0")
                        .contact(new Contact().name("Henry Morales"))
                );
    }
}
