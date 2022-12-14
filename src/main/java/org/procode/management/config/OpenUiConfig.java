package org.procode.management.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenUiConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(
                    new Components()
                            .addSecuritySchemes(
                                    "bearer-key",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
            .info(new Info().title("Employee Management API").description("EM services documentation."));
            //.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
  }
}
