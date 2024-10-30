package com.ufril.medtran.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Rest API documentation configuration
 *
 * @author sazzad
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket ufrilApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hajj-app-api")
                .select()
                .paths(paths())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Hajj App API")
                .description("demo description")
                .termsOfServiceUrl("http://ufril.com/terms-of-service")
                .version("v1")
                .license("License")
                .licenseUrl("http://ufril.com/lincenses")
                .build();
    }

    private Predicate<String> paths() {
        return or(regex("/v1/*.*"));
    }
}
