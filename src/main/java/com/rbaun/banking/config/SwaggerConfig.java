package com.rbaun.banking.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private static final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI setupOpenAPI() {
        return new OpenAPI()
                .info(createApiInfo())
                .components(createAuthorizeComponent())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .addServersItem(createApplicationServer());
    }

    @Bean
    public GroupedOpenApi bankingApiV1Definition() {
        return GroupedOpenApi.builder()
                .group("Banking API v1")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authDefinition() {
        return GroupedOpenApi.builder()
                .group("Auth API")
                .pathsToMatch("/auth/**")
                .build();
    }

    private Info createApiInfo() {
        return new Info()
                .title("Banking API")
                .description("Use the Auth API definition to get a JWT token to access the Banking API. Use the Banking API definition to access the banking services.")
                .version("1.0");
    }

    private Components createAuthorizeComponent() {
        return new Components()
                .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }

    private Server createApplicationServer() {
        return new Server().url(contextPath);
    }
}
