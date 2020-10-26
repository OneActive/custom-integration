package com.activeai.integration.data.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Configuration for Redis mechanism on Core Banking data handling
 *
 */
@Configuration
public class SpringRedisConfig {

  @Value("${REDIS_PORT:6379}") private int redisPort;

  @Value("${REDIS_DATABASE:0}") private int dataBase;

  @Value("${REDIS_HOST:localhost}") private String redisHost;

  @Bean public JedisConnectionFactory connectionFactory() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName(redisHost);
    connectionFactory.setPort(redisPort);
    connectionFactory.setDatabase(dataBase);
    return connectionFactory;
  }
}
