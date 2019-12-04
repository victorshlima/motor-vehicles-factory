package com.motorcompany.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Victor Lima on 29/11/2019.
 */
    @Configuration
    @EnableSwagger2
class SwaggerDocConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry
                    .addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }


    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.motorcompany.config.resource.rest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("rest-vote")
                .description("")
                .version("1.0")
                .contact(new Contact("Dev", "-", "teste@teste.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }
}
