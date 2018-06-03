/*
 * Copyright (c) 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * Swagger configuration for the project
 * @author Youans Ezzat
 */
@Configuration @EnableSwagger2 public class SwaggerConfig {
    @Autowired
    private ServletContext servletContext;
    @Value("${info.build.version}")
    private String version;
    @Bean public Docket swaggerPersonApi10() {

        return new Docket(DocumentationType.SWAGGER_2).pathProvider(new RelativePathProvider(servletContext) {

            @Override public String getApplicationBasePath() {
                return   servletContext.getContextPath()+"/documentation";
            }
        }).select().apis(RequestHandlerSelectors.basePackage("com.dotsub.fileuploader.rest")).build()
                .apiInfo(new ApiInfoBuilder().version(version).title("File Uploader API").build());
    }

}
