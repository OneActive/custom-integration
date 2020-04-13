package com.activeai.integration.data.util;

import com.activeai.integration.data.constants.CoreBankingConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisUtil")
public class CoreBankingUtil {

  private static final String REDIS_PRFIX = "rb:stub:";

  /**
   * Redis Caching Mechanism Enabled or Not
   */
  private static String isCache;


  private CoreBankingUtil(){
    //Utility classes should not have public constructors
  }

  @Value("${isCoreBankingCacheEnabled:YES}")
  public void setIsCache(String isCoreBankingCacheEnabled) {
    isCache = isCoreBankingCacheEnabled;
  }

  public static String getRedisKey(String customerId) {
    return REDIS_PRFIX + customerId;
  }

  /**
   * Verify Redis mechanism for Core Banking need to be enable or not
   *
   * @return
   */
  public static boolean isCacheEnabled() {
    if (CoreBankingConstants.YES.equalsIgnoreCase(StringUtils.trim(isCache))) {
      return true;
    }
    return false;
  }

}
