package com.redmount.template.configurer;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 *
 * @author 朱峰
 * @date 2018年11月12日
 */
@Configuration
@EnableSwagger2
public class Swagger2Configurer {
    /**
     * 当前的运行模式
     */
    @Value("${spring.profiles.active}")
    private String env;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable("dev".equals(env))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springboot利用Swagger构建API文档")
                .description("简单优雅的RESTFul风格，http://github.com/redmount56/SSM-Template")
                .termsOfServiceUrl("http://github.com/redmount56/SSM-Template")
                .version("1.0")
                .build();
    }
}
