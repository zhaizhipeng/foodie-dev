package com.ysdrzp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {

    // http://localhost:8088/doc.html

    // http://localhost:8088/swagger-ui.html

    /**
     * 接口文档版本号
     */
    private static final String VERSION = "1.0.0";

    /**
     * 基础包扫描
     */
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.ysdrzp.controller";

    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("天天吃货在线购物平台")
                .description("这是相关的接口文档 API")
                .contact(new Contact("ysdrzp","http://localhost:8088/doc.html", ""))
                .version(VERSION)
                .build();
    }

}
