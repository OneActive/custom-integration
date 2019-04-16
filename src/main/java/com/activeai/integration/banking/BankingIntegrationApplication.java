package com.activeai.integration.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath:applicationContext.xml")
@ComponentScan("com.activeai.integration.banking")
public class BankingIntegrationApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BankingIntegrationApplication.class, args);
  }
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(BankingIntegrationApplication.class);
  }
  
}
