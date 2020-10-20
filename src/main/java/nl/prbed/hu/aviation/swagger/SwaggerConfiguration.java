package nl.prbed.hu.aviation.swagger;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Bean
    public Docket swaggerSpringfoxDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("nl.prbed.hu.aviation"))
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Aviation Documentation")
                .description("Aviation is a project for the Hogeschool Utrecht")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }
}
