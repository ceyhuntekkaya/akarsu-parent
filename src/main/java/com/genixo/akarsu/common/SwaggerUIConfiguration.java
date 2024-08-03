package com.genixo.akarsu.common;

import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

public class SwaggerUIConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis((Predicate<RequestHandler>) RequestHandlerSelectors.any())
                .paths((Predicate<String>) PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ILC Center Web-API")
                .description("This API provides the endpoints of the ILC Center application")
                .version("1.0.0")
                .termsOfServiceUrl("http://localhost:8080/terms-of-agreement")
                .contact(
                        new Contact(
                                "Ceyhun TEKKAYA",
                                "https://www.ceyhuntekkaya.com/about",
                                "ceyhun.tekkaya@gmail.com"
                        )
                )
                .license("Apache Licence Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
