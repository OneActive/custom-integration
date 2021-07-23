package com.activeai.integration.banking.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class Application.
 *
 * @author Manjunath H
 * @version 1.0
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket integrationAPI(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.activeai.integration"))
        .build()
        .apiInfo(metaInfo());
  }

  public ApiInfo metaInfo() {
    return new ApiInfo("API Documentation Regards Integration", "Here you can test your integrated api", "1.1.0", "https://active.ai/",
        new Contact("Active.ai", "https://active.ai/", "active@active.ai"), "Apache License Version 2.0",
        "https://www.apache.org/license.html");
  }
}
