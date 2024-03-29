package com.activeai.integration.banking.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.activeai.integration")
public class BankingIntegrationApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BankingIntegrationApplication.class, args);
  }
  
}
