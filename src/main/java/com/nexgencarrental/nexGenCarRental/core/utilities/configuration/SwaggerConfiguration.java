package com.nexgencarrental.nexGenCarRental.core.utilities.configuration;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.SecurityConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(SecurityConstants.BEARER_AUTH_SCHEME_NAME, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(SecurityConstants.BEARER_SCHEME)
                                .bearerFormat(SecurityConstants.BEARER_FORMAT)
                        )
                ).security(List.of(new SecurityRequirement().addList(SecurityConstants.BEARER_AUTH_SCHEME_NAME)));
    }
}
