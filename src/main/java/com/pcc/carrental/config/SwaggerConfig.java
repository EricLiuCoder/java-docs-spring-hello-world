package com.pcc.carrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pcc.carrental.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Yifan Liu", "", "yifanliu@hotmail.com");
        return new ApiInfo(
                "CarRental API",
                "CarRental 接口文档",
                "v1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
