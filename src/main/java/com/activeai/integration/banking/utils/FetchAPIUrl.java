package com.activeai.integration.banking.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("fetchAPIUrl")
public class FetchAPIUrl {

  public static final String APITriplePropertyPattern = "{0}_{1}_{3}";
  public static final String APIDoublePropertyPattern = "{0}_{1}";
  @Autowired
  private Environment env;

  /**
   * Remove the return statement and form your URL accordingly with Parameter to Hit in Services
   * @param propertyValue
   * @param customerId
   * @return
   */
  public String getAPIUrl(String propertyValue, String customerId) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  /**
   * Remove the return statement and form your URL accordingly with Parameter to Hit in Services
   * @param propertyValue
   * @param customerId
   * @param accountId
   * @return
   */
  public String getAPIUrl(String propertyValue, String customerId, String accountId) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APITriplePropertyPattern, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(APITriplePropertyPattern, customerId, propertyValue)) :
        StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, accountId, propertyValue))) ?
            env.getProperty(MessageFormat.format(APIDoublePropertyPattern, accountId, propertyValue)) :
            env.getProperty(propertyValue);
  }
}