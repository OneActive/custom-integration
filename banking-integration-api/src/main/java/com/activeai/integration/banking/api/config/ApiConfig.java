package com.activeai.integration.banking.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:banking-integration-api.properties",
    "file:${banking.integration.resources}/banking-integration-api.properties"}, ignoreResourceNotFound = true)
public class ApiConfig {

}
