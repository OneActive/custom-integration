package com.activeai.integration.data.util;

import org.springframework.stereotype.Component;

@Component("redisUtil")
public class CoreBankingUtil {

  private static final String REDIS_PRFIX = "rb:stub:";

  private CoreBankingUtil(){
    //Utility classes should not have public constructors
  }

  public static String getRedisKey(String customerId) {
    return REDIS_PRFIX + customerId;
  }

}
