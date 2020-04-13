package com.activeai.integration.data.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisUtil")
public class CoreBankingUtil {

  private static final String REDIS_PREFIX = "rb:stub:";

  /**
   * Redis Caching Mechanism Enabled or Not
   */
  public static boolean isCacheEnabled;


  private CoreBankingUtil(){
    //Utility classes should not have public constructors
  }

  @Value("${IS_REDIS_ENABLED:true}")
  public void setIsCache(boolean isCoreBankingCacheEnabled) {
    isCacheEnabled = isCoreBankingCacheEnabled;
  }

  public static String getRedisKey(String customerId) {
    return REDIS_PREFIX + customerId;
  }

}
