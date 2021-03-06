package com.course.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket api(){
     return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
             .pathMapping("/")
             .select()
             .paths(PathSelectors.regex("/.*"))
             .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("usermanage service Api ")
                .contact(new Contact("DAMIAO","","123"))
                .description("this is usermanage API")
                .version("1.0")
                .build();
    }
}
