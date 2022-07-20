package jp.co.canon.boilerplate.spring_boilerplate.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class openAPIConfig {
    @Value("${springdoc.title}")
    private String title;

    @Value("${springdoc.description}")
    private String description;

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        final String securitySchemeName = "bearerAuth";

        Info info = new Info().title(title).version(appVersion)
                .description(description);

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(info);
    }
}
