package com.activeai.integration.data.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisUtil")
public class CoreBankingUtil {

  private static final String REDIS_PREFIX = "rb:stub:";

  /**
   * Redis Caching Mechanism Enabled or Not
   */
  private static boolean cacheEnabled;


  private CoreBankingUtil(){
    //Utility classes should not have public constructors
  }

  @Value("${IS_REDIS_ENABLED:true}")
  private void setIsCache(boolean isCoreBankingCacheEnabled) {
    cacheEnabled = isCoreBankingCacheEnabled;
  }

  public static boolean isCacheEnabled() {
    return cacheEnabled;
  }

  public static String getRedisKey(String customerId) {
    return REDIS_PREFIX + customerId;
  }

}
