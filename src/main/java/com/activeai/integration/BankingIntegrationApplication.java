package com.activeai.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class BankingIntegrationApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BankingIntegrationApplication.class, args);
  }
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(BankingIntegrationApplication.class);
  }
  
}
