package com.activeai.integration.data.util;

import org.springframework.stereotype.Component;

@Component("redisUtil")
public class CoreBankingUtil {

  public static String getRedisKey(String customerId) {
    return "stub:" + customerId;
  }

}
